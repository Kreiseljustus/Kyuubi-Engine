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
    private Vector3f m_Position;

    public EditorCamera(float fov, float aspectRatio, float nearClip, float farClip)
    {

    }

    public void OnUpdate(TimeStep ts)
    {

    }


}
