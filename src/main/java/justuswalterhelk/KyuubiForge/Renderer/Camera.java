package justuswalterhelk.KyuubiForge.Renderer;

import org.joml.Matrix4f;

public abstract class Camera
{
    protected Matrix4f m_ProjectionMatrix = new Matrix4f();

    public Camera() {}
    public Camera(Matrix4f projection)
    {
        m_ProjectionMatrix = projection;
    }

    public Matrix4f GetProjection() { return m_ProjectionMatrix;}
}
