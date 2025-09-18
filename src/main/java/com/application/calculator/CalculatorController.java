package com.application.calculator;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class CalculatorController {

    @FXML
    private TextField display;

    private double number1 = 0;
    private String operator = "";
    private boolean check = true;

    public double calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return 0.0;
                }
            default:
                break;
        }
        return 0.0;
    }

    public void numberAction(ActionEvent event) {
        if (check) {
            display.setText("");
            check = false;
        }

        String value = ((Button) event.getSource()).getText();
        display.setText(display.getText() + value);
    }

    public void operatorAction(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (!value.equals("=")) {
            if (!operator.isEmpty()) {
                return;
            }

            operator = value;
            number1 = Double.parseDouble(display.getText());
            display.setText("");
        } else {
            if (operator.isEmpty()) {
                return;
            }

            double number2 = Double.parseDouble(display.getText());
            double result = calculate(number1, number2, operator);
            display.setText(String.valueOf(result));
            operator = "";
            check = true;
        }
    }

    public void percentAction(ActionEvent event) {
        double currentValue = Double.parseDouble(display.getText());
        currentValue = currentValue / 100;
        display.setText(String.valueOf(currentValue));
    }

    public void plusMinusAction(ActionEvent event) {
        double currentValue = Double.parseDouble(display.getText());
        currentValue = -currentValue;
        display.setText(String.valueOf(currentValue));
    }

    public void eraseAction(ActionEvent event) {
        String currentText = display.getText();
        if (currentText.length() > 1) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            display.setText("0");
            check = true;
        }
    }

    public void clearAction(ActionEvent event) {
        display.setText("0");
        number1 = 0;
        operator = "";
        check = true;
    }
}