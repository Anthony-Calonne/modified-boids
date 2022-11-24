package plop;

import java.util.Random;
import java.lang.Math;

public class InitializeBoids {
    static public void Initialize(int proies, int predateurs){

        Stockage.alignementProies=50;
        Stockage.coherenceProies=50;
        Stockage.separationProies=30;
        Stockage.vitesseProies=50;
        Stockage.porteeVisuProies=60;


        for (int i=0; i<proies; i++){
            Proie temp = new Proie();
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

            temp.vert1 = (int) (Math.random()*255);
            temp.bleu1 = (int) (Math.random()*255);
            temp.vert2 = (int) (Math.random()*255);
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
                temp.vert1dom = true;
            }
            double R2 = Math.random();
            if (R2>0.5){
                temp.vert2dom = true;
            }

            if(temp.bleu1dom){
                if (temp.bleu2dom){
                    temp.blue = (temp.bleu1+temp.bleu2)/2;
                } else {
                    temp.blue = temp.bleu1;
                }
            } else if (temp.bleu2dom){
                temp.blue = temp.bleu2;
            } else {temp.blue = (temp.bleu1+temp.bleu2)/2;}


            if(temp.vert1dom){
                if (temp.vert2dom){
                    temp.green = (temp.vert1+temp.vert2)/2;
                } else {
                    temp.green = temp.vert1;
                }
            } else if (temp.vert2dom){
                temp.green = temp.vert2;
            } else {temp.green = (temp.vert1+temp.vert2)/2;}


            temp.previousX=0;
            temp.previousY=0;
            temp.PVinit=(Math.random()*(2000-300))+300;
            temp.PV=temp.PVinit;
            temp.attaquesSubies=0;
            temp.attaquesSupportees=Math.random()*2000;
            Stockage.proies.add(temp);


        }
        for (int i=0; i<predateurs; i++){
            Predateur temp = new Predateur();
            temp.rank=i;



            double x=new Random().nextDouble() * (Stockage.windowWidth - 0);
            double y=new Random().nextDouble() * (Stockage.windowHeight - 0);

            temp.PVinit=(Math.random()*(8000-300))+300;
            temp.PV=temp.PVinit;

            temp.localisation =new Vec(x,y);

            double angleX=(Math.random() * (2)) -1;
            double angleY=(Math.random() * (2)) -1;
            double newAngleX=angleX/(Math.sqrt((Math.pow(angleX,2)+Math.pow(angleY,2))));
            double newAngleY=angleY/(Math.sqrt((Math.pow(angleX,2)+Math.pow(angleY,2))));
            temp.direction = new Vec(newAngleX,newAngleY);

            temp.previousX=0;
            temp.previousY=0;
            temp.nourriture = (int) (Math.random() * (4 - 3) + 3);

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
            } else {temp.blue = (temp.bleu1+temp.bleu2)/2;}


            if(temp.rouge1dom){
                if (temp.rouge2dom){
                    temp.red = (temp.rouge1+temp.rouge2)/2;
                } else {
                    temp.red = temp.rouge1;
                }
            } else if (temp.rouge2dom){
                temp.red = temp.rouge2;
            } else {temp.red = (temp.rouge1+temp.rouge2)/2;}

            temp.probaAttaque= (int) (Math.random() * 2550);
            temp.endurance=temp.red*10;
            temp.enduranceRestante=temp.endurance;


            Stockage.predateurs.add(temp);
        }
    }
}
