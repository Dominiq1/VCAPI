package dev.danvega.blog.controller;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.lang.Math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DistanceMatrix {



    //TEST KEY
    public static final String  TESTKEY = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=Washington%2C%20DC&destinations=New%20York%20City%2C%20NY&units=imperial&key=AIzaSyBdDHwkZ12oAMR7EjAuedIto0QgxC1BDQk";

    private static final String Address = "New%20York%20City%2C%20NY&origins=Washington%2C%20DC &units=imperial";
    public static final String YOUR_API_KEY = "";

    private static final String API_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?";


    //private static final String DESTRUCTURED_API = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    // private static final String VOLTAIC_ADDRESS = "7700 Imperial Hwy. E-1B, Downey, CA 90242";
    //GOOGLE API KEY
    private static final String API_TOKEN = "AIzaSyBdDHwkZ12oAMR7EjAuedIto0QgxC1BDQk";





    //EXTERNAL CLASSES:

    //DISTANCE


    static Distance distance = new Distance();


    static Double change(double value, int decimalpoint)
    {

        // Using the pow() method
        value = value * Math.pow(10, decimalpoint);
        value = Math.floor(value);
        value = value / Math.pow(10, decimalpoint);

        System.out.println(value);

        return value;
    }
    public static ReImbursmentRecord DISTANCE_CALC(String ADDRESS, String ORIGIN) throws Exception {


        String url = API_URL +"units=imperial&origins="+ ORIGIN + "&destinations=" +
                ADDRESS + "&key=" + API_TOKEN;


//        System.out.println("URL");
     //    System.out.println(url);
//        System.out.println("TEST KEY");
//         System.out.println(TESTKEY);


        URL obj = new URL(url);
 //       URL obj = new URL(TESTKEY);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
//        System.out.println("Response Code : " + responseCode);
//

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //JSON PARSE
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(String.valueOf(response)).getAsJsonObject();



        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        jsonWriter.setIndent("  ");
        gson.toJson(jsonObject, jsonWriter);
//        System.out.println(stringWriter.toString());


        //ADDRESSES
        JsonArray data = jsonObject.getAsJsonArray("destination_addresses").getAsJsonArray();
//        System.out.println(data);

        //DISTANCE[{"elements":[{"distance":{"text":"4.5 mi","value":7253},"duration":{"text":"11 mins","value":670},"status":"OK"}]}]
        JsonArray rows = jsonObject.getAsJsonArray("rows").getAsJsonArray();

        JsonArray elements = rows.getAsJsonArray();

        JsonObject elementz = rows.get(0).getAsJsonObject();
        JsonArray elements2 = elementz.getAsJsonArray("elements");
        JsonObject elementz2 = elements2.get(0).getAsJsonObject();
        JsonObject distance = elementz2.getAsJsonObject("distance");
        String text = distance.get("text").toString();
        String value = distance.get("value").toString();

//        System.out.println("Value");
//        System.out.println(value);
        //System.out.println(rows);
        String s = text;
        int spaceIndex = s.indexOf(" ");
        String miles_in_text = s.substring(1, spaceIndex);

//
//          System.out.println("Driving Miles from Voltaic Downey HQ");
//        System.out.println(text);
//        System.out.println(miles_in_text);
//        Double.parseDouble(miles_in_text);
//        System.out.println(miles_in_text);
//        Miles in double
        double miles = Double.parseDouble(miles_in_text);


        ///MILES AS DOUBLE
//        System.out.println("DISTANCE TRAVELED IN MILES FROM VOLTAIC HQ");
//        System.out.println(miles);
//
//        //TOTAL REIMBURSEMENT FOR TRAVEL
//        System.out.println("TOTAL REIMBURSEMENT FOR TRAVEL");
//        System.out.println("$" + (miles - 40.0) * 0.655);

        //



        //
        //
        //
        //
       double reimbursement =  change((miles - 40.0) * 0.655, 2);

    ReImbursmentRecord record = new ReImbursmentRecord(  ADDRESS,  text, reimbursement, "0","0" );




        return record;



        //RETURN DISTANCE IN MILES

        //DO NOT PASS THIS POINT UP TO THE MAIN METHOD


        //distance.FINDdistance(Double.parseDouble(lat), Double.parseDouble(lng), 33.926223, -118.157753);
        //System.out.println(data);

        // System.out.println(data);










        //GOOGLE GEOCODING API RESPONSE
        //System.out.println(response.toString());
    }
}
