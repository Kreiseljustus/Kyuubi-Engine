package justuswalterhelk.KyuubiEditor;

import justuswalterhelk.KyuubiForge.Core.ApplicationSpecification;

public class KyuubiEditorApp
{
    public static void main(String[] args) {
        ApplicationSpecification specs = new ApplicationSpecification();
        specs.SetName("KyuubiEditor");
        specs.SetFullScreen(true);
        KyuubiEditor editor = new KyuubiEditor(specs);
        editor.Run();
    }
}
