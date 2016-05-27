package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
/**
 * Created by JB on 27.05.2016.
 */
public class Cuboid extends Geom{

    //FLOATS
    private static final float MAX_MODEL_DISTANCE = 7.0f; // Needs to be deleted in future
    private static final float TIME_DELTA = 0.3f; // Used for object rotation
    public float movY = 0.0f; // Movement factor in y-direction

    //FLOAT ARRAYS
    private float[] cubeNormals;
    private float[] cubeVertics;
    private float[] modelViewProjection;

    //FLOAT BUFFERS
    private FloatBuffer fbCubeNormals;
    private FloatBuffer fbCubeVertics;

    //INTEGERS
    public static int cubeProgram;
    private static final int COORDS_PER_VERTEX = 3;
    private int cubePositionParam;
    private int cubeModelViewProjectionParam;
    private int cubeNormalParam;
    private int cubeColorParam;
    private int cubeModelParam;
    private int cubeModelViewParam;
    private int cubeLightPosParam;

    //BOOLEANS
    public boolean dir = true;
    private boolean initCase = true;

    //GETTERS
    public FloatBuffer getFbCubeNormals() {
        return this.fbCubeNormals;
    }

    public FloatBuffer getFbCubeVertics() {
        return this.fbCubeVertics;
    }

    public float[] getModelCube() {
        return modelGeom;
    }

    //SETTERS
    public float[] setCubeCoords(float px,float py,float pz ,float width, float height, float depth){



        float[] CUBOID_COORDS = new float[]{
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

        return CUBOID_COORDS;
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

    public void setModelCube(float[] modelCube) {
        this.modelGeom = modelCube;
    }

    //FUNCTIONS / METHODS
    public void draw(float[] lightPosInEyeSpace, float[] view, float[] perspective){
        this.callUpdatePos();

        // You can rotate the cubes by uncommenting Matrix.rotateM, but wont work together with callUpdatePos
        float[] lModelCube = this.getModelCube();
        //Matrix.rotateM(lModelCube, 0,TIME_DELTA, this.modelPosition[0], this.modelPosition[1], this.modelPosition[2]);
        this.setModelCube(lModelCube);

        Matrix.multiplyMM(this.modelView, 0, view, 0, modelGeom, 0);
        Matrix.multiplyMM(modelViewProjection, 0, perspective, 0, modelView, 0);
        GLES20.glUseProgram(cubeProgram);
        GLES20.glUniform3fv(this.cubeLightPosParam, 1, lightPosInEyeSpace, 0);

        // Set the Model in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.cubeModelParam, 1, false, this.modelGeom, 0);

        // Set the ModelView in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.cubeModelViewParam, 1, false, this.modelView, 0);
        GLES20.glVertexAttribPointer(
                this.cubePositionParam,COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, this.getFbCubeVertics());

        // Set the ModelViewProjection matrix in the shader.
        GLES20.glUniformMatrix4fv(this.cubeModelViewProjectionParam, 1, false, this.modelViewProjection, 0);

        // Set the normal positions of the cube, again for shading
        GLES20.glVertexAttribPointer(this.cubeNormalParam, 3, GLES20.GL_FLOAT, false, 0, this.getFbCubeNormals());  //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered

        //Has to do sth with the color of the cube while pointing at it
        GLES20.glVertexAttribPointer(cubeColorParam, 4, GLES20.GL_FLOAT, false, 0,this.islookingAtIt ? this.getFbSelectedGeomColors() : this.getFbGeomColors());
        //GLES20.glVertexAttribPointer(this.cubeColorParam, 4, GLES20.GL_FLOAT, false, 0,this.getFbGeomColors()); //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);  // There is also GL_LINES for rendering lines. We used GL_TRIANGLES , maybe also good for debugging :D looks impressiv
        checkGLError("Drawing cuboid");
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

    //CONSTRUCTOR(S)
    public Cuboid(Float x, Float y, Float z) {

        float r, g, b, a = 1.0f;

    }
    public Cuboid(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        pages = 6;
        verticesPerPage = 6;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelGeom = new float[16];
        modelViewProjection = new float[16];
        modelPosition = new float[] {x, y, z};
        // TODO: Build constructors so we dont need to set static coords for every single cube. DONE so far
        px = x;
        py = y;
        pz = z;
        this.width = width;
        this.height = height;
        this.depth = depth;

        setGeomColors(setInitColor(r, g, b, a)); // Init Cube Colors on first call
        cubeVertics = setCubeCoords(x, y, z, width, height, depth);// Init Cube Coords on first call
        cubeNormals = setCubeNormals(width,height,depth);

        setFbGeomColors(colorFloatBuffer());
        fbCubeNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        fbCubeVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices

        this.callUpdatePos();



    }

}
