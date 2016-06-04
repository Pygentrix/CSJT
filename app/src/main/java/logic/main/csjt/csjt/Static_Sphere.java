package logic.main.csjt.csjt;

import java.lang.Math;

/**
 * Created by Thundergrass on 04.06.2016.
 */
class Static_Sphere extends Geom {

    private float[] setSphereCoords(float px,float py,float pz ,float width, float height, float depth){

        /**
         * 04.06.2016: Spheres are a bit harder to define than other shapes.
         *      I'll construct the faces from the bottom to the top, counterclockwise, starting at the back (or slightly left from it)
         *      Wish me luck!
         *
         */
        /////////
        //helper variables
        /////////
        //halved lengths
        float wid = width / 2;
        float hei = height / 2;
        float dep = depth / 2;
        //Square root of 2
        float tw = (float)Math.sqrt(2);
        //layers of x
        float xLay1 = px;
        float xLay2 = px + wid - (0.5f * tw * wid);
        float xLay3 = px + wid;
        float xLay4 = px + wid + (0.5f * tw * wid);
        float xLay5 = px + width;
        //layers of y
        float yLay1 = py;
        float yLay2 = py + hei - (0.5f * tw * hei);
        float yLay3 = py + hei;
        float yLay4 = py + hei + (0.5f * tw * hei);
        float yLay5 = py + height;
        //layers of z
        float zLay1 = pz;
        float zLay2 = pz + dep - (0.5f * tw * dep);
        float zLay3 = pz + dep;
        float zLay4 = pz + dep + (0.5f * tw * dep);
        float zLay5 = pz + depth;

        return new float[]{

                ////LAYER ONE
                //FACE 1
                xLay3, yLay1, zLay3, //bottommost point
                xLay3, yLay2, zLay2,
                xLay2, yLay2, zLay3,

                //FACE 2
                xLay3, yLay1, zLay3, //bottommost point
                xLay2, yLay2, zLay3,
                xLay3, yLay2, zLay4,

                //FACE 3
                xLay3, yLay1, zLay3, //bottommost point
                xLay3, yLay2, zLay4,
                xLay4, yLay2, zLay3,

                //FACE 4
                xLay3, yLay1, zLay3, //bottommost point
                xLay4, yLay2, zLay3,
                xLay3, yLay2, zLay2,

                ////LAYER TWO
                //FACE 5
                xLay3, yLay2, zLay2,
                xLay3, yLay3, zLay1,
                xLay2, yLay3, zLay2,

                //FACE 6
                xLay3, yLay2, zLay2,
                xLay2, yLay3, zLay2,
                xLay2, yLay2, zLay3,

                //FACE 7
                xLay2, yLay3, zLay2,
                xLay2, yLay2, zLay3,
                xLay1, yLay3, zLay3,

                //FACE 8
                xLay1, yLay3, zLay3,
                xLay2, yLay2, zLay3,
                xLay2, yLay3, zLay4,

                //FACE 9
                xLay2, yLay2, zLay3,
                xLay2, yLay3, zLay4,
                xLay3, yLay2, zLay4,

                //FACE 10
                xLay2, yLay3, zLay4,
                xLay3, yLay2, zLay4,
                xLay3, yLay3, zLay5,

                //TODO: Experiment
                /**
                 * What I did: I mirrored the other half. Not very systematic, but hopefully, it will work
                */
                //FACE 11
                xLay3, yLay2, zLay2,
                xLay3, yLay3, zLay1,
                xLay4, yLay3, zLay2,

                //FACE 12
                xLay3, yLay2, zLay2,
                xLay4, yLay3, zLay2,
                xLay4, yLay2, zLay3,

                //FACE 13
                xLay4, yLay3, zLay2,
                xLay4, yLay2, zLay3,
                xLay5, yLay3, zLay3,

                //FACE 14
                xLay5, yLay3, zLay3,
                xLay4, yLay2, zLay3,
                xLay4, yLay3, zLay4,

                //FACE 15
                xLay4, yLay2, zLay3,
                xLay4, yLay3, zLay4,
                xLay3, yLay2, zLay4,

                //FACE 16
                xLay4, yLay3, zLay4,
                xLay3, yLay2, zLay4,
                xLay3, yLay3, zLay5,

                ////LAVER THREE
                //TODO: Experiment again by mirroring Layer two

                //FACE 17
                xLay3, yLay4, zLay2,
                xLay3, yLay3, zLay1,
                xLay2, yLay3, zLay2,

                //FACE 18
                xLay3, yLay4, zLay2,
                xLay2, yLay3, zLay2,
                xLay2, yLay4, zLay3,

                //FACE 19
                xLay2, yLay3, zLay2,
                xLay2, yLay4, zLay3,
                xLay1, yLay3, zLay3,

                //FACE 20
                xLay1, yLay3, zLay3,
                xLay2, yLay4, zLay3,
                xLay2, yLay3, zLay4,

                //FACE 21
                xLay2, yLay4, zLay3,
                xLay2, yLay3, zLay4,
                xLay3, yLay4, zLay4,

                //FACE 22
                xLay2, yLay3, zLay4,
                xLay3, yLay4, zLay4,
                xLay3, yLay3, zLay5,

                //FACE 23
                xLay3, yLay4, zLay2,
                xLay3, yLay3, zLay1,
                xLay4, yLay3, zLay2,

                //FACE 24
                xLay3, yLay4, zLay2,
                xLay4, yLay3, zLay2,
                xLay4, yLay4, zLay3,

                //FACE 25
                xLay4, yLay3, zLay2,
                xLay4, yLay4, zLay3,
                xLay5, yLay3, zLay3,

                //FACE 26
                xLay5, yLay3, zLay3,
                xLay4, yLay4, zLay3,
                xLay4, yLay3, zLay4,

                //FACE 27
                xLay4, yLay4, zLay3,
                xLay4, yLay3, zLay4,
                xLay3, yLay4, zLay4,

                //FACE 28
                xLay4, yLay3, zLay4,
                xLay3, yLay4, zLay4,
                xLay3, yLay3, zLay5,

                ////LAVER FOUR
                //TODO: Guess what? Do it again

                //FACE 29
                xLay3, yLay5, zLay3, //topmost point
                xLay3, yLay4, zLay2,
                xLay2, yLay4, zLay3,

                //FACE 30
                xLay3, yLay5, zLay3, //topmost point
                xLay2, yLay4, zLay3,
                xLay3, yLay4, zLay4,

                //FACE 31
                xLay3, yLay5, zLay3, //topmost point
                xLay3, yLay4, zLay4,
                xLay4, yLay4, zLay3,

                //FACE 32
                xLay3, yLay5, zLay3, //topmost point
                xLay4, yLay4, zLay3,
                xLay3, yLay4, zLay2,

        };

    }

    private float[] setSphereNormals(float width, float height, float depth){

        /**
         * This is where stuff gets really complicated
         * Idea: Make two sets of normals to see how they react:
         *      - First set are the normals of the sides
         *      - Second set are the normals of the points (straight away from the middle)
         */
        //helper variables
        float tw = (float)Math.sqrt(2);
        float lwid = tw * (width / 4);
        float swid = (width / 2) - lwid;
        float lhei = tw * (height / 4);
        float shei = (height / 2) - lhei;
        float ldep = tw * (depth / 4);
        float sdep = (depth / 2) - ldep;

        return new float[]{

                ////LAYER ONE
                //FACE 1
                -swid, -lhei, -sdep, //bottommost point
                -swid, -lhei, -sdep,
                -swid, -lhei, -sdep,

                //FACE 2
                -swid, -lhei, sdep, //bottommost point
                -swid, -lhei, sdep,
                -swid, -lhei, sdep,

                //FACE 3
                swid, -lhei, sdep, //bottommost point
                swid, -lhei, sdep,
                swid, -lhei, sdep,

                //FACE 4
                swid, -lhei, -sdep, //bottommost point
                swid, -lhei, -sdep,
                swid, -lhei, -sdep,

                ////LAYER TWO
                //FACE 5
                -swid, -shei, -ldep,
                -swid, -shei, -ldep,
                -swid, -shei, -ldep,

                //FACE 6
                -swid, -shei, -sdep,
                -swid, -shei, -sdep,
                -swid, -shei, -sdep,

                //FACE 7
                -lwid, -shei, -sdep,
                -lwid, -shei, -sdep,
                -lwid, -shei, -sdep,

                //FACE 8
                -lwid, -shei, sdep,
                -lwid, -shei, sdep,
                -lwid, -shei, sdep,

                //FACE 9
                -swid, -shei, sdep,
                -swid, -shei, sdep,
                -swid, -shei, sdep,

                //FACE 10
                -swid, -shei, ldep,
                -swid, -shei, ldep,
                -swid, -shei, ldep,

                //FACE 11
                swid, -shei, -ldep,
                swid, -shei, -ldep,
                swid, -shei, -ldep,

                //FACE 12
                swid, -shei, -sdep,
                swid, -shei, -sdep,
                swid, -shei, -sdep,

                //FACE 13
                lwid, -shei, -sdep,
                lwid, -shei, -sdep,
                lwid, -shei, -sdep,

                //FACE 14
                lwid, -shei, sdep,
                lwid, -shei, sdep,
                lwid, -shei, sdep,

                //FACE 15
                swid, -shei, sdep,
                swid, -shei, sdep,
                swid, -shei, sdep,

                //FACE 16
                swid, -shei, ldep,
                swid, -shei, ldep,
                swid, -shei, ldep,

                ////LAYER THREE
                //FACE 17
                -swid, shei, -ldep,
                -swid, shei, -ldep,
                -swid, shei, -ldep,

                //FACE 18
                -swid, shei, -sdep,
                -swid, shei, -sdep,
                -swid, shei, -sdep,

                //FACE 19
                -lwid, shei, -sdep,
                -lwid, shei, -sdep,
                -lwid, shei, -sdep,

                //FACE 20
                -lwid, shei, sdep,
                -lwid, shei, sdep,
                -lwid, shei, sdep,

                //FACE 21
                -swid, shei, sdep,
                -swid, shei, sdep,
                -swid, shei, sdep,

                //FACE 22
                -swid, shei, ldep,
                -swid, shei, ldep,
                -swid, shei, ldep,

                //FACE 23
                swid, shei, -ldep,
                swid, shei, -ldep,
                swid, shei, -ldep,

                //FACE 24
                swid, shei, -sdep,
                swid, shei, -sdep,
                swid, shei, -sdep,

                //FACE 25
                lwid, shei, -sdep,
                lwid, shei, -sdep,
                lwid, shei, -sdep,

                //FACE 26
                lwid, shei, sdep,
                lwid, shei, sdep,
                lwid, shei, sdep,

                //FACE 27
                swid, shei, sdep,
                swid, shei, sdep,
                swid, shei, sdep,

                //FACE 28
                swid, shei, ldep,
                swid, shei, ldep,
                swid, shei, ldep,

                ////LAVER FOUR
                //FACE 29
                -swid, lhei, -sdep, //topmost point
                -swid, lhei, -sdep,
                -swid, lhei, -sdep,

                //FACE 30
                -swid, lhei, sdep, //topmost point
                -swid, lhei, sdep,
                -swid, lhei, sdep,

                //FACE 31
                swid, lhei, sdep, //topmost point
                swid, lhei, sdep,
                swid, lhei, sdep,

                //FACE 32
                swid, lhei, -sdep, //topmost point
                swid, lhei, -sdep,
                swid, lhei, -sdep,

        };

    }

    Static_Sphere(Float x, Float y, Float z) {
        super(x,y,z);
        float r, g, b, a = 1.0f;
        // FINSIH!

    }


    Static_Sphere(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        super(x,y,z);
        pages = 32;
        verticesPerPage = 3;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.geomVertics = setSphereCoords(x, y, z, width, height, depth);// Init Sphere Coords on first call
        this.geomNormals = setSphereNormals(width,height,depth);
        setGeomColors(setInitColor(r, g, b, a)); // Init Sphere Colors on first call

        setFbGeomColors(colorFloatBuffer());
        this.fbGeomNormals = normalsFloatBuffer(); // NORMALS OF SPHERE -> Normals
        this.fbGeomVertics = verticsFloatBuffer();  // COORDS OF SPHERE -> Vertices
        this.updateModelPosition();


    }

    void initialRotCorrection() {

        float[] lModelGeom = this.getModelGeom();
        //Matrix.rotateM(lModelGeom, 0,0, this.modelPosition[0],this.modelPosition[1],this.modelPosition[2]);
        this.setModelGeom(lModelGeom);
    }
}
