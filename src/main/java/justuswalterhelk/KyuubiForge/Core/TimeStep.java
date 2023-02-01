package justuswalterhelk.KyuubiForge.Core;

public class TimeStep
{
    private float m_Time = 0.0f;

    public TimeStep(float time)
    {
        m_Time = time;
    }

    public float GetSeconds() {return m_Time;}
    public float GetMilliSeconds() {return m_Time * 1000.0f;}
}
