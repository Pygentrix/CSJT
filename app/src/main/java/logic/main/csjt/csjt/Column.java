package logic.main.csjt.csjt;

/**
 * Created by Thundergrass on 31.05.2016.
 */
public class Column extends Geom {

    public float[] setColumnCoords(float px,float py,float pz ,float width, float height, float depth){

        /**
         *  Created by using the current Tetrahedron Class. (31.05.16)
         *  Column is basically an elongated Octagon. (Note: Not a regular octagon. those need the square root of 2 and i don't like that.)
         *
         *  02.06.2016: I'll repeat the successful prism experiment with this class. This time,
         *      I'll use quadrangular shapes. (4 points)
         *  03.06.2016: Quadrangular shapes did not work. Time to rewrite everything for triangular shapes.
         *      Note: Started systematically counter-clockwise; abandoned that scheme somewhere down
         */

        //helper variables
        float wid = width / 4;
        float hei = height / 4;
        float dep = depth / 4;

        float[] COL_COORDS = new float[]{
                //Bottom Face
                px+wid,     py,         pz,         //back left point
                px,         py,         pz+dep,     //left back point
                px,         py,         pz+(3*dep), //left front point

                px+wid,     py,         pz,         //back left point
                px,         py,         pz+(3*dep), //left front point
                px+wid,     py,         pz+(4*dep), //front left point

                px+wid,     py,         pz,         //back left point
                px+wid,     py,         pz+(4*dep), //front left point
                px+(3*wid), py,         pz,         //back right point

                px+wid,     py,         pz+(4*dep), //front left point
                px+(3*wid), py,         pz+(4*dep), //front right point
                px+(3*wid), py,         pz,         //back right point

                px+(3*wid), py,         pz+(4*dep), //front right point
                px+(4*wid), py,         pz+(3*dep), //right front point
                px+(4*wid), py,         pz+dep,     //right back point

                px+(3*wid), py,         pz+(4*dep), //front right point
                px+(4*wid), py,         pz+dep,     //right back point
                px+(3*wid), py,         pz,         //back right point

                //Back Face
                px+wid,     py,         pz,         //bottom back left point
                px+(3*wid), py,         pz,         //bottom back right point
                px+(3*wid), py+(4*hei), pz,         //top back right point

                px+wid,     py,         pz,         //bottom back left point
                px+(3*wid), py+(4*hei), pz,         //top back right point
                px+wid,     py+(4*hei), pz,         //top back left point

                //Back left Face
                px,         py,         pz+dep,     //bottom left back point
                px+wid,     py,         pz,         //bottom back left point
                px+wid,     py+(4*hei), pz,         //top back left point

                px,         py,         pz+dep,     //bottom left back point
                px+wid,     py+(4*hei), pz,         //top back left point
                px,         py+(4*hei), pz+dep,     //top left back point

                //Left Face
                px,         py,         pz+(3*dep), //bottom left front point
                px,         py,         pz+dep,     //bottom left back point
                px,         py+(4*hei), pz+dep,     //top left back point

                px,         py+(4*hei), pz+dep,     //top left back point
                px,         py,         pz+(3*dep), //bottom left front point
                px,         py+(4*hei), pz+(3*dep), //top left front point

                //Front left Face
                px+wid,     py,         pz+(4*dep), //bottom front left point
                px,         py,         pz+(3*dep), //bottom left front point
                px,         py+(4*hei), pz+(3*dep), //top left front point

                px+wid,     py,         pz+(4*dep), //bottom front left point
                px,         py+(4*hei), pz+(3*dep), //top left front point
                px+wid,     py+(4*hei), pz+(4*dep), //top front left point

                //Front Face
                px+(3*wid), py,         pz+(4*dep), //bottom front right point
                px+wid,     py,         pz+(4*dep), //bottom front left point
                px+wid,     py+(4*hei), pz+(4*dep), //top front left point

                px+wid,     py+(4*hei), pz+(4*dep), //top front left point
                px+(3*wid), py,         pz+(4*dep), //bottom front right point
                px+(3*wid), py+(4*hei), pz+(4*dep), //top front right point

                //Front right Face
                px+(4*wid), py,         pz+(3*dep), //bottom right front point
                px+(3*wid), py,         pz+(4*dep), //bottom front right point
                px+(3*wid), py+(4*hei), pz+(4*dep), //top front right point

                px+(3*wid), py+(4*hei), pz+(4*dep), //top front right point
                px+(4*wid), py,         pz+(3*dep), //bottom right front point
                px+(4*wid), py+(4*hei), pz+(3*dep), //top right front point

                //Right Face
                px+(4*wid), py,         pz+dep,     //bottom right back point
                px+(4*wid), py,         pz+(3*dep), //bottom right front point
                px+(4*wid), py+(4*hei), pz+(3*dep), //top right front point

                px+(4*wid), py+(4*hei), pz+(3*dep), //top right front point
                px+(4*wid), py,         pz+dep,     //bottom right back point
                px+(4*wid), py+(4*hei), pz+dep,     //top right back point

                //Back right Face
                px+(3*wid), py,         pz,         //bottom back right point
                px+(4*wid), py,         pz+dep,     //bottom right back point
                px+(4*wid), py+(4*hei), pz+dep,     //top right back point

                px+(4*wid), py+(4*hei), pz+dep,     //top right back point
                px+(3*wid), py,         pz,         //bottom back right point
                px+(3*wid), py+(4*hei), pz,         //top back right point

                //Top Face
                px+wid,     py+(4*hei), pz,         //back left point
                px,         py+(4*hei), pz+dep,     //left back point
                px,         py+(4*hei), pz+(3*dep), //left front point

                px,         py+(4*hei), pz+(3*dep), //left front point
                px+wid,     py+(4*hei), pz,         //back left point
                px+wid,     py+(4*hei), pz+(4*dep), //front left point

                px+wid,     py+(4*hei), pz,         //back left point
                px+wid,     py+(4*hei), pz+(4*dep), //front left point
                px+(3*wid), py+(4*hei), pz+(4*dep), //front right point

                px+(3*wid), py+(4*hei), pz+(4*dep), //front right point
                px+wid,     py+(4*hei), pz,         //back left point
                px+(3*wid), py+(4*hei), pz,         //back right point

                px+(3*wid), py+(4*hei), pz+(4*dep), //front right point
                px+(4*wid), py+(4*hei), pz+(3*dep), //right front point
                px+(4*wid), py+(4*hei), pz+dep,     //right back point

                px+(4*wid), py+(4*hei), pz+dep,     //right back point
                px+(3*wid), py+(4*hei), pz+(4*dep), //front right point
                px+(3*wid), py+(4*hei), pz,         //back right point

        };

        return COL_COORDS;
    }

    public float[] setColumnNormals(float width, float height, float depth){

        //Normals are constructed similar to the coordinates
        float[] COL_NORMALS = new float[]{
                //Bottom Face
                0.0f,   -height,    0.0f,   //back left point
                0.0f,   -height,    0.0f,   //left back point
                0.0f,   -height,    0.0f,   //left front point

                0.0f,   -height,    0.0f,   //left front point
                0.0f,   -height,    0.0f,   //back left point
                0.0f,   -height,    0.0f,   //front left point

                0.0f,   -height,    0.0f,   //back left point
                0.0f,   -height,    0.0f,   //front left point
                0.0f,   -height,    0.0f,   //front right point

                0.0f,   -height,    0.0f,   //front right point
                0.0f,   -height,    0.0f,   //back left point
                0.0f,   -height,    0.0f,   //back right point

                0.0f,   -height,    0.0f,   //front right point
                0.0f,   -height,    0.0f,   //right front point
                0.0f,   -height,    0.0f,   //right back point

                0.0f,   -height,    0.0f,   //right back point
                0.0f,   -height,    0.0f,   //front right point
                0.0f,   -height,    0.0f,   //back right point

                //Back Face
                0.0f,   0.0f,   -depth, //bottom back left point
                0.0f,   0.0f,   -depth, //bottom back right point
                0.0f,   0.0f,   -depth, //top back right point

                0.0f,   0.0f,   -depth, //top back right point
                0.0f,   0.0f,   -depth, //bottom back left point
                0.0f,   0.0f,   -depth, //top back left point

                //Back left Face
                -width, 0.0f,   -depth, //bottom left back point
                -width, 0.0f,   -depth, //bottom back left point
                -width, 0.0f,   -depth, //top back left point

                -width, 0.0f,   -depth, //top back left point
                -width, 0.0f,   -depth, //bottom left back point
                -width, 0.0f,   -depth, //top left back point

                //Left Face
                -width, 0.0f,   0.0f,   //bottom left front point
                -width, 0.0f,   0.0f,   //bottom left back point
                -width, 0.0f,   0.0f,   //top left back point

                -width, 0.0f,   0.0f,   //top left back point
                -width, 0.0f,   0.0f,   //bottom left front point
                -width, 0.0f,   0.0f,   //top left front point

                //Front left Face
                -width, 0.0f,    depth,  //bottom front left point
                -width, 0.0f,    depth,  //bottom left front point
                -width, 0.0f,    depth,  //top left front point

                -width, 0.0f,    depth,  //top left front point
                -width, 0.0f,    depth,  //bottom front left point
                -width, 0.0f,    depth,  //top front left point

                //Front Face
                0.0f,   0.0f,   depth,  //bottom front right point
                0.0f,   0.0f,   depth,  //bottom front left point
                0.0f,   0.0f,   depth,  //top front left point

                0.0f,   0.0f,   depth,  //top front left point
                0.0f,   0.0f,   depth,  //bottom front right point
                0.0f,   0.0f,   depth,  //top front right point

                //Front right Face
                width,  0.0f,   depth,  //bottom right front point
                width,  0.0f,   depth,  //bottom front right point
                width,  0.0f,   depth,  //top front right point

                width,  0.0f,   depth,  //top front right point
                width,  0.0f,   depth,  //bottom right front point
                width,  0.0f,   depth,  //top right front point

                //Right Face
                width,  0.0f,   0.0f,   //bottom right back point
                width,  0.0f,   0.0f,   //bottom right front point
                width,  0.0f,   0.0f,   //top right front point

                width,  0.0f,   0.0f,   //top right front point
                width,  0.0f,   0.0f,   //bottom right back point
                width,  0.0f,   0.0f,   //top right back point

                //Back right Face
                width,  0.0f,   -depth, //bottom back right point
                width,  0.0f,   -depth, //bottom right back point
                width,  0.0f,   -depth, //top right back point

                width,  0.0f,   -depth, //top right back point
                width,  0.0f,   -depth, //bottom back right point
                width,  0.0f,   -depth, //top back right point

                //Top Face
                0.0f,   height, 0.0f,   //back left point
                0.0f,   height, 0.0f,   //left back point
                0.0f,   height, 0.0f,   //left front point

                0.0f,   height, 0.0f,   //left front point
                0.0f,   height, 0.0f,   //back left point
                0.0f,   height, 0.0f,   //front left point

                0.0f,   height, 0.0f,   //back left point
                0.0f,   height, 0.0f,   //front left point
                0.0f,   height, 0.0f,   //front right point

                0.0f,   height, 0.0f,   //front right point
                0.0f,   height, 0.0f,   //back left point
                0.0f,   height, 0.0f,   //back right point

                0.0f,   height, 0.0f,   //front right point
                0.0f,   height, 0.0f,   //right front point
                0.0f,   height, 0.0f,   //right back point

                0.0f,   height, 0.0f,   //right back point
                0.0f,   height, 0.0f,   //front right point
                0.0f,   height, 0.0f,   //back right point

        };

        return COL_NORMALS;
    }

    public Column(Float x, Float y, Float z) {

        float r, g, b, a = 1.0f;
        // FINSIH!

    }


    public Column(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        pages = 28;
        verticesPerPage = 3;
        vCount = 84;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelGeom = new float[16];
        modelViewProjection = new float[16];
        modelPosition = new float[] {x, y, z}; //TODO: Look for fourth param
        // TODO: Build constructors so we dont need to set static coords for every single cube. DONE so far
        px = x;
        py = y;
        pz = z;
        this.width = width;
        this.height = height;
        this.depth = depth;

        this.geomVertics = setColumnCoords(x, y, z, width, height, depth);// Init Tetra Coords on first call
        this.geomNormals = setColumnNormals(width,height,depth);
        setGeomColors(setInitColor(r, g, b, a)); // Init Column Colors on first call

        setFbGeomColors(colorFloatBuffer());
        this.fbGeomNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        this.fbGeomVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices
        this.updateModelPosition();



    }
}
