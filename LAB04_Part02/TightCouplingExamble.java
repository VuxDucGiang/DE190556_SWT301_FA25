import java.util.logging.Logger;

class Printer {
    private static final Logger LOGGER = Logger.getLogger(Printer.class.getName());

    void print(String message) {
        LOGGER.info(message); // FIX S106: thay System.out.println bằng Logger
    }
}

class Report {
    private final Printer printer = new Printer(); // vẫn còn coupling, nhưng giờ không còn S106

    void generate() {
        printer.print("Generating report...");
    }
}
