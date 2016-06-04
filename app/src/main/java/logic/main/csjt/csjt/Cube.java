package logic.main.csjt.csjt;

/**
 * Created by Pygentrix on 02.04.2016.
 */

class Cube extends Geom{

//SETTERS
        private float[] setCubeCoords(float px, float py, float pz, float width, float height, float depth){

                //TODO: Implement it correct, some rendering fuck ups. Still not correct

                return new float[]{
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
        }

        private float[] setCubeNormals(float width, float height, float depth){

                return new float[]{
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
        }

//FUNCTIONS / METHODS
        public void updateLightPosition() {

                this.modelPosition[0] = ApplicationTest.LIGHT_POS_IN_WORLD_SPACE[0];
                this.modelPosition[1] = ApplicationTest.LIGHT_POS_IN_WORLD_SPACE[1];
                this.modelPosition[2] = ApplicationTest.LIGHT_POS_IN_WORLD_SPACE[2];
                this.updateModelPosition();
        }

//CONSTRUCTOR(S)
        Cube(float x, float y, float z) {

                super(x,y,z);
                float r, g, b, a = 1.0f;
                //TODO FINSIH!

        }
        Cube(float x, float y, float z, float width, float height, float depth, float r, float g, float b, float a) {

                super(x,y,z);
                pages = 6;
                verticesPerPage = 6;
                vCount = pages * verticesPerPage;
                selectedGeomColors = setSelectedGeomColors();
                setFbSelectedGeomColors(setSelectedColorFloatBuffer());

                this.width = width;
                this.height = height;
                this.depth = depth;

                this.geomVertics = setCubeCoords(x, y, z, width, height, depth);// Init Cube Coords on first call
                this.geomNormals = setCubeNormals(width,height,depth);
                setGeomColors(setInitColor(r, g, b, a)); // Init Cube Colors on first call

                setFbGeomColors(colorFloatBuffer());
                this.fbGeomNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
                this.fbGeomVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices
                this.updateModelPosition();



        }

}
