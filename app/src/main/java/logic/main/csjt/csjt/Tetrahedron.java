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

    //TODO: Change names to Tetra (Unnecessary)
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

    private static final int COORDS_PER_VERTEX = 3;
    private int cubePositionParam;
    private int cubeModelViewProjectionParam;
    private float[] modelViewProjection;
    private int cubeNormalParam;
    private int cubeColorParam;
    private int cubeModelParam;
    private int cubeModelViewParam;
    private int cubeLightPosParam;

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

        /**
         * px, py and pz describe the origin of the Tetrahedron, in our case its exact middle.
         * width, height and depth describe the dimensions of the surrounding cube. (The Tetrahedron fits perfectly inside this cube)
         * Construction of the Tetrahedron vertices according to this method: https://de.wikipedia.org/wiki/Tetraeder#Umgebender_W.C3.BCrfel
         */
        //TODO: Change all this to the Tetrahedron layout   EDIT: Done

        //helper variables
        float wid = width / 2;
        float hei = height / 2;
        float dep = depth / 2;

        float[] TETRA_COORDS = new float[]{
                //Face A
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py-hei, pz+dep,     //right bottom front point
                px-wid, py+hei, pz+dep,     //left top front point

                //Face B
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py-hei, pz+dep,     //right bottom front point
                px+wid, py+hei, pz-dep,     //right top back point

                //Face C
                px+wid, py-hei, pz+dep,     //right bottom front point
                px+wid, py+hei, pz-dep,     //right top back point
                px-wid, py+hei, pz+dep,     //left top front point

                //Face D
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py+hei, pz-dep,     //right top back point
                px-wid, py+hei, pz+dep,     //left top front point

                //TODO: Alternative mode: try at own risk
                /*
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py-hei, pz+dep,     //right bottom front point
                px+wid, py+hei, pz-dep,     //right top back point
                px-wid, py+hei, pz+dep,     //left top front point
                 */

        };

        return TETRA_COORDS;
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

        //Normals are constructed similar to the coordinates
        float[] TETRA_NORMALS = new float[]{
                //FACE A
                -width, -height, depth,
                -width, -height, depth,
                -width, -height, depth,

                //FACE B
                width, -height, -depth,
                width, -height, -depth,
                width, -height, -depth,

                //FACE C
                width, height, depth,
                width, height, depth,
                width, height, depth,

                //FACE D
                -width, height, -depth,
                -width, height, -depth,
                -width, height, -depth

        };

        return TETRA_NORMALS;
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
        Matrix.multiplyMM(modelView, 0, view, 0, modelGeom, 0);
        Matrix.multiplyMM(modelViewProjection, 0, perspective, 0, modelView, 0);
        GLES20.glUseProgram(cubeProgram);
        GLES20.glUniform3fv(this.cubeLightPosParam, 1, lightPosInEyeSpace, 0);

        // Set the Model in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.cubeModelParam, 1, false, this.modelGeom, 0);

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
        GLES20.glDrawArrays(rMode ? GLES20.GL_TRIANGLES : GLES20.GL_LINES, 0, vCount);  // There is also GL_LINES for rendering lines. We used GL_TRIANGLES , maybe also good for debugging :D looks impressiv
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

        pages = 4;
        verticesPerPage = 3;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelGeom = new float[16];
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

        Matrix.setIdentityM(this.modelGeom, 0);
        // We add to the Y-axis a rnd float so cubes start moving....
        Matrix.translateM(this.modelGeom, 0, this.modelPosition[0], this.modelPosition[1], this.modelPosition[2]);

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
    
}