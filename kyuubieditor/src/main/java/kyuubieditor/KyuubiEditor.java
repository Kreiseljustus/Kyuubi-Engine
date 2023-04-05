package kyuubieditor;

import kyuubiforge.Core.Application.Application;
import kyuubiforge.Core.Application.ApplicationSpecification;
import static kyuubiforge.Debug.Debug.log;


public class KyuubiEditor extends Application {
    public KyuubiEditor(ApplicationSpecification specification)
    {
        super(specification);
        log("[KyuubiEditor] Initializing application");
        log("[KyuubiEditor] Current working directory: " + System.getProperty("user.dir"));
    }
}
