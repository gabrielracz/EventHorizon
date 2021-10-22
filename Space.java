import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Space {
    private static double timeStep = 1;
    public static double constG = 3.5;
    public static boolean buckshot = false;
    private static boolean defaultDensity;
    private static double savedTimeStep = 1;
    private static int MAX_BODIES;
    public static int EXPLOSION_MULTIPLIER;
    List<Body> bodies;
    List<Body> scrap;
    public final int xDimension = SpacePane.width;
    public final int yDimension = SpacePane.height;
    public double queuedMass;
    public double queuedRadius;
    private final SpaceApp controller;



    public Space(SpaceApp initController){
        bodies = new ArrayList<>(1500);
        scrap = new ArrayList<>();
        queuedMass = 100;
        queuedRadius = 15;
        controller = initController;
        MAX_BODIES = 1500;
        EXPLOSION_MULTIPLIER = 2;
    }

    public static Space createSpace(SpaceApp initController){
        Space space = new Space(initController);
        return space;
    }

    public void clearSpace(){
        for(int i=0; i<bodies.size(); i++){
            controller.getView().getSpace().erase(bodies.get(i).image);
        }
        bodies.clear();
        scrap.clear();
        //System.out.println(Runtime.getRuntime().totalMemory());
        //System.out.println(Runtime.getRuntime().freeMemory());
    }

    public Body createBody(String type, double initX, double initY, double initMass, double initRadius, double initXV, double initYV){
        Body a;
        if (type.equals("Rocky")){
            a = new Rocky(initX, initY, initMass, initRadius,initXV,initYV);
        }else if (type.equals("GasGiant")){
            a = new GasGiant(initX, initY, initMass, initRadius, initXV, initYV);
        }else if (type.equals("Fire")){
            a = new Fire(initX, initY, initXV, initYV,queuedRadius/2);
        }else if (type.equals("Gas")){
            a = new Gas(initX, initY, initXV, initYV,queuedRadius);
        }else if (type.equals("Dust")){
            a = new Dust(initX, initY, initXV, initYV,queuedRadius/2);
        }else if (type.equals("Immovable")){
            a = new Immovable(initX, initY, initMass, initRadius);
        }else{
            a = new Rocky(initX, initY, initMass, initRadius,initXV,initYV);
        }
        bodies.add(a);
        return a;
    }

    public void addBody(Body b){
        bodies.add(b);
    }

    public boolean applyGravity(){
        //Apply force, check for  then move
        for(int b = 0; b<bodies.size();b++){
            bodies.get(b).applyForce(bodies);
            if (bodies.get(b).getPos().getX() + bodies.get(b).getRadius() < -100 || bodies.get(b).getPos().getX()  - bodies.get(b).getRadius() > xDimension + 100 || bodies.get(b).getPos().getY() + bodies.get(b).getRadius() < -400 || bodies.get(b).getPos().getY() - bodies.get(b).getRadius() > yDimension + 400){
                scrap.add(bodies.get(b));
                controller.erase(bodies.get(b).image);
            }
            else if (bodies.get(b).isCollision()){
                scrap.add(bodies.get(b));
                controller.erase(bodies.get(b).image);
                if (!(bodies.get(b) instanceof Fire)) {
                    explode(bodies.get(b));
                }
            }
            bodies.get(b).move();
        }

        //remove scraps from bodies list, need this because if removed in first pass, causes concurrent modification error
        for (int b = 0; b<scrap.size();b++){
            bodies.remove(scrap.get(b));
        }
        scrap.clear();
        return true;
    }

    private void explode(Body b){        //generate explosion based on what the body's ejector type is
        String type = b.getEjector();
        double explodedXV, explodedYV;
        if(Space.buckshot){
            explodedXV = -b.getXvelocity();
            explodedYV = -b.getYvelocity();
        }else{
            explodedXV = 0;
            explodedYV = 0;
        }
        if (type.equals("Fire")){
            for(int i =0; i<b.getRadius()*EXPLOSION_MULTIPLIER; i++){
                controller.handleCreation(new Fire(b.getPos().getX(), b.getPos().getY(), explodedXV,explodedYV, b.getRadius()));
            }
        }else if (type.equals("Gas")) {
            for (int i = 0; i < b.getRadius() * EXPLOSION_MULTIPLIER; i++) {
                controller.handleCreation(new Gas(b.getPos().getX(), b.getPos().getY(), explodedXV,explodedYV, b.getRadius()));
            }
        }else if(type.equals("GasFire")){
            for(int i = 0; i < EXPLOSION_MULTIPLIER; i++){
                controller.handleCreation(new Fire(b.getPos().getX(), b.getPos().getY(), explodedXV,explodedYV, b.getRadius()));
            }
        }else{
            //Do nothing
        }
    }


    public double calculateYV(Point2D start, Point2D end){
        double distance, theta;
        distance = start.getDistance(end);
        theta = Math.acos(start.getXDistance(end) / start.getDistance(end));
        return distance*Math.sin(theta);
    }
    public double calculateXV(Point2D start, Point2D end){
        double distance, theta;
        distance = start.getDistance(end);
        theta = Math.acos(start.getXDistance(end) / start.getDistance(end));
        return distance*Math.cos(theta);
    }




    public static double getTimeStep() { return timeStep; }
    public static void setTimeStep(double time){timeStep = time;}
    public static double getConstG(){return constG;}
    public static void setConstG(double g){constG = g;}
    public static boolean isDefaultDensity(){return defaultDensity;}
    public static void toggleDefaultDensity(){defaultDensity = !defaultDensity;}
    public static double getSavedTimeStep(){return savedTimeStep;}
    public static void setSavedTimeStep(double saved){savedTimeStep = saved;}
    public static void setExplosionMultiplier(int x){EXPLOSION_MULTIPLIER = x;}
    public static int getExplosionMultiplier(){return EXPLOSION_MULTIPLIER;}
    public static int getMaxBodies(){return MAX_BODIES;}
    public static void setMaxBodies(int x){MAX_BODIES = x;}


    public double getQueuedMass(){return queuedMass;}
    public double getQueuedRadius(){return queuedRadius;}
    public List<Body> getBodies() { return bodies; }
    public void setQueuedMass(double initM){queuedMass = initM;}
    public void setQueuedRadius(double initR){queuedRadius = initR;}


}
