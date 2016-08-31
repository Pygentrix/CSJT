package logic.main.csjt.csjt;


public class TextObject { //this class holds the text, coordinates and the color as variables
    public String text;
    public float x;
    public float y;
    public float[] color;



    public TextObject(String txt, float xcoord, float ycoord)
    {
        text = txt;
        x = xcoord;
        y = ycoord;
        color = new float[] {1f, 1f, 1f, 1.0f};
    }

    public TextObject(String txt, float xcoord, float ycoord,float r,float g,float b, float a){

        this(txt,xcoord,ycoord);
        color = new float[] {r, g, b, a};

    }
}
