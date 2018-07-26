package calculate;

import javafx.scene.control.TextField;

public class Corrector {

    /*De texto*/
    public double createDouble(double shortdecimal, int exp) {
        double calc = shortdecimal * Math.pow(10, exp);
        System.out.println(calc);
        return calc;
    }

    public String createString(double num) {
        String n = "" + num;
        String out = "";
        if (n.contains("E")) {
            out = n.substring(0, 3) + "x10^" + n.split("E")[1];
        } else {
            if (n.contains(".")) {
                for (int i = 0; i < n.length(); i++) {
                    if (n.charAt(i) == '.') {
                        break;
                    }
                    out += n.charAt(i);
                }
            } else {
                return n;
            }
        }
        return out;
    }

    /*Validacion de entradas*/
    public boolean validarNumero(TextField input) {
        try {
            String str = input.getText().trim();
            str = str.replaceAll(",", ".");
            Double.parseDouble(str);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean validarPotencia(TextField inputBase, TextField inputExp) {
        String decimal = "" + inputBase.getText().trim();
        if (decimal == null || decimal.length() == 0) {
            return false;
        }
        int exponencial;
        try {
            exponencial = Integer.parseInt(inputExp.getText());
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        decimal = decimal.replaceAll(",", ".");
        if (decimal.contains(".")) {
            if (decimal.split("\\.").length == 1) {
                decimal += "0";
            } else if (decimal.split("\\.")[1].length() > 2) {
                exponencial -= (decimal.split("\\.")[1].length() - 2);
                decimal = decimal.split("\\.")[0] + "." + decimal.split("\\.")[1].substring(0, 2);
            }
        }
        inputBase.setText(decimal);
        inputExp.setText("" + exponencial);
        return true;
    }
}
