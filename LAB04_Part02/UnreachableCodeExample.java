
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger logger =
            Logger.getLogger(UnreachableCodeExample.class.getName());

    public static int getNumber() {
        logger.log(Level.INFO, "Preparing number...");
        return 42;
    }

    public static void main(String[] args) {
        int result = getNumber();
        logger.log(Level.INFO, "Result: {0}", result);
    }
}
