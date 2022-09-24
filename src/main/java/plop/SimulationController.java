package plop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
    @FXML public  AnchorPane canvaPane;
    GraphicsContext gc;

    double mouseX;
    double mouseY;
    double lastMouseY = mouseY;
    double lastMouseX = mouseX;
    double previousLastMouseY = lastMouseY;
    double previousLastMouseX = lastMouseX;

    void drawBoids(GraphicsContext gc){
        gc.beginPath();
        //gc.setFill(Color.rgb(255,255,255));
        gc.setFill(Color.rgb(0,0,35));
        int width = (int) canvaPane.getWidth()-1;
        int height = (int) canvaPane.getHeight()-1;

        for (int i=0; i<Stockage.nombreProies;i++){
            Proie temp = Stockage.proies.get(i);
            double lateX = temp.getPreviousX();
            double lateY= temp.getPreviousY();
            double x = temp.getX();
            double y = temp.getY();
            gc.setStroke(Color.rgb(100,200,100));
            gc.strokeRect(x,y,2,2);
            gc.setStroke(Color.rgb(244,244,244));
            gc.setFill(Color.rgb(244,244,244));
            gc.strokeRect(lateX,lateY,2,2);

        }
        for (int i=0; i<Stockage.nombrePredateurs;i++){
            Predateur temp = Stockage.predateurs.get(i);
            double lateX = temp.getPreviousX();
            double lateY= temp.getPreviousY();
            double x = temp.getX();
            double y = temp.getY();
            gc.setStroke(Color.rgb(250,50,50));
            gc.strokeRect(x,y,2,2);
            gc.setStroke(Color.rgb(244,244,244));
            gc.setFill(Color.rgb(244,244,244));
            gc.strokeRect(lateX,lateY,2,2);

        }
        /*canvaPane.addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
            double previousLastMouseY = lastMouseY;
            double previousLastMouseX = lastMouseX;

            double lastMouseY = mouseY;
            double lastMouseX = mouseX;
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            gc.setStroke(Color.rgb(00,00,00));
            gc.strokeRect(mouseX,mouseY,1,1);
            gc.setStroke(Color.rgb(244,244,244));
            gc.fillRect(lastMouseX,lastMouseY,1,1);
            gc.strokeRect(lastMouseX,lastMouseY,5,5);
            gc.fillRect(previousLastMouseX,previousLastMouseY,10,10);
            gc.strokeRect(previousLastMouseX,previousLastMouseY,15,15);
        });*/
        }



    public void startSimulation() throws InterruptedException {
        Stockage.windowHeight = canvaPane.getHeight();
        Stockage.windoWidth = canvaPane.getWidth();
        InitializeBoids.Initialize(Stockage.nombreProies, Stockage.nombrePredateurs);
        drawInitiation(gc);
        drawBoids(gc);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                UpdateBoids.updateProies();
                drawBoids(gc);
            }
        };
        timer.scheduleAtFixedRate(task,100,100);
        }




    private void drawInitiation(GraphicsContext gc){
        gc.setStroke(Color.rgb(100,200,100));
        for (int i = 0;i<Stockage.nombreProies;i++ ){
            gc.strokeRect(Stockage.proies.get(i).getX(),Stockage.proies.get(i).getY(),2,2);
        }
        gc.setStroke(Color.rgb(250,50,50));
        for (int i = 0;i<Stockage.nombrePredateurs;i++ ){
            gc.strokeRect(Stockage.predateurs.get(i).getX(),Stockage.predateurs.get(i).getY(),2,2);
        }
    }













    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Canvas canvas = new Canvas();
        canvaPane.getChildren().add(canvas);
        canvas.widthProperty().bind(canvaPane.widthProperty());
        canvas.heightProperty().bind(canvaPane.heightProperty());
        gc = canvas.getGraphicsContext2D();
        Stockage.gc = gc;
        gc.setFill(Color.rgb(0,50,0,00));
    }
}
