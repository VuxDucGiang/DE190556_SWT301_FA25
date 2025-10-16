import java.util.logging.Level;
import java.util.logging.Logger;

class User {
    private static final Logger logger = Logger.getLogger(User.class.getName());
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void display() {
        logger.log(Level.INFO, "Name: {0}, Age: {1}", new Object[]{name, age});
    }
}

public class ViolationOfEncapsulationExample {
    public static void main(String[] args) {
        User user = new User("Alice", 25);
        user.display();
    }
}
