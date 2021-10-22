import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public abstract class Movable extends Body{

    protected double xvelocity;
    protected double yvelocity;


    public double getVelocity(){return Math.sqrt(Math.pow(xvelocity,2) + Math.pow(yvelocity,2));}
    public double getXvelocity(){return xvelocity;}
    public double getYvelocity(){return yvelocity;}


    public Movable(double initX, double initY, double initMass, double initRadius, double initXVelocity, double initYVelocity) {
        super(initX, initY, initMass, initRadius);
        xvelocity = initXVelocity;
        yvelocity = initYVelocity;


        image = new Circle(initX,initY, initRadius);
        image.setFill(javafx.scene.paint.Color.BLACK);
        image.setStroke(javafx.scene.paint.Color.WHITE);
        image.setStrokeWidth(density/10);
        image.setStrokeType(StrokeType.INSIDE);
    }

    public void updateVelocities(double xv, double yv) {
        xvelocity += xv;
        yvelocity += yv;
    }

    public void move(){
        pos.setX(pos.getX() + Space.getTimeStep()*xvelocity);
        pos.setY(pos.getY() + Space.getTimeStep()*yvelocity);
    }


}