package logic.main.csjt.csjt;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pygentrix on 02.04.2016.
 */

public class Cube {

        public Cube(Float x, Float y, Float z,float width,float height, float r, float g, float b, float a) {

                // TODO: Here we !need! to build constructors so we dont need to set static coords for every single shitty cube. Tom got a plan :D
                FloatBuffer cubeVertices;
                FloatBuffer cubeColors = initCubeColors(r, g, b, a);
                FloatBuffer cubeNormals;
                initCubeColors(r,g,b,a);
                ByteBuffer bbColors = ByteBuffer.allocateDirect(Cube.CUBE_COLORS.length * 4);
                bbColors.order(ByteOrder.nativeOrder());
                cubeColors = bbColors.asFloatBuffer();
                cubeColors.put(Cube.CUBE_COLORS);
                cubeColors.position(0);


        }

        private FloatBuffer initCubeColors(float r,float g, float b, float a) {

                final float[] CUBE_COLORS = new float[]{
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
                ByteBuffer bbColors = ByteBuffer.allocateDirect(Cube.CUBE_COLORS.length * 4);
                bbColors.order(ByteOrder.nativeOrder());
                FloatBuffer cubeColors = bbColors.asFloatBuffer();
                return cubeColors;
        }

        public Cube(Float x, Float y, Float z) {

                float r, g, b, a = 0.9f;

        }

        public static final float[] CUBE_COLORS = new float[]{
                // front, green
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,

                // right, blue
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,

                // back, also green
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,
                0f, 0.5273f, 0.2656f, 1.0f,

                // left, also blue
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,
                0.0f, 0.3398f, 0.9023f, 1.0f,

                // top, red
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,

                // bottom, also red
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
                0.8359375f, 0.17578125f, 0.125f, 1.0f,
        };

        public static final float[] CUBE_COORDS = new float[]{
                // Front face
                -1.0f, 1.0f, 1.0f,
                -1.0f, -1.0f, 1.0f,
                1.0f, 1.0f, 1.0f,
                -1.0f, -1.0f, 1.0f,
                1.0f, -1.0f, 1.0f,
                1.0f, 1.0f, 1.0f,

                // Right face
                1.0f, 1.0f, 1.0f,
                1.0f, -1.0f, 1.0f,
                1.0f, 1.0f, -1.0f,
                1.0f, -1.0f, 1.0f,
                1.0f, -1.0f, -1.0f,
                1.0f, 1.0f, -1.0f,

                // Back face
                1.0f, 1.0f, -1.0f,
                1.0f, -1.0f, -1.0f,
                -1.0f, 1.0f, -1.0f,
                1.0f, -1.0f, -1.0f,
                -1.0f, -1.0f, -1.0f,
                -1.0f, 1.0f, -1.0f,

                // Left face
                -1.0f, 1.0f, -1.0f,
                -1.0f, -1.0f, -1.0f,
                -1.0f, 1.0f, 1.0f,
                -1.0f, -1.0f, -1.0f,
                -1.0f, -1.0f, 1.0f,
                -1.0f, 1.0f, 1.0f,

                // Top face
                -1.0f, 1.0f, -1.0f,
                -1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, -1.0f,
                -1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 1.0f,
                1.0f, 1.0f, -1.0f,

                // Bottom face
                1.0f, -1.0f, -1.0f,
                1.0f, -1.0f, 1.0f,
                -1.0f, -1.0f, -1.0f,
                1.0f, -1.0f, 1.0f,
                -1.0f, -1.0f, 1.0f,
                -1.0f, -1.0f, -1.0f,
        };

        public static final float[] CUBE_NORMALS = new float[]{
                // Front face
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f,

                // Right face
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,
                1.0f, 0.0f, 0.0f,

                // Back face
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,
                0.0f, 0.0f, -1.0f,

                // Left face
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,
                -1.0f, 0.0f, 0.0f,

                // Top face
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 0.0f,

                // Bottom face
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,
                0.0f, -1.0f, 0.0f,


        };

}
