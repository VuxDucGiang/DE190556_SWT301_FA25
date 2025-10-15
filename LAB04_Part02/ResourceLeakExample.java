import java.io.*;
import java.util.logging.Logger;

public class ResourceLeakExample {

    private static final Logger LOGGER = Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        // Sử dụng try-with-resources để tự động đóng reader
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LOGGER.info(line); // dùng logger thay vì System.out.println
            }
        } catch (IOException e) {
            LOGGER.severe("Error reading file: " + e.getMessage());
        }
    }
}
