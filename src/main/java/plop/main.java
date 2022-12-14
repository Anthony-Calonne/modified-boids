package plop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class main extends Application {

    private Scene scene;
    private Stage stage;
    private Parent root;

    public static void main(String[] args){
        Application.launch(args);
    }

    void switchToSceneSimulation() throws IOException  {
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/plop/SceneSimulation.fxml")));
        Scene scene2 = new Scene(root,850,600,Color.web("#353535"));
        Stage simuStage = new Stage();
        simuStage.setScene(scene2);
        //javafx.scene.image.Image logo = new Image("prey.png");
        //simuStage.getIcons().add(logo);
        simuStage.setTitle("Simulation");
        simuStage.show();
        simuStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    void switchToSceneGraphiques() throws IOException  {
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/plop/SceneGraphiques.fxml")));
        Scene scene2 = new Scene(root,850,600,Color.web("#353535"));
        Stage graphsStage = new Stage();
        graphsStage.setScene(scene2);
        //Image logo = new Image("");
        //graphsStage.getIcons().add(logo);
        graphsStage.setTitle("Graphiques");
        graphsStage.show();
        graphsStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }
    public void closeWindowEvent(WindowEvent event){
        Stockage.WindowClosed=0;
        Stockage.isInterupted=true;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {		//Crée la première fenêtre quand le programme est lancé
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/plop/SceneOuverture.fxml")));
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
