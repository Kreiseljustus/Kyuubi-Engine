package kyuubiforge.Core;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.callback.ImStrConsumer;
import imgui.callback.ImStrSupplier;
import imgui.flag.ImGuiBackendFlags;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiKey;
import imgui.flag.ImGuiMouseCursor;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import kyuubiforge.Core.Window.Window;

import static org.lwjgl.glfw.GLFW.*;

public abstract class ImGuiLayer {

    private Window window;
    private long glfwWindow;

    private ImGuiIO io = null;

    // Mouse cursors provided by GLFW
    private final long[] mouseCursors = new long[ImGuiMouseCursor.COUNT];

    // LWJGL3 renderer (SHOULD be initialized)
    private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

    private final ImGuiImplGlfw glfw = new ImGuiImplGlfw();

    public ImGuiLayer(Window glfwWindow) {
        this.window = glfwWindow;
        this.glfwWindow = window.getWindowId();
    }

    // Initialize Dear ImGui.
    public void initImGui() {
        // IMPORTANT!!
        // This line is critical for Dear ImGui to work.
        ImGui.createContext();

        // ------------------------------------------------------------
        // Initialize ImGuiIO config
        io = ImGui.getIO();

        io.setIniFilename(null); // We don't want to save .ini file
        io.setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard); // Navigation with keyboard
        io.setBackendFlags(ImGuiBackendFlags.HasMouseCursors); // Mouse cursors to display while resizing windows etc.
        io.setBackendPlatformName("imgui_java_impl_glfw");

        io.setSetClipboardTextFn(new ImStrConsumer() {
            @Override
            public void accept(final String s) {
                glfwSetClipboardString(glfwWindow, s);
            }
        });

        io.setGetClipboardTextFn(new ImStrSupplier() {
            @Override
            public String get() {
                final String clipboardString = glfwGetClipboardString(glfwWindow);
                if (clipboardString != null) {
                    return clipboardString;
                } else {
                    return "";
                }
            }
        });

        // ------------------------------------------------------------
        // Fonts configuration
        // Read: https://raw.githubusercontent.com/ocornut/imgui/master/docs/FONTS.txt

//        final ImFontAtlas fontAtlas = io.getFonts();
//        final ImFontConfig fontConfig = new ImFontConfig(); // Natively allocated object, should be explicitly destroyed
//
//        // Glyphs could be added per-font as well as per config used globally like here
//        fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesCyrillic());
//
//        // Add a default font, which is 'ProggyClean.ttf, 13px'
//        fontAtlas.addFontDefault();
//
//        // Fonts merge example
//        fontConfig.setMergeMode(true); // When enabled, all fonts added with this config would be merged with the previously added font
//        fontConfig.setPixelSnapH(true);
//
//        fontAtlas.addFontFromMemoryTTF(loadFromResources("basis33.ttf"), 16, fontConfig);
//
//        fontConfig.setMergeMode(false);
//        fontConfig.setPixelSnapH(false);
//
//        // Fonts from file/memory example
//        // We can add new fonts from the file system
//        fontAtlas.addFontFromFileTTF("src/test/resources/Righteous-Regular.ttf", 14, fontConfig);
//        fontAtlas.addFontFromFileTTF("src/test/resources/Righteous-Regular.ttf", 16, fontConfig);
//
//        // Or directly from the memory
//        fontConfig.setName("Roboto-Regular.ttf, 14px"); // This name will be displayed in Style Editor
//        fontAtlas.addFontFromMemoryTTF(loadFromResources("Roboto-Regular.ttf"), 14, fontConfig);
//        fontConfig.setName("Roboto-Regular.ttf, 16px"); // We can apply a new config value every time we add a new font
//        fontAtlas.addFontFromMemoryTTF(loadFromResources("Roboto-Regular.ttf"), 16, fontConfig);
//
//        fontConfig.destroy(); // After all fonts were added we don't need this config more
//
//        // ------------------------------------------------------------
//        // Use freetype instead of stb_truetype to build a fonts texture
//        ImGuiFreeType.buildFontAtlas(fontAtlas, ImGuiFreeType.RasterizerFlags.LightHinting);

        // Method initializes LWJGL3 renderer.
        // This method SHOULD be called after you've initialized your ImGui configuration (fonts and so on).
        // ImGui context should be created as well.
        imGuiGl3.init("#version 330 core");
        glfw.init(glfwWindow, true);
    }

    private void cb(){}

    public void update(float dt) {
        startFrame(dt);

        // Any Dear ImGui code SHOULD go between ImGui.newFrame()/ImGui.render() methods
        glfw.newFrame();
        ImGui.newFrame();
        render();
        ImGui.render();

        endFrame();
    }

    public abstract void render();

    private void startFrame(final float deltaTime) {
        // Get window properties and mouse position
        float[] winWidth = {window.getWidth()};
        float[] winHeight = {window.getHeight()};
        double[] mousePosX = {0};
        double[] mousePosY = {0};
        glfwGetCursorPos(glfwWindow, mousePosX, mousePosY);

        // We SHOULD call those methods to update Dear ImGui state for the current frame
        final ImGuiIO io = ImGui.getIO();
        io.setDisplaySize(winWidth[0], winHeight[0]);
        io.setDisplayFramebufferScale(1f, 1f);
        io.setMousePos((float) mousePosX[0], (float) mousePosY[0]);
        io.setDeltaTime(deltaTime);

        // Update the mouse cursor
        final int imguiCursor = ImGui.getMouseCursor();
        glfwSetCursor(glfwWindow, mouseCursors[imguiCursor]);
        glfwSetInputMode(glfwWindow, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    private void endFrame() {
        // After Dear ImGui prepared a draw data, we use it in the LWJGL3 renderer.
        // At that moment ImGui will be rendered to the current OpenGL context.
        imGuiGl3.renderDrawData(ImGui.getDrawData());
    }

    // If you want to clean a room after yourself - do it by yourself
    public void destroyImGui() {
        imGuiGl3.dispose();
        glfw.dispose();
        ImGui.destroyContext();
    }
}