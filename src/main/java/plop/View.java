package plop;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class View {

    public static Vec viewProies(Vec localisation,Vec direction,int greenOne,int blueOne,int nombreReproM,int rangM){

        //Initialisation des variables

        double coherence = Stockage.coherenceProies;
        double separation = Stockage.separationProies;
        double alignement = Stockage.alignementProies;
        double porteeVisu = Stockage.porteeVisuProies;
        double vitesse = Stockage.vitesseProies;

        Vec sepa=new Vec(0,0);
        Vec ali=new Vec(0,0);
        Vec cohe = new Vec(0,0);

        for(int i=0;i<Stockage.proies.size();i++){
            Proie temp = Stockage.proies.get(i);
            Vec locaTemp = temp.localisation;
            Vec dirTemp = temp.direction;
            double distanceBoids = Vec.dist(localisation, locaTemp);
            if (distanceBoids < porteeVisu &&distanceBoids!=0) { //Si le boid peut voir l'autre :
                // 1) Si il est proche alors il s'en éloigne
                if (distanceBoids < separation) {
                    sepa.x += localisation.x - locaTemp.x;
                    sepa.y += localisation.y - locaTemp.y;

                }
                if (distanceBoids < alignement && temp.border==0) {
                    ali.x += localisation.x - locaTemp.x;
                    ali.y += localisation.y - locaTemp.y;
                }
                if (distanceBoids < coherence) {
                    cohe.x += dirTemp.x;
                    cohe.y += dirTemp.y;
                }
            }
        }


        for(int i=0;i<Stockage.proies.size();i++){
            Proie temp = Stockage.proies.get(i);
            Vec locaTemp = temp.localisation;
            Vec dirTemp = temp.direction;
            double distanceBoids = Vec.dist(localisation, locaTemp);
            if(sepa.x==0&&sepa.y==0&& distanceBoids<porteeVisu && nombreReproM>0 && temp.nombreReproRestantes>0){        //ajouter la descendance possible par boid
                int nbProies = Stockage.proies.size();
                proiesRepro(localisation, locaTemp, direction, dirTemp, greenOne, blueOne, temp.getGreen(), temp.getBlue());
                int nbProies2 = Stockage.proies.size();
                if (nbProies2>nbProies){
                    Stockage.proies.get(rangM).nombreReproRestantes--;
                    temp.nombreReproRestantes--;
                }
            }
        }

        double sepaLength =1;

        double aliLength =1;
        double coheLength =1;

        if (sepa.x!=0&&sepa.y!=0) {
            sepaLength = (double) sqrt(pow(sepa.x, 2) + pow(sepa.y, 2));
        }
        if (ali.x!=0&&ali.y!=0) {
            aliLength = (double) sqrt(pow(ali.x, 2) + pow(ali.y, 2));
        }
        if (cohe.x!=0&&cohe.y!=0) {
            coheLength = (double) sqrt(pow(cohe.x, 2) + pow(cohe.y, 2));
        }


        sepa.x/=sepaLength;
        sepa.y/=sepaLength;



        ali.x/=aliLength;
        ali.y/=aliLength;

        cohe.x/=coheLength;
        cohe.y/=coheLength;


        direction.x+=sepa.x*separation+cohe.x*coherence+ali.x*alignement;
        direction.y+=sepa.y*separation+cohe.y*coherence+ali.y*alignement;



        double directionLength = sqrt(pow(direction.x, 2) + pow(direction.y, 2));

        direction.div(directionLength);

        return direction;
    }
    public static Vec flee(Vec localisation, Vec direction){
        double fear = Stockage.fear;
        double porteeVisu = Stockage.porteeVisuProies;
        Vec sepa=new Vec(0,0);
        for (int i=0;i<Stockage.predateurs.size();i++){
            Predateur temp = Stockage.predateurs.get(i);
            Vec locaTemp = temp.localisation;
            double distanceBoids = Vec.dist(localisation, locaTemp);
            if (distanceBoids < porteeVisu &&distanceBoids!=0) {
                sepa.x += localisation.x - locaTemp.x;
                sepa.y += localisation.y - locaTemp.y;
            }
        }

        double sepaLength =1;
        if (sepa.x!=0&&sepa.y!=0) {
            sepaLength = (double) sqrt(pow(sepa.x, 2) + pow(sepa.y, 2));
        }

        sepa.x/=sepaLength;
        sepa.y/=sepaLength;

        direction.x+=(sepa.x*fear);
        direction.y+=(sepa.y*fear);

        double directionLength = sqrt(pow(direction.x, 2) + pow(direction.y, 2));

        direction.div(directionLength);

        return direction;

    }
    public static Vec viewPreda(Vec localisation,Vec direction){

        //Initialisation des variables

        double coherence = Stockage.coherencePreda;
        double separation = Stockage.separationPreda;
        double alignement = Stockage.alignementPreda;
        double porteeVisu = Stockage.porteeVisuPreda;
        double vitesse = Stockage.vitessePreda;

        Vec sepa=new Vec(0,0);
        Vec ali=new Vec(0,0);
        Vec cohe = new Vec(0,0);

        for(int i=0;i<Stockage.predateurs.size();i++){
            Predateur temp = Stockage.predateurs.get(i);
                Vec locaTemp = temp.localisation;
                Vec dirTemp = temp.direction;
                double distanceBoids = Vec.dist(localisation, locaTemp);
                if (distanceBoids < porteeVisu &&distanceBoids!=0) { //Si le boid peut voir l'autre :
                    // 1) Si il est proche alors il s'en éloigne
                    if (distanceBoids < separation) {
                        sepa.x += localisation.x - locaTemp.x;
                        sepa.y += localisation.y - locaTemp.y;

                    }
                    if (distanceBoids < alignement && temp.border==0) {
                        ali.x += localisation.x - locaTemp.x;
                        ali.y += localisation.y - locaTemp.y;
                    }
                    if (distanceBoids < coherence) {
                        cohe.x += dirTemp.x;
                        cohe.y += dirTemp.y;
                    }

                    // Ajout de la fonction de reproduction
            }
        }

        double sepaLength =1;
        double aliLength =1;
        double coheLength =1;

        if (sepa.x!=0&&sepa.y!=0) {
            sepaLength = (double) sqrt(pow(sepa.x, 2) + pow(sepa.y, 2));
        }
        if (ali.x!=0&&ali.y!=0) {
            aliLength = (double) sqrt(pow(ali.x, 2) + pow(ali.y, 2));
        }
        if (cohe.x!=0&&cohe.y!=0) {
            coheLength = (double) sqrt(pow(cohe.x, 2) + pow(cohe.y, 2));
        }


        sepa.x/=sepaLength;
        sepa.y/=sepaLength;



        ali.x/=aliLength;
        ali.y/=aliLength;

        cohe.x/=coheLength;
        cohe.y/=coheLength;


        direction.x+=sepa.x*separation+cohe.x*coherence+ali.x*alignement;
        direction.y+=sepa.y*separation+cohe.y*coherence+ali.y*alignement;



        double directionLength = sqrt(pow(direction.x, 2) + pow(direction.y, 2));

        direction.div(directionLength);

        return direction;
    }









    //Systèmes de reproduction

    public static void proiesRepro(Vec localisation, Vec locaTemp, Vec directionP, Vec directionM,int greenOne,int greenTwo,int blueOne,int blueTwo){
        float proba = (float)Math.random() * (100 - 0) + 0;

        Vec sepa=new Vec(0,0);
        if (proba>99.9 && Stockage.proies.size()<300){
            Proie littleOne= new Proie();
            double x = (localisation.x+ locaTemp.x)/2;
            double y = (localisation.y+ locaTemp.y)/2;
            littleOne.localisation=new Vec(x,y);

            double xVise= (directionP.x+directionM.x)/2;
            double yVise= (directionP.y+directionM.y)/2;
            littleOne.direction=new Vec(xVise,yVise);

            int green =  0;
            int blue = 0;

            littleOne.setGreen((greenOne+greenTwo)/2);
            littleOne.setBlue((blueOne+blueTwo)/2);

            littleOne.previousX=0;
            littleOne.previousY=0;

            Stockage.proies.add(littleOne);
        }
    }
}

