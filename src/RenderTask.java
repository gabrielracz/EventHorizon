import javafx.application.Platform;
import java.time.Clock;
import java.util.TimerTask;

public class RenderTask extends TimerTask {
    private final SpacePane view;
    private final Space model;
    private final SpaceApp controller;
    private boolean offFrame;
    private double click;
    private double elapsed;
    private double frames;


    public RenderTask(SpacePane iView, Space iModel, SpaceApp initController){
        view = iView;
        model = iModel;
        controller = initController;
        offFrame = true;

    }


    public void run(){
        Platform.runLater(new Runnable() {      //Thread safety first!
            @Override
            public void run() {
                update();
            }
        });
    }

    public void update(){
        model.applyGravity();
        view.update();

        if (offFrame){      //Calculate the elapsed time between two frames
            click = Clock.systemUTC().millis();
            offFrame = false;
        }else{
            elapsed = Clock.systemUTC().millis() - click;
            frames = 2/elapsed;
            controller.getView().getSpace().setFps(frames);
            offFrame = true;
        }
    }

}

