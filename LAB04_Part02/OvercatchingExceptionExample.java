import java.util.logging.Logger;

public class OvercatchingExceptionExample {
    private static final Logger LOGGER = Logger.getLogger(OvercatchingExceptionExample.class.getName());

    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            LOGGER.info("Accessing index 10...");
            LOGGER.info(String.valueOf(arr[10])); // cố tình gây lỗi để minh họa
        } catch (ArrayIndexOutOfBoundsException e) { // FIX S7467: bắt lỗi cụ thể
            LOGGER.severe("Array index out of bounds: " + e.getMessage());
        }
    }
}
