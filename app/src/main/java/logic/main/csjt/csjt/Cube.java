package logic.main.csjt.csjt;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pygentrix on 02.04.2016.
 */

public class Cube {

        private float[] cubeColors;
        private FloatBuffer fbCubeColors;

        private float[] cubeNormals;
        private FloatBuffer fbCubeNormals;

        private float[] cubeVertics;
        private FloatBuffer fbCubeVertices;

        private float[] initCubeColors(float r, float g, float b, float a) {

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
                return CUBE_COLORS;
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
        public FloatBuffer coordFloatBuffer(){

                float[] CUBE_COORDS = new float[]{
                        // Front face
                        -0.5f, 0.5f, 0.5f,
                        -0.5f, -0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f,
                        -0.5f, -0.5f, 0.5f,
                        0.5f, -0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f,

                        // Right face
                        0.5f, 0.5f, 0.5f,
                        0.5f, -0.5f, 0.5f,
                        0.5f, 0.5f, -0.5f,
                        0.5f, -0.5f, 0.5f,
                        0.5f, -0.5f, -0.5f,
                        0.5f, 0.5f, -0.5f,

                        // Back face
                        0.5f, 0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                        -0.5f, 0.5f, -0.5f,
                        0.5f, -0.5f, -0.5f,
                        -0.5f, -0.5f, -0.5f,
                        -0.5f, 0.5f, -0.5f,

                        // Left face
                        -0.5f, 0.5f, -0.5f,
                        -0.5f, -0.5f, -0.5f,
                        -0.5f, 0.5f, 0.5f,
                        -0.5f, -0.5f, -0.5f,
                        -0.5f, -0.5f, 0.5f,
                        -0.5f, 0.5f, 0.5f,

                        // Top face
                        -0.5f, 0.5f, -0.5f,
                        -0.5f, 0.5f, 0.5f,
                        0.5f, 0.5f, -0.5f,
                        -0.5f, 0.5f, 0.5f,
                        0.5f, 0.5f, 0.5f,
                        0.5f, 0.5f, -0.5f,

                        // Bottom face
                        0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, 0.5f,
                        -0.5f, -0.5f, -0.5f,
                        0.5f, -0.5f, 0.5f,
                        -0.5f, -0.5f, 0.5f,
                        -0.5f, -0.5f, -0.5f,
                };

                FloatBuffer lFloatBuffer;
                ByteBuffer bbCoords = ByteBuffer.allocateDirect(this.cubeVertics.length * 4);
                bbCoords.order(ByteOrder.nativeOrder());
                lFloatBuffer = bbCoords.asFloatBuffer();
                lFloatBuffer.put(this.cubeVertics);
                lFloatBuffer.position(0);
                return lFloatBuffer;
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

        public Cube(Float x, Float y, Float z) {

                float r, g, b, a = 1.0f;
                // FINSIH!

        }


        public static final float[] CUBE_COORDS = new float[]{
                // Front face
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                // Right face
                0.5f, 0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,

                // Back face
                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,

                // Left face
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,

                // Top face
                -0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,

                // Bottom face
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,
        };

        public static final float[] CUBE_NORMALS = new float[]{
                // Front face
                0.0f, 0.0f, 0.5f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.0f, 0.5f,

                // Right face
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,
                0.5f, 0.0f, 0.0f,

                // Back face
                0.0f, 0.0f, -0.5f,
                0.0f, 0.0f, -0.5f,
                0.0f, 0.0f, -0.5f,
                0.0f, 0.0f, -0.5f,
                0.0f, 0.0f, -0.5f,
                0.0f, 0.0f, -0.5f,

                // Left face
                -0.5f, 0.0f, 0.0f,
                -0.5f, 0.0f, 0.0f,
                -0.5f, 0.0f, 0.0f,
                -0.5f, 0.0f, 0.0f,
                -0.5f, 0.0f, 0.0f,
                -0.5f, 0.0f, 0.0f,

                // Top face
                0.0f, 0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.5f, 0.0f,

                // Bottom face
                0.0f, -0.5f, 0.0f,
                0.0f, -0.5f, 0.0f,
                0.0f, -0.5f, 0.0f,
                0.0f, -0.5f, 0.0f,
                0.0f, -0.5f, 0.0f,


        };

        public Cube(Float x, Float y, Float z,float width,float height, float r, float g, float b, float a) {

                // TODO: Here we !need! to build constructors so we dont need to set static coords for every single shitty cube. Tom got a plan :D
                //FloatBuffer cubeVertices;  Floatbuffers kann man nicht instanzieren, weil sie statisch sind , von daher coords in array speichern dann an buffer übergeben
                cubeColors = initCubeColors(r, g, b, a);
                fbCubeColors = colorFloatBuffer();    // COLORS OF CUBE ->  Colors
                //fbCubeNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
               // fbCubeVertices = coordFloatBuffer();  // COORDS OF CUBE -> Vertices



        }

}
