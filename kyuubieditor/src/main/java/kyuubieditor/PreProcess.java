package kyuubieditor;

import imgui.ImGui;
import imgui.flag.ImGuiConfigFlags;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Objects;

public class PreProcess {

    public EditorSettings settings;

    public void init(EditorSettings settings)
    {
        this.settings = settings;

        try (MemoryStack stack = MemoryStack.stackPush())
        {
            final IntBuffer pWidth = stack.mallocInt(1);
            final IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(settings.window, pWidth, pHeight);
            final GLFWVidMode vidmode = Objects.requireNonNull(GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor()));
            GLFW.glfwSetWindowPos(settings.window, (vidmode.width() - pWidth.get(0)) /2, (vidmode.height() - pHeight.get(0)) /2);
        }

        GLFW.glfwMakeContextCurrent(settings.window);
        GLFW.glfwSwapInterval(GLFW.GLFW_TRUE);

        initImGui();
    }

    public void run()
    {
        while(!GLFW.glfwWindowShouldClose(settings.window))
        {
            startFrame();
            endFrame();
        }
    }

    private void initImGui()
    {
        ImGui.createContext();
    }

    private void startFrame()
    {

        ImGui.newFrame();
    }

    private void endFrame()
    {
        ImGui.render();
        if(ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable))
        {
            final long backupWindow = GLFW.glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            GLFW.glfwMakeContextCurrent(backupWindow);
        }

        GLFW.glfwSwapBuffers(settings.window);
    }

    private void disposeImGui()
    {
        ImGui.destroyContext();
    }
}
