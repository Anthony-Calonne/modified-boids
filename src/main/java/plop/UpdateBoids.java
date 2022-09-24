package plop;

import java.util.TimerTask;

public class UpdateBoids {
    public static TimerTask updateProies(){
        for (int i=0;i<Stockage.proies.size();i++){
            Proie temp;
            temp = Stockage.proies.get(i);
            temp.previousX= temp.getX();
            temp.previousY=temp.getY();
            if (temp.getX()- temp.getxVise()<0){
                temp.x+=Stockage.vitesseProies;
            } else{
                temp.x-=Stockage.vitesseProies;
            }
            if (temp.getY()- temp.getyVise()<0){
                temp.y+=Stockage.vitesseProies;
            } else{
                temp.y-=Stockage.vitesseProies;                // PBM : va plus vite si en diagonale que si en ligne droite
            }
        }
        for (int i=0;i<Stockage.predateurs.size();i++){
            Predateur temp;
            temp = Stockage.predateurs.get(i);
            temp.previousX= temp.getX();
            temp.previousY=temp.getY();
            if (temp.getX()- temp.getxVise()<0){
                temp.x+=Stockage.vitessePredateurs;
            } else{
                temp.x-=Stockage.vitessePredateurs;
            }
            if (temp.getY()- temp.getyVise()<0){
                temp.y+=Stockage.vitessePredateurs;
            } else{
                temp.y-=Stockage.vitessePredateurs;                // PBM : va plus vite si en diagonale que si en ligne droite
            }
        }
        return null;
    }
    public void updatePredateurs(){

    }
}
