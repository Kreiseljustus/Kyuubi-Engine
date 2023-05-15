package kyuubiforge.Core.Application;

import kyuubiforge.Renderer.AbstractRenderLayer;

import static kyuubiforge.Debug.Debug.log;
import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class ApplicationHandler {
    private Application application = null;

    public float beginTime;
    public float endTime;
    public float dt = -1f;

    private boolean running = true;

    public ApplicationHandler(Application application) {
        this.application = application;
    }

    public void onClose() {
        log("[KyuubiEditor] Shutting down update loop");
        running = false;
        disposeRenderers();
    }

    private void disposeRenderers() {
        for(AbstractRenderLayer renderLayer : application.getWindow().getRenderManager().getRenderLayers()) {
            renderLayer.dispose();
        }
    }

    public void run() {
        log("[KyuubiEditor] Initialized main window");

        beginTime = (float)glfwGetTime();
        dt = -1.0f;

        while(running) {
            if(dt >= 0)
            {
                application.getWindow().update(dt);
            }
            if(running) {
                endTime = (float) glfwGetTime();
                dt = endTime - beginTime;
                beginTime = endTime;
            }
        }
    }
}
