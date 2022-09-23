package plop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    public static void main(String args[]){
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {		//Crée la première fenêtre quand le programme est lancé

        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("SceneOuverture.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),700,600, Color.web("#353535"));

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
