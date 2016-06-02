package logic.main.csjt.csjt;

import javax.microedition.khronos.opengles.GL10;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by JB on 02.06.2016.
 */
public abstract class JustForCylinder {

    protected int vertexNumber;
    protected int bufferSize;
    protected FloatBuffer vertexBuffer;
    protected int drawMode = GL10.GL_TRIANGLE_STRIP;

    public JustForCylinder() {

    }

    public void initBuffer() {
        ByteBuffer byteBuffer;
        byteBuffer = ByteBuffer.allocateDirect(bufferSize);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuffer.asFloatBuffer();
    }

    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        vertexBuffer.position(0);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glNormalPointer(GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawArrays(drawMode, 0, vertexNumber);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }

    public int getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(int drawMode) {
        this.drawMode = drawMode;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }


    public abstract void putVertex();
}



