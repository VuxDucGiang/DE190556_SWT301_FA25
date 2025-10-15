import java.util.logging.Logger;

class Animal {
    // Tạo logger cho class Animal
    private static final Logger LOGGER = Logger.getLogger(Animal.class.getName());

    void speak() {
        LOGGER.info("Animal speaks"); // FIX S106: dùng Logger thay System.out.println
    }
}

class Dog extends Animal {
    // Tạo logger riêng cho class Dog
    private static final Logger LOGGER = Logger.getLogger(Dog.class.getName());

    @Override
    void speak() {
        LOGGER.info("Dog barks"); // FIX S106: dùng Logger thay System.out.println
    }
}
