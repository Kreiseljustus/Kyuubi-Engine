import logging.Loggers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class EditorApplication extends Application {

    private final Application application;

    public static void main(String[] args) {
        if(args.length != 0 && Objects.equals(args[0], "enableCoreLogger")) {Loggers.enableCoreLogger();}
        new EditorApplication();
    }

    public EditorApplication() {
        application = createApplication();
        application.run();
    }

    @Override
    Application createApplication() {
        this.applicationName = "test";
        return this;
    }
}
