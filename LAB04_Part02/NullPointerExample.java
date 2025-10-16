
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger logger =
            Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = (args.length > 0) ? args[0] : null;

        if (Objects.equals(text, "hi")) {
            logger.log(Level.INFO, "Text equals 'hi'");
        } else {
            logger.log(Level.INFO, "Text is null or not 'hi'");
        }
    }
}
