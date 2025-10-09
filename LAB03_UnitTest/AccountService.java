package DE190556;

import java.util.regex.Pattern;

public class AccountService {

    // Regex cơ bản để kiểm tra email hợp lệ
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public boolean registerAccount(String username, String password, String email) {
        // Kiểm tra username không được null hoặc rỗng
        if (username == null || username.isEmpty()) {
            return false;
        }

        // Kiểm tra username phải >= 3 ký tự
        if (!isValidUsername(username)) {
            return false;
        }

        // Password phải > 6 ký tự
        if (password == null || password.length() <= 6) {
            return false;
        }

        // Email hợp lệ
        if (!isValidEmail(email)) {
            return false;
        }

        return true;
    }

    // ✅ Kiểm tra email hợp lệ
    public boolean isValidEmail(String email) {
        if (email == null) return false;
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public boolean isValidUsername(String username) {
        if (username == null) return false;
        return username.length() >= 3;
    }
}
