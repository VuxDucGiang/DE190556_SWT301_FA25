import java.util.logging.Logger;

public class SQLInjectionExample {
    private static final Logger LOGGER = Logger.getLogger(SQLInjectionExample.class.getName());

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        LOGGER.info("Executing query: " + query);
    }
}
