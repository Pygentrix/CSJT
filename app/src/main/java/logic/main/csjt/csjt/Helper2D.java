package logic.main.csjt.csjt;

import android.opengl.GLES20;
import android.opengl.Matrix;

/**
 * Created by Pyrox on 30.08.2016.
 */
public class Helper2D {

    public static float mtrxProjection[];
    public static float mtrxView[];
    public static float mtrxProjectionAndView[];

    public static void updateMatrices(int width, int height,float[] mtrxView){

        // We need to know the current width and height.
        float mScreenWidth = width;
        float mScreenHeight = height;

        // Redo the Viewport, making it fullscreen.
        GLES20.glViewport(0, 0, (int)mScreenWidth, (int)mScreenHeight);

        // Clear our matrices
        for(int i=0;i<16;i++)
        {
            Helper2D.mtrxProjection[i] = 0.0f;
            Helper2D.mtrxView[i] = 0.0f;
            Helper2D.mtrxProjectionAndView[i] = 0.0f;
        }

        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM( Helper2D.mtrxProjection, 0, 0f, mScreenWidth, 0.0f, mScreenHeight, 0, 50);

        // Set the camera position (View matrix)
        Matrix.setLookAtM( Helper2D.mtrxView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM( Helper2D.mtrxProjectionAndView, 0, Helper2D.mtrxProjection, 0,  Helper2D.mtrxView, 0);
    }


}
