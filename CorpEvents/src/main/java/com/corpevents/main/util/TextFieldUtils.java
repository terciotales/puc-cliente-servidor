package com.corpevents.main.util;

import javafx.scene.control.TextField;

public class TextFieldUtils {
    public static void numberTextField(TextField textField, int limit) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 0 || newValue.equals("0")) {
                return;
            }

            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
                return;
            }

            if (textField.getText().length() > 2) {
                textField.setText(textField.getText().substring(0, 2));
            }

            if (Integer.parseInt(textField.getText()) > limit) {
                textField.setText(String.valueOf(limit));
            }
        });
    }
}
