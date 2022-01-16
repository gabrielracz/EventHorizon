import javafx.scene.paint.Color;

public class Dust extends Ejector{

    public Dust(double initX,double initY, double initXV, double initYV, double initParentRadius){
        super(initX, initY, 0.01, 2,initXV, initYV, 1, 13,initParentRadius);
        image.setFill(Color.LIGHTSTEELBLUE);
        ejector = "GasFire";
    }

    public void setFizzle(int fizzleTime){
        //indefinite life
    }
}
