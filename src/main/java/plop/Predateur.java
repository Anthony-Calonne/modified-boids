package plop;

public class Predateur {
    public int rank;
    public double x;
    public double y;

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double angle;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPreviousX() {
        return previousX;
    }

    public void setPreviousX(double previousX) {
        this.previousX = previousX;
    }

    public double getPreviousY() {
        return previousY;
    }

    public void setPreviousY(double previousY) {
        this.previousY = previousY;
    }

    public double previousX;
    public double previousY;

    public double getyVise() {
        return yVise;
    }

    public void setyVise(double yVise) {
        this.yVise = yVise;
    }

    public double getxVise() {
        return xVise;
    }

    public void setxVise(double xVise) {
        this.xVise = xVise;
    }

    public double yVise;
    public double xVise;

}
