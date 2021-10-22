import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class PropertiesPane extends Pane {
    private final SpaceApp controller;
    private final Label massLabel, radiusLabel, timeLabel, gLabel, graphicsLabel, description;
    private final Rectangle propBorder, timeBorder, optionsBorder, descBox;
    private final Slider massSlider, radiusSlider, timeSlider, gSlider;
    private final TextField massText, radiusText,timeText, gText;
    private final ListView<String> typeSelect;
    private final  ArrayList<String> types;
    private final Button clear, density, pause, normalT, normalG, play, graphicsLow,graphicsMed, graphicsHigh, buck, grid;



    public PropertiesPane(SpaceApp initController){
        controller = initController;
        setStyle("-fx-background-color: black;-fx-border-color: white;");

        types = new ArrayList<>();
        types.add("Rocky");
        types.add("Gas Giant");
        types.add("Explosion");
        types.add("Gas Cloud");
        types.add("Dust");
        types.add("Anchored");

        typeSelect = new ListView<>();
        typeSelect.relocate(10,10);
        typeSelect.setPrefSize(380,165);
        typeSelect.setStyle("-fx-cell-focus-inner-border: black;-fx-selection-bar-non-focused: white;-fx-selection-bar:white;-fx-control-inner-background: black; -fx-font-family: 'Consolas';-fx-font-size: 16; -fx-border-color: white");
        typeSelect.getSelectionModel().select(0);
        getChildren().add(typeSelect);


        //Properties
        propBorder = new Rectangle(10,195,380,215);
        propBorder.setStroke(Color.WHITE);
        propBorder.setStrokeWidth(1.75);
        propBorder.setFill(Color.BLACK);
        getChildren().add(propBorder);

        density = new Button("Default Density: Off");
        density.relocate(100,345);
        density.setPrefSize(200,50);
        density.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: white;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(density);

        massLabel = new Label("Mass");
        massLabel.setStyle("-fx-font-family: 'Consolas';-fx-font-size: 18;");
        massLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        massLabel.relocate(25,213);
        getChildren().add(massLabel);

        massSlider = new Slider(0,5000,500);
        massSlider.relocate(20, 243);
        massSlider.setPrefWidth(365);
        massSlider.setPrefHeight(25);
        massSlider.setMin(10);
        massSlider.setShowTickMarks(true);
        //massSlider.setShowTickLabels(true);
        massSlider.setMajorTickUnit(2490);
        massSlider.setMinorTickCount(0);
        massSlider.setStyle("-fx-tick-label-fill: white;");
        getChildren().add(massSlider);

        massText = new TextField();
        massText.relocate(317,205);
        massText.setPrefSize(63,15);
        massText.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-border-color: white;-fx-font-family: 'Consolas';-fx-font-size: 17;");
        getChildren().add(massText);

        radiusSlider = new Slider(1,150,10);
        radiusSlider.relocate(20, 315);
        radiusSlider.setPrefWidth(365);
        radiusSlider.setPrefHeight(25);
        radiusSlider.setShowTickMarks(true);
        //radiusSlider.setShowTickLabels(true);
        radiusSlider.setMajorTickUnit(74);
        radiusSlider.setMinorTickCount(0);
        radiusSlider.setStyle("fx-text-fill: white;");
        getChildren().add(radiusSlider);

        radiusText = new TextField();
        radiusText.relocate(317,275);
        radiusText.setPrefSize(63,15);
        radiusText.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-border-color: white;-fx-font-family: 'Consolas';-fx-font-size: 17;");
        getChildren().add(radiusText);

        radiusLabel = new Label("Radius");
        radiusLabel.setStyle("-fx-font-family: 'Consolas';-fx-font-size: 18;");
        radiusLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        radiusLabel.relocate(25,282);
        getChildren().add(radiusLabel);

        //Time
        timeBorder = new Rectangle(10,430,380,160);
        timeBorder.setStroke(Color.WHITE);
        timeBorder.setStrokeWidth(1.75);
        timeBorder.setFill(Color.BLACK);
        getChildren().add(timeBorder);


        pause = new Button("||");
        pause.relocate(100,440);
        pause.setPrefSize(63,36);
        pause.setStyle("-fx-font: 12 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: white;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(pause);


        play = new Button(">");
        play.relocate(171,440);
        play.setPrefSize(63,36);
        play.setStyle("-fx-font: 18 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: white;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(play);

        normalT = new Button("RESET");
        normalT.relocate(244,440);
        normalT.setPrefSize(63,36);
        normalT.setStyle("-fx-font: 14.5 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: white;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(normalT);

        timeSlider = new Slider(-3,3,1);
        timeSlider.relocate(20, 480);
        timeSlider.setPrefHeight(25);
        timeSlider.setPrefWidth(365);
        timeSlider.setShowTickMarks(true);
        timeSlider.setMajorTickUnit(4);
        timeSlider.setMinorTickCount(0);
        getChildren().add(timeSlider);

        timeText = new TextField();
        timeText.relocate(317,440);
        timeText.setPrefSize(63,15);
        timeText.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-border-color: white;-fx-font-family: 'Consolas';-fx-font-size: 17;");
        getChildren().add(timeText);

        timeLabel = new Label("Time");
        timeLabel.setStyle("-fx-font-family: 'Consolas';-fx-font-size: 18;");
        timeLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        timeLabel.relocate(25,448);
        getChildren().add(timeLabel);

        gSlider = new Slider(0.1,7,1);
        gSlider.relocate(20, 550);
        gSlider.setPrefHeight(25);
        gSlider.setPrefWidth(365);
        gSlider.setShowTickMarks(true);
        gSlider.setMajorTickUnit(3.4);
        gSlider.setMinorTickCount(0);
        getChildren().add(gSlider);

        normalG = new Button("RESET");
        normalG.relocate(244,510);
        normalG.setPrefSize(63,36);
        normalG.setStyle("-fx-font: 14.5 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: white;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(normalG);

        gText = new TextField();
        gText.relocate(317,510);
        gText.setPrefSize(63,15);
        gText.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-border-color: white;-fx-font-family: 'Consolas';-fx-font-size: 17;");
        getChildren().add(gText);

        gLabel = new Label("Big G");
        gLabel.setStyle("-fx-font-family: 'Consolas';-fx-font-size: 18;");
        gLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        gLabel.relocate(25,520);
        getChildren().add(gLabel);

        //Options
        optionsBorder = new Rectangle(10,610,380,200);
        optionsBorder.setStroke(Color.WHITE);
        optionsBorder.setStrokeWidth(1.75);
        optionsBorder.setFill(Color.BLACK);
        getChildren().add(optionsBorder);

        clear = new Button("CLEAR SPACE");
        clear.relocate(20,625);
        clear.setPrefSize(360,45);
        clear.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: white;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(clear);

        buck = new Button("BUCKSHOT");
        buck.relocate(20,680);
        buck.setPrefSize(175,45);
        buck.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: White;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(buck);

        grid = new Button("GRID");
        grid.relocate(205,680);
        grid.setPrefSize(175,45);
        grid.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: White;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(grid);

        graphicsLow = new Button("LOW");
        graphicsLow.relocate(20,765);
        graphicsLow.setPrefSize(113,35);
        graphicsLow.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: White;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(graphicsLow);

        graphicsMed = new Button("MED");
        graphicsMed.relocate(143,765);
        graphicsMed.setPrefSize(113,35);
        graphicsMed.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: White;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(graphicsMed);

        graphicsHigh = new Button("HIGH");
        graphicsHigh.relocate(266,765);
        graphicsHigh.setPrefSize(113,35);
        graphicsHigh.setStyle("-fx-font: 16 Consolas; -fx-base: rgb(0,0,0);-fx-text-fill: White;-fx-border-color: white;-fx-hover-background-color: black");
        getChildren().add(graphicsHigh);

        graphicsLabel = new Label("Graphics");
        graphicsLabel.setStyle("-fx-font-family: 'Consolas';-fx-font-size: 18;");
        graphicsLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        graphicsLabel.relocate(160,735);
        getChildren().add(graphicsLabel);

        //Description
        descBox = new Rectangle(10,830,380,240);
        descBox.setStroke(Color.WHITE);
        descBox.setStrokeWidth(1.75);
        descBox.setFill(Color.BLACK);
        getChildren().add(descBox);

        description = new Label();
        description.setText("Event Horizon Sandbox v1.0\n2D Newtonian Gravity Simulation\nby: Gabriel Racz\n12/21/20\n\nSelect object type from list\nAdjust Properties\nClick, drag, and release to launch\n\nRecommended graphics: MED");
        description.setStyle("-fx-font-family: 'Consolas';-fx-font-size: 16;");
        description.setTextFill(javafx.scene.paint.Color.WHITE);
        description.relocate(20,835);
        getChildren().add(description);




    }
    public void update(){
        //Mass Field
        //System.out.println();
        massText.setText(String.valueOf(String.format("%.0f",controller.getModel().getQueuedMass())));
        massSlider.setValue(controller.getModel().getQueuedMass());
        //Radius Field
        radiusText.setText(String.valueOf(String.format("%.0f",controller.getModel().getQueuedRadius())));
        radiusSlider.setValue(controller.getModel().getQueuedRadius());
        //Time Field
        timeText.setText(String.valueOf(String.format("%.1f",Space.getTimeStep())));
        timeSlider.setValue(Space.getTimeStep());
        //G Field
        gText.setText(String.valueOf(String.format("%.2f",Space.getConstG())));
        gSlider.setValue(Space.getConstG());
        //Type Selection ListView
        typeSelect.setItems(FXCollections.observableArrayList(types));

    }

    public ListView<String> getTypeSelect(){return typeSelect;}

    public TextField getMassText() { return massText; }
    public TextField getRadiusText(){return radiusText;}
    public TextField getTimeText(){return timeText;}
    public TextField getgText(){return gText;}

    public Slider getMassSlider(){return massSlider;}
    public Slider getRadiusSlider(){return radiusSlider;}
    public Slider getTimeSlider(){return timeSlider;}
    public Slider getGSlider(){return gSlider;}

    public double getMassSliderValue(){return massSlider.getValue();}
    public double getMassTextValue(){return Double.parseDouble(massText.getText());}
    public double getRadiusSliderValue(){return radiusSlider.getValue();}
    public double getRadiusTextValue(){return Double.parseDouble(radiusText.getText());}
    public double getTimeSliderValue(){return timeSlider.getValue();}

    public Button getClear(){return clear;}
    public Button getBuck(){return buck;}
    public Button getDensity() {return density;}
    public Button getNormalT(){return normalT;}
    public Button getPause(){return pause;}
    public Button getPlay(){return play;}
    public Button getNormalG() {return normalG;}
    public Button getGraphicsLow() {return graphicsLow;}
    public Button getGraphicsMed(){return graphicsMed;}
    public Button getGraphicsHigh(){return graphicsHigh;}
    public Button getGrid(){return grid;}
}
