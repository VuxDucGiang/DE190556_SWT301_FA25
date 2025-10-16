
import java.util.logging.Level;
import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger logger =
            Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            logger.info("Accessing index 10");
            int val = arr[10];
            logger.log(Level.INFO, "Value = {0}", val);
        } catch (ArrayIndexOutOfBoundsException ex) {
            logger.log(Level.WARNING, "Index out of bounds", ex);
        }
    }
}
