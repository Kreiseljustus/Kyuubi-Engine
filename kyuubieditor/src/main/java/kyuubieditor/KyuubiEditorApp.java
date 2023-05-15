package kyuubieditor;

import kyuubiforge.Core.Application.ApplicationSpecification;
import kyuubiforge.Core.Window.WindowSpecification;

public class KyuubiEditorApp
{
    public static void main(String[] args)
    {
        WindowSpecification windowSpecification = new WindowSpecification(1920/2,1080/2,
                "KyuubiEditor (Development Version)");
        ApplicationSpecification specs = new ApplicationSpecification("KyuubiEditor", windowSpecification);
        KyuubiEditor editor = new KyuubiEditor(specs);

        editor.getWindow().attachImGuiLayer(Testlayer.class);

        editor.getApplicationHandler().run();
    }
}
