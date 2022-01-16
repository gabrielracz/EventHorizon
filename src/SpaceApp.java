import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import java.util.List;
import java.util.Timer;


public class SpaceApp extends Application {
    private Space model;
    private SpaceView view;
    private Point2D start, end;
    
    @Override
    public void start(Stage stage) throws Exception {
        model = Space.createSpace(this);
        view = new SpaceView(this);
        start = new Point2D(0,0);
        end = new Point2D(0,0);

        view.getSpace().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleClicked(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        view.getSpace().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                handleReleased(mouseEvent.getX(), mouseEvent.getY());
            }
        });
        view.getMassSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                handleMassSlider();
            }
        });
        view.getMassText().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleMassText();
            }
        });
        view.getProp().getRadiusSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                handleRadiusSlider();
            }
        });
        view.getProp().getRadiusText().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleRadiusText();
            }
        });
        view.getProp().getTimeSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                handleTime();
            }
        });
        view.getProp().getTimeText().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleTimeText();
            }
        });
        view.getProp().getGSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                handleG();
            }
        });
        view.getProp().getgText().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleGText();
            }
        });
        view.getProp().getDensity().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleDefaultDensity();
            }
        });
        view.getProp().getPause().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handlePause();
            }
        });
        view.getProp().getPlay().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handlePlay();
            }
        });
        view.getProp().getNormalT().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleTimeReset();
            }
        });
        view.getProp().getNormalG().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleGReset();
            }
        });
        view.getProp().getClear().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleClear();
            }
        });
        view.getProp().getGrid().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleGrid();
            }
        });
        view.getProp().getBuck().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleBuckToggle();
            }
        });
        view.getProp().getGraphicsLow().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleGraphics(1);
            }
        });
        view.getProp().getGraphicsMed().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleGraphics(2);
            }
        });
        view.getProp().getGraphicsHigh().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                handleGraphics(3);
            }
        });

        RenderTask task = new RenderTask(view.getSpace(), model, this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 33);        //30 fps

        stage.setTitle("Event Horizon");
        stage.setX(-9);
        stage.setY(0);
        stage.setResizable(false);
        //stage.setFullScreen(true);

        Scene scene = new Scene(view, SpacePane.width + 200,SpacePane.height);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }

    public void handleDefaultDensity(){
        Space.toggleDefaultDensity();
        if (Space.isDefaultDensity()) {
            view.getProp().getDensity().setText("Default Density: On");
        }else{
            view.getProp().getDensity().setText("Default Density: Off");
        }
        view.getProp().update();
    }

    public void handlePause(){
        Space.setSavedTimeStep(view.getProp().getTimeSliderValue());
        Space.setTimeStep(0);
        view.getProp().update();
    }

    public void handlePlay(){
        Space.setTimeStep(Space.getSavedTimeStep());
        view.getProp().update();
    }

    public void handleTimeReset(){
        Space.setTimeStep(1);
        Space.setSavedTimeStep(1);
        view.getProp().update();
    }

    public void handleGReset(){
        Space.setConstG(3.5);
        view.getProp().update();
    }

    public void handleClear(){
        model.clearSpace();
    }

    public void handleGrid(){
        view.getSpace().toggleGrid();
        view.getSpace().update();
    }

    public void handleGraphics(int level){
        Space.setExplosionMultiplier(level);
        if(level == 1){
            Space.setMaxBodies(1000);
        }else if(level == 2){
            Space.setMaxBodies(1500);
        }else{
            Space.setMaxBodies(2000);
        }
    }

    public void handleTime(){
        Space.setTimeStep(view.getProp().getTimeSliderValue());
        view.getProp().update();
    }

    public void handleTimeText(){
        double value = Double.parseDouble(view.getProp().getTimeText().getText());
        if(value > 3){
            value = 3;
        }else if (value < -3){
            value = -3;
        }
        Space.setTimeStep(value);
        view.getProp().update();
    }

    public void handleG(){
        Space.setConstG(view.getProp().getGSlider().getValue());
        view.getProp().update();
    }

    public void handleGText(){
        double value = Double.parseDouble(view.getProp().getgText().getText());
        if(value>7){
            value = 7;
        }else if (value<0.1){
            value = 0.1;
        }
        Space.setConstG(value);
        view.getProp().update();
    }

    public void handleCreation(String type, double initX, double initY, double initXV, double initYV){
        if(model.getBodies().size() < Space.getMaxBodies()) {
            Body b = model.createBody(type, initX, initY, model.getQueuedMass(), model.getQueuedRadius(), initXV, initYV);
            view.getSpace().addToSpacePane(b);
        }
    }

    public void handleCreation(Body b){
        if(model.getBodies().size() < Space.getMaxBodies()) {
            model.addBody(b);
            view.getSpace().addToSpacePane(b);
        }
    }

    public void handleReleased(double initX, double initY){         //Calculate velocity based on released positon. Call Space with type of body to create
        int index = view.getProp().getTypeSelect().getSelectionModel().getSelectedIndex();
        double xComponent, yComponent, xv, yv;
        end = new Point2D(initX, initY);

        xComponent = model.calculateXV(start, end);
        yComponent = model.calculateYV(start, end);

        xv = -xComponent / 40;
        yv = -yComponent / 40;
        if (start.getY() > end.getY()) {
            yv = yv * -1;
        }
        if (start.getX() == end.getX() && start.getY() == end.getY()) {
            xv = 0;
            yv = 0;
        }
        if(index == 0){
            handleCreation("Rocky", start.getX(), start.getY(), xv,yv);
        }else if (index == 1){
            handleCreation("GasGiant", start.getX(), start.getY(), xv, yv);
        }else if (index == 2) {
            for (int i = 0; i < model.getQueuedRadius() * Space.getExplosionMultiplier(); i++) {
                handleCreation("Fire", start.getX(), start.getY(), xv, yv);
            }
        }else if (index == 3){
            for(int i = 0;i<model.getQueuedRadius()*Space.getExplosionMultiplier(); i++){
                handleCreation("Gas", start.getX(), start.getY(), xv, yv);
            }
        }else if (index == 4){
            for(int i = 0;i<model.getQueuedRadius()*2; i++){
                handleCreation("Dust", start.getX(), start.getY(), xv, yv);
            }
        }else if (index == 5){
            handleCreation("Immovable",start.getX(), start.getY(), xv, yv);
        }

    }

    public void handleMassSlider(){
        model.setQueuedMass(view.getProp().getMassSliderValue());
        if(Space.isDefaultDensity()){
            model.setQueuedRadius(view.getProp().getMassSliderValue()*3/100);
        }
        view.getProp().update();
    }

    public void handleMassText(){
        double value = view.getProp().getMassTextValue();
        if (value > 5000){
            value = 5000;
        }else if(value < 1){
            value = 1;
        }
        model.setQueuedMass(value);
        if(Space.isDefaultDensity()){
            model.setQueuedRadius(value*3/100);
        }
        view.getProp().update();
    }

    public void handleRadiusSlider(){
        model.setQueuedRadius(view.getProp().getRadiusSliderValue());
        if(Space.isDefaultDensity()){
            model.setQueuedMass(view.getProp().getRadiusSliderValue()*100/3);
        }
        view.getProp().update();
    }
    public void handleRadiusText(){
        double value = view.getProp().getRadiusTextValue();
        if (value > 150){
            value = 150;
        }else if(value < 1){
            value = 1;
        }
        model.setQueuedRadius(value);
        if(Space.isDefaultDensity()){
            model.setQueuedMass(value*100/3);
        }
        view.getProp().update();
    }

    public void handleClicked(double initX, double initY){
        start = new Point2D(initX, initY);
    }

    public void erase(Shape s){
        view.getSpace().erase(s);
    }

    public List<Body> getBodies(){ return model.getBodies(); }
    public Space getModel(){return model;}
    public SpaceView getView(){return view;}

    public void handleBuckToggle(){
        Space.buckshot = !Space.buckshot;
    }

}
