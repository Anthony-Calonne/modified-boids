package plop;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class OuvertureController {
    @FXML private TextField nbProies;
    @FXML private TextField nbPredateurs;
    @FXML private Button demarrer;
    @FXML private Button graphiques;
    @FXML private Text textError;
    private Scene scene;
    private Stage stage;
    private Parent root;

    int nombreProies =0;
    int nombrePredateurs=0;



    public void demarrer(){
        String Proies = nbProies.getText();
        String Predateurs= nbPredateurs.getText();
        try {
            nombreProies = Integer.parseInt(Proies);
            Stockage.nombreProies = nombreProies;
            nombrePredateurs= Integer.parseInt(Predateurs);
            Stockage.nombrePredateurs = nombrePredateurs;
        } catch (Exception f){
            textError.setText("Veuillez entrer des nombres entre 0 et 120");
            textError.setText("aa" + nombrePredateurs);
    }
        if(nombreProies>120 || nombreProies<0 ||nombrePredateurs>120||nombrePredateurs<0){
            textError.setText("Veuillez entrer des nombres entre 0 et 120");
        } else {
            try {
                switchToSceneSimulation();
            } catch (Exception p) {
                System.out.println("pbm");
            }
        }
    }
    public void graphiques(){
                switchToSceneGraphiques();
    }
public void switchToSceneSimulation(){
        main main = new main();
            try {
                main.switchToSceneSimulation();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

}
    public void switchToSceneGraphiques(){
        main main = new main();
        try {
            main.switchToSceneGraphiques();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}