package logic.main.csjt.csjt;

import android.opengl.GLES20;

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
        private FloatBuffer fbCubeVertics;

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
        public float[] setCubeCoords(float px,float py,float pz ,float width, float height, float depth){
                width = width / 2;
                height = height / 2;
                depth = depth / 2;

                //TODO: Implement it correct, some rendering fuck ups :/

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
                        px-width, py-height, pz+depth,
                        px+width, py-height, pz-depth,
                        px-width, py-height, pz-depth,
                        px-width, py+height, pz-depth,

                        // Left face
                        px-width, py+height, pz-depth,
                        px-width, py-height, pz-depth,
                        px-width, py+height, pz+depth,
                        px-width, py-height, pz-depth,
                        px-width, py+height, pz-depth,
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
                        px-width, py-height, pz-depth,
                };

                return CUBE_COORDS;
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

// JUST FOR DEBUGGING SUPPORT HERE !!!!
        public static final float[] CUBE_COORDSSSS = new float[]{
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

        public void draw(){

              //TODO   Port the complete drawing to the Cube class so we call cubexyz.draw();

        }

        public Cube(Float x, Float y, Float z,float width,float height,float depth, float r, float g, float b, float a) {

                // TODO: Here we !need! to build constructors so we dont need to set static coords for every single shitty cube. Tom got a plan :D
                //FloatBuffer cubeVertices;  Floatbuffers kann man nicht instanzieren, weil sie statisch sind , von daher coords in array speichern dann an buffer übergeben
                cubeColors = initCubeColors(r, g, b, a); // Init Cube Colors on first call
                cubeVertics = setCubeCoords(x, y, z, width, height, depth);// Init Cube Coords on first call

                fbCubeColors = colorFloatBuffer();
                //fbCubeNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
                fbCubeVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices



        }

}
