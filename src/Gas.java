import javafx.scene.paint.Color;

public class Gas extends Ejector{

    public Gas(double initX,double initY, double initXV, double initYV, double initParentRadius){
        super(initX, initY, 0.01, 2,initXV, initYV, 1, 13,initParentRadius);
        image.setFill(Color.MEDIUMSPRINGGREEN );
        ejector = "Null";
    }

    public void checkCollision(Body b){
        if (this.getPos().getDistance(b.getPos()) <= radius + b.getRadius()){
            if(b instanceof Fire){
                collision = true;
                ejector = "GasFire";
            }
        }
    }
}
