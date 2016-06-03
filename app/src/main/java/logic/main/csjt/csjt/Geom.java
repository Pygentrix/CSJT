package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by tom on 09.04.16.
 */

public class Geom {

    ArrayList<Geom> allGeoms = new ArrayList<Geom>();

//STRINGS
    private static final String TAG = "CSJT";

//FLOATS
    float px, py, pz;
    float objectDistance;
    float width;
    float height;
    float depth;
    float rotSpeed = 0.5f;
    float movY = 0.0f; // Movement factor in y-direction
    // The default value is 0.12f, but we will increase the value due to debugging
    private float YAW_LIMIT;
    private float PITCH_LIMIT;

//FLOAT ARRAYS
    public float[] modelGeom;
    public float[] modelView = new float[16];
    public float[] modelPosition;
    private float[] geomColors;
    float[] selectedGeomColors;
    float[] modelViewProjection;

    float[] geomNormals;
    float[] geomVertics;

//FLOAT BUFFERS
    FloatBuffer fbGeomNormals;
    FloatBuffer fbGeomVertics;

//INTEGERS
    int vCount;
    int pages;
    int verticesPerPage;

    public static int geomProgram;
    private static final int COORDS_PER_VERTEX = 3;
    int geomPositionParam;
    int geomModelViewProjectionParam;
    int geomNormalParam;
    int geomColorParam;
    int geomModelParam;
    int geomModelViewParam;
    int geomLightPosParam;
    int movStatus = 0; // 0 -> No movement ; 1 -> Rotation ; 2 -> movement in directions

//BUFFERS
    private FloatBuffer fbGeomColors;
    private FloatBuffer fbSelectedGeomColors;

//BOOLEANS
    public boolean islookingAtIt = false;
    public static boolean rMode = true; // used for the rendering mode
    public boolean dir = true;
    private boolean initCase = true;


//GETTERS
    public FloatBuffer getFbSelectedGeomColors() {
        return fbSelectedGeomColors;
    }

    public FloatBuffer getFbGeomColors() {
        return this.fbGeomColors;
    }

    public FloatBuffer getFbGeomNormals() {
        return this.fbGeomNormals;
    }

    public FloatBuffer getFbGeomVertics() {
        return this.fbGeomVertics;
    }



    public float[] getModelGeom() {
        return modelGeom;
    }

//SETTERS
    public void setFbSelectedGeomColors(FloatBuffer fbSelectedGeomColors) {
        this.fbSelectedGeomColors = fbSelectedGeomColors;
    }

    public void setGeomColors(float[] geomColors) {
        this.geomColors = geomColors;
    }

    public void setFbGeomColors(FloatBuffer fbGeomColors) {
        this.fbGeomColors = fbGeomColors;
    }

    public float[] setInitColor(float r, float g, float b, float a) {

        float[] INIT_COLORS = new float[pages * verticesPerPage * 4];

        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                INIT_COLORS[(verticesPerPage * j * 4 + i * 4) + 0] = r;
            }
        }
        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                INIT_COLORS[(verticesPerPage * j * 4 + i * 4) + 1] = g;
            }
        }
        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                INIT_COLORS[(verticesPerPage * j * 4 + i * 4) + 2] = b;
            }
        }
        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                INIT_COLORS[(verticesPerPage * j * 4 + i * 4) + 3] = a;
            }
        }
        return INIT_COLORS;
    }

    public float[] setSelectedGeomColors() {

        float[] S_COLORS = new float[pages * verticesPerPage * 4];

        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                S_COLORS[(verticesPerPage * j * 4 + i * 4) + 0] = 1.0f;
            }
        }
        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                S_COLORS[(verticesPerPage * j * 4 + i * 4) + 1] = 1.0f;
            }
        }
        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                S_COLORS[(verticesPerPage * j * 4 + i * 4) + 2] = 1.0f;
            }
        }
        for (int j = 0; j < pages; j++) {
            for (int i = 0; i < verticesPerPage; i++) {
                S_COLORS[(verticesPerPage * j * 4 + i * 4) + 3] = 1.0f;
            }
        }
        return S_COLORS;
    }

    public FloatBuffer setSelectedColorFloatBuffer() {

        FloatBuffer lFloatBuffer;
        ByteBuffer bbColors = ByteBuffer.allocateDirect(this.selectedGeomColors.length * 4);
        bbColors.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbColors.asFloatBuffer();
        lFloatBuffer.put(this.selectedGeomColors);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public void setModelGeom(float[] modelGeom) {
        this.modelGeom = modelGeom;
    }

//METHODS / FUNCTIONS

    public FloatBuffer verticsFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbVertics = ByteBuffer.allocateDirect(this.geomVertics.length * 4);
        bbVertics.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbVertics.asFloatBuffer();
        lFloatBuffer.put(this.geomVertics);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public FloatBuffer normalsFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbNormals = ByteBuffer.allocateDirect(this.geomNormals.length * 4);
        bbNormals.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbNormals.asFloatBuffer();
        lFloatBuffer.put(this.geomNormals);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    public FloatBuffer colorFloatBuffer() {

        FloatBuffer lFloatBuffer;
        ByteBuffer bbColors = ByteBuffer.allocateDirect(this.geomColors.length * 4);
        bbColors.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbColors.asFloatBuffer();
        lFloatBuffer.put(this.geomColors);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    /**
     * Checks if we've had an error inside of OpenGL ES, and if so what that error is.
     *
     * @param label Label to report in case of error.
     */
    public static void checkGLError(String label) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, label + ": glError " + error);
            throw new RuntimeException(label + ": glError " + error);
        }
    }


    public void initProgram(int vertexShader, int passthroughShader){


        this.geomProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(geomProgram, vertexShader);
        GLES20.glAttachShader(geomProgram, passthroughShader);
        GLES20.glLinkProgram(geomProgram);
        GLES20.glUseProgram(geomProgram);

        checkGLError("Cube program");

        this.geomPositionParam = GLES20.glGetAttribLocation(geomProgram, "a_Position");
        this.geomNormalParam = GLES20.glGetAttribLocation(geomProgram, "a_Normal");
        this.geomColorParam = GLES20.glGetAttribLocation(geomProgram, "a_Color");

        this.geomModelParam = GLES20.glGetUniformLocation(geomProgram, "u_Model");
        this.geomModelViewParam = GLES20.glGetUniformLocation(geomProgram, "u_MVMatrix");
        this.geomModelViewProjectionParam = GLES20.glGetUniformLocation(geomProgram, "u_MVP");
        this.geomLightPosParam = GLES20.glGetUniformLocation(geomProgram, "u_LightPos");

        GLES20.glEnableVertexAttribArray(this.geomPositionParam);// Enables the VertexArrays which is needed so opengl knows wht to render
        GLES20.glEnableVertexAttribArray(this.geomNormalParam);// Enables the VertexArrays which is needed so opengl knows wht to render
        GLES20.glEnableVertexAttribArray(this.geomColorParam);  // Enables the VertexArrays which is needed so opengl knows wht to render
        this.updateModelPosition();
        checkGLError("geom program params");
    }

    public void draw(float[] lightPosInEyeSpace, float[] view, float[] perspective){
        // TODO: Get Updating and rotation work toghether, look at hideObject func to solve problem
        if(movStatus == 0){
            //Actually doing nothing
        }
        else if(movStatus == 1) {
            float[] lModelGeom = this.getModelGeom();
            Matrix.rotateM(lModelGeom, 0,rotSpeed, this.modelPosition[0],this.modelPosition[1],this.modelPosition[2]);
            this.setModelGeom(lModelGeom);
        }
        else if(movStatus == 2){
            this.updateModelPosition();
        }

        Matrix.multiplyMM(this.modelView, 0, view, 0, modelGeom, 0);
        Matrix.multiplyMM(modelViewProjection, 0, perspective, 0, modelView, 0);
        GLES20.glUseProgram(geomProgram);
        GLES20.glUniform3fv(this.geomLightPosParam, 1, lightPosInEyeSpace, 0);

        // Set the Model in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.geomModelParam, 1, false, this.modelGeom, 0);

        // Set the ModelView in the shader, used to calculate lighting
        GLES20.glUniformMatrix4fv(this.geomModelViewParam, 1, false, this.modelView, 0);
        GLES20.glVertexAttribPointer(
                this.geomPositionParam,COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0, this.getFbGeomVertics());

        // Set the ModelViewProjection matrix in the shader.
        GLES20.glUniformMatrix4fv(this.geomModelViewProjectionParam, 1, false, this.modelViewProjection, 0);

        // Set the normal positions of the geom, again for shading
        GLES20.glVertexAttribPointer(this.geomNormalParam, 3, GLES20.GL_FLOAT, false, 0, this.getFbGeomNormals());  //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered

        //Has to do sth with the color of the geom while pointing at it
        GLES20.glVertexAttribPointer(geomColorParam, 4, GLES20.GL_FLOAT, false, 0,this.islookingAtIt ? this.getFbSelectedGeomColors() : this.getFbGeomColors());
        //GLES20.glVertexAttribPointer(this.geomColorParam, 4, GLES20.GL_FLOAT, false, 0,this.getFbGeomColors()); //<- Points to the active Array other words: OpenGL now knows, that this needs to be rendered
        GLES20.glDrawArrays(rMode ? GLES20.GL_TRIANGLES : GLES20.GL_LINES, 0, vCount);  // There is also GL_LINES for rendering lines. We used GL_TRIANGLES , maybe also good for debugging :D looks impressiv
        checkGLError("Drawing geom");
    }

    public void updateModelPosition() {
        Matrix.setIdentityM(this.modelGeom, 0);
        calcY();
        Matrix.translateM(this.modelGeom, 0, this.modelPosition[0], this.modelPosition[1] - this.movY, this.modelPosition[2]);
        //float[] transMatrix = this.modelGeom;
        //Matrix.rotateM(this.modelGeom, 0,this.rotSpeed, this.modelPosition[0], this.modelPosition[1], this.modelPosition[2]);

        objectDistance =(float) Math.sqrt((((this.modelPosition[0]-ApplicationTest.CAMERA_X)
                *(this.modelPosition[0]-ApplicationTest.CAMERA_X))
                +((this.modelPosition[1]-ApplicationTest.CAMERA_Y)*(this.modelPosition[1]-ApplicationTest.CAMERA_Y))
                +((this.modelPosition[2]-ApplicationTest.CAMERA_Z)*(this.modelPosition[2]-ApplicationTest.CAMERA_Z))));

        checkGLError("updateGeomPosition");
    }

    private void calcY(){

        // We add to the Y-axis a rnd float so geoms start moving....
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
    }


    //TODO This function is really trivial in checking obj(more tiny obj are triggered inaccurate), this is because of no distance to obj calcs,
    //TODO no more time will be spent as the MUST-HAVEs are more important
    public boolean isLookingAtObject(float[] headView) {
        float[] initVec = {this.modelPosition[0], this.modelPosition[1], this.modelPosition[2], 1.0f};
        float[] objPositionVec = new float[4];

        // Convert object space to camera space. Use the headView from onNewFrame.
        // Multiplies 2 matrices and stores it into the result
        Matrix.multiplyMM(this.modelView, 0, headView, 0, this.modelGeom, 0);

        //Multiplies a 4 element vector by a 4x4 matrix and stores the result in a 4-element column vector.
        Matrix.multiplyMV(objPositionVec, 0, this.modelView, 0, initVec, 0);
        // Pitch: Tolerance up & down, switching to german for myself( Nimmt die Koordinaten Y,Z und berechnet daraus im Polar Koordinatensystem den pitch)
        float pitch = (float) Math.atan2(objPositionVec[1], -objPositionVec[2]);
        // Yaw: Tolerance to the left and right, german again ( Nimmt X und Z und berechnet den YAW des obj in Polar Koordinatensystem)
        float yaw = (float) Math.atan2(objPositionVec[0], -objPositionVec[2]);
        // Returns true if pitch and yaw are in the space of the object
        // Modified the PITCH AND YAW LIMIT!!
        PITCH_LIMIT = this.height / objectDistance /2;
        YAW_LIMIT = this.width / objectDistance /2;
        this.islookingAtIt = Math.abs(pitch) < PITCH_LIMIT && Math.abs(yaw) < YAW_LIMIT;
        return this.islookingAtIt;
    }
    /**
     * Find a new random position for the object.
     *
     * <p>We'll rotate it around the Y-axis so it's out of sight, and then up or down by a little bit.
     */
    //TODO: Implement function if needed.... (Not needed yet)
    private void hideObject() {
        float[] rotationMatrix = new float[16];
        float[] posVec = new float[4];

        // First rotate in XZ plane, between 90 and 270 deg away, and scale so that we vary
        // the object's distance from the user.
        float angleXZ = (float) Math.random() * 180 + 90;
        Matrix.setRotateM(rotationMatrix, 0, angleXZ, 0f, 1f, 0f);
        float oldObjectDistance = objectDistance;
        //objectDistance =
        //        (float) Math.random() * (MAX_MODEL_DISTANCE - MIN_MODEL_DISTANCE) + MIN_MODEL_DISTANCE;
        float objectScalingFactor = objectDistance / oldObjectDistance;
        Matrix.scaleM(rotationMatrix, 0, objectScalingFactor, objectScalingFactor, objectScalingFactor);
        //Matrix.multiplyMV(posVec, 0, rotationMatrix, 0, modelGeom, 12);

        float angleY = (float) Math.random() * 80 - 40; // Angle in Y plane, between -40 and 40.
        angleY = (float) Math.toRadians(angleY);
        float newY = (float) Math.tan(angleY) * objectDistance;

        modelPosition[0] = posVec[0];
        modelPosition[1] = newY;
        modelPosition[2] = posVec[2];

        //updateModelPosition();
    }

    public Geom(){

        this.addToGlobalList();
        //this should add the obj to a global list where all geoms are referenced in
    }

    private void addToGlobalList() {


    }

}