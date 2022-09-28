package plop;

public class Proie {
    public int getGreen() {
        return green;
    }
    public int border=0;
    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public Vec getLocalisation() {
        return localisation;
    }

    public Vec getDirection() {
        return direction;
    }

    public void setDirection(Vec direction) {
        this.direction = direction;
    }

    public Vec direction;

    public void setLocalisation(Vec localisation) {
        this.localisation = localisation;
    }


    public Vec localisation;
    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int blue;
    public int green;
    public int rank;

    public double previousX;
    public double previousY;

    public Vec getAngle() {
        return angle;
    }

    public void setAngle(Vec angle) {
        this.angle = angle;
    }

    public Vec angle;

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




}
