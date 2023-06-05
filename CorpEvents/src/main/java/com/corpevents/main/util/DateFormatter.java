package com.corpevents.main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
    public static String dateFormatter(String inputDate) {
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        String outputFormat = "dd/MM/yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);

        try {
            java.util.Date date = inputDateFormat.parse(inputDate);
            return outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return inputDate;
    }
}
