package kyuubiforge.Renderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class EditorCamera extends Camera
{
    public Vector3f position;

    public EditorCamera(Vector3f position) {
        this.position = position;
        this.projectionMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection() {
        projectionMatrix.identity();
        projectionMatrix.ortho(0.0f, 1920, 0.0f, 1080, 0.1f, 1000.0f);
    }

    public Matrix4f getViewMatrix() {
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
        this.viewMatrix.identity();
        viewMatrix.lookAt(new Vector3f(position.x, position.y, position.z),
                cameraFront.add(position.x, position.y, 0),
                cameraUp);

        return this.viewMatrix;
    }

    public Matrix4f getProjectionMatrix() {
        return this.projectionMatrix;
    }
}