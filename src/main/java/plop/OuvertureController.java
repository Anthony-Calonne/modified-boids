package plop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OuvertureController implements Initializable {
    @FXML private TextField nbCycles;
    @FXML private TextField nbProies;
    @FXML private TextField nbPredateurs;
    @FXML private Button TransmiCultu;
    @FXML private TextField txMutation;
    @FXML private Button Heredite;
    @FXML private Button demarrer;
    @FXML private Button graphiques;
    @FXML private Text textError;
    private Scene scene;
    private Stage stage;
    private Parent root;

    int nombreProies =0;
    int nombrePredateurs=0;
    int nombreCycles = 0;
    int tauxMutation = 0;


    public void heredite() {
        if (Stockage.heredite){
            Heredite.setText("Pas de transmission héréditaire");
            Stockage.heredite=false;
        } else{
            Heredite.setText("Transmission héréditaire");
            Stockage.heredite=true;
        }
    }

    public void culture() {
        if (Stockage.culture){
            TransmiCultu.setText("Pas de transmission culturelle");
            Stockage.culture=false;
        } else{
            TransmiCultu.setText("Transmission culturelle");
            Stockage.culture=true;
        }
    }



        public void demarrer(){
        String Proies = nbProies.getText();
        String Predateurs= nbPredateurs.getText();
        String NbCycles = nbCycles.getText();
        String TxMutation = txMutation.getText();
        try {
            nombreProies = Integer.parseInt(Proies);
            Stockage.nombreProies = nombreProies;
            tauxMutation = Integer.parseInt(TxMutation);
            if (tauxMutation!=0){
                Stockage.txMutation = 1000/tauxMutation;
            } else {
                Stockage.txMutation = 0;
            }
            nombrePredateurs= Integer.parseInt(Predateurs);
            Stockage.nombrePredateurs = nombrePredateurs;
            nombreCycles = Integer.parseInt(NbCycles);
            if (nombreCycles==0){
                nombreCycles=1;
            }
            Stockage.nombreCyclesActualisation = nombreCycles;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TransmiCultu.setText("Transmission culturelle");
        Heredite.setText("Transmission héréditaire");
    }
}