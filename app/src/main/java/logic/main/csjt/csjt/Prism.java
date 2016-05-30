package logic.main.csjt.csjt;

/**
 * Created by Pygentrix on 20.05.2016.
 */

public class Prism extends Geom{

    //SETTERS
    public float[] setPrismCoords(float px,float py,float pz ,float width,float height){

        //TODO: Implement it correct, some rendering fuck ups. Still not correct

        float[] PRISM_COORDS = new float[]{
                // Top face
                px, py+height, pz,
                px+width, py+height, pz,
                px+(width/2), py+height, pz+(width/2),
                //TODO: pz+(depth/2)

                // Bottom face
                px, py, pz,
                px+width, py, pz,
                px+(width/2), py, (pz+width/2),
                //TODO: pz+(depth/2)

                // Back face
                px, py, pz,
                px+width, py, pz,
                px, py+height, pz,
                px+width, py+height, pz,


                // Left face
                px, py, pz,
                px+(width/2), py, pz+(width/2),
                //TODO: pz+(depth/2)
                px, py+height, pz,
                px+(width/2), py+height, pz+(width/2),
                //TODO: pz+(depth/2)


                //TODO: Right face
                px+width, py, pz,
                px+(width/2), py, pz+(width/2),
                //TODO: pz+(depth/2)
                px+width, py+height, pz,
                px+(width/2), py+height, pz+(width/2),

        };

        return PRISM_COORDS;
    }


    public float[] setPrismNormals(float width, float height){

        float[] PRISM_NORMALS = new float[]{
                //Top Face
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,
                0.0f, height, 0.0f,


                // Bottom Face
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f,
                0.0f, -height, 0.0f,


                // Back face
                0.0f, 0.0f, -width,
                0.0f, 0.0f, -width,
                0.0f, 0.0f, -width,
                0.0f, 0.0f, -width,
                //TODO: use -depth instead of -width


                // Left Face (evtl - bie z achse)
                -width, 0.0f, width,
                -width, 0.0f, width,
                -width, 0.0f, width,
                -width, 0.0f, width,
                //TODO: replace ^^ with depth


                // Right Face
                width, 0.0f, width,
                width, 0.0f, width,
                width, 0.0f, width,
                width, 0.0f, width,
                //TODO: replace ^^ with depth
                
        };

        return PRISM_NORMALS;
    }


    //FUNCTIONS / METHODS

    //CONSTRUCTOR(S)
    public Prism(Float x, Float y, Float z) {

        float r, g, b, a = 1.0f;
        //TODO FINSIH!

    }
    public Prism(float x, float y, float z,float width,float height, float r, float g, float b, float a) {

        pages = 3;
        verticesPerPage = 6;
        vCount = 18;
        selectedGeomColors = setSelectedGeomColors();
        setFbSelectedGeomColors(setSelectedColorFloatBuffer());
        modelGeom = new float[16];
        modelViewProjection = new float[16];
        modelPosition = new float[] {x, y, z};
        // TODO: Build constructors so we dont need to set static coords for every single prism. DONE so far
        px = x;
        py = y;
        pz = z;
        this.width = width;
        this.height = height;


        this.geomVertics = setPrismCoords(x, y, z, width, height);// Init Tetra Coords on first call
        this.geomNormals = setPrismNormals(width,height);
        setGeomColors(setInitColor(r, g, b, a)); // Init Tetra Colors on first call

        setFbGeomColors(colorFloatBuffer());
        this.fbGeomNormals = normalsFloatBuffer(); // NORMALS OF CUBE -> Normals
        this.fbGeomVertics = verticsFloatBuffer();  // COORDS OF CUBE -> Vertices

        this.updateModelPosition();



    }

}
