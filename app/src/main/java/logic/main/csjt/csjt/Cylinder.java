package logic.main.csjt.csjt;

/**
 * Created by JB on 31.05.2016.
 */
public class Cylinder {
    /*
    float steps = (float) ((Math.PI * 2) / parts);

    vertex = new float[parts * 6 + 12];



    for (int i = 0; i <= parts; i++) {
        float sin = (float) Math.sin(i * steps);
        float cos = (float) Math.cos(i * steps);
// Hintere Seite des Zylinders
        vertex[i * 3] = cos;
        vertex[i * 3 + 1] = sin;
        vertex[i * 3 + 2] = -0.5f;
// Vordere Seite des Zylinders
        vertex[i * 3 + parts * 3 + 3] = cos;
        vertex[i * 3 + parts * 3 + 4] = sin;
        vertex[i * 3 + parts * 3 + 5] = 0.5f;
    }
// Mitte der beiden Kreise
    vertex[parts * 6 + 6] = 0;
    vertex[parts * 6 + 7] = 0;
    vertex[parts * 6 + 8] = -0.5f;
    vertex[parts * 6 + 9] = 0;
    vertex[parts * 6 + 10] = 0;
    vertex[parts * 6 + 11] = 0.5f;

    // Buffer für die Punkte
ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length *
4);
vbb.order(ByteOrder.nativeOrder());
vertexBuffer = vbb.asFloatBuffer();
vertexBuffer.put(vertex);
vertexBuffer.position(0);
gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

*/
}
