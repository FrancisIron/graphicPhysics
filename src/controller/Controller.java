package controller;

import calculate.Formula;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class Controller {

    private double sourceVoltage = 0;
    private double time = 30;
    private double resistance = 0;
    private double capacity = 0;
    private final Formula f = new Formula();
    private Series condensadorData = null;
    private Series resistenciaData = null;
    private Series cargaData = null;
    private Series corrienteData = null;

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
    private CheckBox toggleCorriente;
    @FXML
    private CheckBox toggleResistencia;
    @FXML
    private CheckBox toggleCondensador;

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
        condensadorData = new Series();
        resistenciaData = new Series();
        cargaData = new Series();
        corrienteData = new Series();
        sourceVoltage = 0.01; //Reemplazar por los getText de los input
        //time = 30;//segundos    //Reemplazar por los getText de los input
        resistance = 0.2;    //Reemplazar por los getText de los input
        capacity = 10;        //Reemplazar por los getText de los input

        chart.getData().clear();
        chart.setLegendVisible(true);
        chart.setCreateSymbols(false);

        checkCarga(toggleCarga.isSelected());
        checkResistencia(toggleResistencia.isSelected());
        checkCondensador(toggleCondensador.isSelected());
        checkCorriente(toggleCorriente.isSelected());

    }

    private void checkCarga(boolean show) {
        if (cargaData != null) {
            if (show) {
                cargaData = f.charge(sourceVoltage, time, resistance, capacity);
                chart.getData().add(cargaData);
            } else {
                chart.getData().remove(cargaData);
                cargaData = null;
            }
        }
    }

    private void checkResistencia(boolean show) {
        if (resistenciaData != null) {
            if (show) {
                resistenciaData = f.resistanceVoltage(sourceVoltage, time, resistance, capacity);
                chart.getData().add(resistenciaData);
            } else {
                chart.getData().remove(resistenciaData);
                resistenciaData = null;
            }
        }
    }

    private void checkCondensador(boolean show) {
        if (condensadorData != null) {
            if (show) {
                condensadorData = f.condenserVoltage(sourceVoltage, time, resistance, capacity);
                chart.getData().add(condensadorData);
            } else {
                chart.getData().remove(condensadorData);
                condensadorData = null;
            }
        }
    }

    private void checkCorriente(boolean show) {
        if (corrienteData != null) {
            if (show) {
                corrienteData = f.current(sourceVoltage, time, resistance, capacity);
                chart.getData().add(corrienteData);
            } else {
                chart.getData().remove(corrienteData);
                corrienteData = null;
            }
        }
    }

    @FXML
    private void carga(MouseEvent event) {
        checkCarga(toggleCarga.isSelected());
    }

    @FXML
    private void condensador(MouseEvent event) {
        checkCondensador(toggleCondensador.isSelected());
    }

    @FXML
    private void resistencia(MouseEvent event) {
        checkResistencia(toggleResistencia.isSelected());
    }

    @FXML
    private void corriente(MouseEvent event) {
        checkCorriente(toggleCorriente.isSelected());
    }
}
