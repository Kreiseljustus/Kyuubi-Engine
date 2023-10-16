import org.apache.logging.log4j.LogManager;

public abstract class Application {
    protected String applicationName;

    public void run() {
        System.out.println(applicationName + " has been run");
        Loggers.ClientLogger.debug("He");
        System.out.println("Called");
        while(true) {
            LogManager.getLogger("KYUUBI").info("HHH");

        }
    }

    abstract Application createApplication();
}
