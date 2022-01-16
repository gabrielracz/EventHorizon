import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class SpaceView extends Pane {
    private final SpacePane space;
    private final PropertiesPane prop;
    private final SpaceApp controller;

    public SpaceView(SpaceApp initController){

        controller = initController;

        space = new SpacePane(this);
        space.relocate(0,0);
        space.setPrefSize(SpacePane.width,SpacePane.height);
        getChildren().add(space);

        prop = new PropertiesPane(controller);
        prop.relocate(SpacePane.width-200,0);
        prop.setPrefSize(400, SpacePane.height);
        getChildren().add(prop);

        update();

    }

    public void update(){
        space.update();     //Update space
        prop.update();      //update properties
    }




    public SpaceApp getController(){return controller;}
    public SpacePane getSpace() {
        return space;
    }
    public Slider getMassSlider() {return prop.getMassSlider();}
    public PropertiesPane getProp() {return prop;}
    public TextField getMassText(){return prop.getMassText();}
}
