import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class GasGiant extends Movable{


    public GasGiant(double initX, double initY, double initMass, double initRadius, double initXVelocity, double initYVelocity){
        super(initX, initY, initMass, initRadius, initXVelocity, initYVelocity);
        image.setStroke(Color.MEDIUMSPRINGGREEN);
        image.setStrokeWidth(density/6);
        image.setStrokeType(StrokeType.INSIDE);
        ejector = "Gas";
    }

    public void checkCollision(Body b){
        if (this.getPos().getDistance(b.getPos()) <= radius + b.getRadius()){
            if(b instanceof Gas){       //If collided with gas particle, increase size
                mass+= 1;
                radius += 3/radius;
                density = mass/radius;
                influenceRadius += 1;
                image.setRadius(image.getRadius()+(3/image.getRadius()));
                b.setCollision(true);
                return;
            }
            else if(b instanceof Ejector){
                b.setCollision(true);
                return;
            }
            double p1 = getMass()*1.5 + getMass()*this.getVelocity();      //momentum
            double p2 = b.getMass()*1.5 + b.getMass()*b.getVelocity();
            if(p1>p2){
                b.setCollision(true);
            }else if (p2>p1){
                this.setCollision(true);
            }else{
                this.setCollision(true);
                b.setCollision(true);
            }
        }
    }

}
