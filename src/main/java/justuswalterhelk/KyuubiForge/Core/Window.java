package justuswalterhelk.KyuubiForge.Core;

import justuswalterhelk.KyuubiForge.Input.KeyListener;
import justuswalterhelk.KyuubiForge.UI.Container;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/*
    This class needs to get reworked at some point.
    Maybe add Object that just contains the window settings

    Figure out FPS cap per window
 */
public class Window
{
    private List<Container> containers = new LinkedList<Container>();

    private static int windowNumber = 0;

    private WindowSpecification windowSpecification = null;

    public Window(WindowSpecification specification)
    {
        this.windowSpecification = specification;
    }

    public Window initWindow()
    {
        //Print errors to console
        GLFWErrorCallback.createPrint(System.err).set();

        //Initialize GLFW
        if(!glfwInit())
        {
            assert false : "[KyuubiForge] Failed to initialize GLFW! Restart required.";
            throw new IllegalStateException("Initializing GLFW failed!");
        }

        //Reset all hints to their default values
        glfwDefaultWindowHints();
        //Set the window properties
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, windowSpecification.isFullScreen ? 1:0);

        //For apple ARM silicone
        //glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        //glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        //glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        //TODO: Add monitor and share to constructor
        //Create the window and save its id in a long
        windowSpecification.windowID = glfwCreateWindow(windowSpecification.width,windowSpecification.height,windowSpecification.title, NULL,NULL);
        windowNumber += 1;
        if(windowSpecification.windowID == NULL)
        {
            assert false : "[KyuubiForge] Failed to create window!";
            throw new IllegalStateException("Failed to create window");
        }

        glfwSetKeyCallback(windowSpecification.windowID, KeyListener::keyCallback);

        //WARNING: Needs proper handling when dealing with multiple windows on the same Thread!
        glfwMakeContextCurrent(windowSpecification.windowID);

        //V-Sync
        glfwSwapInterval(1);

        //Show the window
        glfwShowWindow(windowSpecification.windowID);

        //IMPORTANT
        //Create all the needed opengl objects
        //TODO: Needs proper handling with multi threading
        GL.createCapabilities();

        return this;
    }

    public Window addContainer(Container container)
    {
        containers.add(container);
        System.out.println("Added " + container.containerName + " as a container to " + windowSpecification.windowID);

        return this;
    }

    public Window initContainers()
    {
        for(Container e : containers)
        {
            e.init();
        }

        return this;
    }

    float beginTime = Time.getTime();
    float endTime;
    float dt = -1.0f;

    public void Update()
    {
        //Can be called from any thread!
        //Process all events that are still in the queue
        glfwPollEvents();

        if(glfwWindowShouldClose(windowSpecification.windowID))
        {
            this.onClose();
            return;
        }

            //Clear color
        glClearColor(1.0f,1.0f,1.0f,1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        if (dt >= 0) {
                //Update?
            for (Container e : containers)
            {
                //System.out.println("[KyuubiForge] " + dt/1);
                e.update(dt);
            }
        }

            //Switches the buffers to display the next frame
        glfwSwapBuffers(windowSpecification.windowID);
        endTime = Time.getTime();
        dt = endTime - beginTime;
        beginTime = endTime;
    }


    public void onClose()
    {
        windowNumber -= 1;

        if(windowNumber <= 0) {
            Application.get().onClose();

            glfwFreeCallbacks(windowSpecification.windowID);
            glfwDestroyWindow(windowSpecification.windowID);

            System.out.println("[KyuubiForge] Destroyed window [" + windowSpecification.windowID + "]");

            glfwTerminate();
        }
        else
        {
            glfwFreeCallbacks(windowSpecification.windowID);
            glfwDestroyWindow(windowSpecification.windowID);

            System.out.println("[KyuubiForge] Destroyed window [" + windowSpecification.windowID + "]");
        }
    }
}
