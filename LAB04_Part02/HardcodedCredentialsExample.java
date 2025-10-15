
import java.util.logging.Logger;

public class HardcodedCredentialsExample {

    private static final Logger LOGGER = Logger.getLogger(HardcodedCredentialsExample.class.getName());

    public static void main(String[] args) {
        String username = "admin";
        String password = System.getenv("APP_PASSWORD"); // lấy từ biến môi trường thay vì hardcoded

        if (password == null) {
            LOGGER.warning("Password environment variable not set!");
            return;
        }

        AuthService authService = new AuthService();
        if (authService.authenticate(username, password)) {
            LOGGER.info("Access granted");
        } else {
            LOGGER.info("Access denied");
        }
    }
}

class AuthService {

    boolean authenticate(String user, String pass) {
        // Giả lập xác thực (trong thực tế sẽ kiểm tra từ DB)
        return "admin".equals(user) && "123456".equals(pass);
    }
}
