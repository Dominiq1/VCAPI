package dev.danvega.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReportController {

    private static final String Get_Voltaic_Report = "https://api.quickbase.com/v1/reports/305?tableId=br5cqr4r3";

    private static final String Post_Pull_Voltaic_Report = "https://api.quickbase.com/v1/reports/305/run?tableId=br5cqr4r3";





//
//    @PostMapping("/getReport")
//    public void getReport(){
//
//        Map<String, Double> DURATION = new HashMap<>();
//
//        ArrayList<Double> DURATIONREPORT = new ArrayList<>();
//        ArrayList<String> DURATIONNAMES = new ArrayList<>();
//
//
//
//        try{
//            QBApi QBAPI =  new QBApi();
//
//
//            //Run Quickbase logic and get data
//            ArrayList<String> DURATIONReportFields =  QBAPI.getAppTables(Get_Voltaic_Report);
//            //DURATION  = QBAPI.PipelineDuration(Post_Pull_Voltaic_Report, DURATIONReportFields);
//// Loop through the map using for-each loop
//            for (Map.Entry<String, Double> entry : DURATION.entrySet()) {
//
//                String key = entry.getKey();
//                Double value = entry.getValue();
//                DURATIONREPORT.add(value);
//                DURATIONNAMES.add(key);
//                System.out.println("Key: " + key + ", Value: " + value);
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//




}
