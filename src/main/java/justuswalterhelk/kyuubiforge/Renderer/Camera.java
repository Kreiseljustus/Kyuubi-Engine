package justuswalterhelk.kyuubiforge.Renderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class Camera
{
    protected Matrix4f projectionMatrix = new Matrix4f();
    protected Matrix4f viewMatrix = new Matrix4f();
    public Vector3f position = new Vector3f();
}
