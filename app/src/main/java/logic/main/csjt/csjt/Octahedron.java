package logic.main.csjt.csjt;

/**
 * Created by JB on 03.06.2016.
 */
public class Octahedron extends Geom {
    public float[] setOctaCoords(float px, float py, float pz, float width, float height, float depth) {


        float[] OCTA_COORDS = new float[]{

                //Back Top
                px, py, pz,                                       //back left point //just for me: BLUE
                px + width, py, pz,                               //back right point //just for me: RED
                px + (width / 2), py + height, pz + (depth / 2),  //top //just for me: ORANGE

                //Left Top
                px, py, pz,                                      //back left point //just for me: BLUE
                px, py, pz + depth,                              //front left point  //just for me: YELLOW
                px + (width / 2), py + height, pz + (depth / 2), //top //just for me: ORANGE

                //Front Top
                px, py, pz + depth,                              //front left point //just for me: YELLOW
                px + width, py, pz + depth,                      //front right point //just for me: GREEN
                px + (width / 2), py + height, pz + (depth / 2), //top //just for me: ORANGE

                //Right Top
                px + width, py, pz,                              //back right point //just for me: RED
                px + width, py, pz + depth,                      //front right point //just for me: GREEN
                px + (width / 2), py + height, pz + (depth / 2), //top //just for me: ORANGE

                //Front Bottom
                px, py, pz + depth,                               //front left point //just for me: YELLOW
                px + width, py, pz + depth,                       //front right point //just for me: GREEN
                px - (width / 2), py - height, pz - (depth / 2),  //bottom //just for me: BLACK

                //Right Bottom
                px + width, py, pz,                               //back right point //just for me: RED
                px + width, py, pz + depth,                       //front right point //just for me: GREEN
                px - (width / 2), py - height, pz - (depth / 2),  //bottom //just for me: BLACK

                //Left Bottom
                px, py, pz,                                      //back left point //just for me: BLUE
                px, py, pz + depth,                              //front left point  //just for me: YELLOW
                px - (width / 2), py - height, pz - (depth / 2), //bottom //just for me: BLACK

                //Back Bottom
                px, py, pz,                                      //back left point //just for me: BLUE
                px + width, py, pz,                              //back right point //just for me: RED
                px - (width / 2), py - height, pz - (depth / 2), //bottom //just for me: BLACK
        };

        return OCTA_COORDS;
    }

    public float[] setOctaNormals(float width, float height, float depth) {

        //Normals are constructed similar to the coordinates
        float[] OCTA_NORMALS = new float[]{

                //Back Top
                0.0f, height, -2 * depth,   //back left point //BLUE
                0.0f, height, -2 * depth,   //back right point //RED
                0.0f, height, -2 * depth,   //top //ORANGE

                //Left Top
                -2 * width, height, 0.0f,   //back left point //BLUE
                -2 * width, height, 0.0f,   //front left point //YELLOW
                -2 * width, height, 0.0f,   //top //ORANGE

                //Front Top
                0.0f, height, 2 * depth,    //front left point //YELLOW
                0.0f, height, 2 * depth,    //front right point //GREEN
                0.0f, height, 2 * depth,    //top //ORANGE

                //Right Top
                2 * width, height, 0.0f,    //back right point //RED
                2 * width, height, 0.0f,    //front right point //GREEN
                2 * width, height, 0.0f,    //top //ORANGE

                //Back Bottom
                0.0f, -height, -2 * depth,   //back left point //BLUE
                0.0f, -height, -2 * depth,   //back right point //RED
                0.0f, -height, -2 * depth,   //top //ORANGE

                //Left Bottom
                -2 * width, -height, 0.0f,   //back left point //BLUE
                -2 * width, -height, 0.0f,   //front left point //YELLOW
                -2 * width, -height, 0.0f,   //top //ORANGE

                //Front Bottom
                0.0f, -height, 2 * depth,    //front left point //YELLOW
                0.0f, -height, 2 * depth,    //front right point //GREEN
                0.0f, -height, 2 * depth,    //top //ORANGE

                //Right Bottom
                2 * width, -height, 0.0f,    //back right point //RED
                2 * width, -height, 0.0f,    //front right point //GREEN
                2 * width, -height, 0.0f,    //top //ORANGE

        };

        return OCTA_NORMALS;
    }

    public Octahedron(Float x, Float y, Float z) {

        float r, g, b, a = 1.0f;

    }

    public Octahedron(float x, float y, float z, float width, float height, float depth, float r, float g, float b, float a) {

        pages = 8;
        verticesPerPage = 3;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelGeom = new float[16];
        modelViewProjection = new float[16];
        modelPosition = new float[]{x, y, z};
        px = x;
        py = y;
        pz = z;
        this.width = width;
        this.height = height;
        this.depth = depth;

        this.geomVertics = setOctaCoords(x, y, z, width, height, depth);
        this.geomNormals = setOctaNormals(width, height, depth);
        setGeomColors(setInitColor(r, g, b, a));

        setFbGeomColors(colorFloatBuffer());
        this.fbGeomNormals = normalsFloatBuffer();
        this.fbGeomVertics = verticsFloatBuffer();


    }

}
