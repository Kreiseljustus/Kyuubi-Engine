package kyuubiforge.Debug;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import kyuubiforge.Core.ImGuiLayer;
import kyuubiforge.Core.Window.Window;

import static kyuubiforge.Debug.Debug.log;

public class Testlayer extends ImGuiLayer {
    public Testlayer(Window glfwWindow) {
        super(glfwWindow);
    }

    @Override
    public void render() {
        if(ImGui.begin("Inspector"))
        {
            ImGui.text("Test");
            if(ImGui.button("Lol"))
            {
                log("Button pressed");
            }
        }

        ImGui.end();
    }
}
