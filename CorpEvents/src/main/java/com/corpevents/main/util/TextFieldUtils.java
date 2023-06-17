package com.corpevents.main.util;

import javafx.scene.control.TextField;

import java.text.Normalizer;

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

    public static String removeSpecialCharacters(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    public static String escapeSpecialCharacters(String s) {
        // Remove os acentos
        String textoSemAcentos = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // Substitui o "ç" por "c"

        return textoSemAcentos.replaceAll("[çÇ]", "c");
    }
}
