package kyuubiforge.Core.Window;

import kyuubiforge.Core.Application.Application;
import kyuubiforge.Core.AbstractImGuiLayer;

import static kyuubiforge.Debug.Debug.log;

import kyuubiforge.Core.AbstractScene;
import kyuubiforge.Renderer.RenderManager;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/*
    This class needs to get reworked at some point.
    Maybe add Object that just contains the window settings

    Figure out FPS cap per window
 */
public class Window {

    protected static int windowNumber;
    protected GeneralWindowHandler windowHandler;

    public Window(@NotNull final WindowSpecification specification) {
        this.windowHandler = new GeneralWindowHandler(this, specification);
        init();
    }

    public void update(float dt) {
        windowHandler.update(dt);
    }
    private void init() {
        windowHandler.init();
    }

    public <T extends AbstractImGuiLayer> void attachImGuiLayer(@NotNull Class<T> imGuiLayer) {
        windowHandler.attachImGuiLayer(imGuiLayer);
    }

    public int getWidth() {
        return windowHandler.windowSpecification.width;
    }
    public int getHeight() {
        return windowHandler.windowSpecification.height;
    }
    public long getWindowId() {
        return windowHandler.windowSpecification.windowID;
    }
    public RenderManager getRenderManager() {
        return windowHandler.getRenderManager();
    }
}
