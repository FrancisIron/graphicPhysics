package controller;

import calculate.Formula;
import javafx.animation.TranslateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller {

    private double sourceVoltage = 0;
    private double time = 30;
    private double resistance = 0;
    private double capacity = 0;
    private final Formula f = new Formula();
    private final Rectangle zoomRect = new Rectangle();
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

    //FXID Otros
    @FXML
    private Text error;

    @FXML
    private LineChart<?, ?> chart;
    @FXML
    private CheckBox toggleCarga;
    @FXML
    private CheckBox toggelCondensador;
    @FXML
    private CheckBox toggleCorriente;
    @FXML
    private CheckBox toggleResistencia;
    @FXML
    private GridPane chartContainer;

    @FXML
    public void onEnterPressed(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            actionCalcular();
        }
    }

    @FXML
    private void mouseWheel(ScrollEvent scroll) {
        time -= (scroll.getDeltaY() / 100);
        actionCalcular();
    }

    @FXML
    public void actionCalcular() {
        sourceVoltage = 0.1; //Reemplazar por los getText de los input
        //time = 30;//segundos    //Reemplazar por los getText de los input
        resistance = 0.2;    //Reemplazar por los getText de los input
        capacity = 5;        //Reemplazar por los getText de los input

        chart.getData().clear();
        chart.setLegendVisible(true);
        chart.setCreateSymbols(false);

        checkCarga(toggleCarga.isSelected());
        chart.getData().add(f.resistanceVoltage(sourceVoltage, time, resistance, capacity));

        chart.getData().add(f.condenserVoltage(sourceVoltage, time, resistance, capacity));
        chart.getData().add(f.current(sourceVoltage, time, resistance, capacity));

    }

    private void checkCarga(boolean show) {
        if (show) {
            chart.getData().add(f.charge(sourceVoltage, time, resistance, capacity));
        } else {
            chart.getData().remove(f.charge(sourceVoltage, time, resistance, capacity));
        }
    }
}
