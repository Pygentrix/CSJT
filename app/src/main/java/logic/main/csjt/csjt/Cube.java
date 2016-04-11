package logic.main.csjt.csjt;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by Pygentrix on 02.04.2016.
 */

public class Cube extends Geom{


        private float[] cubeColors;
        private FloatBuffer fbCubeColors;

        private float[] cubeNormals;

        public FloatBuffer getFbCubeNormals() {
                return this.fbCubeNormals;
        }

        public FloatBuffer getFbCubeVertics() {
                return this.fbCubeVertics;
        }

        public FloatBuffer getFbCubeColors() {
                return this.fbCubeColors;
        }

        private FloatBuffer fbCubeNormals;

        private float[] cubeVertics;
        private FloatBuffer fbCubeVertics;

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

                //TODO: Implement it correct, some rendering fuck ups. DONE NOW

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

        public float[] setCubeNormals(float width, float height, float depth){
                width = width / 2;
                height = height / 2;
                depth = depth / 2;

                float[] CUBE_NORMALS = new float[]{
                        // Front face
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
                        0.0f, -height, 0.0f,
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

        public Cube(Float x, Float y, Float z) {

                float r, g, b, a = 1.0f;
                // FINSIH!

        }

        public void draw(){

              //TODO   Port the complete drawing to the Cube class so we call cubexyz.draw();

        }

        public Cube(Float x, Float y, Float z,float width,float height,float depth, float r, float g, float b, float a) {


                // TODO: Build constructors so we dont need to set static coords for every single cube. DONE so far
                px = x;
                py = y;
                pz = z;
                cubeColors = setInitColor(r, g, b, a); // Init Cube Colors on first call
                cubeVertics = setCubeCoords(x, y, z, width, height, depth);// Init Cube Coords on first call
                cubeNormals = setCubeNormals(width,height,depth);

                fbCubeColors = colorFloatBuffer();
                fbCubeNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
                fbCubeVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices



        }

}
