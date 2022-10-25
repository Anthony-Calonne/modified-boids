package plop;

import java.util.TimerTask;
public class UpdateBoids {
    
    static Vec centreGauche = new Vec(Stockage.windowWidth /4,Stockage.windowHeight/2);
    static Vec centreDroit = new Vec(Stockage.windowWidth -Stockage.windowWidth /4,Stockage.windowHeight/2);
    static Vec centreHaut = new Vec(Stockage.windowWidth /2,Stockage.windowHeight-Stockage.windowHeight/4);
    static Vec centreBas = new Vec(Stockage.windowWidth /2,Stockage.windowHeight/4);
    static Vec centreFenetre = new Vec(Stockage.windowWidth/2,Stockage.windowHeight/2);
    static int border = 35;
    static int thinBorder = 3;
    public static TimerTask update(){

        for (int i=0;i<Stockage.nombreProies;i++){ //Proies

            Proie temp;
            temp = Stockage.proies.get(i);
            temp.previousX= temp.getLocalisation().x;
            temp.previousY=temp.getLocalisation().y;
            temp.localisation.add(temp.getDirection());

            Vec loca=temp.localisation;

            if (loca.x<border || loca.x>Stockage.windowWidth-border||loca.y<border||loca.y>Stockage.windowHeight-border){
                recentrer(temp.localisation,temp.direction,centreFenetre);
                temp.border=(int)Stockage.porteeVisuProies;
            } else if (temp.border<0){
                temp.setDirection((View.viewProies(temp.localisation,temp.direction,temp.getGreen(),temp.getBlue(),temp.nombreReproRestantes,i, temp.attaquesSupportees)));
                View.flee(temp.localisation,temp.direction);
            } else{
                temp.border--;
            }

        }

        for (int i=0;i<Stockage.predateurs.size();i++){  //Prédateurs
            Predateur temp;
            temp = Stockage.predateurs.get(i);
            temp.previousX= temp.getLocalisation().x;
            temp.previousY=temp.getLocalisation().y;
            temp.localisation.add(temp.getDirection());
            Vec loca=temp.localisation;

            if (loca.x<border || loca.x>Stockage.windowWidth-border||loca.y<border||loca.y>Stockage.windowHeight-border){
                recentrer(temp.localisation,temp.direction,centreFenetre);
                temp.border=(int)Stockage.porteeVisuPreda;
            } else if (temp.border<0){
                temp.setDirection((View.viewPreda(temp.localisation,temp.direction,i)));
                if (temp.attaque){
                    for (int y = 0; y<10; y++){
                        temp.enduranceRestante--;
                    }
                    if (temp.enduranceRestante<0){
                        temp.attaque=false;
                        temp.nourriture--;
                        temp.enduranceRestante=temp.endurance;
                    }
                } else {
                    for (int y = 0; y<10; y++){
                        temp.enduranceRestante--;
                    }
                    if (temp.enduranceRestante<0){
                        temp.enduranceRestante=temp.endurance;
                        double y=(Math.random() * 256);
                        if (y>temp.probaAttaque && temp.competiteurs<Stockage.competiteursToleres){
                            temp.attaque=true;
                            temp.rangProieSuivie=-1;
                        }
                    }
                }
            } else{
                temp.border--;
            }
            if (temp.nourriture==0){
                Stockage.predateursMorts.add(i);
            }


        }

        return null;
    }
    public static void recentrer(Vec localisation, Vec direction, Vec centre){
        double centreX=centre.x;
        double centreY=centre.y;


        Vec centreTempPourCalc = new Vec(centreX-localisation.x,centreY-localisation.y);
        centreTempPourCalc.div(centreTempPourCalc.mag());
        if (localisation.x>thinBorder && localisation.x<Stockage.windowWidth-thinBorder &&localisation.y>thinBorder && localisation.y<Stockage.windowHeight-thinBorder){
            for (int i = 0; i < 3; i++) {
                direction.add(direction);
            }
        }
        direction.add(centreTempPourCalc);
        direction.div(direction.mag());
    }
}
