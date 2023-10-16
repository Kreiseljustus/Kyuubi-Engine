import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loggers {
    public static final Logger CoreLogger = LogManager.getLogger("KYUUBI");
    public static final Logger ClientLogger = LogManager.getLogger("APPLICATION");
}
