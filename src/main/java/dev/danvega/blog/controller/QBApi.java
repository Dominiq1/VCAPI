package dev.danvega.blog.controller;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QBApi {

    public static final String API_URL = "https://api.quickbase.com/v1/apps/br5cqr3bn";


    private static final String USER_TOKEN = "QB-USER-TOKEN b7738j_mm72_0_d6r6badbrm2xkxdxica2mx5a7sz";



    private static final String QB_DOMAIN = "solarcrm.quickbase.com";



    public QBApi(){

    }

    // ###########################   CLASSES         ##################################
    public static boolean isDateNotBetween(String dateString, String startDateString, String endDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        return date.isBefore(startDate) || date.isAfter(endDate);
    }


    public ArrayList<String> getAppTables(String API){
        ArrayList<String> ReportFields = new ArrayList<String>();
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

//////////      ****************************************            /////////

            //      Parse JSON
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(String.valueOf(response)).getAsJsonObject();

            //      BUILD GSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setIndent("  ");
            gson.toJson(jsonObject, jsonWriter);

            //    ////////****************************************/////////
            //   ********** VIEW RESPONSE!  **********
            //    ////////****************************************/////////

            //System.out.println(stringWriter.toString());


            //    ////////****************************************/////////
            //   ********** PARSE RESPONSE!  **********
            //    ////////****************************************/////////
            JsonArray data = jsonObject.getAsJsonObject("query").get("fields").getAsJsonArray();
            // System.out.print("Queriable data in report:");
            //    System.out.print(data);


            //  LOOP THROUGH DATA ARRAY
            for (int i = 0; i < data.size(); i++) {
                //        DATA ITEM
                //               System.out.println("=====================================\n");

                // JsonObject item = data.get(i).getAsJsonObject();
//                System.out.print(data.get(i).toString());
//
//                System.out.print("\nAdded new query field to report!");

                ReportFields.add(data.get(i).toString());


                // System.out.print(item.toString());

//                System.out.println("\n=====================================");


            }


            return ReportFields;




        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }

        return  null;


    }


    public ArrayList<VoltaicProject> PipelineDuration(String API, ArrayList<String> ReportIDs) {
//        System.out.println("Pulling report: " +ReportID + "..." );



        Map<String, Double> PipelineDuration = new HashMap<String, Double>();

        ArrayList<VoltaicProject> Projects = new ArrayList<VoltaicProject>();

        int AmountOfVCNotes = 0;



// Stage Pie Chart numbers
        //PROJECT STAGE COUNTERS
        int NewSale_count = 0;
        int SITE_SURVEY_count = 0;
        int QC_CHECK_count = 0;
        int FLA_count = 0;
        int NTP_count = 0;
        int PLANS_count = 0;
        int SOLAR_PERMIT_count = 0;
        int SOLARINSTALL_count = 0;
        int FinalInspection_count = 0;
        int SystemActivation_count = 0;
        int PTO_count = 0;
        int Complete_count = 0;
        int SALET_PTO_count = 0;


// Last weeksPermit
        int LastWeeksPermit = 0;
        int LastWeeksPermit_recieved = 0;




        //DURATIONS


        //FULLFILLMENT STAGES
        Double NewSale = 0.0;
        Double SITE_SURVEY = 0.0;
        Double QC_CHECK = 0.0;
        Double FLA = 0.0;
        Double NTP = 0.0;
        Double PLANS = 0.0;
        Double SOLAR_PERMIT = 0.0;
        Double SOLARINSTALL = 0.0;
        Double FinalInspection = 0.0;
        Double SystemActivation = 0.0;
        Double PTO = 0.0;
        Double Complete = 0.0;
        Double SALET_PTO = 0.0;



        try {



            // Create the URL to the Quickbase API endpoint for the table
            URL url = new URL(API);

            // Open the connection to the Quickbase API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and add the user token to the request header
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", USER_TOKEN);
            connection.setRequestProperty("QB-Realm-Hostname", QB_DOMAIN);
            connection.setRequestProperty("Content-Type", "application/json");

            //    ////////****************************************/////////

            //   **********  FORMAT JSON BODY WITH - TABLE_ID_# AND FIELD_IDS TO QUERY  **********

            //    ////  ****** CURRENTLY HARDCODED INTO THE JsonBody blow  **********////
            //    ////////****************************************/////////


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            //      Parse JSON
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(String.valueOf(response)).getAsJsonObject();

            //      BUILD GSON
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            jsonWriter.setIndent("  ");
            gson.toJson(jsonObject, jsonWriter);

            //      RESPONSE:
            //    ////////****************************************/////////
            //   ********** BE SURE TO LABEL DATA BEING OUTPUT IN THIS OUTPUT STRING:   **********
            //    ////////****************************************/////////


            //    ////////****************************************/////////
            //LOOP THROUGH DATA ARRAY



            JsonArray data = jsonObject.getAsJsonArray("data");

//            JsonObject data = jsonObject.getAsJsonObject("data");
            //    gson.toJson(data, jsonWriter);

            System.out.print(ReportIDs + "\n");
            System.out.println(" TABLE DATA SIZE : ( " + data.size() + " Items ): ===========");

            //   System.out.println(data);


            for (int i = 0; i < data.size(); i++) {

                JsonObject item = data.get(i).getAsJsonObject();

               // System.out.println(item);

                //Stage
                String stage = item.get("599").getAsJsonObject().get("value").toString();
                   String recordId = item.get("3").getAsJsonObject().get("value").toString();
//                String Address = item.get("92").getAsJsonObject().get("value").toString();
//                String HomeownerName = item.get("105").getAsJsonObject().get("value").toString();
//



                // Initialize new project get project info in email.

                VoltaicProject Project = new VoltaicProject("Address", "HomeownerName" , recordId);



                if (stage.equals("\"New Sale\"")){
                    System.out.println(NewSale_count);
                    NewSale_count += 1;

                }else if (stage.equals("\"Site Survey\"")){
                    System.out.println(SITE_SURVEY_count);
                    SITE_SURVEY_count += 1;}
                else if (stage.equals("\"QC Check\"")){
                    System.out.println(QC_CHECK_count);
                    QC_CHECK_count += 1;
                }else if (stage.equals("\"FLA\"")){
                    System.out.println(FLA_count);
                    FLA_count += 1;

                }else if (stage.equals("\"NTP\"")) {
                    System.out.println(NTP_count);
                    NTP_count += 1;
                }
                else if (stage.equals("\"Plans\"")){
                    System.out.println(PLANS_count);
                    PLANS_count += 1;
                }
                else if (stage.equals("\"Solar Permit\"")){
                    System.out.println(SOLAR_PERMIT_count);
                    SOLAR_PERMIT_count += 1;
                }
                else if (stage.equals("\"Solar Install\"")){
                    System.out.println(SOLARINSTALL_count);
                    SOLARINSTALL_count += 1;
                }
                else if (stage.equals("\"Final Inspection\"")){
                    System.out.println(FinalInspection_count);
                    FinalInspection_count += 1;
                }
                else if (stage.equals("\"System Activation\"")){
                    System.out.println(SystemActivation_count);
                    SystemActivation_count += 1;
                }
                else if (stage.equals("\"PTO\"")){
                    System.out.println(PTO_count);
                    PTO_count += 1;
                }
                else if (stage.equals("\"Complete\"")){
                    System.out.println(Complete_count);
                    Complete_count += 1;
                }
                else {
                    System.out.println("ERROR:  Stage not found");
                    System.out.println("Stage: " + stage);
                }

                for (int j =0; j < ReportIDs.size(); j++){


                    //PROJECT.updateStatusTime("Welcome", "date" ,  WelcomeDate);


                    String value =  item.get(ReportIDs.get(j)).getAsJsonObject().get("value").toString();
                    //  System.out.println(Homeowner);

                    //Build the Voltaic object

                    // PRINT AND CHECK FOR ANY NEW FIELDS IN THE PROJECT REPORT

                    switch (ReportIDs.get(j)) {
                        case "3":
                            System.out.println("Record ID:   " + value);
                            Project.setProjectRecordID(value);
                            break;

                        case "105":
                            System.out.println("Homeowner 1:   " + value);
                            Project.setHomeownerName(value);
                            break;
                        case "93":
                            System.out.println("Address: Street:   " + value);
                            Project.setAddress(value);
                            break;
                        case "95":
                            System.out.println("Address City:   " + value);
                            Project.setAddress(value);

                            break;
                        case "719":
                            System.out.println("AHJ:   " + value);
                            Project.setAHJ(value);
                            break;
                        case "770":
                            System.out.println("Products:   " + value);
                            break;
                        case "599":
                            System.out.println("Stage:   " + value);

                            break;
                        case "102":
                            System.out.println("Sale Date:   " + value);
                            break;
                        case "915":
                            System.out.println("Site Survey - Date:    " + value);
                            break;
                        case "862":
                            System.out.println("NTP - Date:    " + value);
                            break;
                        case "855":
                            System.out.println("QC Check Status:   " + value);
                            break;
                        case "906":
                            System.out.println("Design - Status:    " + value);
                            break;
                        case "907":
                            System.out.println("Design - Date:    " + value);
                            break;
                        case "1446":
                            System.out.println("Plans - Status:   " + value);
                            break;
                        case "1443":
                            System.out.println("Plans Requested:    " + value);
                            break;
                        case "1444":
                            System.out.println("Plans Received:   " + value);
                            break;
                        case "858":
                            System.out.println("FLA - Status:   " + value);
                            break;
                        case "867":
                            System.out.println("Solar Permit - Status:   " + value);
                            break;
                        case "1346":
                            System.out.println("Solar Permit - Date:    " + value);
                            break;
                        case "1403":
                            System.out.println("Solar Permit - Permit Submitted:    " + value);
                            break;
                        case "1402":
                            System.out.println("Solar Permit - Permit Received:   " + value);
                            break;
                        case "877":
                            System.out.println("Solar Install - Date:   " + value);
                            break;
                        case "634":
                            System.out.println("Installer:   " + value);
                            break;
                        case "408":
                            System.out.println("Status:   " + value);
                            break;

                        case "839":
                            System.out.println("Voltaic Check Date:   " + value);
                            break;

                        case "1377":
                            System.out.println("Welcome Date:    " + value);
                            break;
                        case "856":
                            System.out.println("QC Check Date:   " + value);
                            break;

                        case "859":
                            System.out.println("FLA Date:    " + value);
                            break;
                        case "1657":
                            System.out.println("Solar Permit Submitted Date:     " + value);
                            Project.setSolarPermitSubmitDate(value);
                            break;

                        case "1656":
                            System.out.println("Solar Permit Permit Receive Date:    " + value);
                            break;

                        case "1577":
                            System.out.println("Solar Permit Re-Submitted Date:    " + value);
                            break;

                        case "1574":
                            System.out.println("Solar Permit Approved Date:    " + value);
                            break;
                        case "1485":
                            System.out.println("Solar Permit Rejected Red; Lines:    " + value);
                            break;
                        case "740":
                            System.out.println("Progress Bar:    " + "**HTML Element" + "value");
                            break;

                        case "1639":
                            System.out.println("Site Survey Schedule Date:    " + value);
                            break;
                        case "1641":
                            System.out.println("Site Survey Complete Date:    " + value);
                            break;
                        case "1642":
                            System.out.println("Site Survey Cancelled Date:    " + value);
                            break;
                        case "1644":
                            System.out.println("NTP Submited Date    " + value);
                            break;
                        case "1643":
                            System.out.println("NTP Granted Date:   " + value);
                            break;
                        case "1648":
                            System.out.println("QC Check Pass with Electrical Date:   " + value);
                            break;

                        case "1645":
                            System.out.println("QC Check Pass Date:   " + value);
                            break;
                        case "1646":
                            System.out.println("QC Chcek Fail Date:    " + value);
                            break;
                        case "1647":
                            System.out.println("QC Check Incomplete Date:   " + value);
                            break;

                        case "1445":
                            System.out.println("Plans - Date:    " + value);
                            break;
                        case "1649":
                            System.out.println("Plans Requested Date:    " + value);
                            break;
                        case "1650":
                            System.out.println("Plans Recieved Date:    " + value);
                            break;
                        case "1651":
                            System.out.println("Plans Revison Date:    " + value);
                            break;
                        case "1652":
                            System.out.println("FLA Sent Date:    " + value);
                            break;

                        case "1653":
                            System.out.println("FLA Recieved Date:     " + value);
                            break;

                        case "1654":
                            System.out.println("FLA Approved Date:      " + value);
                            break;
                        case "1655":
                            System.out.println("FLA Revision Date:      " + value);
                            break;
                        case "1575":
                            System.out.println("Check Solar Submission:      " + value);
                            Project.SetSolarPermit(value);
                            break;
                        case "1660":
                            System.out.println("Solar Install Schedule Date:     " + value);
                            break;
                        case "1659":
                            System.out.println("Solar Install Complete Date:      " + value);
                            break;
                        case "1658":
                            System.out.println("Solar Install Cancelled Date:     " + value);
                            break;
                        case "1661":
                            System.out.println("Solar Install Unscheduled Date:     " + value);
                            break;
                        case "883":
                            System.out.println("Final Inspection Date:     " + value);
                            break;
                        case "1662":
                            System.out.println("Final Inspection Scheduled Date:      " + value);
                            break;
                        case "1664":
                            System.out.println("Final Inspection Passed Date:     " + value);
                            break;
                        case "1663":
                            System.out.println("Final Inspection Cancelled Date:      " + value);
                            break;
                        case "1665":
                            System.out.println("Final Inspection Corrections Date:        " + value);
                            break;
                        case "889":
                            System.out.println("PTO Date:         " + value);
                            break;
                        case "1666":
                            System.out.println("PTO Prepped Date:        " + value);
                            break;
                        case "1667":
                            System.out.println("PTO Submitted Date:        " + value);
                            break;
                        case "1670":
                            System.out.println("PTO Approved Date:       " + value);
                            break;
                        case "1669":
                            System.out.println("PTO Re-Submitted:        " + value);
                            break;
                        case "1668":
                            System.out.println("PTO Rejected Date:      " + value);
                            break;
                        case "1671":
                            System.out.println("EV Charger Cancelled Date:        " + value);
                            break;
                        case "1672":
                            System.out.println("EV Charger Complete Date:        " + value);
                            break;
                        case "1673":
                            System.out.println("EV Charger Scheduled Date:         " + value);
                            break;
                        case "1674":
                            System.out.println("Insulation Cancelled Date:        " + value);
                            break;
                        case "1675":
                            System.out.println("Insulation Complete Date:      " + value);
                            break;
                        case "1676":
                            System.out.println("Insulation Scheduled Date:     " + value);
                            break;
                        case "1677":
                            System.out.println("Roof Permit Approved Date:      " + value);
                            break;
                        case "1678":
                            System.out.println("Roof Permit Rejected Date:     " + value);
                            break;
                        case "1679":
                            System.out.println("Roof Permit Submitted Date:     " + value);
                            break;
                        case "1680":
                            System.out.println("Roof Inspection Cancelled Date:    " + value);
                            break;
                        case "1681":
                            System.out.println("Roof Inspection Complete Date:     " + value);
                            break;
                        case "1682":
                            System.out.println("Roof Inspection Scheduled Date:    " + value);
                            break;
                        case "1683":
                            System.out.println("HVAC Cancelled Date:  " + value);
                            break;
                        case "1684":
                            System.out.println("HVAC Complete Date:  " + value);
                            break;
                        case "1685":
                            System.out.println("HVAC Pending MPU Date:  " + value);
                            break;
                        case "1686":
                            System.out.println("HVAC Scheduled Date: " + value);
                            break;
                        case "1687":
                            System.out.println("Battery Ordered - Delivery Date:  " + value);
                            break;
                        case "1688":
                            System.out.println("Battery Ordered - Order Placed Date:  " + value);
                            break;

                        case "1691":
                            System.out.println("Battery Install - Scheduled Date:  " + value);
                            break;
                        case "1692":
                            System.out.println("MPU Install Cancelled Date:  " + value);
                            break;
                        case "1693":
                            System.out.println("MPU Install Complete Date: " + value);
                            break;
                        case "1694":
                            System.out.println("MPU Install Pending Meter Spot Date:  " + value);
                            break;
                        case "1695":
                            System.out.println("MPU Install Scheduled  Date:   " + value);
                            break;
                        case "1638":
                            System.out.println("Check Meter Spot:    " + value);
                            Project.setCheckMPU(value);
                            break;
                        case "1697":
                            System.out.println("Meter Spot Requested Date:    " + value);
                            break;
                        case "1696":
                            System.out.println("Meter Spot Scheduled Date:    " + value);
                            break;
                        case "1698":
                            System.out.println("Meter Spot Complete Date  " + value);
                            break;




                        case "1453":
                            System.out.println("Construction Call Date:  " + value);
                            break;

                        case "1422":
                            System.out.println("Final Inspection Scheduled Date:  " + value);
                            break;
                        case "1421":
                            System.out.println("Final Inspection Pass Date:  " + value);
                            break;
                        case "1408":
                            System.out.println("FI Scheduled Date:  " + value);
                            break;
                        case "1409":
                            System.out.println("FI Pass Date:  " + value);
                            break;

                        case "313":
                            System.out.println("Sale to Site Survey Duration (days)" +   value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                SITE_SURVEY += Double.parseDouble(value);
                            }
                            break;
                        case "1457":
                            System.out.println("Site Survey to NTP Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                NTP += Double.parseDouble(value);
                            }
                            break;
                        case "1458":
                            System.out.println("NTP to QC Check Duration (days): " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                QC_CHECK += Double.parseDouble(value);
                            }

                            break;
                        case "1115":
                            System.out.println("QC Check to FLA Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                FLA += Double.parseDouble(value);
                            }

                            break;
                        case "1461":
                            System.out.println("FLA to Plans Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                PLANS += Double.parseDouble(value);
                            }

                            break;
                        case "1460":
                            System.out.println("Plans to Permit Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                SOLAR_PERMIT += Double.parseDouble(value);
                            }

                            break;
                        case "762":
                            System.out.println("Permit to Installation Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                SOLARINSTALL += Double.parseDouble(value);
                            }

                            break;
                        case "1118":
                            System.out.println("Installation to Inspection Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                FinalInspection += Double.parseDouble(value);
                            }

                            break;
                        case "1120":
                            System.out.println("Inspection to PTO Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                PTO += Double.parseDouble(value);
                            }

                            break;
                        case "1462":
                            System.out.println("Sale to PTO Duration (days):  " + value);
                            if (value != null && !value.isEmpty() && !value.equals("null")) {
                                SALET_PTO += Double.parseDouble(value);
                            }

                            break;






                        default:

                            System.out.print("Field ID Not Idenitified " + ReportIDs.get(j) + '\n');
//                            System.out.print(value );
                    }


                }






                System.out.println("============================ \n");

                Projects.add(Project);

            }


            System.out.print("==============\n");
            System.out.print( AmountOfVCNotes + " Voltaic Construction Notes" );
            //  ==============================================  //
            // **********  PRINT PROJECT STAGE COUNTERS **********





        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("failed");
        }



//
//        String startDateString = "01-01-23";
//        String endDateString = "02-13-23";
//        long daysBetween = daysBetween(startDateString, endDateString);
//        System.out.println("Number of days between " + startDateString + " and " + endDateString + " is " + daysBetween);


        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        System.out.println("Current date is: " + formattedDate);



        LocalDate oneWeekAgoFromToday = currentDate.minusDays(7);
        DateTimeFormatter Weekformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String OnWeekAgoformattedDate = oneWeekAgoFromToday.format(Weekformatter);
        System.out.println("One week from today is: " + OnWeekAgoformattedDate);


        String startDateString = OnWeekAgoformattedDate;
        String endDateString = formattedDate;


        //Permit Submit
        String dateString1 = "2023-01-15";
        //Permit Received
        String dateString2 = "2023-01-15";
        boolean isDate1NotBetween = isDateNotBetween(dateString1, startDateString, endDateString);
        boolean isDate2NotBetween = isDateNotBetween(dateString2, startDateString, endDateString);


        System.out.println(dateString1 + " is not between " + startDateString + " and " + endDateString + ": " + isDate1NotBetween);

        if(isDate1NotBetween == true) {
            System.out.println("Date is not between");
        }else{
            LastWeeksPermit++;

            System.out.println("Date is between");
        }


        if(isDate2NotBetween == true) {
            System.out.println("Date is not between");
        }else{
            LastWeeksPermit_recieved++;
            System.out.println("Date is between");
        }



        //convert int to double

        double PermitSubmittedCount = (double) LastWeeksPermit;
        double PermitReceivedCount = (double) LastWeeksPermit_recieved;

        double SITE_SURVEYCount = (double) SITE_SURVEY_count;
        double QC_CHECKCount = (double) QC_CHECK_count;
        double FLACount = (double) FLA_count;
        double NTPCount = (double) NTP_count;
        double PLANSCount = (double) PLANS_count;
        double SOLAR_PERMITCount = (double) SOLAR_PERMIT_count;
        double SOLARINSTALLCount = (double) SOLARINSTALL_count;
        double FinalInspectionCount = (double) FinalInspection_count;
        double PTOCount = (double) PTO_count;
        double NewSaleCount = (double) NewSale_count;
        double ComCount = (double) Complete_count;
        double SALET_PTOCount = (double) SALET_PTO_count;


        //  ==============================================  //

        PipelineDuration.put("Permit Submitted", PermitSubmittedCount);
        PipelineDuration.put("Permits Received", PermitReceivedCount);


//        PipelineDuration.put("SITE_SURVEY", SITE_SURVEYCount);
//        PipelineDuration.put("QC_CHECK", QC_CHECKCount);
//        PipelineDuration.put("FLA", FLACount);
//        PipelineDuration.put("NTP", NTPCount);
//        PipelineDuration.put("PLANS", PLANSCount);
//        PipelineDuration.put("SOLAR_PERMIT", SOLAR_PERMITCount);
//        PipelineDuration.put("SOLARINSTALL", SOLARINSTALLCount);
//        PipelineDuration.put("FinalInspection", FinalInspectionCount);
//        PipelineDuration.put("PTO", PTOCount);
//        PipelineDuration.put("NewSale", NewSaleCount);
//        PipelineDuration.put("Complete", ComCount);
//        PipelineDuration.put("SALET_PTO", SALET_PTOCount);

//        return PipelineDuration;

        return Projects;


    }

//    public Map<String, Double> PipelineDuration(String API, ArrayList<String> ReportIDs) {
////        System.out.println("Pulling report: " +ReportID + "..." );
//
//
//
//        Map<String, Double> PipelineDuration = new HashMap<String, Double>();
//
//        int AmountOfVCNotes = 0;
//
//
//
//// Stage Pie Chart numbers
//        //PROJECT STAGE COUNTERS
//        int NewSale_count = 0;
//        int SITE_SURVEY_count = 0;
//        int QC_CHECK_count = 0;
//        int FLA_count = 0;
//        int NTP_count = 0;
//        int PLANS_count = 0;
//        int SOLAR_PERMIT_count = 0;
//        int SOLARINSTALL_count = 0;
//        int FinalInspection_count = 0;
//        int SystemActivation_count = 0;
//        int PTO_count = 0;
//        int Complete_count = 0;
//        int SALET_PTO_count = 0;
//
//
//// Last weeksPermit
//        int LastWeeksPermit = 0;
//        int LastWeeksPermit_recieved = 0;
//
//
//
//
//        //DURATIONS
//
//
//        //FULLFILLMENT STAGES
//        Double NewSale = 0.0;
//        Double SITE_SURVEY = 0.0;
//        Double QC_CHECK = 0.0;
//        Double FLA = 0.0;
//        Double NTP = 0.0;
//        Double PLANS = 0.0;
//        Double SOLAR_PERMIT = 0.0;
//        Double SOLARINSTALL = 0.0;
//        Double FinalInspection = 0.0;
//        Double SystemActivation = 0.0;
//        Double PTO = 0.0;
//        Double Complete = 0.0;
//        Double SALET_PTO = 0.0;
//
//
//
//        try {
//
//
//
//            // Create the URL to the Quickbase API endpoint for the table
//            URL url = new URL(API);
//
//            // Open the connection to the Quickbase API
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            // Set the request method and add the user token to the request header
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Authorization", USER_TOKEN);
//            connection.setRequestProperty("QB-Realm-Hostname", QB_DOMAIN);
//            connection.setRequestProperty("Content-Type", "application/json");
//
//            //    ////////****************************************/////////
//
//            //   **********  FORMAT JSON BODY WITH - TABLE_ID_# AND FIELD_IDS TO QUERY  **********
//
//            //    ////  ****** CURRENTLY HARDCODED INTO THE JsonBody blow  **********////
//            //    ////////****************************************/////////
//
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//            //      Parse JSON
//            JsonParser parser = new JsonParser();
//            JsonObject jsonObject = parser.parse(String.valueOf(response)).getAsJsonObject();
//
//            //      BUILD GSON
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            StringWriter stringWriter = new StringWriter();
//            JsonWriter jsonWriter = new JsonWriter(stringWriter);
//            jsonWriter.setIndent("  ");
//            gson.toJson(jsonObject, jsonWriter);
//
//            //      RESPONSE:
//            //    ////////****************************************/////////
//            //   ********** BE SURE TO LABEL DATA BEING OUTPUT IN THIS OUTPUT STRING:   **********
//            //    ////////****************************************/////////
//
//
//            //    ////////****************************************/////////
//            //LOOP THROUGH DATA ARRAY
//
//
//
//            JsonArray data = jsonObject.getAsJsonArray("data");
//
////            JsonObject data = jsonObject.getAsJsonObject("data");
//            //    gson.toJson(data, jsonWriter);
//
//            System.out.print(ReportIDs + "\n");
//            System.out.println(" TABLE DATA SIZE : ( " + data.size() + " Items ): ===========");
//
//            //   System.out.println(data);
//
//
//            for (int i = 0; i < data.size(); i++) {
//
//                JsonObject item = data.get(i).getAsJsonObject();
//
//                //Stage
//                String count = item.get("599").getAsJsonObject().get("value").toString();
//
//
//                if (count.equals("\"New Sale\"")){
//                    System.out.println(NewSale_count);
//                    NewSale_count += 1;
//
//                }else if (count.equals("\"Site Survey\"")){
//                    System.out.println(SITE_SURVEY_count);
//                    SITE_SURVEY_count += 1;}
//                else if (count.equals("\"QC Check\"")){
//                    System.out.println(QC_CHECK_count);
//                    QC_CHECK_count += 1;
//                }else if (count.equals("\"FLA\"")){
//                    System.out.println(FLA_count);
//                    FLA_count += 1;
//
//                }else if (count.equals("\"NTP\"")) {
//                    System.out.println(NTP_count);
//                    NTP_count += 1;
//                }
//                else if (count.equals("\"Plans\"")){
//                    System.out.println(PLANS_count);
//                    PLANS_count += 1;
//                }
//                else if (count.equals("\"Solar Permit\"")){
//                    System.out.println(SOLAR_PERMIT_count);
//                    SOLAR_PERMIT_count += 1;
//                }
//                else if (count.equals("\"Solar Install\"")){
//                    System.out.println(SOLARINSTALL_count);
//                    SOLARINSTALL_count += 1;
//                }
//                else if (count.equals("\"Final Inspection\"")){
//                    System.out.println(FinalInspection_count);
//                    FinalInspection_count += 1;
//                }
//                else if (count.equals("\"System Activation\"")){
//                    System.out.println(SystemActivation_count);
//                    SystemActivation_count += 1;
//                }
//                else if (count.equals("\"PTO\"")){
//                    System.out.println(PTO_count);
//                    PTO_count += 1;
//                }
//                else if (count.equals("\"Complete\"")){
//                    System.out.println(Complete_count);
//                    Complete_count += 1;
//                }
//                else {
//                    System.out.println("ERROR:  Stage not found");
//                    System.out.println("Stage: " + count);
//                }
//
//                for (int j =0; j < ReportIDs.size(); j++){
//
//
//                    //PROJECT.updateStatusTime("Welcome", "date" ,  WelcomeDate);
//
//
//                    String value =  item.get(ReportIDs.get(j)).getAsJsonObject().get("value").toString();
////                  System.out.println(Homeowner);
////
//// PRINT AND CHECK FOR ANY NEW FIELDS IN THE PROJECT REPORT
//
//                    switch (ReportIDs.get(j)) {
//
//                        case "105":
//                            System.out.println("Homeowner 1:   " + value);
//                            break;
//                        case "93":
//                            System.out.println("Address: Street:   " + value);
//                            break;
//                        case "95":
//                            System.out.println("Address City:   " + value);
//                            break;
//                        case "719":
//                            System.out.println("AHJ:   " + value);
//                            break;
//                        case "770":
//                            System.out.println("Products:   " + value);
//                            break;
//                        case "599":
//                            System.out.println("Stage:   " + value);
//
//                            break;
//                        case "102":
//                            System.out.println("Sale Date:   " + value);
//                            break;
//                        case "915":
//                            System.out.println("Site Survey - Date:    " + value);
//                            break;
//                        case "862":
//                            System.out.println("NTP - Date:    " + value);
//                            break;
//                        case "855":
//                            System.out.println("QC Check Status:   " + value);
//                            break;
//                        case "906":
//                            System.out.println("Design - Status:    " + value);
//                            break;
//                        case "907":
//                            System.out.println("Design - Date:    " + value);
//                            break;
//                        case "1446":
//                            System.out.println("Plans - Status:   " + value);
//                            break;
//                        case "1443":
//                            System.out.println("Plans Requested:    " + value);
//                            break;
//                        case "1444":
//                            System.out.println("PLans Received:   " + value);
//                            break;
//                        case "858":
//                            System.out.println("FLA - Status:   " + value);
//                            break;
//                        case "867":
//                            System.out.println("Solar Permit - Status:   " + value);
//                            break;
//                        case "1346":
//                            System.out.println("Solar Permit - Date:    " + value);
//                            break;
//                        case "1403":
//                            System.out.println("Solar Permit - Permit Submitted:    " + value);
//                            break;
//                        case "1402":
//                            System.out.println("Solar Permit - Permit Received:   " + value);
//                            break;
//                        case "877":
//                            System.out.println("Solar Install - Date:   " + value);
//                            break;
//                        case "634":
//                            System.out.println("Installer:   " + value);
//                            break;
//                        case "408":
//                            System.out.println("Status:   " + value);
//                            break;
//
//                        case "839":
//                            System.out.println("Voltaic Check Date:   " + value);
//                            break;
//
//                        case "1377":
//                            System.out.println("Welcome Date:    " + value);
//                            break;
//                        case "856":
//                            System.out.println("QC Check Date:   " + value);
//                            break;
//
//                        case "859":
//                            System.out.println("FLA Date:    " + value);
//                            break;
//                        case "1657":
//                            System.out.println("Solar Permit Submitted Date:     " + value);
//                            break;
//
//                        case "1656":
//                            System.out.println("Solar Permit Permit Receive Date:    " + value);
//                            break;
//
//                        case "1577":
//                            System.out.println("Solar Permit Re-Submitted Date:    " + value);
//                            break;
//
//                        case "1574":
//                            System.out.println("Solar Permit Approved Date:    " + value);
//                            break;
//                        case "1485":
//                            System.out.println("Solar Permit Rejected Red; Lines:    " + value);
//                            break;
//                        case "740":
//                            System.out.println("Progress Bar:    " + "**HTML Element" + "value");
//                            break;
//
//                        case "1639":
//                            System.out.println("Site Survey Schedule Date:    " + value);
//                            break;
//                        case "1641":
//                            System.out.println("Site Survey Complete Date:    " + value);
//                            break;
//                        case "1642":
//                            System.out.println("Site Survey Cancelled Date:    " + value);
//                            break;
//                        case "1644":
//                            System.out.println("NTP Submited Date    " + value);
//                            break;
//                        case "1643":
//                            System.out.println("NTP Granted Date:   " + value);
//                            break;
//                        case "1648":
//                            System.out.println("QC Check Pass with Electrical Date:   " + value);
//                            break;
//
//                        case "1645":
//                            System.out.println("QC Check Pass Date:   " + value);
//                            break;
//                        case "1646":
//                            System.out.println("QC Chcek Fail Date:    " + value);
//                            break;
//                        case "1647":
//                            System.out.println("QC Check Incomplete Date:   " + value);
//                            break;
//
//                        case "1445":
//                            System.out.println("Plans - Date:    " + value);
//                            break;
//                        case "1649":
//                            System.out.println("Plans Requested Date:    " + value);
//                            break;
//                        case "1650":
//                            System.out.println("Plans Recieved Date:    " + value);
//                            break;
//                        case "1651":
//                            System.out.println("Plans Revison Date:    " + value);
//                            break;
//                        case "1652":
//                            System.out.println("FLA Sent Date:    " + value);
//                            break;
//
//                        case "1653":
//                            System.out.println("FLA Recieved Date:     " + value);
//                            break;
//
//                        case "1654":
//                            System.out.println("FLA Approved Date:      " + value);
//                            break;
//                        case "1655":
//                            System.out.println("FLA Revision Date:      " + value);
//                            break;
//                        case "1575":
//                            System.out.println("Check Solar Submission:      " + value);
//                            break;
//                        case "1660":
//                            System.out.println("Solar Install Schedule Date:     " + value);
//                            break;
//                        case "1659":
//                            System.out.println("Solar Install Complete Date:      " + value);
//                            break;
//                        case "1658":
//                            System.out.println("Solar Install Cancelled Date:     " + value);
//                            break;
//                        case "1661":
//                            System.out.println("Solar Install Unscheduled Date:     " + value);
//                            break;
//                        case "883":
//                            System.out.println("Final Inspection Date:     " + value);
//                            break;
//                        case "1662":
//                            System.out.println("Final Inspection Scheduled Date:      " + value);
//                            break;
//                        case "1664":
//                            System.out.println("Final Inspection Passed Date:     " + value);
//                            break;
//                        case "1663":
//                            System.out.println("Final Inspection Cancelled Date:      " + value);
//                            break;
//                        case "1665":
//                            System.out.println("Final Inspection Corrections Date:        " + value);
//                            break;
//                        case "889":
//                            System.out.println("PTO Date:         " + value);
//                            break;
//                        case "1666":
//                            System.out.println("PTO Prepped Date:        " + value);
//                            break;
//                        case "1667":
//                            System.out.println("PTO Submitted Date:        " + value);
//                            break;
//                        case "1670":
//                            System.out.println("PTO Approved Date:       " + value);
//                            break;
//                        case "1669":
//                            System.out.println("PTO Re-Submitted:        " + value);
//                            break;
//                        case "1668":
//                            System.out.println("PTO Rejected Date:      " + value);
//                            break;
//                        case "1671":
//                            System.out.println("EV Charger Cancelled Date:        " + value);
//                            break;
//                        case "1672":
//                            System.out.println("EV Charger Complete Date:        " + value);
//                            break;
//                        case "1673":
//                            System.out.println("EV Charger Scheduled Date:         " + value);
//                            break;
//                        case "1674":
//                            System.out.println("Insulation Cancelled Date:        " + value);
//                            break;
//                        case "1675":
//                            System.out.println("Insulation Complete Date:      " + value);
//                            break;
//                        case "1676":
//                            System.out.println("Insulation Scheduled Date:     " + value);
//                            break;
//                        case "1677":
//                            System.out.println("Roof Permit Approved Date:      " + value);
//                            break;
//                        case "1678":
//                            System.out.println("Roof Permit Rejected Date:     " + value);
//                            break;
//                        case "1679":
//                            System.out.println("Roof Permit Submitted Date:     " + value);
//                            break;
//                        case "1680":
//                            System.out.println("Roof Inspection Cancelled Date:    " + value);
//                            break;
//                        case "1681":
//                            System.out.println("Roof Inspection Complete Date:     " + value);
//                            break;
//                        case "1682":
//                            System.out.println("Roof Inspection Scheduled Date:    " + value);
//                            break;
//                        case "1683":
//                            System.out.println("HVAC Cancelled Date:  " + value);
//                            break;
//                        case "1684":
//                            System.out.println("HVAC Complete Date:  " + value);
//                            break;
//                        case "1685":
//                            System.out.println("HVAC Pending MPU Date:  " + value);
//                            break;
//                        case "1686":
//                            System.out.println("HVAC Scheduled Date: " + value);
//                            break;
//                        case "1687":
//                            System.out.println("Battery Ordered - Delivery Date:  " + value);
//                            break;
//                        case "1688":
//                            System.out.println("Battery Ordered - Order Placed Date:  " + value);
//                            break;
//
//                        case "1691":
//                            System.out.println("Battery Install - Scheduled Date:  " + value);
//                            break;
//                        case "1692":
//                            System.out.println("MPU Install Cancelled Date:  " + value);
//                            break;
//                        case "1693":
//                            System.out.println("MPU Install Complete Date: " + value);
//                            break;
//                        case "1694":
//                            System.out.println("MPU Install Pending Meter Spot Date:  " + value);
//                            break;
//                        case "1695":
//                            System.out.println("MPU Install Scheduled  Date:   " + value);
//                            break;
//                        case "1638":
//                            System.out.println("Check Meter Spot:    " + value);
//                            break;
//                        case "1697":
//                            System.out.println("Meter Spot Requested Date:    " + value);
//                            break;
//                        case "1696":
//                            System.out.println("Meter Spot Scheduled Date:    " + value);
//                            break;
//                        case "1698":
//                            System.out.println("Meter Spot Complete Date  " + value);
//                            break;
//
//
//
//
//                        case "1453":
//                            System.out.println("Construction Call Date:  " + value);
//                            break;
//
//                        case "1422":
//                            System.out.println("Final Inspection Scheduled Date:  " + value);
//                            break;
//                        case "1421":
//                            System.out.println("Final Inspection Pass Date:  " + value);
//                            break;
//                        case "1408":
//                            System.out.println("FI Scheduled Date:  " + value);
//                            break;
//                        case "1409":
//                            System.out.println("FI Pass Date:  " + value);
//                            break;
//
//                        case "313":
//                            System.out.println("Sale to Site Survey Duration (days)" +   value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                SITE_SURVEY += Double.parseDouble(value);
//                            }
//                            break;
//                        case "1457":
//                            System.out.println("Site Survey to NTP Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                NTP += Double.parseDouble(value);
//                            }
//                            break;
//                        case "1458":
//                            System.out.println("NTP to QC Check Duration (days): " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                QC_CHECK += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "1115":
//                            System.out.println("QC Check to FLA Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                FLA += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "1461":
//                            System.out.println("FLA to Plans Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                PLANS += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "1460":
//                            System.out.println("Plans to Permit Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                SOLAR_PERMIT += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "762":
//                            System.out.println("Permit to Installation Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                SOLARINSTALL += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "1118":
//                            System.out.println("Installation to Inspection Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                FinalInspection += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "1120":
//                            System.out.println("Inspection to PTO Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                PTO += Double.parseDouble(value);
//                            }
//
//                            break;
//                        case "1462":
//                            System.out.println("Sale to PTO Duration (days):  " + value);
//                            if (value != null && !value.isEmpty() && !value.equals("null")) {
//                                SALET_PTO += Double.parseDouble(value);
//                            }
//
//                            break;
//
//
//
//
//
//
//                        default:
//
//                            System.out.print("Field ID Not Idenitified " + ReportIDs.get(j) + '\n');
////                            System.out.print(value );
//                    }
//
//
//                }
//                System.out.println("============================ \n");
//
//
//
//            }
//
//
//            System.out.print("==============\n");
//            System.out.print( AmountOfVCNotes + " Voltaic Construction Notes" );
//            //  ==============================================  //
//            // **********  PRINT PROJECT STAGE COUNTERS **********
//
//
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("failed");
//        }
//
//
//
////
////        String startDateString = "01-01-23";
////        String endDateString = "02-13-23";
////        long daysBetween = daysBetween(startDateString, endDateString);
////        System.out.println("Number of days between " + startDateString + " and " + endDateString + " is " + daysBetween);
//
//
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String formattedDate = currentDate.format(formatter);
//        System.out.println("Current date is: " + formattedDate);
//
//
//
//        LocalDate oneWeekAgoFromToday = currentDate.minusDays(7);
//        DateTimeFormatter Weekformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String OnWeekAgoformattedDate = oneWeekAgoFromToday.format(Weekformatter);
//        System.out.println("One week from today is: " + OnWeekAgoformattedDate);
//
//
//        String startDateString = OnWeekAgoformattedDate;
//        String endDateString = formattedDate;
//
//
//        //Permit Submit
//        String dateString1 = "2023-01-15";
//        //Permit Received
//        String dateString2 = "2023-01-15";
//        boolean isDate1NotBetween = isDateNotBetween(dateString1, startDateString, endDateString);
//        boolean isDate2NotBetween = isDateNotBetween(dateString2, startDateString, endDateString);
//
//
//        System.out.println(dateString1 + " is not between " + startDateString + " and " + endDateString + ": " + isDate1NotBetween);
//
//        if(isDate1NotBetween == true) {
//            System.out.println("Date is not between");
//        }else{
//            LastWeeksPermit++;
//
//            System.out.println("Date is between");
//        }
//
//
//        if(isDate2NotBetween == true) {
//            System.out.println("Date is not between");
//        }else{
//            LastWeeksPermit_recieved++;
//            System.out.println("Date is between");
//        }
//
//
//
//        //convert int to double
//
//        double PermitSubmittedCount = (double) LastWeeksPermit;
//        double PermitReceivedCount = (double) LastWeeksPermit_recieved;
//
//        double SITE_SURVEYCount = (double) SITE_SURVEY_count;
//        double QC_CHECKCount = (double) QC_CHECK_count;
//        double FLACount = (double) FLA_count;
//        double NTPCount = (double) NTP_count;
//        double PLANSCount = (double) PLANS_count;
//        double SOLAR_PERMITCount = (double) SOLAR_PERMIT_count;
//        double SOLARINSTALLCount = (double) SOLARINSTALL_count;
//        double FinalInspectionCount = (double) FinalInspection_count;
//        double PTOCount = (double) PTO_count;
//        double NewSaleCount = (double) NewSale_count;
//        double ComCount = (double) Complete_count;
//        double SALET_PTOCount = (double) SALET_PTO_count;
//
//
//        //  ==============================================  //
//
//        PipelineDuration.put("Permit Submitted", PermitSubmittedCount);
//        PipelineDuration.put("Permits Received", PermitReceivedCount);
//
//
////        PipelineDuration.put("SITE_SURVEY", SITE_SURVEYCount);
////        PipelineDuration.put("QC_CHECK", QC_CHECKCount);
////        PipelineDuration.put("FLA", FLACount);
////        PipelineDuration.put("NTP", NTPCount);
////        PipelineDuration.put("PLANS", PLANSCount);
////        PipelineDuration.put("SOLAR_PERMIT", SOLAR_PERMITCount);
////        PipelineDuration.put("SOLARINSTALL", SOLARINSTALLCount);
////        PipelineDuration.put("FinalInspection", FinalInspectionCount);
////        PipelineDuration.put("PTO", PTOCount);
////        PipelineDuration.put("NewSale", NewSaleCount);
////        PipelineDuration.put("Complete", ComCount);
////        PipelineDuration.put("SALET_PTO", SALET_PTOCount);
//
//        return PipelineDuration;
//
//
//    }






}
