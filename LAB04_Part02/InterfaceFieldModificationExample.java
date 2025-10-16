
import java.util.logging.Level;
import java.util.logging.Logger;

interface Shape {
    void draw();
    void resize();
}

class Square implements Shape {
    private static final Logger logger = Logger.getLogger(Square.class.getName());

    @Override
    public void draw() {
        logger.log(Level.INFO, "Drawing square");
    }

    @Override
    public void resize() {
        logger.log(Level.INFO, "Resizing square");
    }
}
