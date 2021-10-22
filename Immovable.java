import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Immovable extends Body {

    public Immovable(double initX, double initY, double initMass, double initRadius){
        super(initX, initY, initMass, initRadius);

        image = new Circle(initX,initY, initRadius);
        image.setFill(javafx.scene.paint.Color.BLACK);
        image.setStroke(javafx.scene.paint.Color.WHITE);
        image.setStrokeWidth(density/10);
        image.setStrokeType(StrokeType.INSIDE);
    }
    public void updateVelocities(double xv, double yv){}
    public void move(){}    //If called to move do nothing
}
