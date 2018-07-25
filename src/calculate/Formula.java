package calculate;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class Formula {

    private Series series;

    public Formula() {
    }

    public Series condenserVoltage(double sourceVoltage, int time, double resistance, double capacity) {
        series = new Series();
        series.setName("Voltaje en Condensador");

        for (int i = 0; i < time; i++) {
            double result = sourceVoltage * (1 - Math.exp((-i) / (resistance * capacity)));
            series.getData().add(new XYChart.Data("" + i, result));
        }

        return series;
    }

    public Series resistanceVoltage(double sourceVoltage, int time, double resistance, double capacity) {
        series = new Series();
        series.setName("Voltaje en Resistencia");

        for (int i = 0; i < time; i++) {
            double result = sourceVoltage * (Math.exp((-i) / (resistance * capacity)));
            series.getData().add(new XYChart.Data("" + i, result));
        }

        return series;
    }

    public Series charge(double sourceVoltage, int time, double resistance, double capacity) {
        series = new Series();
        series.setName("Carga del Capacitor");

        for (int i = 0; i < time; i++) {
            double result = capacity * sourceVoltage * (Math.exp((-i) / (resistance * capacity)));
            series.getData().add(new XYChart.Data("" + i, result));
        }

        return series;
    }

    public Series current(double sourceVoltage, int time, double resistance, double capacity) {
        series = new Series();
        series.setName("Corriente");

        for (int i = 0; i < time; i++) {
            double result = (sourceVoltage / resistance) * (Math.exp((-i) / (resistance * capacity)));
            series.getData().add(new XYChart.Data("" + i, result));
        }

        return series;
    }
}

/////////////////////////////////////////////////////////////////////
/*
Para asignar los datos al grafico usar LineChart y asignarlo con el codigo

Formula f = new Formula();
chart.setCreateSymbols(false);
chart.getData().add(f.resistanceVoltage(10, 30, 0.002, 5));

(chart es la id del grafico, reemplazar por cada id de los graficos)
*/