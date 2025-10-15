import java.util.logging.Logger;

public final class Constants {
    private Constants() {} // Ngăn tạo đối tượng

    public static final int MAX_USERS = 100;
}

class InterfaceFieldModificationExample {
    private static final Logger LOGGER = Logger.getLogger(InterfaceFieldModificationExample.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Max users: " + Constants.MAX_USERS); // FIX S106: dùng logger thay vì System.out
    }
}
