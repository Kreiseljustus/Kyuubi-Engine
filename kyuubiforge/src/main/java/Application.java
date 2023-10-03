public abstract class Application {
    protected String applicationName;

    public void run() {
        System.out.println(applicationName + " has been run");
    }

    abstract Application createApplication();
}
