package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pyrox on 03.06.2016.
 */
public class Grid {

    private FloatBuffer floorVertices;
    private FloatBuffer floorColors;
    private FloatBuffer floorNormals;

    private int floorProgram;

    private int floorPositionParam;
    private int floorNormalParam;
    private int floorColorParam;
    private int floorModelParam;
    private int floorModelViewParam;
    private int floorModelViewProjectionParam;
    private int floorLightPosParam;

    private float floorDepth;
    private float[] gfloorNormals;
    private float[] gfloorVertics;
    private float[] gfloorColors;
    private float[] modelFloor;
    private float[] modelView;
    private float[] modelViewProjection;

    private static float r = 0.4f;
    private static float g = 0.7f;
    private static float b = 1.0f;
    private static float a = 0.9f;

    private FloatBuffer verticsFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbVertics = ByteBuffer.allocateDirect(this.gfloorVertics.length * 4);
        bbVertics.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbVertics.asFloatBuffer();
        lFloatBuffer.put(this.gfloorVertics);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    private FloatBuffer normalsFloatBuffer(){

        FloatBuffer lFloatBuffer;
        ByteBuffer bbNormals = ByteBuffer.allocateDirect(this.gfloorNormals.length * 4);
        bbNormals.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbNormals.asFloatBuffer();
        lFloatBuffer.put(this.gfloorNormals);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    private FloatBuffer colorFloatBuffer() {

        FloatBuffer lFloatBuffer;
        ByteBuffer bbColors = ByteBuffer.allocateDirect(this.gfloorColors.length * 4);
        bbColors.order(ByteOrder.nativeOrder());
        lFloatBuffer = bbColors.asFloatBuffer();
        lFloatBuffer.put(this.gfloorColors);
        lFloatBuffer.position(0);
        return lFloatBuffer;
    }

    void initProgramm(int gridShader,int vertexShader){


        floorProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(floorProgram, vertexShader);
        GLES20.glAttachShader(floorProgram, gridShader);
        GLES20.glLinkProgram(floorProgram);
        GLES20.glUseProgram(floorProgram);

        checkGLError("Floor program");

        floorModelParam = GLES20.glGetUniformLocation(floorProgram, "u_Model");
        floorModelViewParam = GLES20.glGetUniformLocation(floorProgram, "u_MVMatrix");
        floorModelViewProjectionParam = GLES20.glGetUniformLocation(floorProgram, "u_MVP");
        floorLightPosParam = GLES20.glGetUniformLocation(floorProgram, "u_LightPos");

        floorPositionParam = GLES20.glGetAttribLocation(floorProgram, "a_Position");
        floorNormalParam = GLES20.glGetAttribLocation(floorProgram, "a_Normal");
        floorColorParam = GLES20.glGetAttribLocation(floorProgram, "a_Color");

        checkGLError("Floor program params");

        Matrix.setIdentityM(modelFloor, 0);
        Matrix.translateM(modelFloor, 0, 0, -floorDepth, 0); // Floor appears below user.

    }

    /**
     * Checks if we've had an error inside of OpenGL ES, and if so what that error is.
     *
     * @param label Label to report in case of error.
     */
    private static void checkGLError(String label) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("CSJT", label + ": glError " + error);
            throw new RuntimeException(label + ": glError " + error);
        }
    }

    void drawFloor(float[] lightPosInEyeSpace, float[] view, float[] perspective) {

        // Set modelView for the floor, so we draw floor in the correct location
        Matrix.multiplyMM(modelView, 0, view, 0, modelFloor, 0);
        Matrix.multiplyMM(modelViewProjection, 0, perspective, 0, modelView, 0);
        GLES20.glUseProgram(floorProgram);

        // Set ModelView, MVP, position, normals, and color.
        GLES20.glUniform3fv(floorLightPosParam, 1, lightPosInEyeSpace, 0);
        GLES20.glUniformMatrix4fv(floorModelParam, 1, false, modelFloor, 0);
        GLES20.glUniformMatrix4fv(floorModelViewParam, 1, false, modelView, 0);
        GLES20.glUniformMatrix4fv(floorModelViewProjectionParam, 1, false, modelViewProjection, 0);
        GLES20.glVertexAttribPointer(
                floorPositionParam, 3, GLES20.GL_FLOAT, false, 0, floorVertices);
        GLES20.glVertexAttribPointer(floorNormalParam, 3, GLES20.GL_FLOAT, false, 0, floorNormals);
        GLES20.glVertexAttribPointer(floorColorParam, 4, GLES20.GL_FLOAT, false, 0, floorColors);

        GLES20.glEnableVertexAttribArray(floorPositionParam);
        GLES20.glEnableVertexAttribArray(floorNormalParam);
        GLES20.glEnableVertexAttribArray(floorColorParam);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 24);

        checkGLError("drawing floor");
    }

    // The grid lines on the floor are rendered procedurally and large polygons cause floating point
    // precision problems on some architectures. So we split the floor into 4 quadrants.
    private static final float[] FLOOR_COORDS = new float[] {
            // +X, +Z quadrant
            200, 0, 0,
            0, 0, 0,
            0, 0, 200,
            200, 0, 0,
            0, 0, 200,
            200, 0, 200,

            // -X, +Z quadrant
            0, 0, 0,
            -200, 0, 0,
            -200, 0, 200,
            0, 0, 0,
            -200, 0, 200,
            0, 0, 200,

            // +X, -Z quadrant
            200, 0, -200,
            0, 0, -200,
            0, 0, 0,
            200, 0, -200,
            0, 0, 0,
            200, 0, 0,

            // -X, -Z quadrant
            0, 0, -200,
            -200, 0, -200,
            -200, 0, 0,
            0, 0, -200,
            -200, 0, 0,
            0, 0, 0,
    };

    private static final float[] FLOOR_NORMALS = new float[] {
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
    };

    private static final float[] FLOOR_COLORS = new float[] {
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
            r,g, b, a,
    };

    Grid(float floorDepth){

        this.floorDepth = floorDepth;

        //setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelFloor = new float[16];
        modelViewProjection = new float[16];
        modelView =new float[16];


        this.gfloorVertics = FLOOR_COORDS;// Init Cube Coords on first call
        this.gfloorNormals = FLOOR_NORMALS;
        this.gfloorColors = FLOOR_COLORS;

        //setfloorColors(colorFloatBuffer());
        this.floorNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        this.floorVertices = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices
        this.floorColors = colorFloatBuffer();


    }
}
