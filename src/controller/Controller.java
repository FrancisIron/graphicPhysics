package controller;

import calculate.Formula;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Controller {

    //FXID y atributos de animacion
    private TranslateTransition transition;

    private StackPane stackParticula;

    //FXID Entradas
    @FXML
    private TextField baseMasa;

    @FXML
    private TextField expMasa;

    @FXML
    private TextField baseCarga;

    @FXML
    private TextField expCarga;

    @FXML
    private TextField potencial;

    @FXML
    private TextField campoElectrico;

    @FXML
    private TextField distancia;

    //FXID Salidas
    @FXML
    private TextField vel;

    @FXML
    private TextField time;

    @FXML
    private TextField difPotencial;

    @FXML
    private TextField difEPotencial;

    //FXID Otros
    @FXML
    private Text error;

    @FXML
    private LineChart<?, ?> chart;

    @FXML
    public void onEnterPressed(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            simular();
        }
    }

    @FXML
    public double actionCalcular() {
        
        double sourceVoltage = 0.1; //Reemplazar por los getText de los input
        int time = 30;//segundos    //Reemplazar por los getText de los input
        double resistance = 0.2;    //Reemplazar por los getText de los input
        double capacity = 5;        //Reemplazar por los getText de los input
        
        Formula f = new Formula();
        chart.getData().clear();
        chart.setLegendVisible(true);
        chart.setCreateSymbols(false);
        chart.getData().add(f.resistanceVoltage(sourceVoltage, time, resistance, capacity));;
        chart.getData().add(f.charge(sourceVoltage, time, resistance, capacity));
        chart.getData().add(f.condenserVoltage(sourceVoltage, time, resistance, capacity));
        chart.getData().add(f.current(sourceVoltage, time, resistance, capacity));
        return 123.0;

    }

    @FXML
    public void simular() {
        /*
        animationStop();
        error.setVisible(false);
        if (Double.parseDouble(baseCarga.getText()) > 0) {
            double t = actionCalcular();
            if (t != 0.0) {
                String num = "" + t;
                double frames = t * Math.pow(10, -Integer.parseInt(num.split("E")[1]) + 1);
                animationPlay(frames);
            } else {
                error.setVisible(true);
            }
        }
*/
    }

    public void animationPlay(double frames) {
        /* transition.setDuration(Duration.millis(frames));
        transition.setNode(stackParticula);
        transition.setByX(194);
        transition.play();*/
    }

    public void animationStop() {
        /*transition.stop();
        stackParticula.setTranslateX(0);*/
    }
    
}