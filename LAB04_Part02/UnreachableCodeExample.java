import java.util.logging.Logger;

public class UnreachableCodeExample {
    private static final Logger LOGGER = Logger.getLogger(UnreachableCodeExample.class.getName());

    public static int getNumber() {
        LOGGER.info("Returning number 42"); // dùng logger thay vì println
        return 42; // return phải nằm cuối -> không có code sau nó
    }

    public static void main(String[] args) {
        LOGGER.info("Number is: " + getNumber()); // thay System.out.println bằng logger
    }
}
