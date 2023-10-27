package logging;

import logging.EmptyLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loggers {
    public static Logger CoreLogger = new EmptyLogger();
    public static final Logger ClientLogger = LogManager.getLogger("APPLICATION");

    public static void enableCoreLogger() {
        CoreLogger = LogManager.getLogger("KYUUBI");
    }
}
