package plop;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class View {

    public static Vec viewProies(Vec localisation,Vec direction,int greenOne,int blueOne,int nombreReproM,int rangM, double attaquesSupportees,double PVinit){
        Stockage.proies.get(rangM).PV--;
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
        if (Stockage.proies.get(rangM).PV<0){
            tuerProie(rangM);
        }


        for(int i=0;i<Stockage.proies.size();i++){
            Proie temp = Stockage.proies.get(i);
            Vec locaTemp = temp.localisation;
            Vec dirTemp = temp.direction;
            double distanceBoids = Vec.dist(localisation, locaTemp);
            if(sepa.x==0&&sepa.y==0&& distanceBoids<porteeVisu && nombreReproM>0 && temp.nombreReproRestantes>0){        //ajouter la descendance possible par boid
                int nbProies = Stockage.proies.size();
                proiesRepro(localisation, locaTemp, direction, dirTemp, greenOne, blueOne, temp.getGreen(), temp.getBlue(),temp.attaquesSupportees,attaquesSupportees,PVinit,temp.PVinit );
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
    public static Vec viewPreda(Vec localisation,Vec direction, int rang){

        //Initialisation des variables

        double coherence = Stockage.coherencePreda;
        double separation = Stockage.separationPreda;
        double alignement = Stockage.alignementPreda;
        double porteeVisu = Stockage.porteeVisuPreda;
        double vitesse = Stockage.vitessePreda;

        Vec sepa=new Vec(0,0);
        Vec ali=new Vec(0,0);
        Vec cohe = new Vec(0,0);
        Predateur predateur = Stockage.predateurs.get(rang);
        predateur.competiteurs=0;
        if(predateur.attaque){      //si le prédateur est en phase d'attaque
            if (predateur.rangProieSuivie<0){       //s'il ne suit pas de proie, on compare toutes les proies visibles et il mémorise la plus proche. Ce rang est oublié à chaque début d'initiation du cycle global de chasse.
                double tempLongueur = 100000;
                int tempRang = Stockage.predateurs.get(rang).rangProieSuivie;
                for (int i = 0; i < Stockage.proies.size(); i++) {
                    Proie temp = Stockage.proies.get(i);
                    Vec locaTemp = temp.localisation;
                    double distanceBoids = Vec.dist(localisation, locaTemp);
                    if (distanceBoids < porteeVisu && distanceBoids != 0){
                        if (distanceBoids<tempLongueur){
                            tempLongueur=tempLongueur;
                            tempRang=i;
                        }
                    }
                }
                Stockage.predateurs.get(rang).rangProieSuivie=tempRang;
            } else {        // s'il suit une proie
                ali.x+=Stockage.proies.get(predateur.rangProieSuivie).localisation.x-predateur.localisation.x;
                ali.y+=Stockage.proies.get(predateur.rangProieSuivie).localisation.y-predateur.localisation.y;
                cohe.x+=Stockage.proies.get(predateur.rangProieSuivie).direction.x;
                cohe.y+=Stockage.proies.get(predateur.rangProieSuivie).direction.y;
                attaque(Stockage.predateurs.get(rang).rangProieSuivie,rang);
            }

        }else {
            if(predateur.nourriture>5&&predateur.reproPossible==0){
                predateur.nourriture--;
                predateur.reproPossible++;
            }
            for (int i = 0; i < Stockage.predateurs.size(); i++) {
                double follower = 1;
                Predateur temp = Stockage.predateurs.get(i);
                if (temp.attaque){
                    follower=3;
                }
                Vec locaTemp = temp.localisation;
                Vec dirTemp = temp.direction;
                double distanceBoids = Vec.dist(localisation, locaTemp);
                if (distanceBoids < porteeVisu && distanceBoids != 0) { //Si le boid peut voir l'autre :
                    predateur.competiteurs++;       //compte le nombre de competiteurs
                    if (predateur.reproPossible==1){        //si la reproduction est possible pour 1
                        if (predateur.tempsDepuisReproPossible==Stockage.tempsReproPredateurs){
                            predateur.tempsDepuisReproPossible=0;
                            predateur.reproPossible=0;
                        }else if (temp.reproPossible == 1) {      //si la reproduction est possible pour 2
                            predaRepro(temp, predateur);
                            predateur.reproPossible = 0;
                            temp.reproPossible = 0;
                            predateur.tempsDepuisReproPossible = 0;
                            temp.tempsDepuisReproPossible = 0;
                        }
                    }else{
                        if(predateur.nourriture>Stockage.nourritureNecessairePredateurs){
                            predateur.rangProieSuivie=1;
                            predateur.tempsDepuisReproPossible=0;
                        }
                    }
                    //fonctions de direction
                    // 1) Si il est proche alors il s'en éloigne
                    if (distanceBoids < separation) {
                        sepa.x += localisation.x - locaTemp.x;
                        sepa.y += localisation.y - locaTemp.y;

                    }
                    if (distanceBoids < alignement && temp.border == 0) {
                        ali.x += localisation.x - locaTemp.x;
                        ali.y += localisation.y - locaTemp.y;
                    }
                    if (distanceBoids < coherence) {        //multiplication ici sur la cohérence des direction si l'autre prédateur chasse
                        cohe.x += dirTemp.x*follower;
                        cohe.y += dirTemp.y*follower;
                    }

                    // Ajout de la fonction de reproduction
                }
            }
            if (predateur.reproPossible==1){
                predateur.tempsDepuisReproPossible++;
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

    private static void predaRepro(Predateur temp, Predateur predateur) {
        Predateur littleOne = new Predateur();
        double x = (temp.localisation.x+ predateur.localisation.x)/2;
        double y = (temp.localisation.y+ predateur.localisation.y)/2;
        littleOne.localisation=new Vec(x,y);

        double xVise= (predateur.direction.x+temp.direction.x)/2;
        double yVise= (predateur.direction.y+temp.direction.y)/2;
        littleOne.direction=new Vec(xVise,yVise);

        littleOne.red=(temp.red+predateur.red)/2;
        littleOne.blue=(temp.blue+predateur.blue)/2;

        littleOne.probaAttaque= (int) (Math.random() * 2550);
        littleOne.endurance=littleOne.red*10;
        littleOne.enduranceRestante= littleOne.endurance;
        littleOne.PVinit=(temp.PVinit+predateur.PVinit)/2;
        littleOne.PV= littleOne.PVinit;
        Stockage.predateurs.add(littleOne);
    }

    public static void tuerProie(int rang){
        Vec locaProie = Stockage.proies.get(rang).localisation;
        Stockage.localisationProiesTuees.add(locaProie);
        nourrirPreda(locaProie);
        Stockage.proies.remove(rang);
        Stockage.nombreProies--;
        for (int i=0;i<Stockage.predateurs.size();i++){
            if (Stockage.predateurs.get(i).rangProieSuivie>rang){
                Stockage.predateurs.get(i).rangProieSuivie--;
            } else if (Stockage.predateurs.get(i).rangProieSuivie==rang){
                Stockage.predateurs.get(i).rangProieSuivie=-1;
            }
        }
    }
    public static void nourrirPreda(Vec locaProie){     //nourriture et culture
        for (int i = 0; i<Stockage.predateurs.size();i++){
            double distance = Vec.dist(locaProie,Stockage.predateurs.get(i).localisation);
            if (distance<Stockage.porteeVisuPreda){
                Stockage.predateurs.get(i).nourriture++;
                Stockage.predateurs.get(i).probaAttaque=(Stockage.predateurs.get(i).probaAttaque+Stockage.predaDataACopier)/2;
            }
        }
    }

    public static void attaque(int rangProie, int rangPredateur){
        double degatsPreda = Stockage.degatsPreda;
        Predateur predateur=Stockage.predateurs.get(rangPredateur);
        Proie proie = Stockage.proies.get(rangProie);
        Vec locaPreda=predateur.localisation;
        Vec locaProie=proie.localisation;
        double degatsProie = 0;
        double distance=Vec.dist(locaPreda,locaProie);
        if (distance<Stockage.porteeVisuPreda){
            if (distance>1){
                degatsProie=(degatsPreda/distance)*(predateur.red/255)*(proie.green/255);
            } else{
                degatsProie=degatsPreda*2;
            }
        }
        Stockage.proies.get(rangProie).attaquesSubies+=degatsProie;
        if (Stockage.proies.get(rangProie).attaquesSupportees<Stockage.proies.get(rangProie).attaquesSubies){
            Stockage.predaDataACopier=predateur.probaAttaque;
            tuerProie(rangProie);
        }
    }





    //Systèmes de reproduction

    public static void proiesRepro(Vec localisation, Vec locaTemp, Vec directionP, Vec directionM,int greenOne,int greenTwo,int blueOne,int blueTwo, double attaqueSupOne, double attaqueSupTwo,double PVIun, double PVIdeux){
        float proba = (float)Math.random() * (100 - 0) + 0;

        Vec sepa=new Vec(0,0);
        if (proba>99.9 && Stockage.proies.size()<Stockage.maxProies){
            Proie littleOne= new Proie();
            double x = (localisation.x+ locaTemp.x)/2;
            double y = (localisation.y+ locaTemp.y)/2;
            littleOne.localisation=new Vec(x,y);

            double xVise= (directionP.x+directionM.x)/2;
            double yVise= (directionP.y+directionM.y)/2;
            littleOne.direction=new Vec(xVise,yVise);

            littleOne.setGreen((greenOne+greenTwo)/2);
            littleOne.setBlue((blueOne+blueTwo)/2);
            double attaqueSup=(attaqueSupOne+attaqueSupTwo)/2;
            littleOne.attaquesSupportees=attaqueSup;
            littleOne.attaquesSubies=0;

            littleOne.PVinit=(PVIun+PVIdeux)/2;
            littleOne.PV= littleOne.PVinit;

            littleOne.previousX=0;
            littleOne.previousY=0;

            Stockage.proies.add(littleOne);
        }
    }
}

