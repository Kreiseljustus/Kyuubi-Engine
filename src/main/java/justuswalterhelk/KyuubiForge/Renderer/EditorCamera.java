package justuswalterhelk.KyuubiForge.Renderer;

import justuswalterhelk.KyuubiForge.Core.TimeStep;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class EditorCamera extends Camera
{
    private float m_FOV = 45.0f;
    private float m_AspectRatio = 1.778f;
    private float m_NearClip = 0.1f;
    private float m_FarClip = 1000.0f;

    private Matrix4f m_ViewMatrix;
    public Vector3f m_Position;

    private float m_Pitch = 0.0f;
    private float m_Yaw = 0.0f;

    private float m_ViewportWidth = 1280;
    private float m_ViewportHeight = 720;

    public EditorCamera(Vector3f position, float fov, float aspectRatio, float nearClip, float farClip)
    {
        this.m_Position = position;
        this.m_ProjectionMatrix = new Matrix4f();
        this.m_ViewMatrix = new Matrix4f();
    }

    public EditorCamera(Vector3f position)
    {
        this.m_Position = position;
        this.m_ProjectionMatrix = new Matrix4f();
        this.m_ViewMatrix = new Matrix4f();
    }

    public void OnUpdate(TimeStep ts)
    {

    }

    public void SetViewPortSize(float width, float height)
    {
        m_ViewportWidth = width;
        m_ViewportHeight = height;

        //Update Projection?

    }

    public Matrix4f GetViewMatrix()
    {
        Vector3f cameraFront = new Vector3f(0.0f,0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0.0f,1.0f,0.0f);
        this.m_ViewMatrix.identity();
        this.m_ViewMatrix = m_ViewMatrix.lookAt(new Vector3f(m_Position.x, m_Position.y, m_Position.z), cameraFront.add(m_Position.x,m_Position.y,m_Position.z), cameraUp);
        return m_ViewMatrix;
    }

    public Matrix4f GetViewProjection()
    {
        return null;
    }

    public Vector3f GetPosition()
    {
        return m_Position;
    }

    public float GetPitch()
    {
        return m_Pitch;
    }

    public float GetYaw()
    {
        return m_Yaw;
    }

    private void UpdateProjection()
    {
        m_ProjectionMatrix.identity();
        m_ProjectionMatrix.ortho(0.0f, m_ViewportWidth, 0.0f, m_ViewportHeight, m_NearClip, m_FarClip);
    }

    private void UpdateView()
    {

    }

    private Vector3f CalculatePosition()
    {
        return null;
    }

}
