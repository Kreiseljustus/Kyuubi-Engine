package kyuubiforge.Core.Application;

import kyuubiforge.Core.Window.Window;
import kyuubiforge.Core.Window.Container.TestContainer;
import static kyuubiforge.Debug.Debug.log;

/*
    This class needs to get reworked at some point
 */
public class Application
{
    private Window window = null;

    private final ApplicationSpecification applicationSpecification;
    public ApplicationSpecification getSpecification() {return applicationSpecification;}

    private static Application instance = null;

    public Window getWindow() { return window;}

    private boolean running = true;

    public Application(ApplicationSpecification specification)
    {
        instance = this;

        applicationSpecification = specification;

        window = new Window(specification.mainWindowSpecification);

        //Initalize Renderer

        //Create ImGui Layer
    }

    public static Application get() { return instance;}

    public void onClose()
    {
        log("[KyuubiEditor] Shutting down update loop");
        running = false;
        //Shutdown Renderer
    }

    public void run()
    {
        window.addContainer(new TestContainer()).initContainers();
        log("[KyuubiEditor] Initialized main window");

        while(running)
        {
            window.Update();
        }
    }


}
