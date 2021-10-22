import javafx.scene.shape.Circle;
import java.util.List;

public abstract class Body {
    protected Point2D pos;
    protected double mass;
    protected double density;
    protected double radius;
    protected double influenceRadius;
    protected boolean collision;
    public Circle image;
    protected String ejector;


    public Body(double initX, double initY, double initMass, double initRadius) {
        pos = new Point2D(initX, initY);
        mass = initMass;
        radius = initRadius;
        density = mass/radius;
        influenceRadius = 40*Math.sqrt(mass) +3;
        collision = false;
        ejector = "Fire";
    }

    public void applyForce(List<Body> bodies){
        for (int b = 0; b<bodies.size();b++){
            if (!bodies.get(b).equals(this)){
                if (this.getPos().getDistance(bodies.get(b).getPos()) <= this.influenceRadius){     //Is inside sphere of influence
                    bodies.get(b).getForceFrom(this);       //apply force to object
                    this.checkCollision(bodies.get(b));         //Check if its colliding
                }
            }
        }
    }
    public void checkCollision(Body b){
        if (this.getPos().getDistance(b.getPos()) <= radius + b.getRadius()){
            if(b instanceof Ejector){
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

    public void getForceFrom(Body other) {
        double force, acceleration, velocity, xv, yv, theta;
        double constG = Space.constG;

        force = (constG * this.mass * other.mass) / Math.pow(this.pos.getDistance(other.pos), 2);

        acceleration = force / this.mass;

        velocity = acceleration * Space.getTimeStep();

        theta = pos.getAngle(other.pos);

        xv = velocity * Math.cos(theta);
        yv = velocity * Math.sin(theta);

        double tempxv = xv;
        double tempyv = yv;

        if(getPos().getY() > other.getPos().getY()){
            tempyv = tempyv*-1;

        }
        updateVelocities(tempxv, tempyv);
    }
    public abstract void updateVelocities(double xv, double yv);
    public abstract void move();





    public String toString() {
        return this.getClass() + " [" + String.format("%.2f", pos.getX()) + " , " + String.format("%.2f", pos.getY()) + "]";
    }
    public double getMass() {
        return mass;
    }
    public Point2D getPos() {
        return pos;
    }
    public double getRadius() {
        return radius;
    }
    public double getVelocity(){return 0;}
    public void setCollision(Boolean x){ collision = x;}
    public boolean isCollision(){return collision;}
    public String getEjector(){return ejector;}
    public double getXvelocity(){return 0;}
    public double getYvelocity(){return 0;}
}
