package kyuubiforge.Core.Window;

import kyuubiforge.Core.AbstractImGuiLayer;
import kyuubiforge.Core.AbstractScene;
import kyuubiforge.Core.Application.Application;
import kyuubiforge.Core.SceneManager;
import kyuubiforge.Debug.TestScene;
import kyuubiforge.Renderer.GameRenderer;
import kyuubiforge.Renderer.RenderManager;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import static java.sql.Types.NULL;
import static kyuubiforge.Debug.Debug.log;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractWindowHandler {

    private final Window window;
    protected WindowSpecification windowSpecification;

    private final SceneManager sceneManager = new SceneManager();
    private final RenderManager renderManager = new RenderManager();

    protected List<WindowState> state = new LinkedList<>();
    private AbstractImGuiLayer imGuiLayer;

    public AbstractWindowHandler(Window window, WindowSpecification windowSpecification) {
        this.window = window;
        this.windowSpecification = windowSpecification;
    }

    protected void init() {
        setupGLFW();

        setWindowHints();

        createWindow();

        if(imGuiLayer != null) {
            imGuiLayer.initImGui();
        }

        sceneManager.loadScene(TestScene.class);

        renderManager.addRenderer(new GameRenderer());
    }

    protected void update(float dt) {
        glfwPollEvents();

        if(shouldClose()) {
            return;
        }

        glClearColor(1.0f,1.0f,1.0f,1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        updateScenes(dt);

        if(imGuiLayer != null) imGuiLayer.update(dt);

        glfwSwapBuffers(windowSpecification.windowID);
    }

    protected void close() {
        window.windowNumber -= 1;
        state.add(WindowState.CLOSED);

        if(window.windowNumber <= 0) {
            destroyApplication();
        }
        else {
            destroyWindow();
        }
    }

    protected <T extends AbstractImGuiLayer> void attachImGuiLayer(@NotNull Class<T> imGuiLayer) {
        try {
            Constructor<T> declaredConstructor = imGuiLayer.getDeclaredConstructor(Window.class);
            this.imGuiLayer = declaredConstructor.newInstance(this);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        this.imGuiLayer.initImGui();
    }

    private void setupGLFW() {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            assert false : "[KyuubiForge] Failed to initialize GLFW! Restart required.";
            throw new IllegalStateException("Initializing GLFW failed!");
        }
    }

    private void setWindowHints() {
        glfwDefaultWindowHints();

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_DECORATED, windowSpecification.isDecorated ? GLFW_TRUE : GLFW_FALSE);
        if(windowSpecification.isResizeable) {
            glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
            state.add(WindowState.RESIZEABLE);
        }
        if(windowSpecification.isFullScreen) {
            state.add(WindowState.MAXIMIZED);
            glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);
        }
    }

    private void createWindow() {
        windowSpecification.windowID = glfwCreateWindow(windowSpecification.width,windowSpecification.height,windowSpecification.title, NULL,NULL);
        window.windowNumber += 1;
        if(windowSpecification.windowID == NULL) {
            assert false : "[KyuubiForge] Failed to create window!";
            throw new IllegalStateException("Failed to create window");
        }

        //WARNING: Needs proper handling when dealing with multiple windows on the same Thread!
        glfwMakeContextCurrent(windowSpecification.windowID);

        glfwSwapInterval(1);

        //Show the window
        glfwShowWindow(windowSpecification.windowID);
        state.add(WindowState.OPEN);

        //IMPORTANT
        //Create all the needed opengl objects
        //TODO: Needs proper handling with multi threading
        GL.createCapabilities();
    }

    private boolean shouldClose() {
        if(glfwWindowShouldClose(windowSpecification.windowID))
        {
            this.close();
            return true;
        }
        return false;
    }

    private void updateScenes(float dt) {
        for(AbstractScene scene : sceneManager.getLoadedScenes())
        {
            scene.update(dt);
        }
    }

    private void destroyWindow() {
        if(imGuiLayer != null)
        {
            imGuiLayer.destroyImGui();
        }

        glfwFreeCallbacks(windowSpecification.windowID);
        glfwDestroyWindow(windowSpecification.windowID);

        System.gc();
        log("[KyuubiForge] Destroyed window [" + windowSpecification.windowID + "]");
    }

    private void destroyApplication() {
        Application.get().getApplicationHandler().onClose();

        if(imGuiLayer != null)
        {
            imGuiLayer.destroyImGui();
        }

        glfwFreeCallbacks(windowSpecification.windowID);
        glfwDestroyWindow(windowSpecification.windowID);

        System.gc();
        log("[KyuubiForge] Destroyed window [" + windowSpecification.windowID + "]");
        Application.get().getApplicationHandler().onClose();
        glfwTerminate();
    }

    protected RenderManager getRenderManager() {
        return renderManager;
    }
}
