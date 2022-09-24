package plop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class main extends Application {

    private Scene scene;
    private Stage stage;
    private Parent root;

    public static void main(String args[]){
        Application.launch(args);
    }

    void switchToSceneSimulation() throws IOException  {
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SceneSimulation.fxml")));
        Scene scene2 = new Scene(root,850,600,Color.web("#353535"));
        Stage simuStage = new Stage();
        simuStage.setScene(scene2);
        //Image logo = new Image("");
        //simuStage.getIcons().add(logo);
        simuStage.setTitle("Simulation");
        simuStage.show();

    }
    @Override
    public void start(Stage primaryStage) throws IOException {		//Crée la première fenêtre quand le programme est lancé
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SceneOuverture.fxml")));
        Scene scene = new Scene(root);

        /*FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("SceneOuverture.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),700,600, Color.web("#353535"));
        */
        //String css = this.getClass().getResource("mef.css").toExternalForm();
        //scene.getStylesheets().add(css);
        //Image logo = new Image("");		//logo et titre
        //primaryStage.getIcons().add(logo);
        primaryStage.setTitle("Boids");
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);

        primaryStage.setResizable(true);
        primaryStage.setFullScreen(false);
        primaryStage.setFullScreenExitHint("Appuyez sur la touche Echap pour quitter le mode plein écran");


        primaryStage.setScene(scene);

        primaryStage.show();

        ///
    }



}
