package justuswalterhelk.KyuubiForge.Core;

import justuswalterhelk.KyuubiForge.UI.TestContainer;
import justuswalterhelk.KyuubiForge.UI.Window;


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

        window = new Window(specification.getWidth(), specification.getHeight(), specification.getName(), specification.isFullScreen());

        //Initalize Renderer

        //Create ImGui Layer
    }

    public static Application get() { return instance;}

    public void onClose()
    {
        System.out.println("[KyuubiEditor] Shutting down update loop");
        running = false;
        //Shutdown Renderer
    }

    public void run()
    {
        window.initWindow().addContainer(new TestContainer()).initContainers();
        System.out.println("[KyuubiEditor] Initialized main window");

        while(running)
        {
            window.Update();
        }
    }


}
