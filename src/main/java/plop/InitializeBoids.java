package plop;

import java.util.Random;

public class InitializeBoids {
    static public void Initialize(int proies, int predateurs){
        for (int i=0; i<proies; i++){
            Proie temp = new Proie();
            temp.rank=i;
            temp.x=new Random().nextDouble() * (Stockage.windoWidth - 0);
            temp.y=new Random().nextDouble() * (Stockage.windowHeight - 0);
            temp.previousX=0;
            temp.previousY=0;
            temp.xVise=new Random().nextDouble() * (Stockage.windoWidth - 0);
            temp.yVise=new Random().nextDouble() * (Stockage.windowHeight - 0);
            Stockage.proies.add(temp);
        }
        for (int i=0; i<predateurs; i++){
            Predateur temp = new Predateur();
            temp.rank=i;
            temp.x=new Random().nextDouble() * (Stockage.windoWidth - 0);
            temp.y=new Random().nextDouble() * (Stockage.windowHeight - 0);
            temp.previousX=0;
            temp.previousY=0;
            temp.xVise=new Random().nextDouble() * (Stockage.windoWidth - 0);
            temp.yVise=new Random().nextDouble() * (Stockage.windowHeight - 0);
            Stockage.predateurs.add(temp);
        }
    }
}
