package logic.main.csjt.csjt;

/**
 * Created by JB on 03.06.2016.
 */
public class Octahedron extends Geom{
    public float[] setOctaCoords(float px,float py,float pz ,float width, float height, float depth){


        float[] OCTA_COORDS = new float[]{

                //Back Top
                px,         py,         pz,         //back left point //just for me: BLUE
                px+width,   py,         pz,         //back right point //just for me: RED
                px+(width/2), py+height, pz+(depth/2), //top //just for me: ORANGE

                //Left Top
                px,         py,         pz,         //back left point //just for me: BLUE
                px,         py,         pz+depth,   //front left point  //just for me: YELLOW
                px+(width/2), py+height, pz+(depth/2), //top //just for me: ORANGE

                //Front Top
                px,         py,         pz+depth,   //front left point //just for me: YELLOW
                px+width,   py,         pz+depth,   //front right point //just for me: GREEN
                px+(width/2), py+height, pz+(depth/2), //top //just for me: ORANGE

                //Right Top
                px+width,   py,         pz,         //back right point //just for me: RED
                px+width,   py,         pz+depth,   //front right point //just for me: GREEN
                px+(width/2), py+height, pz+(depth/2), //top //just for me: ORANGE

                //Front Bottom
                px,         py,         pz+depth,   //front left point //just for me: YELLOW
                px+width,   py,         pz+depth,   //front right point //just for me: GREEN
                px-(width/2), py-height, pz-(depth/2), //bottom //just for me: BLACK

                //Right Bottom
                px+width,   py,         pz,         //back right point //just for me: RED
                px+width,   py,         pz+depth,   //front right point //just for me: GREEN
                px-(width/2), py-height, pz-(depth/2), //bottom //just for me: BLACK

                //Left Bottom
                px,         py,         pz,         //back left point //just for me: BLUE
                px,         py,         pz+depth,   //front left point  //just for me: YELLOW
                px-(width/2), py-height, pz-(depth/2), //bottom //just for me: BLACK

                //Back Bottom
                px,         py,         pz,         //back left point //just for me: BLUE
                px+width,   py,         pz,         //back right point //just for me: RED
                px-(width/2), py-height, pz-(depth/2), //bottom //just for me: BLACK
        };

        return OCTA_COORDS;
    }
}
