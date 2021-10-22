import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Ejector extends Movable{
    Point2D parentPos;
    double parentRadius;
    Random rand = new Random();
    private int ejectionSpeed = 5;

    public Ejector(double initX, double initY, double initMass, double initRadius, double initXV, double initYV, int initES, int initFizzle, double initParentRadius){
        super(initX, initY, initMass, initRadius, initXV, initYV);
        ejectionSpeed = initES;
        parentPos = new Point2D(initX, initY);
        parentRadius = initParentRadius;
        pos = calculatePositionCircular();
        setFizzle(initFizzle);
        randomVelocity();
    }

    private Point2D calculatePositionCircular(){

        double x, y, radius;
        boolean switcher = rand.nextBoolean();



        if (switcher){      //Need this due to weird results if using only random x values. Makes circle look like two sided PacMan. I think it's because it's not likely to get two randInts that are the same right after eachother
            radius = rand.nextInt((int) parentRadius)+1;
            x = rand.nextInt((int)(radius)) - rand.nextDouble();
            y = (Math.sqrt(Math.pow(radius,2)-Math.pow(x,2)));
        }else{
            radius = rand.nextInt((int) parentRadius)+1;
            y = rand.nextInt((int)(radius)) - rand.nextDouble();
            x = (Math.sqrt(Math.pow(radius,2)-Math.pow(y,2)));
        }

        int xSign = rand.nextInt(2);
        if (xSign == 1){
            x = -x;
        }
        int ySign = rand.nextInt(2);
        if (ySign == 1){
            y = -y;
        }
        y = (parentPos.getY() + y);
        x = parentPos.getX() + x;

        return new Point2D(x,y);

    }

    private void randomVelocity(){
        double velocity = rand.nextInt(ejectionSpeed) + rand.nextDouble();
        yvelocity += calculateYV(velocity);
        xvelocity += calculateXV(velocity);
    }

    private double calculateYV(double velocity){
        double theta = pos.getAngle(parentPos);
        double yv = velocity * Math.sin(theta);
        if (pos.getY() < parentPos.getY()){
            return -yv;
        }else{
            return yv;
        }

    }

    public void setFizzle(int fizzleTime){
        Timer fizzle = new Timer();
        TimerTask burnout = new TimerTask() {
            @Override
            public void run() {
                collision = true;
                cancel();
            }
        };
        long randomDelay = rand.nextInt(12)*250+(long)((fizzleTime*1000)/Math.abs(Space.getTimeStep()));
        fizzle.schedule(burnout, randomDelay);
    }
    private double calculateXV(double velocity){
        double theta = pos.getAngle(parentPos);
        double xv = velocity * Math.cos(theta);
        return -xv;
    }

    public void checkCollision(Body b){
        if (this.getPos().getDistance(b.getPos()) <= radius + b.getRadius() && (!(b instanceof Ejector))){
            this.setCollision(true);
            }
    }
}
