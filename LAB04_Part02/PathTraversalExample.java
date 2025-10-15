import java.io.*;
import java.util.logging.Logger;

public class PathTraversalExample {

    private static final Logger LOGGER = Logger.getLogger(PathTraversalExample.class.getName());

    public static void main(String[] args) throws IOException {
        String userInput = "../secret.txt";
        File file = new File(userInput);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                LOGGER.info("Reading file: " + file.getPath());
                String line;
                while ((line = reader.readLine()) != null) {
                    LOGGER.info(line);
                }
            }
        } else {
            LOGGER.warning("File not found: " + file.getPath());
        }
    }
}
