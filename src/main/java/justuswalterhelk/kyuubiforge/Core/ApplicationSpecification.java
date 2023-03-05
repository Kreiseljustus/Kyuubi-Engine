package justuswalterhelk.kyuubiforge.Core;

public class ApplicationSpecification
{
    public ApplicationSpecification(String name, WindowSpecification windowSpecification)
    {
        this.name = name;
        this.mainWindowSpecification = windowSpecification;
    }

    private String name = "KyuubiForge (Development Version)";
    private String workingDirectory = System.getProperty("user.dir");
    private ApplicationCommandLineArgs commandLineArgs;

    public WindowSpecification mainWindowSpecification = null;

    public String getName()
    {
        return name;
    }

    public String getWorkingDirectory()
    {
        return workingDirectory;
    }

    public ApplicationCommandLineArgs getCommandLineArgs()
    {
        return commandLineArgs;
    }

    public void setName(String name) {name = name;}
    public void setWorkingDirectory(String workingDirectory) {workingDirectory = workingDirectory;}
    public void setCommandLineArgs(ApplicationCommandLineArgs commandLineArgs) {commandLineArgs = commandLineArgs;}
}
