package plop;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.controlsfx.tools.Platform;

import java.awt.event.MouseEvent;
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
    @FXML public Text nbProiesRestantes;
    @FXML public Text nbPredateursRestants;
    @FXML public Button traces;
    GraphicsContext gc;
    Canvas canvas = new Canvas();

    double mouseX;
    double mouseY;
    double lastMouseY = mouseY;
    double lastMouseX = mouseX;
    double previousLastMouseY = lastMouseY;
    double previousLastMouseX = lastMouseX;
    Color backGroundColor = Color.rgb(40,80,120);
    void drawBoids(GraphicsContext gc){
        gc.beginPath();
        //gc.setFill(Color.rgb(255,255,255));
        gc.setFill(backGroundColor);
        int width = (int) canvaPane.getWidth()-1;
        int height = (int) canvaPane.getHeight()-1;

        for (int i=0; i<Stockage.nombreProies;i++){
            Proie temp = Stockage.proies.get(i);
            double lateX = temp.getPreviousX();
            double lateY= temp.getPreviousY();
            double x = temp.getLocalisation().x;
            double y = temp.getLocalisation().y;
            int green  = temp.getGreen();
            int blue = temp.getBlue();

            gc.setStroke(backGroundColor);
            gc.setFill(backGroundColor);
            gc.strokeRect(lateX-2,lateY-2,6,6);
            if (Stockage.traces){
                gc.fillRect(lateX - 2, lateY - 2, 6, 6);
            }
            gc.setFill(Color.rgb(0,green,blue));
            gc.setStroke(Color.rgb(0,green,blue));
            gc.strokeRect(x,y,3,3);
            gc.fillRect(x,y,3,3);
        }
        for (int i=0; i<Stockage.nombrePredateurs;i++){
            Predateur temp = Stockage.predateurs.get(i);
            double lateX = temp.getPreviousX();
            double lateY= temp.getPreviousY();
            double x = temp.getLocalisation().x;
            double y = temp.getLocalisation().y;
            int red = temp.getRed();
            int blue = temp.getBlue();

            gc.setStroke(backGroundColor);
            gc.setFill(backGroundColor);
            gc.strokeRect(lateX-2,lateY-2,6,6);
            if (Stockage.traces){
                gc.fillRect(lateX - 2, lateY - 2, 6, 6);
            }

            gc.setFill(Color.rgb(red,50,blue));
            gc.setStroke(Color.rgb(red,50,blue));
            gc.fillRect(x,y,3,3);
            gc.strokeRect(x,y,3,3);
        }
        }

    public void traces(){
        if (Stockage.traces){
            Stockage.traces=false;
            gc.clearRect(0, 0,canvas.getWidth(),canvas.getHeight());
        } else{
            Stockage.traces=true;
        }
    }

    public void startSimulation() throws InterruptedException {
        start.setDisable(true);
        Stockage.windowHeight = gc.getCanvas().getHeight();
        Stockage.windowWidth = gc.getCanvas().getWidth();
        InitializeBoids.Initialize(Stockage.nombreProies, Stockage.nombrePredateurs);
        drawInitiation(gc);
        drawBoids(gc);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                UpdateBoids.update();
                drawBoids(gc);
                updateDataSet();
                updateShownData();
                if (Stockage.WindowClosed==0){
                    timer.cancel();
                    timer.purge();
                }
            }
        };
        timer.scheduleAtFixedRate(task,50,50);

    }



    public void updateDataSet(){
        Stockage.alignementProies=aliProies.getValue();
        Stockage.alignementPreda=aliPreda.getValue();
        Stockage.porteeVisuProies=visuProies.getValue();
        Stockage.porteeVisuPreda=visuPreda.getValue();
        Stockage.coherenceProies=coheProies.getValue();
        Stockage.coherencePreda=cohePreda.getValue();
        Stockage.separationProies=sepaProies.getValue();
        Stockage.separationPreda=sepaPreda.getValue();
    }

    public void updateShownData(){
        nbProiesRestantes.setText("Proies : "+Stockage.proies.size());
        nbPredateursRestants.setText("Predateurs : "+ Stockage.predateurs.size());
    }



    private void drawInitiation(GraphicsContext gc){
        gc.setStroke(Color.rgb(100,200,100));
        for (int i = 0;i<Stockage.nombreProies;i++ ){
            gc.strokeRect(Stockage.proies.get(i).getLocalisation().x,Stockage.proies.get(i).getLocalisation().y,2,2);
        }
        gc.setStroke(Color.rgb(250,50,50));
        for (int i = 0;i<Stockage.nombrePredateurs;i++ ){
            gc.strokeRect(Stockage.predateurs.get(i).getLocalisation().x,Stockage.predateurs.get(i).getLocalisation().y,2,2);
        }
    }













    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvaPane.getChildren().add(canvas);
        canvas.widthProperty().bind(canvaPane.widthProperty());
        canvas.heightProperty().bind(canvaPane.heightProperty());
        gc = canvas.getGraphicsContext2D();
        Stockage.gc = gc;
        gc.setFill(Color.rgb(0,50,0,00));

    }
}
