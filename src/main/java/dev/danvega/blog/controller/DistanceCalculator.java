package dev.danvega.blog.controller;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;


import java.util.List;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.awt.Point;

public class DistanceCalculator {




    private static final String API_URLZ = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=YOUR_API_KEY";
    private static final String DESTRUCTURED_API = "https://maps.googleapis.com/maps/api/geocode/json?address=";


    private static final String ADDRESS = "1600+Amphitheatre+Parkway,+Mountain+View,+CA&key";



    private static final String APITOKEN = "=AIzaSyDzUn0CKCVkUOaJtzzw16qT3QTSfPTtS6Q";



    private static final String API_URL = "https://api.quickbase.com/v1/apps/br5cqr3bn";
    private static final String USER_TOKEN = "QB-USER-TOKEN b7738j_mm72_0_d6r6badbrm2xkxdxica2mx5a7sz";
    private static final String QB_DOMAIN = "solarcrm.quickbase.com";



    public void processAddresses(List<String> addresses) {
        for (String address : addresses) {
            System.out.println("Processing address: " + address);
            // perform processing on the address
        }
    }



    DistanceCalculator(){

    }


//CREATE A TABLE

    public void getDistance(){
        String[] cars;
        String[] addresses = {"Volvo", "BMW", "Ford", "Mazda"};
        int[] myNum = {10, 20, 30, 40};
        int i = 0;
        while (i < 5) {
            System.out.println("array[i]");
            i++;
        }
    }



    //UPDATE APP
    public void getApp(String API){
        try {
            // Create the URL to the Quickbase API endpoint for the table
            URL url = new URL(API);

            // Open the connection to the Quickbase API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request method and add the user token to the request header
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", USER_TOKEN);
            connection.setRequestProperty("QB-Realm-Hostname", QB_DOMAIN);


            // Read the response from the Quickbase API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the response from the Quickbase API
            //System.out.println(response);

            // String res = toString(response)

//Your json response
//            jsonResponse.addProperty("ancestorId", "bj4g9tafj");
//            jsonResponse.addProperty("created", "2023-01-04T08:37:33Z");
//            jsonResponse.addProperty("dateFormat", "MM-DD-YYYY");
//... other properties



            //Pretty print JSON with this
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(String.valueOf(response)).getAsJsonObject();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setIndent("  ");
            gson.toJson(jsonObject, jsonWriter);

            System.out.println(stringWriter.toString());


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }

    }


    public void getAppTables(String API){
        try {
            // Create the URL to the Quickbase API endpoint for the table
            URL url = new URL(API);

            // Open the connection to the Quickbase API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request method and add the user token to the request header
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", USER_TOKEN);
            connection.setRequestProperty("QB-Realm-Hostname", QB_DOMAIN);


            // Read the response from the Quickbase API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the response from the Quickbase API
            //System.out.println(response);

            // String res = toString(response)

//Your json response
//            jsonResponse.addProperty("ancestorId", "bj4g9tafj");
//            jsonResponse.addProperty("created", "2023-01-04T08:37:33Z");
//            jsonResponse.addProperty("dateFormat", "MM-DD-YYYY");
//... other properties




            JsonArray array = new JsonParser().parse(response.toString()).getAsJsonArray();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            for (JsonElement json : array) {
                String jsonString = gson.toJson(json);
                System.out.println(jsonString);
            }


//            System.out.println(response);
//            System.out.println("App Tables");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }


    }


    //GET APP
    public String updateApp(String body) {

        String Response = null;


        try {
            // Create the URL to the Quickbase API endpoint for the table
            URL url = new URL(API_URL);

            // Open the connection to the Quickbase API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and add the user token to the request header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", USER_TOKEN);
            connection.setRequestProperty("QB-Realm-Hostname", QB_DOMAIN);
            // Connect to the server and send the request
            connection.setDoOutput(true); // This line is important

            // Write the body of the request to the connection
            byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
            connection.setRequestProperty("Content-Length", Integer.toString(bodyBytes.length));
            OutputStream os = connection.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8);

            osw.write(body);
            osw.flush();
            osw.close();
            os.close();


            connection.connect();

            // Read the response from the Quickbase API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Print the response from the Quickbase API
            System.out.println(response);
            Response = response.toString();
            return  response.toString();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }

        return Response;
    }






}
