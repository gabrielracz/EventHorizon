public class Point2D {
    private double x;
    private double y;

    public Point2D(double initx, double inity){
        x = initx;
        y = inity;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistance(Point2D other){
        double a = other.getY()-this.getY();
        double b = other.getX()-this.getX();

       return Math.sqrt(Math.pow(a,2)+Math.pow(b, 2));
    }
    public double getAngle(Point2D other){
        return Math.acos(getXDistance(other) / getDistance(other));
    }



    public double getXDistance(Point2D other){
        return other.getX() - this.getX();
    }

    public double getYDistance(Point2D other){
        return other.getY()-this.getY();
    }

    public String toString(){
        return "[" + getX() + "," + getY() + "]";
    }
}
