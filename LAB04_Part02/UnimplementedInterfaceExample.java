
import java.util.logging.Level;
import java.util.logging.Logger;

interface Drawable {
    void draw();
}

abstract class Circle implements Drawable {
}

class SimpleCircle extends Circle {
    private static final Logger logger =
            Logger.getLogger(SimpleCircle.class.getName());

    @Override
    public void draw() {
        logger.log(Level.INFO, "Drawing a simple circle...");
    }
}

public class UnimplementedInterfaceExample {
    public static void main(String[] args) {
        Drawable d = new SimpleCircle();
        d.draw();
    }
}
