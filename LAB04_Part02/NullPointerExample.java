import java.util.logging.Logger;

public class NullPointerExample {
    private static final Logger LOGGER = Logger.getLogger(NullPointerExample.class.getName());

    public static void main(String[] args) {
        String text = null;

        // FIX S2259: kiểm tra null trước khi gọi text.length()
        if (text != null && text.length() > 0) {
            LOGGER.info("Text is not empty"); // FIX S106: thay System.out.println bằng Logger
        } else {
            LOGGER.warning("Text is null or empty");
        }
    }
}
