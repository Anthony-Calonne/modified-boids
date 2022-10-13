package plop;

public class Predateur {
    public boolean attaque=false;
    public int rangProieSuivie=-1;
    public int nourriture = 3;
    public int probaAttaque;
    public double endurance;
    public double enduranceRestante;
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




/*Pour le moment : proposition :


Autre idée :
Les prédateurs restent en groupe ou suivent les proies, on peut leur donner deux variables : le temps, lié à leur endurance.
La deuxième variable est le pourcentage d'essai d'attaque de proies/suivi de proies.
Si ils suivent une proie, il le font durant un temps égal à leur endurance, de même pour le suivi des prédateurs.
Si ils suivent une proie, automatiquement, qu'ils aient réussi ou non, ils retournent ensuite vers les prédateurs.
Si ils suivent les prédateurs, à chaque fin d'endurance, ils ont une chance égal au pourcentage d'essai d'attaque de commencer à viser les proies.

On leur attribue une troisième variable, le nombre d'essai restant, s'il est égal à zéro, ils meurent.
S'ils attrapent une proie, il est réinitialisé, et la probab d'attaquer est copiée dans les prédas qui peuvent le voir (mettre une animation graphique pour le montrer).
Il faut donc également créer un système de réussite/perte lors de la chasse.*/
