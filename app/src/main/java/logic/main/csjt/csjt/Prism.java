package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pygentrix on 20.05.2016.
 */

public class Prism extends Geom{

    //FLOATS
    private static final float TIME_DELTA = 0.3f; // Used for object rotation
    public float movY = 0.0f; // Movement factor in y-direction

    //FLOAT ARRAYS
    private float[] prismNormals;
    private float[] prismVertics;
    private float[] modelViewProjection;

    //FLOAT BUFFERS
    private FloatBuffer fbPrismNormals;
    private FloatBuffer fbPrismVertics;

    //INTEGERS
    public static int prismProgram;
    private static final int COORDS_PER_VERTEX = 3;
    private int prismPositionParam;
    private int prismModelViewProjectionParam;
    private int prismNormalParam;
    private int prismColorParam;
    private int prismModelParam;
    private int prismModelViewParam;
    private int prismLightPosParam;

    //BOOLEANS
    public boolean dir = true;
    private boolean initCase = true;

    //GETTERS
    public FloatBuffer getFbPrismNormals() {
        return this.fbPrismNormals;
    }

    public FloatBuffer getFbPrismVertics() {
        return this.fbPrismVertics;
    }

    public float[] getModelPrism() {
        return modelGeom;
    }

    //SETTERS
    public float[] setPrismCoords(float px,float py,float pz ,float width, float height, float depth){

        //TODO: Implement it correct, some rendering fuck ups. Still not correct

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
        ByteBuffer bbVertics = ByteBuffer.allocateDirect(this.prismVertics.length * 4);
        bbVertics.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbVertics.asFloatBuffer();
        lFloatBuffer.put(this.prismVertics);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public float[] setPrismNormals(float width, float height, float depth){

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
        ByteBuffer bbNormals = ByteBuffer.allocateDirect(this.prismNormals.length * 4);
        bbNormals.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbNormals.asFloatBuffer();
        lFloatBuffer.put(this.prismNormals);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public void setModelPrism(float[] modelPrism) {
        this.modelGeom = modelPrism;
    }

    //FUNCTIONS / METHODS
    public void draw(float[] lightPosInEyeSpace, float[] view, float[] perspective){
        // TODO: Get Updating and rotation work toghether, look at hideObject func to solve proble
        this.callUpdatePos();

        // You can rotate the prisms by uncommenting Matrix.rotateM, but wont work together with callUpdatePos
        float[] lModelPrism = this.getModelPrism();
        //Matrix.rotateM(lModelPrism, 0,TIME_DELTA, this.modelPosition[0], this.modelPosition[1], this.modelPosition[2]);
        this.setModelPrism(lModelPrism);

        Matrix.multiplyMM(this.modelView, 0, view, 0, modelGeom, 0);
        Matrix.multiplyMM(modelViewProjection, 0, perspective, 0, modelView, 0);
        GLES20.glUseProgram(prismProgram);
        GLES20.glUniform3fv(this.prismLightPosParam, 1, lightPosInEyeSpace, 0);

        // Set the Model in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.prismModelParam, 1, false, this.modelGeom, 0);

        // Set the ModelView in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.prismModelViewParam, 1, false, this.modelView, 0);
        GLES20.glVertexAttribPointer(
                this.prismPositionParam,COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, this.getFbPrismVertics());

        // Set the ModelViewProjection matrix in the shader.
        GLES20.glUniformMatrix4fv(this.prismModelViewProjectionParam, 1, false, this.modelViewProjection, 0);

        // Set the normal positions of the prism, again for shading
        GLES20.glVertexAttribPointer(this.prismNormalParam, 3, GLES20.GL_FLOAT, false, 0, this.getFbPrismNormals());  //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered

        //Has to do sth with the color of the prism while pointing at it
        GLES20.glVertexAttribPointer(prismColorParam, 4, GLES20.GL_FLOAT, false, 0,this.islookingAtIt ? this.getFbSelectedGeomColors() : this.getFbGeomColors());
        //GLES20.glVertexAttribPointer(this.prismColorParam, 4, GLES20.GL_FLOAT, false, 0,this.getFbGeomColors()); //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);  // There is also GL_LINES for rendering lines. We used GL_TRIANGLES , maybe also good for debugging :D looks impressiv
        checkGLError("Drawing prism");
    }

    public void initProgram(int vertexShader, int passthroughShader){


        this.prismProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(prismProgram, vertexShader);
        GLES20.glAttachShader(prismProgram, passthroughShader);
        GLES20.glLinkProgram(prismProgram);
        GLES20.glUseProgram(prismProgram);

        checkGLError("Prism program");

        this.prismPositionParam = GLES20.glGetAttribLocation(prismProgram, "a_Position");
        this.prismNormalParam = GLES20.glGetAttribLocation(prismProgram, "a_Normal");
        this.prismColorParam = GLES20.glGetAttribLocation(prismProgram, "a_Color");

        this.prismModelParam = GLES20.glGetUniformLocation(prismProgram, "u_Model");
        this.prismModelViewParam = GLES20.glGetUniformLocation(prismProgram, "u_MVMatrix");
        this.prismModelViewProjectionParam = GLES20.glGetUniformLocation(prismProgram, "u_MVP");
        this.prismLightPosParam = GLES20.glGetUniformLocation(prismProgram, "u_LightPos");

        GLES20.glEnableVertexAttribArray(this.prismPositionParam);// Enables the VertexArrays which is needed so opengl knows wht to render
        GLES20.glEnableVertexAttribArray(this.prismNormalParam);// Enables the VertexArrays which is needed so opengl knows wht to render
        GLES20.glEnableVertexAttribArray(this.prismColorParam);  // Enables the VertexArrays which is needed so opengl knows wht to render

        checkGLError("Prism program params");
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
    public Prism(Float x, Float y, Float z) {

        float r, g, b, a = 1.0f;
        //TODO FINSIH!

    }
    public Prism(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        pages = 6;
        verticesPerPage = 6;
        vCount = 9;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelGeom = new float[16];
        modelViewProjection = new float[16];
        modelPosition = new float[] {x, y, z};
        // TODO: Build constructors so we dont need to set static coords for every single prism. DONE so far
        px = x;
        py = y;
        pz = z;
        this.width = width;
        this.height = height;
        this.depth = depth;

        setGeomColors(setInitColor(r, g, b, a)); // Init Prism Colors on first call
        prismVertics = setPrismCoords(x, y, z, width, height, depth);// Init Prism Coords on first call
        prismNormals = setPrismNormals(width,height,depth);

        setFbGeomColors(colorFloatBuffer());
        fbPrismNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        fbPrismVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices

        this.callUpdatePos();



    }

}
