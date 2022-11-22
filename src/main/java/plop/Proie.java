package plop;

public class Proie {
    public int border=0;
    public int nombreReproRestantes =5;
    public int tempsDepuisRepro = 0;
    public Vec direction;
    public Vec localisation;

    public int blue;
    public int green;

    public double previousX;
    public double previousY;
    public double PVinit;
    public double PV;
    public double attaquesSupportees;
    public double attaquesSubies;




    public int getGreen() {
        return green;
    }
    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
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


    public void setLocalisation(Vec localisation) {
        this.localisation = localisation;
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
