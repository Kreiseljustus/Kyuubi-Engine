package justuswalterhelk.KyuubiForge.core;

public class ApplicationCommandLineArgs
{
    private int m_Count = 0;
    private String[] m_Args = null;

    public String get(int index)
    {
        return m_Args[index];
    }
}
