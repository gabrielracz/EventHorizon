import javafx.scene.paint.Color;

public class Fire extends Ejector {


    public Fire(double initX,double initY, double initXV, double initYV, double initParentRadius){
        super(initX, initY, 0.01, 1.5, initXV,initYV,9, 5,initParentRadius);
        image.setFill(Color.RED);
        int random = rand.nextInt(2);
        if (random == 0){
            image.setFill(javafx.scene.paint.Color.RED);
        }
        if (random == 1){
            image.setFill(javafx.scene.paint.Color.ORANGERED);
        }
        ejector = "Null";

    }

}
