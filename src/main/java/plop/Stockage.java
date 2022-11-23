package plop;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.Date;

public class Stockage {
    public static boolean heredite = true;
    public static boolean culture = true;
    public static boolean traces=true;
    public static int WindowClosed=1;
    public static int competiteursToleres = 5;
    static double degatsPreda = 100;
    static double distanceNutritionPreda = 20;

    public static int nourritureNecessairePredateurs = 3;
    public static int tempsReproPredateurs=800;
    public static int maxProies=1500;
    static int iteration = 0;
    static double fear = 1;

    static FXMLLoader currentFxmlLoader = new FXMLLoader(main.class.getResource("SceneOuverture.fxml"));
    static int nombreProies;
    static int nombrePredateurs;
    static int txMutation;
    static int nombreReproDepuisMutation = 0;
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
    static boolean isInterupted = false;
    static int predaDataACopier;
    static ArrayList<Vec> localisationProiesTuees = new ArrayList<>();
    static ArrayList<Proie> proies = new ArrayList<>();
    static ArrayList<Predateur> predateurs = new ArrayList<>();
    static ArrayList<Integer> predateursMorts = new ArrayList<Integer>();
    static int vitessePredateurs = 1;

    static GraphicsContext gc;
    static Date debut;
    static int nombreCyclesActualisation;
    //partie Graphiques
    static XYChart.Series<String,Number> SerieEffectifProies = new XYChart.Series<String,Number>();
    static XYChart.Series<String,Number> SerieeffectifPredateurs = new XYChart.Series<String,Number>();
    static XYChart.Series<String,Number> SerieSeuilChasseMoyen = new XYChart.Series<String,Number>();
    static XYChart.Series<String,Number> SeriecouleurVertMoyenne = new XYChart.Series<String,Number>();
    static XYChart.Series<String,Number> SeriecouleurBleuProies = new XYChart.Series<String,Number>();
    static XYChart.Series<String,Number> SeriecouleurRougeMoyenne = new XYChart.Series<String,Number>();
    static XYChart.Series<String,Number> SeriecouleurBleuPreda = new XYChart.Series<String,Number>();

    static void clearAll(){

        predateurs.clear();
        proies.clear();
        localisationProiesTuees.clear();
        predateursMorts.clear();
        SerieEffectifProies.getData().clear();
        SerieeffectifPredateurs.getData().clear();
        SerieSeuilChasseMoyen.getData().clear();
        SeriecouleurVertMoyenne.getData().clear();
        SeriecouleurBleuProies.getData().clear();
        SeriecouleurRougeMoyenne.getData().clear();
        SeriecouleurBleuPreda.getData().clear();
    }
}
