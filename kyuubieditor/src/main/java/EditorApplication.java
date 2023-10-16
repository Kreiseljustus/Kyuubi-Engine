import org.apache.logging.log4j.LogManager;

public class EditorApplication extends Application {

    private final Application application;

    public static void main(String[] args) {
        new EditorApplication();
    }

    public EditorApplication() {
        application = createApplication();
        application.run();
    }

    @Override
    Application createApplication() {
        LogManager.getLogger(this).warn("TERs");
        this.applicationName = "test";
        return this;
    }
}
