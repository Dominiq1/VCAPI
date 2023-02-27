package dev.danvega.blog.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DaysBetweenDates{

    public static boolean isDateNotBetween(String dateString, String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        return date.isBefore(startDate) || date.isAfter(endDate);
    }

    public static void main(String[] args) {
        String startDateString = "2023-01-01";
        String endDateString = "2023-02-13";
        String dateString1 = "2023-01-15";
        String dateString2 = "2023-02-15";

        boolean isDate1NotBetween = isDateNotBetween(dateString1, startDateString, endDateString);
        System.out.println(dateString1 + " is not between " + startDateString + " and " + endDateString + ": " + isDate1NotBetween);

        boolean isDate2NotBetween = isDateNotBetween(dateString2, startDateString, endDateString);
        System.out.println(dateString2 + " is not between " + startDateString + " and " + endDateString + ": " + isDate2NotBetween);
    }
}
