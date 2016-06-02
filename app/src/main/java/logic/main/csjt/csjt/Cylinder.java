package logic.main.csjt.csjt;

/**
 * Created by JB on 31.05.2016.
 */
public class Cylinder extends JustForCylinder{

    private static final double TWOPI = 2 * Math.PI;
    //Anzahl der Scheiben des Rings
    int slices;
    //Radius
    double radius;
    private float[] verticesAux = new float[3];
    //Höhe
    private double height;
    //Radius der Basis des Zylinders, wenn =0 erhält man einen Kegel, wenn =1 erhält man einen Zylinder
    private double base;

    public Cylinder(){
        super();
        height = 2;
        slices = 5;
        radius = 1;
        base = 1;
        initBuffAndPutV();
    }

    public Cylinder(double base, double radius, double height, int slices){
        super();
        this.radius = radius > 0.0 ? radius : 0.1;
        this.height = height > 0.0 ? height : 0.1;
        this.slices = slices > 2 ? slices : 3;
        this.base = base >= 0.0 ? base : 0.0;
        initBuffAndPutV();
    }

    private void initBuffAndPutV() {
        vertexNumber = slices *2+2;
        bufferSize = slices * slices * 4 * 4;
        initBuffer();
        putVertex();
    }

    @Override
    public void putVertex() {
        double Theta = 0;
        for (int i = 0; i < slices; i++) {

            for (int j = 0; j < 2; j++)
            {
                verticesAux[0] = (float) (base * radius * Math.cos(Theta));
                verticesAux[1] = (float) (0);
                verticesAux[2] = (float) (base * radius * Math.sin(Theta));
                vertexBuffer.put(verticesAux);

                verticesAux[0] = (float) (radius * Math.cos(Theta));
                verticesAux[1] = (float) (height);
                verticesAux[2] = (float) (radius * Math.sin(Theta));
                vertexBuffer.put(verticesAux);

                Theta = Theta + (TWOPI / slices);
            }
        }
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius > 0.0 ? radius : 0.1;
        initBuffAndPutV();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height > 0.0 ? height : 0.1;
        initBuffAndPutV();
    }

    public int getSlices() {
        return slices;
    }

    public void setSlices(int slices) {
        this.slices = slices > 2 ? slices : 3;
        initBuffAndPutV();
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base >= 0.0 ? base : 0.0;
        initBuffAndPutV();
    }
}

