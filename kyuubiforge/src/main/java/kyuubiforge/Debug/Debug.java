package kyuubiforge.Debug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Debug {
    private static final Logger logger = (Logger) LogManager.getLogger("[KyuubiForge]");

    public static void log(Object log) {
        logger.debug(log);
    }
}
