package plop;

import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Stockage {
    static FXMLLoader currentFxmlLoader = new FXMLLoader(main.class.getResource("SceneOuverture.fxml"));
    static int nombreProies;
    static int nombrePredateurs;
    static double windowHeight;
    static double windoWidth;
    static ArrayList<Proie> proies = new ArrayList<>();
    static ArrayList<Predateur> predateurs = new ArrayList<>();
    static int vitesseProies = 1;
    static int vitessePredateurs = 1;
    static GraphicsContext gc;

}
