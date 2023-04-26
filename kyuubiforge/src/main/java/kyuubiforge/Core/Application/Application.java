package kyuubiforge.Core.Application;

import kyuubiforge.Core.Window.Window;
import static kyuubiforge.Debug.Debug.log;
import static org.lwjgl.glfw.GLFW.*;

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

    public float beginTime;
    public float endTime;
    public float dt = -1f;

    public void run()
    {
        log("[KyuubiEditor] Initialized main window");

        beginTime = (float)glfwGetTime();
        dt = -1.0f;

        while(running)
        {
            if(dt >= 0)
            {
                window.Update(dt);
            }
            if(running) {
                endTime = (float) glfwGetTime();
                dt = endTime - beginTime;
                beginTime = endTime;
            }
        }
    }


}
