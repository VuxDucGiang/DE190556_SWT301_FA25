
import java.util.logging.Logger;

public class HardcodedCredentialsExample {

    private static final Logger logger = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        String username = "admin";
        String password = "123456";

        if (authenticate(username, password)) {
            logger.info("Access granted");
        } else {
            logger.warning("Access denied");
        }
    }

    private static boolean authenticate(String user, String pass) {
        return user.equals("admin") && pass.equals("123456");
    }
}
