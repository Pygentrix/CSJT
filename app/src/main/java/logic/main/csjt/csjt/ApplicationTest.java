package logic.main.csjt.csjt;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import com.google.vrtoolkit.cardboard.*;

import javax.microedition.khronos.egl.EGLConfig;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class ApplicationTest extends CardboardActivity implements CardboardView.StereoRenderer  {

//STRINGS
    private static final String TAG = "CSJT";

//FLOATS
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 100.0f;

    static final float CAMERA_X = 0.0f;
    static final float CAMERA_Y = 0.0f;
    static final float CAMERA_Z = 0.01f;

//FLOAT ARRAYS
    // We keep the light always position just above the user. CHANGE a to see whether it changes sth
    public static float[] LIGHT_POS_IN_WORLD_SPACE = new float[] {1.0f, 2.0f, 0.0f, 1.0f};
    private final float[] lightPosInEyeSpace = new float[4];

    private float[] camera;
    private float[] view;
    private float[] headView;
    private float[] headRotation;

//INTEGERS
    //int testLightning = 1;
    int m = 10;// dont do m=100 , rendering 10000 cubes atm is too much

//OBJECTS

    ArrayList<Geom> allGeoms = new ArrayList<>();

    Grid grid;

    private Vibrator vibrator;
    Random random = new Random();

    private long timeOfLastTap;


    public void initGeoms(int vertexShader,int passthroughShader){


        allGeoms.add(new Cube(2.0f,-1.0f,0.0f,1.2f,0.2f,5.0f, 1.0f, 1.0f, 1.0f, 0.9f)); //right (0)
        allGeoms.add(new Cube(-2.0f,-1.0f,0.0f,1.2f,0.2f,5.0f, 1.0f, 1.0f, 1.0f, 0.9f));//left
        allGeoms.add(new Cube(0.0f,-1.0f,-2.5f,7.4f,0.2f,1.2f, 1.0f, 1.0f, 1.0f, 0.9f));//front

        allGeoms.add(new Tetrahedron(-2.0f,-0.70f,-1.3f,1.0f,1.0f,1.0f, 1.0f, 0.6f, 0.3f, 1.0f)); // old testtetra
        allGeoms.add(new Prism(0.5f,-0.8f,-2.1f,1.0f,1.0f,0.0f, 1.0f, 0.1f, 1.0f));  //old testprism1 (4)
        //allGeoms.add(new Cube(-10.0f,-10.0f,10.0f,40.0f,40.0f,40.0f, 0.0f, 0.2f, 1.0f, 1.0f));cub1
        allGeoms.add(new Cube(-1.1f,-0.55f,-2.1f,0.5f,0.5f,0.5f, 1.0f, 0.2f, 0.1f, 1.0f)); //old cube2 (5)

        allGeoms.add(new Column(-0.4f,-0.8f,-2.3f,1.1f,1.1f,1.1f, 0.0f, 0.4f, 1.0f, 1.0f));  // old column1
        allGeoms.add(new Pyramid(1.5f, -0.8f, -1.8f, 1.0f, 1.0f, 1.0f, 1.0f, 0.6f, 0.1f, 1.0f)); //old pyram
        allGeoms.add(new Cube(LIGHT_POS_IN_WORLD_SPACE[0],LIGHT_POS_IN_WORLD_SPACE[1],LIGHT_POS_IN_WORLD_SPACE[2],0.7f,0.7f,0.7f, 1.0f, 1.0f, 1.0f, 1.0f )); //old cube2

        allGeoms.add(new Cube(0.0f,1.0f,-2.5f,1.0f,1.0f,1.0f, 0.9f, 0.7f, 0.1f, 1.0f)); //old cube2 (9)
        allGeoms.get(9).movStatus = 1;

        for(int i =0;i< m; i++){
            for(int j=0; j < m; j++){

                allGeoms.add(new Cube(30.0f-(5*i),-2.0f,-30.0f+(5*j),1.0f,1.0f,1.0f, 1.0f, 0.6523f, 0.0f, 1.0f));
            }
        }
        for(int i = 0; i < allGeoms.size();i++){

            allGeoms.get(i).initProgram(vertexShader,passthroughShader);
        }

        //Moved init update pos to initProgramm!
    }

    public void initFloor(int gridShader,int vertexShader){

        grid = new Grid(2.0f);
        grid.initProgramm(gridShader, vertexShader);

    }
    public static int sp_Text;

     // This shader is for rendering 2D text textures straight from a texture

    public static final String vs_Text =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec4 a_Color;" +
                    "attribute vec2 a_texCoord;" +
                    "varying vec4 v_Color;" +
                    "varying vec2 v_texCoord;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "  v_texCoord = a_texCoord;" +
                    "  v_Color = a_Color;" +
                    "}";
    public static final String fs_Text =
            "precision mediump float;" +
                    "varying vec4 v_Color;" +
                    "varying vec2 v_texCoord;" +
                    "uniform sampler2D s_texture;" +
                    "void main() {" +
                    "  gl_FragColor = texture2D( s_texture, v_texCoord ) * v_Color;" +
                    "  gl_FragColor.rgb *= v_Color.a;" +
                    "}";

    /**
     * Converts a raw text file, saved as a resource, into an OpenGL ES shader.
     *
     * @param type The type of shader we will be creating.
     * @param resId The resource ID of the raw text file about to be turned into a shader.
     * @return The shader object handler.
     */
    private int loadGLShader(int type, int resId) {
        String code = readRawTextFile(resId);
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, code);
        GLES20.glCompileShader(shader);

        // Get the compilation status.
        final int[] compileStatus = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

        // If the compilation failed, delete the shader.
        if (compileStatus[0] == 0) {
            Log.e(TAG, "Error compiling shader: " + GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
        }

        if (shader == 0) {
            throw new RuntimeException("Error creating shader.");
        }

        return shader;
    }

    /**
     * Checks if we've had an error inside of OpenGL ES, and if so what that error is.
     *
     * @param label Label to report in case of error.
     */
    private static void checkGLError(String label) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, label + ": glError " + error);
            throw new RuntimeException(label + ": glError " + error);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_ui);

        CardboardView cardboardView = (CardboardView) findViewById(R.id.cardboard_view);
        cardboardView.setRenderer(this);
        cardboardView.setTransitionViewEnabled(true);
        cardboardView.setOnCardboardBackButtonListener(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        });
        setCardboardView(cardboardView);
        camera = new float[16];
        view = new float[16];
        headRotation = new float[4];
        headView = new float[16];
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onRendererShutdown() {
        Log.i(TAG, "onRendererShutdown");
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        Log.i(TAG, "onSurfaceChanged");
    }

    /**
     * Creates the buffers we use to store information about the 3D world.
     *
     * <p>OpenGL doesn't use Java arrays, but rather needs data in a format it can understand.
     * Hence we use ByteBuffers.
     *
     * @param config The EGL configuration used when creating the surface.
     */
    @Override
    public void onSurfaceCreated(EGLConfig config) {
        Log.i(TAG, "onSurfaceCreated");
        GLES20.glClearColor(0.1f, 0.1f, 0.1f, 0.5f); // Dark background so text shows up well.

        ////////////
        // Some stages earlier the Buffers where generated here, now everything is done inside the Obj Constructor (f.e.: Cube)
        ////////////

        int vertexShader = loadGLShader(GLES20.GL_VERTEX_SHADER, R.raw.light_vertex);
        int gridShader = loadGLShader(GLES20.GL_FRAGMENT_SHADER, R.raw.grid_fragment); //NOT NEEDED YET
        int passthroughShader = loadGLShader(GLES20.GL_FRAGMENT_SHADER, R.raw.passthrough_fragment);

        initGeoms(vertexShader,passthroughShader);

        initFloor(gridShader,vertexShader);

        checkGLError("onSurfaceCreated");
    }

    /**
     * Converts a raw text file into a string.
     *
     * @param resId The resource ID of the raw text file about to be turned into a shader.
     * @return The context of the text file, or null in case of error.
     */
    private String readRawTextFile(int resId) {
        InputStream inputStream = getResources().openRawResource(resId);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Prepares OpenGL ES before we draw a frame.
     *
     * @param headTransform The head transformation in the new frame.
     */
    @Override
    public void onNewFrame(HeadTransform headTransform) {
        // Build the Model part of the ModelView matrix.
        //Matrix.rotateM(cube1.getModelCube(), 0, 0.3f, 0.5f, 0.5f, 1.0f);  // <- Lets rotate the cube ROTATION

        // Build the camera matrix and apply it to the ModelView.
        //Changed EyeY to 1.0f instead of 0.0f
        Matrix.setLookAtM(camera, 0, CAMERA_X, CAMERA_Y, CAMERA_Z, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);

        headTransform.getHeadView(headView, 0);

        // Update anything with the most recent head rotation.
        headTransform.getQuaternion(headRotation, 0);

        checkGLError("onReadyToDraw");
    }

    /**
     * Draws a frame for an eye.
     *
     * @param eye The eye to render. Includes all required transformations.
     */
    @Override
    public void onDrawEye(Eye eye) {
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        checkGLError("colorParam");

        // Apply the eye transformation to the camera.
        Matrix.multiplyMM(view, 0, eye.getEyeView(), 0, camera, 0);

        // Set the position of the light
        Matrix.multiplyMV(lightPosInEyeSpace, 0, view, 0, LIGHT_POS_IN_WORLD_SPACE, 0);

        // Build the ModelView and ModelViewProjection matrices
        // for calculating cube position and light.
        float[] perspective = eye.getPerspective(Z_NEAR, Z_FAR);

        drawGeoms(perspective);
        grid.drawFloor(lightPosInEyeSpace,view,perspective);


    }

    @Override
    public void onFinishFrame(Viewport viewport) {}

    /**
     * Draw the cube.
     *
     * <p>We've set all of our transformation matrices. Now we simply pass them into the shader.
     * @param perspective
     */
    public void drawGeoms(float[] perspective) {

/*        if(cube1.isLookingAtObject(headView)){
            cube1.movY = 0.25f;
            vibrator.vibrate(50);
            Log.e(TAG, "TRIGGERED event in DrawCube");
        }*/
        //table[0].draw(lightPosInEyeSpace,view,perspective);
        for(int i = 0;i < allGeoms.size();i++){

            allGeoms.get(i).isLookingAtObject(headView);
            allGeoms.get(i).draw(lightPosInEyeSpace,view,perspective);
        }


    }

    /**
     * Called when the Cardboard trigger is pulled. (Means if display is being touched)
     */
    @Override
    public void onCardboardTrigger() {
        Log.i(TAG, "onCardboardTrigger");

        if(System.currentTimeMillis() - timeOfLastTap < 400){
            Geom.rMode = !Geom.rMode;
        }

        for(int i = 0; i < allGeoms.size(); i++){

            if(allGeoms.get(i).islookingAtIt){
                if(!(i <= 2)){
                    //All geoms start rotating if selected and display is touched except the "table" cubes
                    //allGeoms.get(i).movStatus = 1;
                }
            }
            else{
                allGeoms.get(i).movStatus = 0;
            }
        }
        /**
         * Thundergrass, 03.06.2016: Trying to apply the movement ability to the pyramid and the column to see them from more sides
         * Pygentrix Not needed anymore because you done good job!
         */

        //We try to change the light pos while being in the app
        //testLightning = testLightning + 1;
        //LIGHT_POS_IN_WORLD_SPACE[0] = 0.01f;
        //LIGHT_POS_IN_WORLD_SPACE[1] = 2.0f;
        //LIGHT_POS_IN_WORLD_SPACE[2] = 0.01f;
        //selectRndCubeAndStartMoving();

        // Always give user feedback.
        timeOfLastTap = System.currentTimeMillis();
        vibrator.vibrate(50);
    }

    private void selectRndCubeAndStartMoving() {

        int rndCubeSelector = random.nextInt((allGeoms.size()-1));
        float rndMovY = (random.nextFloat() / 2);
        allGeoms.get(rndCubeSelector).movY = rndMovY;
        Log.e(TAG, "RndCubeSelector" + rndCubeSelector + "rndfloat:" + rndMovY);

    }

}