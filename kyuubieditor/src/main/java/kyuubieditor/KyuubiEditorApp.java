package kyuubieditor;

import kyuubiforge.Core.Application.Application;
import kyuubiforge.Core.Application.ApplicationSpecification;
import kyuubiforge.Core.Window.WindowSpecification;

import static kyuubiforge.Debug.Debug.log;

public class KyuubiEditorApp
{
    public static void main(String[] args)
    {
        WindowSpecification windowSpecification = new WindowSpecification(1920/2,1080/2,
                "KyuubiEditor (Development Version)");
        ApplicationSpecification specs = new ApplicationSpecification("KyuubiEditor", windowSpecification);
        KyuubiEditor editor = new KyuubiEditor(specs);

        EditorSettings settings = new EditorSettings();
        settings.window = editor.getWindow().getWindowId();

        PreProcess process = new PreProcess();
        process.init(settings);
        process.run();

        //editor.run();
    }
}
