import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsecureLogin {

    // Khai báo logger cho class này
    private static final Logger LOGGER = Logger.getLogger(InsecureLogin.class.getName());

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123456";

    public static void login(String username, String password) {
        if (Objects.equals(username, ADMIN_USERNAME) &&
                Objects.equals(password, ADMIN_PASSWORD)) {
            LOGGER.log(Level.INFO, "Login successful for user: {0}", username);
        } else {
            LOGGER.log(Level.WARNING, "Login failed for user: {0}", username);
        }
    }

    public void printUserInfo(String user) {
        if (user != null && !user.isEmpty()) {
            LOGGER.log(Level.INFO, "User: {0}", user);
        } else {
            LOGGER.warning("User information not available");
        }
    }

    public void doNothing() {
        // This method intentionally left blank
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LOGGER.info("Enter username: ");
        String user = sc.nextLine();
        LOGGER.info("Enter password: ");
        String pass = sc.nextLine();

        login(user, pass);
    }
}
