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
        for (int i=0;i<Stockage.proies.size();i++){

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
                temp.setDirection((View.viewProies(temp.localisation,temp.direction)));
            } else{
                temp.border--;
            }
            /*if(temp.localisation.x<border){
                recentrer(temp.localisation,temp.direction,centreGauche);
                temp.border=1;
            } else if (temp.localisation.x>Stockage.windowWidth -border) {
                recentrer(temp.localisation,temp.direction,centreDroit);
                temp.border=1;
            } else if (temp.localisation.y<border) {
                recentrer(temp.localisation,temp.direction,centreBas);
                temp.border=1;
            } else if (temp.localisation.y>Stockage.windowHeight-border) {
                recentrer(temp.localisation,temp.direction,centreHaut);
                temp.border=1;
            } else if (temp.localisation.x>border+Stockage.porteeVisuProies&&temp.localisation.x<Stockage.windowWidth-(border+Stockage.porteeVisuProies)&&temp.localisation.y>border+Stockage.porteeVisuProies&&temp.localisation.y<Stockage.windowHeight-(border+Stockage.porteeVisuProies)){
                temp.setDirection(View.viewProies(temp.localisation,temp.direction));
                temp.border=0;
            }*/

        }
        for (int i=0;i<Stockage.predateurs.size();i++){
            Predateur temp;
            temp = Stockage.predateurs.get(i);
            temp.previousX= temp.getLocalisation().x;
            temp.previousY=temp.getLocalisation().y;
            temp.localisation.add(temp.getDirection());
            if(temp.localisation.x<border){
                recentrer(temp.localisation,temp.direction,centreGauche);
            } else if (temp.localisation.x>Stockage.windowWidth-border) {
                recentrer(temp.localisation,temp.direction,centreDroit);
            } else if (temp.localisation.y<border) {
                recentrer(temp.localisation,temp.direction,centreBas);
            } else if (temp.localisation.y>Stockage.windowHeight-border) {
                recentrer(temp.localisation,temp.direction,centreHaut);
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
