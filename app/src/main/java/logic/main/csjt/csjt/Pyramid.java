package logic.main.csjt.csjt;

/**
 * Created by Thundergrass on 02.06.2016.
 */
class Pyramid extends Geom {

    private float[] setPyramCoords(float px, float py, float pz, float width, float height, float depth){


        return new float[]{
                //Bottom
                px,         py,         pz,         //back left point
                px,         py,         pz+depth,   //front left point
                px+width,   py,         pz,         //back right point

                px,         py,         pz+depth,   //front left point
                px+width,   py,         pz,         //back right point
                px+width,   py,         pz+depth,   //front right point

                //Back
                px,         py,         pz,         //back left point
                px+width,   py,         pz,         //back right point
                px+(width/2), py+height, pz+(depth/2), //top

                //Left
                px,         py,         pz,         //back left point
                px,         py,         pz+depth,   //front left point
                px+(width/2), py+height, pz+(depth/2), //top

                //Front
                px,         py,         pz+depth,   //front left point
                px+width,   py,         pz+depth,   //front right point
                px+(width/2), py+height, pz+(depth/2), //top

                //Right
                px+width,   py,         pz,         //back right point
                px+width,   py,         pz+depth,   //front right point
                px+(width/2), py+height, pz+(depth/2), //top

        };
    }

    private float[] setPyramNormals(float width, float height, float depth){

        //Normals are constructed similar to the coordinates

        return new float[]{
                //Bottom
                0.0f,       -height,    0.0f,       //back left point
                0.0f,       -height,    0.0f,       //front left point
                0.0f,       -height,    0.0f,       //back right point

                0.0f,       -height,    0.0f,       //front left point
                0.0f,       -height,    0.0f,       //back right point
                0.0f,       -height,    0.0f,       //front right point

                //Back
                0.0f,       height,     -2*depth,   //back left point
                0.0f,       height,     -2*depth,   //back right point
                0.0f,       height,     -2*depth,   //top

                //Left
                -2*width,   height,     0.0f,       //back left point
                -2*width,   height,     0.0f,       //front left point
                -2*width,   height,     0.0f,       //top

                //Front
                0.0f,       height,     2*depth,    //front left point
                0.0f,       height,     2*depth,    //front right point
                0.0f,       height,     2*depth,    //top

                //Right
                2*width,    height,     0.0f,       //back right point
                2*width,    height,     0.0f,       //front right point
                2*width,    height,     0.0f,       //top

        };
    }

    Pyramid(Float x, Float y, Float z) {
        super(x,y,z);
        float r, g, b, a = 1.0f;
        // FINSIH!

    }


    Pyramid(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        super(x,y,z);
        pages = 6;
        verticesPerPage = 3;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.geomVertics = setPyramCoords(x, y, z, width, height, depth);// Init Pyram Coords on first call
        this.geomNormals = setPyramNormals(width,height,depth);
        setGeomColors(setInitColor(r, g, b, a)); // Init Tetra Colors on first call

        setFbGeomColors(colorFloatBuffer());
        this.fbGeomNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        this.fbGeomVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices



    }
}
