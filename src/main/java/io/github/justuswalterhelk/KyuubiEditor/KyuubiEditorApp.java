package io.github.justuswalterhelk.KyuubiEditor;

import io.github.justuswalterhelk.KyuubiForge.core.ApplicationSpecification;

public class KyuubiEditorApp
{
    public static void main(String[] args) {
        KyuubiEditor editor = new KyuubiEditor(new ApplicationSpecification());
        editor.Run();
    }
}
