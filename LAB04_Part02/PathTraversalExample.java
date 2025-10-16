
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PathTraversalExample {

    private static final Logger logger =
            Logger.getLogger(PathTraversalExample.class.getName());

    public static void main(String[] args) throws IOException {
        String userInput = "../secret.txt"; // giả lập input người dùng

        // (Khuyến nghị) Ràng buộc đường dẫn để tránh path traversal
        Path baseDir = Paths.get("resources").toAbsolutePath().normalize();
        Path requestedPath = baseDir.resolve(userInput).normalize();

        if (!requestedPath.startsWith(baseDir)) {
            logger.log(Level.WARNING, "Blocked path traversal attempt: {0}", requestedPath);
            return;
        }

        File file = requestedPath.toFile();
        if (file.exists()) {
            // try-with-resources để đóng reader an toàn
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                logger.log(Level.INFO, "Reading file: {0}", file.getPath());
                // ví dụ đọc 1 dòng (tùy nhu cầu)
                String line = reader.readLine();
                if (line == null) {
                    logger.log(Level.FINE, "File is empty: {0}", file.getPath());
                } else {
                    logger.log(Level.FINE, "First line: {0}", line);
                }
            }
        } else {
            logger.log(Level.WARNING, "File not found: {0}", file.getPath());
        }
    }
}
