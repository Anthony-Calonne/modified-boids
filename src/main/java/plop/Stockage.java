package plop;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Stockage {
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
    static ArrayList<Proie> proies = new ArrayList<>();
    static ArrayList<Predateur> predateurs = new ArrayList<>();
    static int vitessePredateurs = 1;

    static GraphicsContext gc;

}
