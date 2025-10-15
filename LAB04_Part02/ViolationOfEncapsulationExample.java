import java.util.logging.Logger;

class User {
    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    // FIX S1104: Biến không nên public → để private và cung cấp getter/setter
    private String name;
    private int age;

    // Constructor
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter & Setter (nếu cần chỉnh sửa dữ liệu)
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

    // FIX S106: Thay System.out.println bằng Logger
    public void display() {
        LOGGER.info("Name: " + name + ", Age: " + age);
    }
}
