import java.util.logging.Logger;

public class CatchGenericExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(CatchGenericExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            String s = null;

            // Kiểm tra null trước khi truy cập .length()
            if (s == null) {
                LOGGER.warning("Variable 's' is null");
            } else if (s.length() == 0) {
                LOGGER.info("String is empty");
            } else {
                LOGGER.info("String length: " + s.length());
            }

        } catch (Exception _) { // ✅ dùng unnamed pattern (Java 21+)
            LOGGER.severe("Something went wrong");
        }
    }
}
