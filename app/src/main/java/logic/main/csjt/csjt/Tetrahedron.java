package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Paul Stonjek on 11.05.2016.
 * Uploaded to the Git by Pygentrix ( Due to setting problems )
 */
public class Tetrahedron extends Geom {

    //TODO: Change names to Tetra
    public float[] modelPosition;
    private static final float MAX_MODEL_DISTANCE = 7.0f;
    private float[] cubeColors;
    private FloatBuffer fbCubeColors;

    private float[] cubeNormals;
    private FloatBuffer fbCubeNormals;

    private float[] cubeVertics;
    private FloatBuffer fbCubeVertics;

    private float width;
    private float height;
    private float depth;

    public static int cubeProgram;
    private static final float YAW_LIMIT = 0.12f;
    private static final float PITCH_LIMIT = 0.12f;

    private static final int COORDS_PER_VERTEX = 3;
    private int cubePositionParam;
    private int cubeModelViewProjectionParam;
    private float[] modelViewProjection;
    private int cubeNormalParam;
    private int cubeColorParam;
    private int cubeModelParam;
    private int cubeModelViewParam;
    private int cubeLightPosParam;
    private float[] modelCube;
    private float[] modelView = new float[16];

    public float movY = 0.0f;
    public boolean dir = true;
    private boolean initCase = true;

    public FloatBuffer getFbCubeNormals() {
        return this.fbCubeNormals;
    }

    public FloatBuffer getFbCubeVertics() {
        return this.fbCubeVertics;
    }

    public FloatBuffer getFbCubeColors() {
        return this.fbCubeColors;
    }

    public FloatBuffer colorFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbColors = ByteBuffer.allocateDirect(this.cubeColors.length * 4);
        bbColors.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbColors.asFloatBuffer();
        lFloatBuffer.put(this.cubeColors);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public float[] setCubeCoords(float px,float py,float pz ,float width, float height, float depth){

        //TODO: Implement it correct, some rendering fuck ups. Still not correct
        //TODO: Change all this to the Tetrahedron layout

        float[] CUBE_COORDS = new float[]{
                // Front face
                px-width, py+height, pz+depth,
                px-width, py-height, pz+depth,  // unten rechts
                px+width, py+height, pz+depth,  // rechts oben
                px-width, py-height, pz+depth, // unten rechts
                px+width, py-height, pz+depth,
                px+width, py+height, pz+depth,  // rechts oben

                // Right face
                px+width, py+height, pz+depth,
                px+width, py-height, pz+depth,
                px+width, py+height, pz-depth,
                px+width, py-height, pz+depth,
                px+width, py-height, pz-depth,
                px+width, py+height, pz-depth,

                // Back face
                px+width, py+height, pz-depth,
                px+width, py-height, pz-depth,
                px-width, py+height, pz-depth,
                px+width, py-height, pz-depth,
                px-width, py-height, pz-depth,
                px-width, py+height, pz-depth,

                // Left face
                px-width, py+height, pz-depth,
                px-width, py-height, pz-depth,
                px-width, py+height, pz+depth,
                px-width, py-height, pz-depth,
                px-width, py-height, pz+depth,
                px-width, py+height, pz+depth,

                // Top face
                px-width, py+height, pz-depth,
                px-width, py+height, pz+depth,
                px+width, py+height, pz-depth,
                px-width, py+height, pz+depth,
                px+width, py+height, pz+depth,
                px+width, py+height, pz-depth,

                // Bottom face
                px+width, py-height, pz-depth,
                px+width, py-height, pz+depth,
                px-width, py-height, pz-depth,
                px+width, py-height, pz+depth,
                px-width, py-height, pz+depth,
                px-width, py-height, pz-depth
        };

        return CUBE_COORDS;
    }

    public FloatBuffer verticsFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbVertics = ByteBuffer.allocateDirect(this.cubeVertics.length * 4);
        bbVertics.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbVertics.asFloatBuffer();
        lFloatBuffer.put(this.cubeVertics);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public float[] setCubeNormals(float width, float height, float depth){

        float[] CUBE_NORMALS = new float[]{
                0.0f, 0.0f, depth,
                0.0f, 0.0f, depth,
                0.0f, 0.0f, depth,
                0.0f, 0.0f, depth,
                0.0f, 0.0f, depth,
                0.0f, 0.0f, depth,

                // Right face
                width, 0.0f, 0.0f,
                width, 0.0f, 0.0f,
                width, 0.0f, 0.0f,
                width, 0.0f, 0.0f,
                width, 0.0f, 0.0f,
                width, 0.0f, 0.0f,

                // Back face
                0.0f, 0.0f, -depth,
                0.0f, 0.0f, -depth,
                0.0f, 0.0f, -depth,
                0.0f, 0.0f, -depth,
                0.0f, 0.0f, -depth,
                0.0f, 0.0f, -depth,

                // Left face
                -width, 0.0f, 0.0f,
                -width, 0.0f, 0.0f,
                -width, 0.0f, 0.0f,
                -width, 0.0f, 0.0f,
                -width, 0.0f, 0.0f,
                -width, 0.0f, 0.0f,

                // Top face
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,

                // Bottom face
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f
        };

        return CUBE_NORMALS;
    }
    public FloatBuffer normalsFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbNormals = ByteBuffer.allocateDirect(this.cubeNormals.length * 4);
        bbNormals.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbNormals.asFloatBuffer();
        lFloatBuffer.put(this.cubeNormals);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public Tetrahedron(Float x, Float y, Float z) {

        float r, g, b, a = 1.0f;
        // FINSIH!
        modelPosition = new float[] {1.0f, 1.0f, -MAX_MODEL_DISTANCE / 2.0f};

    }

    public void draw(float[] lightPosInEyeSpace, float[] view, float[] perspective){
        // TODO: Init Params !
        Matrix.multiplyMM(modelView, 0, view, 0, modelCube, 0);
        Matrix.multiplyMM(modelViewProjection, 0, perspective, 0, modelView, 0);
        GLES20.glUseProgram(cubeProgram);
        GLES20.glUniform3fv(this.cubeLightPosParam, 1, lightPosInEyeSpace, 0);

        // Set the Model in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.cubeModelParam, 1, false, this.modelCube, 0);

        // Set the ModelView in the shader, used to calculate lighting
        //hier gehts mit ner nullpointer kaputt, jetzt nicht mehr :D aber geht trotzdem net
        GLES20.glUniformMatrix4fv(this.cubeModelViewParam, 1, false, this.modelView, 0);
        GLES20.glVertexAttribPointer(
                this.cubePositionParam,COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, this.getFbCubeVertics());

        // Set the ModelViewProjection matrix in the shader.
        GLES20.glUniformMatrix4fv(this.cubeModelViewProjectionParam, 1, false, this.modelViewProjection, 0);

        // Set the normal positions of the cube, again for shading
        GLES20.glVertexAttribPointer(this.cubeNormalParam, 3, GLES20.GL_FLOAT, false, 0, this.getFbCubeNormals());  //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered

        //Has to do sth with the color of the cube while pointing at it
        // GLES20.glVertexAttribPointer(cubeColorParam, 4, GLES20.GL_FLOAT, false, 0,
        //        isLookingAtObject() ? cube1. : cube1.cubeColors);
        GLES20.glVertexAttribPointer(this.cubeColorParam, 4, GLES20.GL_FLOAT, false, 0,this.getFbCubeColors()); //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered
        // TODO: How is the GL Triangles Mode working ?
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 36);  // There is also GL_LINES for rendering lines. We used GL_TRIANGLES , maybe also good for debugging :D looks impressiv
        checkGLError("Drawing cube");
    }

    public void initProgram(int vertexShader, int passthroughShader){


        this.cubeProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(cubeProgram, vertexShader);
        GLES20.glAttachShader(cubeProgram, passthroughShader);
        GLES20.glLinkProgram(cubeProgram);
        GLES20.glUseProgram(cubeProgram);

        checkGLError("Cube program");

        this.cubePositionParam = GLES20.glGetAttribLocation(cubeProgram, "a_Position");
        this.cubeNormalParam = GLES20.glGetAttribLocation(cubeProgram, "a_Normal");
        this.cubeColorParam = GLES20.glGetAttribLocation(cubeProgram, "a_Color");

        this.cubeModelParam = GLES20.glGetUniformLocation(cubeProgram, "u_Model");
        this.cubeModelViewParam = GLES20.glGetUniformLocation(cubeProgram, "u_MVMatrix");
        this.cubeModelViewProjectionParam = GLES20.glGetUniformLocation(cubeProgram, "u_MVP");
        this.cubeLightPosParam = GLES20.glGetUniformLocation(cubeProgram, "u_LightPos");

        GLES20.glEnableVertexAttribArray(this.cubePositionParam);// Enables the VertexArrays which is needed so opengl knows wht to render
        GLES20.glEnableVertexAttribArray(this.cubeNormalParam);// Enables the VertexArrays which is needed so opengl knows wht to render
        GLES20.glEnableVertexAttribArray(this.cubeColorParam);  // Enables the VertexArrays which is needed so opengl knows wht to render

        checkGLError("Cube program params");
    }

    public Tetrahedron(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        modelCube = new float[16];
        modelViewProjection = new float[16];
        modelPosition = new float[] {1.0f, 1.0f, -MAX_MODEL_DISTANCE / 2.0f};
        // TODO: Build constructors so we dont need to set static coords for every single cube. DONE so far
        px = x;
        py = y;
        pz = z;
        this.width = width;
        this.height = height;
        this.depth = depth;
        cubeColors = setInitColor(r, g, b, a); // Init Cube Colors on first call
        cubeVertics = setCubeCoords(x, y, z, width, height, depth);// Init Cube Coords on first call
        cubeNormals = setCubeNormals(width,height,depth);

        fbCubeColors = colorFloatBuffer();
        fbCubeNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        fbCubeVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices



    }

    public void updateModelPosition() {

        Matrix.setIdentityM(this.modelCube, 0);
        // We add to the Y-axis a rnd float so cubes start moving....
        Matrix.translateM(this.modelCube, 0, this.modelPosition[0], this.modelPosition[1], this.modelPosition[2]);

        checkGLError("updateCubePosition");
    }
    public void updateLightPosition() {

        this.modelPosition[0] = ApplicationTest.LIGHT_POS_IN_WORLD_SPACE[0];
        this.modelPosition[1] = ApplicationTest.LIGHT_POS_IN_WORLD_SPACE[1];
        this.modelPosition[2] = ApplicationTest.LIGHT_POS_IN_WORLD_SPACE[2];
        this.callUpdatePos();
    }


    public void callUpdatePos() {
        if(this.initCase){
            this.initCase = false;
            this.modelPosition[1] = this.modelPosition[1] + this.movY;
        }
        else if(this.modelPosition[1] >= 50.0f){
            this.dir = false;
        }
        else if(this.modelPosition[1] <= -20.0f){
            this.dir = true;
        }
        if(this.dir) {this.modelPosition[1] = this.modelPosition[1] + this.movY;}
        else if(!this.dir) {this.modelPosition[1] = this.modelPosition[1] - this.movY;}


        this.updateModelPosition();
        checkGLError("updatePositions");
    }

    public boolean isLookingAtObject(float[] headView) {
        float[] initVec = {0, 0, 0, 1.0f};
        float[] objPositionVec = new float[4];

        // Convert object space to camera space. Use the headView from onNewFrame.
        Matrix.multiplyMM(this.modelView, 0, headView, 0, this.modelCube, 0);
        Matrix.multiplyMV(objPositionVec, 0, this.modelView, 0, initVec, 0);

        float pitch = (float) Math.atan2(objPositionVec[1], -objPositionVec[2]);
        float yaw = (float) Math.atan2(objPositionVec[0], -objPositionVec[2]);

        return Math.abs(pitch) < PITCH_LIMIT && Math.abs(yaw) < YAW_LIMIT;
    }
}