package logic.main.csjt.csjt;

/**
 * Created by JB on 03.06.2016.
 */
public class Octahedron extends Geom {
    public float[] setOctaCoords(float px, float py, float pz, float width, float height, float depth) {


        return new float[]{

                //Back Top
                px, py, pz,                                       //back left point //just for me: BLUE
                px + width, py, pz,                               //back right point //just for me: RED
                px + (width / 2.0f), py + (height/1.5f), pz + (depth / 2.0f),  //top //just for me: ORANGE

                //Left Top
                px, py, pz,                                      //back left point //just for me: BLUE
                px, py, pz + depth,                              //front left point  //just for me: YELLOW
                px + (width / 2.0f), py + (height/1.5f), pz + (depth / 2.0f), //top //just for me: ORANGE

                //Front Top
                px, py, pz + depth,                              //front left point //just for me: YELLOW
                px + width, py, pz + depth,                      //front right point //just for me: GREEN
                px + (width / 2.0f), py + (height/1.5f), pz + (depth / 2.0f), //top //just for me: ORANGE

                //Right Top
                px + width, py, pz,                              //back right point //just for me: RED
                px + width, py, pz + depth,                      //front right point //just for me: GREEN
                px + (width / 2.0f), py + (height/1.5f), pz + (depth / 2.0f), //top //just for me: ORANGE

                //Front Bottom
                px, py, pz + depth,                               //front left point //just for me: YELLOW
                px + width, py, pz + depth,                       //front right point //just for me: GREEN
                px + (width / 2.0f), py - (height/1.5f), pz + (depth / 2.0f),  //bottom //just for me: BLACK

                //Right Bottom
                px + width, py, pz,                               //back right point //just for me: RED
                px + width, py, pz + depth,                       //front right point //just for me: GREEN
                px + (width / 2.0f), py - (height/1.5f), pz + (depth / 2.0f),  //bottom //just for me: BLACK

                //Left Bottom
                px, py, pz,                                      //back left point //just for me: BLUE
                px, py, pz + depth,                              //front left point  //just for me: YELLOW
                px + (width / 2.0f), py - (height/1.5f), pz + (depth / 2.0f), //bottom //just for me: BLACK

                //Back Bottom
                px, py, pz,                                      //back left point //just for me: BLUE
                px + width, py, pz,                              //back right point //just for me: RED
                px + (width / 2.0f), py - (height/1.5f), pz + (depth / 2.0f), //bottom //just for me: BLACK
        };
    }

    private float[] setOctaNormals(float width, float height, float depth) {

        //Normals are constructed similar to the coordinates

        return new float[]{

                //Back Top
                0.0f, height, -2.0f * depth,   //back left point //BLUE
                0.0f, height, -2.0f * depth,   //back right point //RED
                0.0f, height, -2.0f * depth,   //top //ORANGE

                //Left Top
                -2.0f * width, height, 0.0f,   //back left point //BLUE
                -2.0f * width, height, 0.0f,   //front left point //YELLOW
                -2.0f * width, height, 0.0f,   //top //ORANGE

                //Front Top
                0.0f, height, 2.0f * depth,    //front left point //YELLOW
                0.0f, height, 2.0f * depth,    //front right point //GREEN
                0.0f, height, 2.0f * depth,    //top //ORANGE

                //Right Top
                2.0f * width, height, 0.0f,    //back right point //RED
                2.0f * width, height, 0.0f,    //front right point //GREEN
                2.0f * width, height, 0.0f,    //top //ORANGE

                //Back Bottom
                0.0f, -height, -2.0f * depth,   //back left point //BLUE
                0.0f, -height, -2.0f * depth,   //back right point //RED
                0.0f, -height, -2.0f * depth,   //top //ORANGE

                //Left Bottom
                -2.0f * width, -height, 0.0f,   //back left point //BLUE
                -2.0f * width, -height, 0.0f,   //front left point //YELLOW
                -2.0f * width, -height, 0.0f,   //top //ORANGE

                //Front Bottom
                0.0f, -height, 2.0f * depth,    //front left point //YELLOW
                0.0f, -height, 2.0f * depth,    //front right point //GREEN
                0.0f, -height, 2.0f * depth,    //top //ORANGE

                //Right Bottom
                2.0f * width, -height, 0.0f,    //back right point //RED
                2.0f * width, -height, 0.0f,    //front right point //GREEN
                2.0f * width, -height, 0.0f,    //top //ORANGE

        };
    }

    public Octahedron(Float x, Float y, Float z) {

        super(x,y,z);
        float r, g, b, a = 1.0f;

    }

     Octahedron(float x, float y, float z, float width, float height, float depth, float r, float g, float b, float a) {

        super(x,y,z);
        pages = 8;
        verticesPerPage = 3;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());

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
