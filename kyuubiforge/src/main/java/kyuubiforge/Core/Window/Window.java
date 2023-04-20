package kyuubiforge.Core.Window;

import imgui.ImGui;
import kyuubiforge.Core.Application.Application;
import kyuubiforge.Core.ImGuiLayer;
import kyuubiforge.Core.Time;
import static kyuubiforge.Debug.Debug.log;

import kyuubiforge.Debug.Debug;
import kyuubiforge.Debug.Testlayer;
import kyuubiforge.Input.KeyListener;
import kyuubiforge.Core.Window.Container.Container;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.lang.reflect.InvocationTargetException;
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

    private ImGuiLayer imGuiLayer = null;

    //List that contains the current state of the window or a combination of those
    public List<WindowState> state = new LinkedList<>();

    private WindowSpecification windowSpecification = null;

    public Window(WindowSpecification specification)
    {
        this.windowSpecification = specification;

        this.imGuiLayer = specification.imGuiLayer;

        initWindow();
    }

    public int getWidth()
    {
        return windowSpecification.width;
    }

    public int getHeight()
    {
        return windowSpecification.height;
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
        if(windowSpecification.isResizeable)
        {
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
            state.add(WindowState.RESIZEABLE);
        }
        if(windowSpecification.isFullScreen)
        {
            state.add(WindowState.MAXIMIZED);
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        }

        //TODO: Add monitor and share to constructor
        //Create the window and save its id in a long
        windowSpecification.windowID = glfwCreateWindow(windowSpecification.width,windowSpecification.height,windowSpecification.title, NULL,NULL);
        windowNumber += 1;
        if(windowSpecification.windowID == NULL)
        {
            assert false : "[KyuubiForge] Failed to create window!";
            throw new IllegalStateException("Failed to create window");
        }

        //glfwSetKeyCallback(windowSpecification.windowID, KeyListener::keyCallback);

        //WARNING: Needs proper handling when dealing with multiple windows on the same Thread!
        glfwMakeContextCurrent(windowSpecification.windowID);

        //V-Sync
        glfwSwapInterval(1);

        //Show the window
        glfwShowWindow(windowSpecification.windowID);
        state.add(WindowState.OPEN);

        //IMPORTANT
        //Create all the needed opengl objects
        //TODO: Needs proper handling with multi threading
        GL.createCapabilities();

        if(imGuiLayer != null)
        {
            imGuiLayer.initImGui();
        }

        return this;
    }

    public Window addContainer(Container container)
    {
        containers.add(container);
        log("Added " + container.containerName + " as a container to " + windowSpecification.windowID);

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

    public void Update(float dt)
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


                //Update?
            for (Container e : containers)
            {
                e.update(dt);
            }
            if(imGuiLayer != null) imGuiLayer.update(dt);

            //Switches the buffers to display the next frame
        glfwSwapBuffers(windowSpecification.windowID);
    }

    public void resize(int x, int y)
    {

    }

    public <T extends ImGuiLayer> void attachImGuiLayer(Class<T> imGuiLayer)
    {
        try {
            this.imGuiLayer = imGuiLayer.getDeclaredConstructor(Window.class).newInstance(this);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        this.imGuiLayer.initImGui();
    }

    public void onClose()
    {
        windowNumber -= 1;
        state.add(WindowState.CLOSED);

        if(windowNumber <= 0) {
            Application.get().onClose();

            if(imGuiLayer != null)
            {
                imGuiLayer.destroyImGui();
            }

            glfwFreeCallbacks(windowSpecification.windowID);
            glfwDestroyWindow(windowSpecification.windowID);

            System.gc();
            log("[KyuubiForge] Destroyed window [" + windowSpecification.windowID + "]");
            Application.get().onClose();
            glfwTerminate();
        }
        else
        {
            if(imGuiLayer != null)
            {
                imGuiLayer.destroyImGui();
            }

            glfwFreeCallbacks(windowSpecification.windowID);
            glfwDestroyWindow(windowSpecification.windowID);

            System.gc();
            log("[KyuubiForge] Destroyed window [" + windowSpecification.windowID + "]");
        }
    }

    public long getWindowId()
    {
        return windowSpecification.windowID;
    }
}
