package plop;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class View {

    public static Vec viewProies(Vec localisation,Vec direction){

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
            {
                Vec locaTemp = temp.localisation;
                Vec dirTemp = temp.direction;
                double distanceBoids = Vec.dist(localisation, locaTemp);
                if (distanceBoids < porteeVisu) { //Si le boid peut voir l'autre :
                    // 1) Si il est proche alors il s'en éloigne
                    if (distanceBoids < separation) {
                        sepa.x += locaTemp.x - localisation.x;
                        sepa.y += locaTemp.y - localisation.y;

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
        }

        double sepaLength = (double) sqrt(pow(sepa.x, 2) + pow(sepa.y, 2));
        double aliLength = (double) sqrt(pow(ali.x, 2) + pow(ali.y, 2));
        double coheLength = (double) sqrt(pow(ali.x, 2) + pow(ali.y, 2));

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
}

