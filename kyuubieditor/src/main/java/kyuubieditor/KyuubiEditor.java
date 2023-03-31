package kyuubieditor;

import kyuubiforge.Core.Application;
import kyuubiforge.Core.ApplicationSpecification;

public class KyuubiEditor extends Application {
    public KyuubiEditor(ApplicationSpecification specification)
    {
        super(specification);
        System.out.println("[KyuubiEditor] Initializing application");
        System.out.println("[KyuubiEditor][Debug] Current working directory: " + System.getProperty("user.dir"));
    }
}
