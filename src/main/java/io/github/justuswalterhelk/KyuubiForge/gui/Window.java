package io.github.justuswalterhelk.KyuubiForge.gui;

import io.github.justuswalterhelk.KyuubiEditor.KyuubiEditor;
import io.github.justuswalterhelk.KyuubiEditor.KyuubiEditorApp;
import io.github.justuswalterhelk.KyuubiForge.core.Application;
import io.github.justuswalterhelk.KyuubiForge.core.Time;
import io.github.justuswalterhelk.KyuubiForge.input.KeyListener;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window
{
    private final int width;
    private final int height;
    private String title;

    private List<Container> containers = new LinkedList<Container>();

    private final boolean fullScreen;

    private static int s_WindowNumber = 0;

    public long getWindowID() {
        return window;
    }

    private long window;

    public Window(int width, int height, String title, boolean fullScreen)
    {
        this.width = width;
        this.height = height;
        this.title = title;

        this.fullScreen = fullScreen;
    }

    public Window run()
    {
        System.out.println("Running window with id of " + window);
        glfwSetWindowTitle(window, this.title += " ([Debug]: Current Window: " + window + " )");

        return this;
    }

    public Window initWindow()
    {
        //Print errors to console
        GLFWErrorCallback.createPrint(System.err).set();

        //Initialize GLFW
        if(!glfwInit())
        {
            //TODO: Add a warning in the engine debugger
            throw new IllegalStateException("Initalizing GLFW failed!");
        }

        //Reset all hints to their default values
        glfwDefaultWindowHints();
        //Set the window properties
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, fullScreen ? 1:0);

        //For apple ARM silicone
        //glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        //glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        //glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        //glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        //TODO: Add monitor and share to constructor
        //Create the window and save its id in a long
        window = glfwCreateWindow(this.width,this.height,this.title, NULL,NULL);
        s_WindowNumber += 1;
        if(window == NULL)
        {
            //TODO: Add a warning in the engine debugger
            throw new IllegalStateException("Failed to create window");
        }

        glfwSetKeyCallback(window, KeyListener::keyCallback);

        //WARNING: Needs proper handling when dealing with multiple windows on the same Thread!
        glfwMakeContextCurrent(window);

        //V-Sync
        //glfwSwapInterval(1);

        //Show the window
        glfwShowWindow(window);

        //IMPORTANT
        //Create all the needed opengl objects
        //TODO: Needs proper handling with multi threading
        GL.createCapabilities();

        return this;
    }

    public Window addContainer(Container container)
    {
        containers.add(container);
        System.out.println("Added " + container.containerName + " as a container to " + window);

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

    public void OnUpdate()
    {
        //Can be called from any thread!
        //Process all events that are still in the queue
        glfwPollEvents();

        if(glfwWindowShouldClose(window))
        {
            OnClose();
        }

            //Clear color
        glClearColor(1.0f,1.0f,1.0f,1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        if (dt >= 0) {
                //Update?
            for (Container e : containers)
            {
                e.update(dt);
            }
        }

            //Switches the buffers to display the next frame
        glfwSwapBuffers(window);
        endTime = Time.getTime();
        dt = endTime - beginTime;
        beginTime = endTime;

    }

    public void OnClose()
    {
        s_WindowNumber -= 1;
        //Free the callbacks and destroy window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        System.out.println("Destroyed window with id of " + window);
        //Destroy glfw
        //May need some refactoring when working with multiple windows
        //will destroy EVERY window
        if(s_WindowNumber <= 0) {
            Application.Get().OnClose();
            glfwTerminate();
        }
    }
}
