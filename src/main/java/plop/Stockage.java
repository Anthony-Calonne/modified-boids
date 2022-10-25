package plop;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Stockage {
    public static boolean traces=true;
    public static int WindowClosed=1;
    public static int competiteursToleres = 5;
    static double degatsPreda = 500;
    static double distanceNutritionPreda = 20;

    public static int nourritureNecessairePredateurs = 3;
    public static int tempsReproPredateurs=1000;
    public static int maxProies=1500;
    static int iteration = 0;
    static double fear = 1;

    static FXMLLoader currentFxmlLoader = new FXMLLoader(main.class.getResource("SceneOuverture.fxml"));
    static int nombreProies;
    static int nombrePredateurs;
    static double windowHeight;
    static double windowWidth;
    static double coherenceProies;
    static double separationProies;
    static double alignementProies;
    static double porteeVisuProies;
    static double vitesseProies;
    static double coherencePreda;
    static double separationPreda;
    static double alignementPreda;
    static double porteeVisuPreda;
    static double vitessePreda;
    static int predaDataACopier;
    static ArrayList<Vec> localisationProiesTuees = new ArrayList<>();
    static ArrayList<Proie> proies = new ArrayList<>();
    static ArrayList<Predateur> predateurs = new ArrayList<>();
    static ArrayList<Integer> predateursMorts = new ArrayList<Integer>();
    static int vitessePredateurs = 1;

    static GraphicsContext gc;

}
