package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by tom on 09.04.16.
 */

public class Geom {
    private static final String TAG = "CSJT";
    float px, py, pz;
    static int pages;
    static int verticesPerPage;
    private float[] geomColors;
    public static float[] selectedGeomColors;
    private FloatBuffer fbGeomColors;
    private FloatBuffer fbSelectedGeomColors;

    public float[] modelGeom;
    public float[] modelView = new float[16];
    public float[] modelPosition;

    // The default value is 0.12f, but we will increase the value due to debugging
    private static final float YAW_LIMIT = 0.12f;
    private static final float PITCH_LIMIT = 0.12f;

    public boolean islookingAtIt = false;

    public FloatBuffer getFbSelectedGeomColors() {
        return fbSelectedGeomColors;
    }

    public void setFbSelectedGeomColors(FloatBuffer fbSelectedGeomColors) {
        this.fbSelectedGeomColors = fbSelectedGeomColors;
    }

    public void setGeomColors(float[] geomColors) {
        this.geomColors = geomColors;
    }

    public void setFbGeomColors(FloatBuffer fbGeomColors) {
        this.fbGeomColors = fbGeomColors;
    }

    public FloatBuffer getFbGeomColors() {
        return this.fbGeomColors;
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

    public FloatBuffer colorFloatBuffer() {

        FloatBuffer lFloatBuffer;
        ByteBuffer bbColors = ByteBuffer.allocateDirect(this.geomColors.length * 4);
        bbColors.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbColors.asFloatBuffer();
        lFloatBuffer.put(this.geomColors);
        lFloatBuffer.position(0);
        return lFloatBuffer;
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

    public void updateModelPosition() {

        Matrix.setIdentityM(this.modelGeom, 0);
        // We add to the Y-axis a rnd float so cubes start moving....
        Matrix.translateM(this.modelGeom, 0, this.modelPosition[0], this.modelPosition[1], this.modelPosition[2]);

        checkGLError("updateCubePosition");
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
        this.islookingAtIt = Math.abs(pitch) < PITCH_LIMIT && Math.abs(yaw) < YAW_LIMIT;
        return Math.abs(pitch) < PITCH_LIMIT && Math.abs(yaw) < YAW_LIMIT;
    }
}