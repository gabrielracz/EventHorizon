import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import java.util.List;

public class SpacePane extends Pane {
    SpaceView view;
    List<Body> bodies;
    Label count, fps;
    Line vertical, horizontal, h1,h2,h3,h4,h5,h6,v1,v2,v3,v4;
    //public static int height = (int) Screen.getPrimary().getBounds().getHeight();
    //public static int width = (int) Screen.getPrimary().getBounds().getWidth()-200;
    private boolean grid = true;
    public static int height = 1080;
    public static int width = 1920 - 200;

    public SpacePane(SpaceView initView){
        view = initView;
        bodies = view.getController().getBodies();

        count = new Label();
        count.relocate(1479,5);
        count.setStyle("-fx-text-fill: white;-fx-font-family: 'Consolas';-fx-font-size: 16;");
        getChildren().add(count);

        fps = new Label();
        fps.relocate(5,5);
        fps.setStyle("-fx-text-fill: lime;-fx-font-family: 'Consolas';-fx-font-size: 16;");
        getChildren().add(fps);

        setStyle("-fx-background-color: black;-fx-border-color: black;");
        for(Body b: bodies){
            getChildren().add(b.image);
        }
        drawGrid();




        update();

    }


    public void addToSpacePane(Body b){
        getChildren().add(b.image);
        update();
    }

    public void update(){
        update(bodies);
    }

    public void update(List<Body> bodies){
        count.setText(String.format("%4d",bodies.size()));
        for(int b = 0; b<bodies.size(); b++) {
            bodies.get(b).image.setCenterX( bodies.get(b).getPos().getX());
            bodies.get(b).image.setCenterY( bodies.get(b).getPos().getY());
        }
    }

    public void erase(Shape s){
        getChildren().remove(s);
    }

    public void setFps(double frames){
        if (frames > 0.1){frames = 0.1;}
        fps.setText(String.format("%.0f",frames*500));
    }

    public void toggleGrid(){
        grid = !grid;
        drawGrid();
    }

    public void drawGrid(){ //Add or remove grid
        if (grid){
            vertical = new Line((SpacePane.width-200)/2,0, (SpacePane.width-200)/2,SpacePane.height);
            vertical.setStroke(Color.SNOW);
            vertical.setStrokeWidth(1.75);
            vertical.toBack();
            getChildren().add(vertical);

            horizontal = new Line(0,SpacePane.height/2, SpacePane.width-200,SpacePane.height/2);
            horizontal.setStroke(Color.SNOW);
            horizontal.setStrokeWidth(1.75);
            horizontal.toBack();
            getChildren().add(horizontal);

            h1 = new Line((SpacePane.width-200)/4,(SpacePane.height/2)-30, (SpacePane.width-200)/4,(SpacePane.height/2)+30);
            h1.setStroke(Color.SNOW);
            h1.setStrokeWidth(1.75);
            h1.toBack();
            getChildren().add(h1);

            h2 = new Line(((SpacePane.width-200)/4)*3,(SpacePane.height/2)-30, ((SpacePane.width-200)/4)*3,(SpacePane.height/2)+30);
            h2.setStroke(Color.SNOW);
            h2.setStrokeWidth(1.75);
            h2.toBack();
            getChildren().add(h2);

            v1 = new Line(((SpacePane.width-200)/2)-30,110, ((SpacePane.width-200)/2)+30,110);
            v1.setStroke(Color.SNOW);
            v1.setStrokeWidth(1.75);
            v1.toBack();
            getChildren().add(v1);

            v2 = new Line(((SpacePane.width-200)/2)-30,970, ((SpacePane.width-200)/2)+30,970);
            v2.setStroke(Color.SNOW);
            v2.setStrokeWidth(1.75);
            v2.toBack();
            getChildren().add(v2);

            h3 = new Line(((SpacePane.width-200)/8),(SpacePane.height/2)-15, ((SpacePane.width-200)/8),(SpacePane.height/2)+15);
            h3.setStroke(Color.SNOW);
            h3.setStrokeWidth(1.75);
            h3.toBack();
            getChildren().add(h3);

            h4 = new Line(((SpacePane.width-200)*3/8),(SpacePane.height/2)-15, ((SpacePane.width-200)*3/8),(SpacePane.height/2)+15);
            h4.setStroke(Color.SNOW);
            h4.setStrokeWidth(1.75);
            h4.toBack();
            getChildren().add(h4);

            h5 = new Line(((SpacePane.width-200)*5/8),(SpacePane.height/2)-15, ((SpacePane.width-200)*5/8),(SpacePane.height/2)+15);
            h5.setStroke(Color.SNOW);
            h5.setStrokeWidth(1.75);
            h5.toBack();
            getChildren().add(h5);

            h6 = new Line(((SpacePane.width-200)*7/8),(SpacePane.height/2)-15, ((SpacePane.width-200)*7/8),(SpacePane.height/2)+15);
            h6.setStroke(Color.SNOW);
            h6.setStrokeWidth(1.75);
            h6.toBack();
            getChildren().add(h6);

            v3 = new Line(((SpacePane.width-200)/2)-15,325, ((SpacePane.width-200)/2)+15,325);
            v3.setStroke(Color.SNOW);
            v3.setStrokeWidth(1.75);
            v3.toBack();
            getChildren().add(v3);

            v4 = new Line(((SpacePane.width-200)/2)-15,755, ((SpacePane.width-200)/2)+15,755);
            v4.setStroke(Color.SNOW);
            v4.setStrokeWidth(1.75);
            v4.toBack();
            getChildren().add(v4);
        }else{
            getChildren().removeAll(vertical, horizontal, h1,h2,h3,h4,h5,h6,v1,v2,v3,v4);
        }
    }
}
