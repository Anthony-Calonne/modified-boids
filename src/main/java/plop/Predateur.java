package plop;

public class Predateur {
    public Vec getDirection() {
        return direction;
    }

    public void setAngle(Vec angle) {
        this.angle = angle;
    }

    public Vec angle;

    public void setDirection(Vec direction) {
        this.direction = direction;
    }

    public Vec getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Vec localisation) {
        this.localisation = localisation;
    }

    public Vec direction;
    public Vec localisation;
    public int border=0;
    public int rank;

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int blue;
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int red;

    public Vec getAngle() {
        return angle;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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


}
