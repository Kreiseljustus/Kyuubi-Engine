package justuswalterhelk.KyuubiForge.core;

public class ApplicationSpecification
{
    private String m_Name = "KyuubiForge (Development Version)";
    private String m_WorkingDirectory = System.getProperty("user.dir");
    private ApplicationCommandLineArgs m_CommandLineArgs;

    private int m_Width = 1080;
    private int m_Height = 1920;

    private boolean m_FullScreen = true;

    public String getName()
    {
        return m_Name;
    }

    public String getWorkingDirectory()
    {
        return m_WorkingDirectory;
    }

    public int getWidth() {return m_Width;}
    public int getHeight() {return m_Height;}

    public boolean isFullScreen() {return m_FullScreen;}

    public ApplicationCommandLineArgs getCommandLineArgs()
    {
        return m_CommandLineArgs;
    }

    public void SetName(String name) {m_Name = name;}
    public void SetWorkingDirectory(String workingDirectory) {m_WorkingDirectory = workingDirectory;}
    public void SetCommandLineArgs(ApplicationCommandLineArgs commandLineArgs) {m_CommandLineArgs = commandLineArgs;}
    public void SetWidth(int width) {m_Width = width;}
    public void SetHeight(int height) {m_Height = height;}
    public void SetFullScreen(boolean fullScreen) {m_FullScreen = fullScreen;}
}
