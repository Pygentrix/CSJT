package logic.main.csjt.csjt;

import java.nio.FloatBuffer;

/**
 * Created by tom on 09.04.16.
 */
public class Geom {

    float px , py ,pz;
    float[] coords;
    FloatBuffer vertics;


public float[] setInitColor(float r, float g, float b,float a) {

    float[] INIT_COLORS = new float[]{
            // front, green
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,

            // right, blue
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,

            // back, also green
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,

            // left, also blue
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,

            // top, red
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,

            // bottom, also red
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
            r, g, b, a,
        };
    return INIT_COLORS;
    }

}


