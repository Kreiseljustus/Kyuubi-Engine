package justuswalterhelk.kyuubieditor;

import justuswalterhelk.kyuubiforge.Core.Application;
import justuswalterhelk.kyuubiforge.Core.ApplicationSpecification;

public class KyuubiEditor extends Application {
    public KyuubiEditor(ApplicationSpecification specification)
    {
        super(specification);
        System.out.println("[KyuubiEditor] Initializing application");
    }
}
