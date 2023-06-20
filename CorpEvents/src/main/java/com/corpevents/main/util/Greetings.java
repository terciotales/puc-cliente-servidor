package com.corpevents.main.util;

/**
 * Classe utilitária para retornar uma saudação de acordo com o horário
 */
public class Greetings {
    public static String getGreeting() {
        String greeting = "";
        int hour = java.time.LocalTime.now().getHour();

        if (hour < 12) {
            greeting = "Bom dia,";
        } else if (hour < 18) {
            greeting = "Boa tarde,";
        } else {
            greeting = "Boa noite,";
        }

        return greeting;
    }
}
