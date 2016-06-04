package logic.main.csjt.csjt;

import android.opengl.Matrix;

/**
 * Created by Paul Stonjek on 11.05.2016.
 * Uploaded to the Git by Pygentrix ( Due to setting problems )
 */
public class Tetrahedron extends Geom {

    public float[] setTetraCoords(float px,float py,float pz ,float width, float height, float depth){

        /**
         * px, py and pz describe the origin of the Tetrahedron, in our case its exact middle.
         * width, height and depth describe the dimensions of the surrounding cube. (The Tetrahedron fits perfectly inside this cube)
         * Construction of the Tetrahedron vertices according to this method: https://de.wikipedia.org/wiki/Tetraeder#Umgebender_W.C3.BCrfel
         */
        //TODO: Change all this to the Tetrahedron layout   EDIT: Done

        //helper variables
        float wid = width / 2;
        float hei = height / 2;
        float dep = depth / 2;

        float[] TETRA_COORDS = new float[]{
                //Face A
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py-hei, pz+dep,     //right bottom front point
                px-wid, py+hei, pz+dep,     //left top front point

                //Face B
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py-hei, pz+dep,     //right bottom front point
                px+wid, py+hei, pz-dep,     //right top back point

                //Face C
                px+wid, py-hei, pz+dep,     //right bottom front point
                px+wid, py+hei, pz-dep,     //right top back point
                px-wid, py+hei, pz+dep,     //left top front point

                //Face D
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py+hei, pz-dep,     //right top back point
                px-wid, py+hei, pz+dep,     //left top front point

                //TODO: Alternative mode: try at own risk
                /*
                px-wid, py-hei, pz-dep,     //left bottom back point
                px+wid, py-hei, pz+dep,     //right bottom front point
                px+wid, py+hei, pz-dep,     //right top back point
                px-wid, py+hei, pz+dep,     //left top front point
                 */

        };

        return TETRA_COORDS;
    }

    public float[] setTetraNormals(float width, float height, float depth){

        //Normals are constructed similar to the coordinates
        float[] TETRA_NORMALS = new float[]{
                //FACE A
                -width, -height, depth,
                -width, -height, depth,
                -width, -height, depth,

                //FACE B
                width, -height, -depth,
                width, -height, -depth,
                width, -height, -depth,

                //FACE C
                width, height, depth,
                width, height, depth,
                width, height, depth,

                //FACE D
                -width, height, -depth,
                -width, height, -depth,
                -width, height, -depth

        };

        return TETRA_NORMALS;
    }

    public Tetrahedron(Float x, Float y, Float z) {
        super(x,y,z);
        float r, g, b, a = 1.0f;
        // FINSIH!

    }


    public Tetrahedron(float x, float y, float z,float width,float height,float depth, float r, float g, float b, float a) {

        super(x,y,z);
        pages = 4;
        verticesPerPage = 3;
        vCount = pages * verticesPerPage;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());

        this.width = width;
        this.height = height;
        this.depth = depth;

        this.geomVertics = setTetraCoords(x, y, z, width, height, depth);// Init Tetra Coords on first call
        this.geomNormals = setTetraNormals(width,height,depth);
        setGeomColors(setInitColor(r, g, b, a)); // Init Tetra Colors on first call

        setFbGeomColors(colorFloatBuffer());
        this.fbGeomNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        this.fbGeomVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices
        this.updateModelPosition();


    }

    void initialRotCorrection() {

        float[] lModelGeom = this.getModelGeom();
        //Matrix.rotateM(lModelGeom, 0,0, this.modelPosition[0],this.modelPosition[1],this.modelPosition[2]);
        this.setModelGeom(lModelGeom);
    }

}