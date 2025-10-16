
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResourceLeakExample {

    private static final Logger logger =
            Logger.getLogger(ResourceLeakExample.class.getName());

    public static void main(String[] args) {
        String fileName = "data.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.log(Level.INFO, "{0}", line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "I/O error while reading file", e);
            logger.log(Level.SEVERE, "File: {0}", fileName);
        }
    }
}
