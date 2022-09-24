package plop;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {

    @FXML private Slider aliPreda;
    @FXML private Slider aliProies;
    @FXML private Slider cohePreda;
    @FXML private Slider coheProies;
    @FXML private Slider sepaPreda;
    @FXML private Slider sepaProies;
    @FXML private Slider visuPreda;
    @FXML private Slider visuProies;
    @FXML private Slider vitessePreda;
    @FXML private Slider vitesseProies;
    @FXML private Button start;
    @FXML public AnchorPane canvaPane;
    GraphicsContext gc;
    private void drawLines(GraphicsContext gc){
        gc.beginPath();
        int width = (int) canvaPane.getWidth()-1;
        int height = (int) canvaPane.getHeight()-1;
        gc.moveTo(0, 0);
        gc.lineTo(0, height);
        gc.lineTo(width, 0);
        gc.lineTo(width, height);
        gc.stroke();
    }
    public void startSimulation(){
        drawLines(gc);
    }















    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Canvas canvas = new Canvas();
        canvaPane.getChildren().add(canvas);
        canvas.widthProperty().bind(canvaPane.widthProperty());
        canvas.heightProperty().bind(canvaPane.heightProperty());
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(0,50,0,00));
    }
}
