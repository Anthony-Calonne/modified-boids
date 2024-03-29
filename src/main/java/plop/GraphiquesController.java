package plop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GraphiquesController implements Initializable {

    @FXML private Button effectifs;

    @FXML private Button probaAttaque;

    @FXML private Button suiviPreda;

    @FXML private Button suiviProies;
    @FXML
    AnchorPane GraphiquePane;
    @FXML LineChart<String,Number> Graphique;

    static boolean Effectif = false;
    static boolean ProbaAttaque = false;
    static boolean SuiviPreda = false;
    static boolean SuiviProies = false;
    public void reinitialiserGraphique(){
        if (Effectif) {
            Effectif=false;
            Graphique.getData().remove(Stockage.SerieEffectifProies);
            Graphique.getData().remove(Stockage.SerieeffectifPredateurs);
        }else if (ProbaAttaque){
            ProbaAttaque=false;
            Graphique.getData().remove(Stockage.SerieSeuilChasseMoyen);
        } else if (SuiviPreda) {
            SuiviPreda=false;
            Graphique.getData().remove(Stockage.SeriecouleurBleuPreda);
            Graphique.getData().remove(Stockage.SeriecouleurRougeMoyenne);
        } else if (SuiviProies) {
            SuiviProies=false;
            Graphique.getData().remove(Stockage.SeriecouleurBleuProies);
            Graphique.getData().remove(Stockage.SeriecouleurVertMoyenne);
        }
    }
    @FXML void effectifs(ActionEvent event) {
        reinitialiserGraphique();
        Graphique.setTitle("Effectifs");
        Graphique.getData().add(Stockage.SerieEffectifProies);
        Graphique.getData().add(Stockage.SerieeffectifPredateurs);

        Effectif=true;
    }

    @FXML void probaAttaque(ActionEvent event) {
        reinitialiserGraphique();
        Graphique.setTitle("Seuil moyen de chasse");
        Graphique.getData().add(Stockage.SerieSeuilChasseMoyen);
        ProbaAttaque=true;
    }

    @FXML void suiviPreda(ActionEvent event) {
        reinitialiserGraphique();
        Graphique.setTitle("Couleur moyenne des prédateurs (rouge et bleu)");
        Graphique.getData().add(Stockage.SeriecouleurBleuPreda);
        Graphique.getData().add(Stockage.SeriecouleurRougeMoyenne);
        SuiviPreda=true;
    }

    @FXML void suiviProies(ActionEvent event) {
        reinitialiserGraphique();
        Graphique.setTitle("Couleur moyenne des proies (vert et bleu)");
        Graphique.getData().add(Stockage.SeriecouleurBleuProies);
        Graphique.getData().add(Stockage.SeriecouleurVertMoyenne);
        SuiviProies=true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Graphique.setAnimated(false);
        Stockage.SeriecouleurVertMoyenne.setName("Vert moyen des proies");
        Stockage.SeriecouleurBleuProies.setName("Bleu moyen des proies");
        Stockage.SeriecouleurRougeMoyenne.setName("Rouge moyen des prédateurs");
        Stockage.SeriecouleurBleuPreda.setName("Bleu moyen des prédateurs");
        Stockage.SerieeffectifPredateurs.setName("Nombre de prédateurs");
        Stockage.SerieEffectifProies.setName("Nombre de proies");
        Stockage.SerieSeuilChasseMoyen.setName("Seuil moyen à partir duquel un prédateur commence à chasser");
    }
}