package controller;

import calculate.Corrector;
import calculate.Formula;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class Controller {

    private double sourceVoltage = 0;
    private double time = 0;
    private double resistance = 0;
    private double capacity = 0;
    private final Formula f = new Formula();
    private final Corrector juanito = new Corrector();
    private Series condensadorData = null;
    private Series resistenciaData = null;
    private Series cargaData = null;
    private Series corrienteData = null;

    //FXID Entradas
    @FXML
    private TextField baseVoltaje;
    @FXML
    private TextField expVoltaje;
    @FXML
    private TextField baseResistencia;
    @FXML
    private TextField expResistencia;
    @FXML
    private TextField tiempo;
    @FXML
    private TextField baseCapacitancia;
    @FXML
    private TextField expCapacitancia;
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
        if (time > 2 && scroll.getDeltaY() > 0) {
            int t = Integer.parseInt(tiempo.getText()) - 1;
            tiempo.setText("" + (t));
            actionCalcular();
        } else if (scroll.getDeltaY() < 0) {
            int t = Integer.parseInt(tiempo.getText()) + 1;
            tiempo.setText("" + (t));
            actionCalcular();
        }
    }

    private void validarExp() {
        if (expCapacitancia.getText().length() == 0) {
            expCapacitancia.setText("0");
        }
        if (expResistencia.getText().length() == 0) {
            expResistencia.setText("0");
        }
        if (expVoltaje.getText().length() == 0) {
            expVoltaje.setText("0");
        }
    }

    @FXML
    public void actionCalcular() {
        validarExp();
        if (juanito.validarPotencia(baseVoltaje, expVoltaje) && juanito.validarPotencia(baseCapacitancia, expCapacitancia) && juanito.validarPotencia(baseResistencia, expResistencia) && juanito.validarNumero(tiempo)) {
            condensadorData = new Series();
            resistenciaData = new Series();
            cargaData = new Series();
            corrienteData = new Series();

            error.setVisible(false);
            sourceVoltage = juanito.createDouble(Double.parseDouble(baseVoltaje.getText()), Integer.parseInt(expVoltaje.getText()));
            time = Integer.parseInt(tiempo.getText());
            resistance = juanito.createDouble(Double.parseDouble(baseResistencia.getText()), Integer.parseInt(expResistencia.getText()));
            capacity = juanito.createDouble(Double.parseDouble(baseCapacitancia.getText()), Integer.parseInt(expCapacitancia.getText()));

            chart.getData().clear();
            chart.setLegendVisible(true);
            chart.setCreateSymbols(false);

            checkCarga(toggleCarga.isSelected());
            checkResistencia(toggleResistencia.isSelected());
            checkCondensador(toggleCondensador.isSelected());
            checkCorriente(toggleCorriente.isSelected());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("(╯°□°）╯︵ ┻━┻");
            alert.setHeaderText("Error al ingresar los datos");
            String s ="Intentelo de nuevo, por favor";
            alert.setContentText(s);
            alert.show();
        }
    }

    private void checkCarga(boolean show) {
        if (cargaData != null) {
            if (show) {
                cargaData = f.charge(sourceVoltage, time, resistance, capacity);
                chart.getData().add(cargaData);
            } else {
                chart.getData().remove(cargaData);
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

    //Easter egg

    public void easterEgg(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Easter Egg");
        alert.setHeaderText("Acerca del software");
        String s ="Este software fue creado y diseñado con un plazo\nde 50,6 horas por Francisco Fierro y Diego Garrido\npara el ramo de Fisica II dictado por la profesora\nMariela Gonzalez.\n\nEspero lo disfrute, como nosotros programandolo.";
        alert.setContentText(s);
        alert.show();
    }
}
