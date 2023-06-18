package com.corpevents.main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatter {
    public static String dateFormatter(String inputDate) {
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        String outputFormat = "dd/MM/yyyy 'às' HH:mm";

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

    public static LocalDate toLocalDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDate.parse(s, formatter);
    }

    public static Date toDate(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
