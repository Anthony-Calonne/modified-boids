package plop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    @FXML private Text textError;

    int nombreProies =0;
    int nombrePredateurs=0;



    public void demarrer(ActionEvent e){
        System.out.println(stage.toString());
        String Proies = nbProies.getText();
        String Predateurs= nbPredateurs.getText();
        try {
            nombreProies = Integer.parseInt(Proies);
            nombrePredateurs= Integer.parseInt(Predateurs);
        } catch (Exception f){
            textError.setText("Veuillez entrer des nombres entre 0 et 120");
            textError.setText("aa" + nombrePredateurs);
    }
        if(nombreProies>120 || nombreProies<0 ||nombrePredateurs>120||nombrePredateurs<0){
            textError.setText("Veuillez entrer des nombres entre 0 et 120");
        } else{
            try{
                System.out.println("1");
                switchToSceneSimulation();
            } catch(IOException p){
                System.out.println("pbm");
            }
        }
}

void switchToSceneSimulation() throws IOException  {
    FXMLLoader loader = new FXMLLoader(main.class.getResource("SceneSimulation.fxml"));
    Stage plop = //pbm à régler demain : trouver comment accéder au stage affiché.
    Scene scene = new Scene(loader.load(),700,600);
    Stage stage = (Stage) demarrer.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
}
}