import logging.Loggers;

public abstract class Application {
    protected String applicationName;

    public void run() {
        Loggers.CoreLogger.info("Starting " + applicationName);
    }

    abstract Application createApplication();
}
