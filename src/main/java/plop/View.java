package plop;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class View {

    public static Vec viewProies(Vec localisation,Vec direction,int greenOne,int blueOne,int nombreReproM,int rangM, double attaquesSupportees,double PVinit,double tempsDepuisRepro){
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
            if(sepa.x==0&&sepa.y==0&& distanceBoids<porteeVisu  && tempsDepuisRepro*((100*Stockage.maxProies)/Stockage.nombreProies)>20){        //ajouter la descendance possible par boid
                int nbProies = Stockage.proies.size();
                proiesRepro(localisation, locaTemp, direction, dirTemp, greenOne, blueOne, temp.getGreen(), temp.getBlue(),temp.attaquesSupportees,attaquesSupportees,PVinit,temp.PVinit );
                int nbProies2 = Stockage.proies.size();
                if (nbProies2>nbProies){
                    Stockage.proies.get(rangM).nombreReproRestantes--;
                    Stockage.proies.get(rangM).tempsDepuisRepro=0;
                    temp.tempsDepuisRepro=0;
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
            if(predateur.nourriture>10&&predateur.reproPossible==0){
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
        if (Stockage.heredite){
            littleOne.red=(temp.red+predateur.red+ (int) (Math.random() * (255 - 90) + 90))/3;
            littleOne.blue=(temp.blue+predateur.blue + (int) (Math.random() * (100 - 10)) + 10)/3;
            littleOne.PVinit=(temp.PVinit+predateur.PVinit)/2;

            //calcul de l'hérédité mendélienne
            //1
            double P1 = Math.random();
            if (P1>0.5){
                littleOne.bleu1dom = temp.bleu1dom;
                littleOne.bleu1=temp.bleu1;
            }else {
                littleOne.bleu1dom=temp.bleu2dom;
                littleOne.bleu1=temp.bleu2;
            }
            double P2 = Math.random();
            if (P2>0.5){
                littleOne.bleu2dom = predateur.bleu1dom;
                littleOne.bleu2 = predateur.bleu1;
            } else{
                littleOne.bleu2dom= predateur.bleu2dom;
                littleOne.bleu2 = predateur.bleu2;
            }

            double P3 = Math.random();
            if (P3>0.5){
                littleOne.rouge1dom = temp.rouge1dom;
                littleOne.rouge1=temp.rouge1;
            }else {
                littleOne.rouge1dom=temp.rouge2dom;
                littleOne.rouge1=temp.rouge2;
            }
            double P4 = Math.random();
            if (P4>0.5){
                littleOne.rouge2dom = predateur.rouge1dom;
                littleOne.rouge2 = predateur.rouge1;
            } else{
                littleOne.rouge2dom= predateur.rouge2dom;
                littleOne.rouge2 = predateur.rouge2;
            }
            Stockage.nombreReproDepuisMutation++;
            if (Stockage.txMutation!=0){
                if (Stockage.nombreReproDepuisMutation>Stockage.txMutation){
                    Stockage.nombreReproDepuisMutation=0;
                    littleOne.rouge1 = (int) (Math.random()*255);
                    littleOne.bleu1 = (int) (Math.random()*255);
                    littleOne.rouge2 = (int) (Math.random()*255);
                    littleOne.bleu2 = (int) (Math.random()*255);

                    double B1 = Math.random();
                    if (B1>0.5){
                        littleOne.bleu1dom = true;
                    }
                    double B2 = Math.random();
                    if (B2>0.5){
                        littleOne.bleu2dom = true;
                    }

                    double R1 = Math.random();
                    if (R1>0.5){
                        littleOne.rouge1dom = true;
                    }
                    double R2 = Math.random();
                    if (R2>0.5){
                        littleOne.rouge2dom = true;
                    }

                    if(littleOne.bleu1dom){
                        if (littleOne.bleu2dom){
                            littleOne.blue = (littleOne.bleu1+littleOne.bleu2)/2;
                        } else {
                            littleOne.blue = littleOne.bleu1;
                        }
                    } else if (littleOne.bleu2dom){
                        littleOne.blue = littleOne.bleu2;
                    }


                    if(littleOne.rouge1dom){
                        if (littleOne.rouge2dom){
                            littleOne.red = (littleOne.rouge1+littleOne.rouge2)/2;
                        } else {
                            littleOne.red = littleOne.rouge1;
                        }
                    } else if (littleOne.rouge2dom){
                        littleOne.red = littleOne.rouge2;
                    }
                }
            }




            if(littleOne.bleu1dom){
                if (littleOne.bleu2dom){
                    littleOne.blue = (littleOne.bleu1+littleOne.bleu2)/2;
                } else {
                    littleOne.blue = littleOne.bleu1;
                }
            } else if (littleOne.bleu2dom){
                littleOne.blue = littleOne.bleu2;
            }


            if(littleOne.rouge1dom){
                if (littleOne.rouge2dom){
                    littleOne.red = (littleOne.rouge1+littleOne.rouge2)/2;
                } else {
                    littleOne.red = littleOne.rouge1;
                }
            } else if (littleOne.rouge2dom){
                littleOne.red = littleOne.rouge2;
            }

        }else{
            littleOne.setRed((int) (Math.random() * (255 - 90) + 90));
            littleOne.setBlue( (int) (Math.random() * (100 - 10)) + 10);
            littleOne.PVinit=(Math.random()*(8000-300))+300;


            temp.previousX=0;
            temp.previousY=0;
            temp.nourriture = (int) (Math.random() * (4 - 3) + 3);
            temp.red = (int) (Math.random() * (255 - 90) + 90);
            temp.blue = (int) (Math.random() * (100 - 10)) + 10;


            temp.rouge1 = (int) (Math.random()*255);
            temp.bleu1 = (int) (Math.random()*255);
            temp.rouge2 = (int) (Math.random()*255);
            temp.bleu2 = (int) (Math.random()*255);

            double B1 = Math.random();
            if (B1>0.5){
                temp.bleu1dom = true;
            }
            double B2 = Math.random();
            if (B2>0.5){
                temp.bleu2dom = true;
            }

            double R1 = Math.random();
            if (R1>0.5){
                temp.rouge1dom = true;
            }
            double R2 = Math.random();
            if (R2>0.5){
                temp.rouge2dom = true;
            }

            if(temp.bleu1dom){
                if (temp.bleu2dom){
                    temp.blue = (temp.bleu1+temp.bleu2)/2;
                } else {
                    temp.blue = temp.bleu1;
                }
            } else if (temp.bleu2dom){
                temp.blue = temp.bleu2;
            }  else {littleOne.blue = (littleOne.bleu1+littleOne.bleu2)/2;}


            if(temp.rouge1dom){
                if (temp.rouge2dom){
                    temp.red = (temp.rouge1+temp.rouge2)/2;
                } else {
                    temp.red = temp.rouge1;
                }
            } else if (temp.rouge2dom){
                temp.red = temp.rouge2;
            }  else {littleOne.red = (littleOne.rouge1+littleOne.rouge2)/2;}
        }

        littleOne.probaAttaque= (int) (Math.random() * 2550);
        littleOne.endurance=littleOne.red*10;
        littleOne.enduranceRestante= littleOne.endurance;
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
                if (Stockage.predateurs.get(i).nourriture>7 && Stockage.nombrePredateurs<15){
                    Stockage.predateurs.get(i).PV=Stockage.predateurs.get(i).PVinit;
                }
                if (Stockage.culture) {
                    Stockage.predateurs.get(i).probaAttaque = (Stockage.predateurs.get(i).probaAttaque + Stockage.predaDataACopier) / 2;
                }
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
                degatsProie=(degatsPreda/distance)*((proie.green*predateur.probaAttaque)/(255*2550))*((proie.blue*predateur.probaAttaque)/(255*2550))*((predateur.blue)/255)*((predateur.red)/255);
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
        if (proba>99.9 && Stockage.proies.size()<Stockage.maxProies) {
            int portee = (int) (Math.random() * (3 - 1) + 1);
            for (int z = 0; z<portee; z++){
                Proie littleOne = new Proie();
                double al = Math.random() * 15;
                double x = (localisation.x + locaTemp.x + al) / 2;
                double y = (localisation.y + locaTemp.y + al) / 2;
                littleOne.localisation = new Vec(x, y);

                double xVise = (directionP.x + directionM.x) / 2;
                double yVise = (directionP.y + directionM.y) / 2;
                littleOne.direction = new Vec(xVise, yVise);

                if (Stockage.heredite) {
                    littleOne.setGreen((greenOne + greenTwo + (int) (Math.random() * (100 - 90)) + 90) / 3);
                    littleOne.setBlue((blueOne + blueTwo + (int) (Math.random() * (255 - 10)) + 10) / 3);
                    littleOne.PVinit = (PVIun + PVIdeux) / 2;
                } else {
                    littleOne.setBlue((int) (Math.random() * (100 - 10)) + 10);
                    littleOne.setGreen((int) (Math.random() * (255 - 90)) + 90);
                    littleOne.PVinit = (Math.random() * (2000 - 300)) + 300;
                }
                Stockage.nombreReproDepuisMutation++;
                if (Stockage.txMutation!=0){
                    if (Stockage.nombreReproDepuisMutation>Stockage.txMutation){
                        Stockage.nombreReproDepuisMutation=0;
                        littleOne.vert1 = (int) (Math.random()*255);
                        littleOne.bleu1 = (int) (Math.random()*255);
                        littleOne.vert2 = (int) (Math.random()*255);
                        littleOne.bleu2 = (int) (Math.random()*255);

                        double B1 = Math.random();
                        if (B1>0.5){
                            littleOne.bleu1dom = true;
                        }
                        double B2 = Math.random();
                        if (B2>0.5){
                            littleOne.bleu2dom = true;
                        }

                        double R1 = Math.random();
                        if (R1>0.5){
                            littleOne.vert1dom = true;
                        }
                        double R2 = Math.random();
                        if (R2>0.5){
                            littleOne.vert2dom = true;
                        }

                        if(littleOne.bleu1dom){
                            if (littleOne.bleu2dom){
                                littleOne.blue = (littleOne.bleu1+littleOne.bleu2)/2;
                            } else {
                                littleOne.blue = littleOne.bleu1;
                            }
                        } else if (littleOne.bleu2dom){
                            littleOne.blue = littleOne.bleu2;
                        }  else {littleOne.blue = (littleOne.bleu1+littleOne.bleu2)/2;}


                        if(littleOne.vert1dom){
                            if (littleOne.vert2dom){
                                littleOne.green = (littleOne.vert1+littleOne.vert2)/2;
                            } else {
                                littleOne.green = littleOne.vert1;
                            }
                        } else if (littleOne.vert2dom){
                            littleOne.green = littleOne.vert2;
                        }  else {littleOne.green = (littleOne.vert1+littleOne.vert2)/2;}
                    }
                }

                double attaqueSup = (attaqueSupOne + attaqueSupTwo) / 2;
                littleOne.attaquesSupportees = attaqueSup;
                littleOne.attaquesSubies = 0;

                littleOne.PV = littleOne.PVinit;

                littleOne.previousX = 0;
                littleOne.previousY = 0;


                Stockage.proies.add(littleOne);
            }
        }
    }
}

