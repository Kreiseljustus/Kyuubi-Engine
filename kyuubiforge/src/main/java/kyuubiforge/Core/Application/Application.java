package kyuubiforge.Core.Application;

import kyuubiforge.Core.Window.Window;
import org.jetbrains.annotations.NotNull;

/*
    This class needs to get reworked at some point
 */
public class Application {
    private Window window;

    private final ApplicationSpecification applicationSpecification;
    public ApplicationSpecification getSpecification() {return applicationSpecification;}

    private static Application instance;

    public Window getWindow() { return window;}

    private final ApplicationHandler applicationHandler = new ApplicationHandler(this);

    public Application(@NotNull ApplicationSpecification specification)
    {
        instance = this;

        applicationSpecification = specification;

        window = new Window(specification.mainWindowSpecification);
    }

    public static Application get() {return instance;}
    public ApplicationHandler getApplicationHandler() {return applicationHandler;}
}