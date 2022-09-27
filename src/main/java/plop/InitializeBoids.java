package plop;

import java.util.Random;
import java.lang.Math;

public class InitializeBoids {
    static public void Initialize(int proies, int predateurs){
        for (int i=0; i<proies; i++){
            Proie temp = new Proie();
            temp.rank=i;
            double x=new Random().nextDouble() * (Stockage.windowWidth - 0);
            double y=new Random().nextDouble() * (Stockage.windowHeight - 0);


            temp.localisation =new Vec(x,y);


            double angleX=(Math.random() * (1 +1)) -1;
            double angleY=(Math.random() * (1 +1)) - 1;
            double newAngleX=angleX/(Math.sqrt((Math.pow(angleX,2)+Math.pow(angleY,2))));
            double newAngleY=angleY/(Math.sqrt((Math.pow(angleX,2)+Math.pow(angleY,2))));
            temp.direction = new Vec(newAngleX,newAngleY);
            //Ajout de la division par la norme pour que l'angle n'influe pas sur la vitesse. C'est ici qu'on pourra ajouter le paramètre de vitesse par la suite.

            temp.green = (int) (Math.random() * (255 - 90)) + 90;
            temp.blue = (int) (Math.random() * (100 - 10)) + 10;
            temp.previousX=0;
            temp.previousY=0;
            Stockage.proies.add(temp);


        }
        for (int i=0; i<predateurs; i++){
            Predateur temp = new Predateur();
            temp.rank=i;

            double x=new Random().nextDouble() * (Stockage.windowWidth - 0);
            double y=new Random().nextDouble() * (Stockage.windowHeight - 0);



            temp.localisation =new Vec(x,y);

            double angleX=(Math.random() * (2)) -1;
            double angleY=(Math.random() * (2)) -1;
            double newAngleX=angleX/(Math.sqrt((Math.pow(angleX,2)+Math.pow(angleY,2))));
            double newAngleY=angleY/(Math.sqrt((Math.pow(angleX,2)+Math.pow(angleY,2))));
            temp.direction = new Vec(newAngleX,newAngleY);

            temp.previousX=0;
            temp.previousY=0;
            temp.red = (int) (Math.random() * (255 - 90) + 90);
            temp.blue = (int) (Math.random() * (100 - 10)) + 10;
            Stockage.predateurs.add(temp);
        }
    }
}
