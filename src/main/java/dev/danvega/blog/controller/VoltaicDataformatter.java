package dev.danvega.blog.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VoltaicDataformatter {


    // ********** 1. Convert date format **********
    // Convert date format from yyyy-MM-dd to MM-dd-yy
    // Convert date format from MM/dd/yyyy to MM-dd-yy
    // Convert date format from  yyyy-MM-dd to MM-dd-yy
    // If the date format is not one of the above, return the original date


    public static String convertDateFormat(String originalDate) {


        SimpleDateFormat originalFormat = null;


            if (originalDate == null || originalDate.isEmpty()) {
                return originalDate;
            }else if(originalDate.length() > 12){
                System.out.println("Date is null or empty");


                // Use recursion to resubmit





            }else{
            if (originalDate.matches("^\\d{1,2}-\\d{1,2}-\\d{2}$")) {
                originalFormat = new SimpleDateFormat("MM-dd-yy");
            }
            else if (originalDate.matches("^\\d{1,2}/\\d{1,2}/\\d{2}$")) {
                originalFormat = new SimpleDateFormat("MM/dd/yy");
            }
            else if (originalDate.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$")) {
                originalFormat = new SimpleDateFormat("MM/dd/yyyy");
            }
            else if (originalDate.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                originalFormat = new SimpleDateFormat("yyyy-MM-dd");
            }
            else {
                //DATE DOES NOT EQUAL ANY OF THE ABOVE FORMATS

                // Try to parse date using default date format
                originalFormat = new SimpleDateFormat();
                try {
                    originalFormat.parse(originalDate);
                } catch (ParseException e) {
                    return originalDate;
                }
            }

        }







        SimpleDateFormat targetFormat = new SimpleDateFormat("MM-dd-yy");
        Date date;
        try {
            date = originalFormat.parse(originalDate);
        }
        catch (ParseException e) {
            return originalDate;
        }



        return targetFormat.format(date);
    }
}