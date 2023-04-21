package kyuubieditor;

import imgui.ImGui;
import imgui.flag.ImGuiCond;
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
        ImGui.showDemoWindow();
        ImGui.setNextWindowPos(0,0, ImGuiCond.FirstUseEver);
        ImGui.setNextWindowSize(300,1920, ImGuiCond.FirstUseEver);
        if(ImGui.begin("Inspector"))
        {
            ImGui.text("Test");
            for(int i = 0; i < 50; i++)
            {
                if(ImGui.button("Button " + i)){

                }
            }
        }

        ImGui.end();
    }
}
