package plop;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML public  AnchorPane canvaPane;
    @FXML public Text nbProiesRestantes;
    @FXML public Text nbPredateursRestants;
    @FXML public Button traces;
    @FXML public Circle couleurProieInit;
    @FXML public Circle couleurProieActu;
    @FXML public Circle couleurPredaInit;
    @FXML public Circle couleurPredaActu;
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
        for (int i=0; i<Stockage.predateurs.size();i++){
            Predateur temp = Stockage.predateurs.get(i);
            double lateX = temp.getPreviousX();
            double lateY= temp.getPreviousY();
            double x = temp.getLocalisation().x;
            double y = temp.getLocalisation().y;
            int red = temp.getRed();
            int blue = temp.getBlue();
            if (temp.attaque){
                red=0;
                blue=0;
            }

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
        final int[] compteur = {0};
        start.setDisable(true);
        Stockage.windowHeight = gc.getCanvas().getHeight();
        Stockage.windowWidth = gc.getCanvas().getWidth();
        InitializeBoids.Initialize(Stockage.nombreProies, Stockage.nombrePredateurs);
        drawInitiation(gc);
        drawBoids(gc);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50),e ->{
            compteur[0]++;
            Stockage.nombreProies=Stockage.proies.size();
            UpdateBoids.update();
            drawBoids(gc);
            updateDataSet();
            updateShownData();
            killPreys();
            couleurPredaActu.setFill(updateColor(1));
            couleurProieActu.setFill(updateColor(0));
            killPredators();
            if (cleaningList2.size()>0){
                cleanPreys();
            }
            if (Stockage.predateursMorts.size()!=0){
                cleanPredators();
            }
            if (compteur[0] %5==0){
                actualiserDonneesGraphiques();
            }
        }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void actualiserDonneesGraphiques() {
        Date now = new Date();

        //predateurs
        double bleuPreda=0;
        double rougePreda=0;
        int probaAttaque=0;
        int effectifPreda=Stockage.predateurs.size();
        for(int i=0;i<Stockage.predateurs.size();i++){
            bleuPreda+=Stockage.predateurs.get(i).getBlue();
            rougePreda+=Stockage.predateurs.get(i).getRed();
            probaAttaque+=Stockage.predateurs.get(i).probaAttaque;
        }
        bleuPreda/=effectifPreda;
        rougePreda/=effectifPreda;
        probaAttaque/=effectifPreda;
        Stockage.SerieeffectifPredateurs.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),effectifPreda));
        Stockage.SeriecouleurBleuPreda.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),bleuPreda));
        Stockage.SeriecouleurRougeMoyenne.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),rougePreda));
        Stockage.SerieprobaAttaqueMoyenne.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),probaAttaque));


        //proies
        double bleuProies=0;
        double vertProies=0;
        int effectifProies=Stockage.proies.size();
        for (int i=0;i<Stockage.proies.size();i++){
            bleuProies+=Stockage.proies.get(i).getBlue();
            vertProies+=Stockage.proies.get(i).getGreen();
        }
        bleuProies/=effectifProies;
        vertProies/=effectifProies;
        Stockage.SeriecouleurBleuProies.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),bleuProies));
        Stockage.SeriecouleurVertMoyenne.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),vertProies));
        Stockage.SerieEffectifProies.getData().add(new XYChart.Data<String,Number>(simpleDateFormat(now),effectifProies));
    }

    private String simpleDateFormat(Date now) {
        String hours = String.valueOf(now.getHours());
        String minutes = String.valueOf(now.getMinutes());
        String seconds = String.valueOf(now.getSeconds());
        return(hours+":"+minutes+":"+seconds);
    }


    public ArrayList<Vec> cleaningList = new ArrayList<Vec>();
    public ArrayList<Vec> cleaningList2 = new ArrayList<Vec>();
    public void killPredators(){
        for (int i = 0; i<Stockage.predateursMorts.size();i++ ){
            cleaningList.add(Stockage.predateurs.get(i).getLocalisation());
            Stockage.predateurs.remove(Stockage.predateursMorts.get(i));
            for(int y = 0;y<Stockage.predateursMorts.size();y++){
                if (Stockage.predateursMorts.get(y)>i){
                    Stockage.predateursMorts.set(y,Stockage.predateursMorts.get(y)-1);
                }
            }
        }

    }
    public void killPreys(){
        for (int i = 0; i<Stockage.localisationProiesTuees.size();i++ ){
            cleaningList2.add(Stockage.localisationProiesTuees.get(i));
        }
        Stockage.localisationProiesTuees.clear();
    }
    public void cleanPreys(){
        gc.setStroke(backGroundColor);
        gc.setFill(backGroundColor);
        for (int i=0;i<cleaningList2.size();i++){
            gc.strokeRect(cleaningList2.get(i).x-2,cleaningList2.get(i).y,6,6);
            gc.fillRect(cleaningList2.get(i).x - 2, cleaningList2.get(i).y - 2, 6, 6);
        }
        cleaningList2.clear();
    }
    public void cleanPredators(){
        gc.setStroke(backGroundColor);
        gc.setFill(backGroundColor);
        for(int i= 0; i< Stockage.predateursMorts.size();i++){
            gc.strokeRect(cleaningList.get(i).x-2,cleaningList.get(i).y,6,6);
            gc.fillRect(cleaningList.get(i).x - 2, cleaningList.get(i).y - 2, 6, 6);
        }
        Stockage.predateursMorts.clear();
        cleaningList.clear();
    }

    public Color updateColor(int i){        //met à jour les couleurs du FXML
        Color color = null;
        if (i==1&&Stockage.predateurs.size()!=0){
            int red=0;
            int blue=0;
            for (int y=0;y<Stockage.predateurs.size();y++){
                red += Stockage.predateurs.get(y).getRed();
                blue += Stockage.predateurs.get(y).getBlue();
            }
            red/=Stockage.predateurs.size();
            blue/=Stockage.predateurs.size();
            color = Color.rgb(red,50,blue);
        } else {
            int blue=0;
            int green=0;
            for(int y=0;y<Stockage.proies.size();y++){
                blue+=Stockage.proies.get(y).getBlue();
                green+=Stockage.proies.get(y).getGreen();
            }
            blue/=Stockage.proies.size();
            green/=Stockage.proies.size();
            color = Color.rgb(50,green,blue);
        }
        return color;
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
        Stockage.vitessePreda=vitessePreda.getValue();
        Stockage.vitesseProies=vitesseProies.getValue();
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
        for (int i = 0;i<Stockage.predateurs.size();i++ ){
            gc.strokeRect(Stockage.predateurs.get(i).getLocalisation().x,Stockage.predateurs.get(i).getLocalisation().y,2,2);
        }
        couleurProieInit.setFill(updateColor(0));
        couleurPredaInit.setFill(updateColor(1));
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
