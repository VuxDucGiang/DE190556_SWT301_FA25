
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger logger =
            Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        String s = (args != null && args.length > 0 && !args[0].isBlank()) ? args[0] : null;

        if (s == null) {
            logger.warning("Input 's' is null; skipping length computation");
            return;
        }

        int len = s.length();
        logger.log(Level.INFO, "Length = {0}", len);
    }
}
