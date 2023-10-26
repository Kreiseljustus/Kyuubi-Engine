import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        this.applicationName = "test";
        return this;
    }
}
