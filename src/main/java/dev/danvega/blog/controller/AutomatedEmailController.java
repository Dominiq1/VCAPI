package dev.danvega.blog.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import  org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
@RestController
public class AutomatedEmailController {
    private static final String Get_Voltaic_Report = "https://api.quickbase.com/v1/reports/321?tableId=br5cqr4r3";
    private static final String Post_Pull_Voltaic_Report = "https://api.quickbase.com/v1/reports/321/run?tableId=br5cqr4r3";


    private static final String Get_Vitals_Report = "https://api.quickbase.com/v1/reports/321?tableId=br5cqr4r3";
    private static final String Post_Pull_Vitals_Report = "https://api.quickbase.com/v1/reports/321/run?tableId=br5cqr4r3";


    private static final String template = "Sent an email to %s! It should be there soon.";
    private final AtomicLong counter = new AtomicLong();

    private final JavaMailSender emailSender;

    public AutomatedEmailController(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @GetMapping("/email")
    public Greeting email(@RequestParam String email) {

        // Create and send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("New Task!");
        message.setFrom("construction@voltaicnow.com");
        message.setText("Hey there! You have a new task!");

        emailSender.send(message);
        // Return response
        return new Greeting(counter.incrementAndGet(), String.format(template, email));
    }




    // ==============================================
    //  *****   SCHEDULED EMAILS
    // ==============================================

    // ==============================================
    // Voltaic Construction Pipeline Automated Emails
    // ==============================================

    // ==============================================
    //  *****   1.Voltaic Check
    // ==============================================

    // ==============================================
    //  *****   2.Welcome
    // ==============================================

    // ==============================================
    // *****   3.Site Survey
    // ==============================================


    // ==============================================
    //   *****  4.Construction Call
    // ==============================================


    // ==============================================
    //  *****   5.NTP
    // ==============================================


    // ==============================================
    //   *****  6.QC Check
    // ==============================================


    // ==============================================
    //  *****   7.Plans
    // ==============================================


    // ==============================================
    //  *****   8.FLA
    // ==============================================

    // ==============================================
    //   *****  9.Solar Permit
    // ==============================================



    @PostMapping("/permitsubmitted1week")
    public Greeting permitSubmitted1Week(@RequestBody Map<String, String> request) {

        int i = 0;
        ArrayList<String> ProjectsWithMeterSpotsReq10DaysPlus = new ArrayList<>();

//        Map<String, Double> DURATION = new HashMap<>();
        ArrayList<VoltaicProject> VOLTAICPROJECTS = new ArrayList<>();

        ArrayList<Double> DURATIONREPORT = new ArrayList<>();
        ArrayList<String> DURATIONNAMES = new ArrayList<>();

        try{
            QBApi QBAPI =  new QBApi();


            //Run Quickbase logic and get data
            ArrayList<String> DURATIONReportFields =  QBAPI.getAppTables(Get_Vitals_Report);
            VOLTAICPROJECTS  = QBAPI.PipelineDuration(Post_Pull_Vitals_Report, DURATIONReportFields);


            // SEARCH PROEJCTS
            System.out.println("Searching Projects...");

            for (VoltaicProject project : VOLTAICPROJECTS) {

                String projectRecrodID = project.getProjectRecordID();
                String ProjectAHJ = project.getAHJ();
                String ProjectAddress = project.getAddress();
                String SolarStatus = project.getCheckSolarPermit();

                if(SolarStatus.equals("\"true\"")){

                    System.out.println(projectRecrodID);
                    System.out.println(ProjectAddress);
                    System.out.println(ProjectAHJ);
                    System.out.println(SolarStatus);

                    ProjectsWithMeterSpotsReq10DaysPlus.add(projectRecrodID);
                    System.out.println("Project to add to email Check Meter Spot Requested 10 Days !");
                    i++;
                }




//                DURATION.put(key, value);
//                DURATIONREPORT.add(value);
//                DURATIONNAMES.add(key);
//                System.out.println("Key: " + key + ", Value: " + value);
            }

// Loop through the map using for-each loop
//            for (Map.Entry<String, Double> entry : DURATION.entrySet()) {
//                i++;
//                String key = entry.getKey();
//                Double value = entry.getValue();
//                DURATIONREPORT.add(value);
//                DURATIONNAMES.add(key);
//                System.out.println("Key: " + key + ", Value: " + value);
//
//
//            }


        }catch (Exception e){
            e.printStackTrace();
        }


        //May need to parse the request body for ALL mpu links to send this in PROD.
        // Get the current date
        LocalDate currentDate = LocalDate.now();

// Format the date as desired
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);

        String subject = "Solar permit(s) submitted 1 week ago or more with out response:  " + formattedDate + " !";


        String body = "<body><h1>Here is the active list of Solar Permits submitted 7 days ago or more without response.  " +
                "</h1><br><p>Take action below.</p>";

        for (String projectRecordID : ProjectsWithMeterSpotsReq10DaysPlus) {
            body += "<p>QB Project Link: <a href=\"https://solarcrm.quickbase.com/db/br5cqr4r3?a=er&rid=" + projectRecordID + "&rl=nvq\">" + projectRecordID + "</a></p>";
        }

        body += "<img src=\"https://firebasestorage.googleapis.com/v0/b/voltaicconstruction.appspot.com/o/VC.png?alt=media&amp;token=58122a6a-bed0-4344-875b-8f3a1b4822ad\" alt=\"Voltaic Construction\" style=\"width: 100px; height: auto;\"/></body>";

//        String body = "<body><h1>Here is the active list of Meter Spots requested 10 days ago or more.  " +
//                homeOwnerName + "</h1><br><p>Take action below. "+
//

//
//                "</p><p>Project Link(s): <a href=\"https://solarcrm.quickbase.com/db/br5cqr4r3?a=er&rid="+RecordAddress+"&rl=nvq\">Link 1</a></p><img src=\"https://firebasestorage.googleapis.com/v0/b/voltaicconstruction.appspot.com/o/VC.png?alt=media&amp;token=58122a6a-bed0-4344-875b-8f3a1b4822ad\" alt=\"Voltaic Construction\" style=\"width: 100px; height: auto;\"/></body>";

        // Create and send email
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(homeOwnerEmail);
            helper.setTo("Jessmathieu@voltaicnow.com ");
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom("construction@voltaicnow.com");

//            // Add corporate logo to the email
//            ClassPathResource resource = new ClassPathResource("static/images/VC.png");
//            helper.addInline("VC.png", resource);


            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Greeting(counter.incrementAndGet(), "Failed to send email.");
        }

        // Return response
        return new Greeting(counter.incrementAndGet(), String.format("Automated Email Sent"));
    }



    // ==============================================
    //   *****  10.Solar Install
    // ==============================================


    // ==============================================
    //   *****  11.Final Inspection
    // ==============================================


    // ==============================================
    //  *****   PTO
    // ==============================================





    // ==============================================
    //  *****  Meter Spot
    // ==============================================


    // 1. Daily "Check Meter Spot Report Email".




    @PostMapping("/meterSpotRequested10Days")
    public Greeting meterSpotRequested10Days(@RequestBody Map<String, String> request) {

        int i = 0;
        ArrayList<String> ProjectsWithMeterSpotsReq10DaysPlus = new ArrayList<>();

//        Map<String, Double> DURATION = new HashMap<>();
        ArrayList<VoltaicProject> VOLTAICPROJECTS = new ArrayList<>();

        ArrayList<Double> DURATIONREPORT = new ArrayList<>();
        ArrayList<String> DURATIONNAMES = new ArrayList<>();

        try{
            QBApi QBAPI =  new QBApi();


            //Run Quickbase logic and get data
            ArrayList<String> DURATIONReportFields =  QBAPI.getAppTables(Get_Vitals_Report);
            VOLTAICPROJECTS  = QBAPI.PipelineDuration(Post_Pull_Vitals_Report, DURATIONReportFields);


            // SEARCH PROEJCTS
            System.out.println("Searching Projects...");

            for (VoltaicProject project : VOLTAICPROJECTS) {

                String projectRecrodID = project.getProjectRecordID();
                String ProjectAHJ = project.getAHJ();
                String ProjectAddress = project.getAddress();
                String MeterStatus = project.getCheckMPU();

                if(MeterStatus.equals("\"true\"")){

                    System.out.println(projectRecrodID);
                    System.out.println(ProjectAddress);
                    System.out.println(ProjectAHJ);
                    System.out.println(MeterStatus);

                    ProjectsWithMeterSpotsReq10DaysPlus.add(projectRecrodID);
                    System.out.println("Project to add to email Check Meter Spot Requested 10 Days !");
                    i++;
                }




//                DURATION.put(key, value);
//                DURATIONREPORT.add(value);
//                DURATIONNAMES.add(key);
//                System.out.println("Key: " + key + ", Value: " + value);
            }

// Loop through the map using for-each loop
//            for (Map.Entry<String, Double> entry : DURATION.entrySet()) {
//                i++;
//                String key = entry.getKey();
//                Double value = entry.getValue();
//                DURATIONREPORT.add(value);
//                DURATIONNAMES.add(key);
//                System.out.println("Key: " + key + ", Value: " + value);
//
//
//            }


        }catch (Exception e){
            e.printStackTrace();
        }


        //May need to parse the request body for ALL mpu links to send this in PROD.
        // Get the current date
        LocalDate currentDate = LocalDate.now();

// Format the date as desired
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);

        String subject = "Overdue Meter Spots! " + formattedDate + " !";


        String body = "<body><h1>Here is the active list of Meter Spots requested 10 days ago or more.  " +
                "</h1><br><p>Take action below.</p>";

        for (String projectRecordID : ProjectsWithMeterSpotsReq10DaysPlus) {
            body += "<p>QB Project Link: <a href=\"https://solarcrm.quickbase.com/db/br5cqr4r3?a=er&rid=" + projectRecordID + "&rl=nvq\">" + projectRecordID + "</a></p>";
        }

        body += "<img src=\"https://firebasestorage.googleapis.com/v0/b/voltaicconstruction.appspot.com/o/VC.png?alt=media&amp;token=58122a6a-bed0-4344-875b-8f3a1b4822ad\" alt=\"Voltaic Construction\" style=\"width: 100px; height: auto;\"/></body>";

//        String body = "<body><h1>Here is the active list of Meter Spots requested 10 days ago or more.  " +
//                homeOwnerName + "</h1><br><p>Take action below. "+
//

//
//                "</p><p>Project Link(s): <a href=\"https://solarcrm.quickbase.com/db/br5cqr4r3?a=er&rid="+RecordAddress+"&rl=nvq\">Link 1</a></p><img src=\"https://firebasestorage.googleapis.com/v0/b/voltaicconstruction.appspot.com/o/VC.png?alt=media&amp;token=58122a6a-bed0-4344-875b-8f3a1b4822ad\" alt=\"Voltaic Construction\" style=\"width: 100px; height: auto;\"/></body>";

        // Create and send email
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(homeOwnerEmail);
            helper.setTo("alyssaosborne@voltaicnow.com");
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom("construction@voltaicnow.com");

//            // Add corporate logo to the email
//            ClassPathResource resource = new ClassPathResource("static/images/VC.png");
//            helper.addInline("VC.png", resource);


            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Greeting(counter.incrementAndGet(), "Failed to send email.");
        }

        // Return response
        return new Greeting(counter.incrementAndGet(), String.format("Automated Email Sent"));
    }


//    @PostMapping("/meterSpotsRequested10Days")
//    public Greeting meterSpotRequested10Days(@RequestBody Map<String, String> request) {
//
//
//        int i = 0;
//        ArrayList<String> ProjectsWithMeterSpotsReq10DaysPlus = new ArrayList<>();
//
////        Map<String, Double> DURATION = new HashMap<>();
//        ArrayList<VoltaicProject> VOLTAICPROJECTS = new ArrayList<>();
//
//        ArrayList<Double> DURATIONREPORT = new ArrayList<>();
//        ArrayList<String> DURATIONNAMES = new ArrayList<>();
//
//        try{
//            QBApi QBAPI =  new QBApi();
//
//
//            //Run Quickbase logic and get data
//            ArrayList<String> DURATIONReportFields =  QBAPI.getAppTables(Get_Vitals_Report);
//            VOLTAICPROJECTS  = QBAPI.PipelineDuration(Post_Pull_Vitals_Report, DURATIONReportFields);
//
//
//            // SEARCH PROEJCTS
//            System.out.println("Searching Projects...");
//
//            for (VoltaicProject project : VOLTAICPROJECTS) {
//
//                String projectRecrodID = project.getProjectRecordID();
//                String ProjectAHJ = project.getAHJ();
//                String ProjectAddress = project.getAddress();
//                String MeterStatus = project.getCheckMPU();
//
//                if(MeterStatus.equals("\"true\"")){
//
//                    System.out.println(projectRecrodID);
//                    System.out.println(ProjectAddress);
//                    System.out.println(ProjectAHJ);
//                    System.out.println(MeterStatus);
//
//                    ProjectsWithMeterSpotsReq10DaysPlus.add(projectRecrodID);
//                    System.out.println("Project to add to email Check Meter Spot Requested 10 Days !");
//                    i++;
//                }
//
//
//
//
////                DURATION.put(key, value);
////                DURATIONREPORT.add(value);
////                DURATIONNAMES.add(key);
////                System.out.println("Key: " + key + ", Value: " + value);
//            }
//
//// Loop through the map using for-each loop
////            for (Map.Entry<String, Double> entry : DURATION.entrySet()) {
////                i++;
////                String key = entry.getKey();
////                Double value = entry.getValue();
////                DURATIONREPORT.add(value);
////                DURATIONNAMES.add(key);
////                System.out.println("Key: " + key + ", Value: " + value);
////
////
////            }
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//
//        String HomeAddress = request.get("HomeAddress");
//        String HomeOwnerName = request.get("HomeOwnerName");
//        String HomeownerEmail = request.get("HomeownerEmail");
//
//        String subject = "It's been 1 week since permit has been submitted!";
//        String body = "Permit submitted for " + HomeOwnerName + " \n\n At Home Address: "+ HomeAddress +"\n\nHomeowner email: " + HomeownerEmail + "\n\n" + "MPUs to check up on:" + "\n\n" + i;
//
//
//        //Create the body of the email
//
//
//
//        // Create and send email
//        SimpleMailMessage emailMessage = new SimpleMailMessage();
//        emailMessage.setTo("dominiqmartinez@voltaicnow.com");
//        emailMessage.setSubject(subject);
//        emailMessage.setFrom("construction@voltaicnow.com");
//        emailMessage.setText(body);
//
//        emailSender.send(emailMessage);
//
//        //Can then send email to homeowner
//        System.out.println("Email sent!");
//        // Return response
//        return new Greeting(counter.incrementAndGet(), String.format("FI Passed Automated Emails sent to %s with message '%s' and body '%s'", "dominiq", subject, body));
//    }




    // ================================  *********   =================================


    // ==============================================
    //  *****   TRIGGERED EMAILS
    // ==============================================

    // ==============================================
        // Voltaic Construction Pipeline Automated Emails
    // ==============================================

    // ==============================================
    //  *****   1.Voltaic Check
    // ==============================================

    // ==============================================
    //  *****   2.Welcome
    // ==============================================

    // ==============================================
    // *****   3.Site Survey
    // ==============================================


    // ==============================================
    //   *****  4.Construction Call
    // ==============================================


    // ==============================================
    //  *****   5.NTP
    // ==============================================


    // ==============================================
    //   *****  6.QC Check
    // ==============================================


    // ==============================================
    //  *****   7.Plans
    // ==============================================


    // ==============================================
    //  *****   8.FLA
    // ==============================================

    // ==============================================
    //   *****  9.Solar Permit
    // ==============================================


    // ==============================================
    //   *****  10.Solar Install
    // ==============================================


    // ==============================================
    //   *****  11.Final Inspection
    // ==============================================

    // 1 - FINAL INSPECTION PASSED
    @PostMapping("/fipassed")
    public Greeting fiPassed(@RequestBody Map<String, String> request) {

        //May need to parse the request body for ALL mpu links to send this in PROD.
        // Get the current date
        LocalDate currentDate = LocalDate.now();

// Format the date as desired
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = currentDate.format(formatter);

        String homeAddress = request.get("HomeAddress");
        String RecordAddress = request.get("RecordID");

        String subject = "Final Inspection passed on " + formattedDate + " !";
        String body = "<body><h1>Final Inspection just passed!" +
              "</h1><br><p>At Home Address: "+
                homeAddress +

                "<p>QB Project Link: <a href=\"https://solarcrm.quickbase.com/db/br5cqr4r3?a=er&rid=" + RecordAddress + "&rl=nvq\">" + RecordAddress + "</a></p>";
//                "</p><p>Project Link: <a href=\"https://solarcrm.quickbase.com/db/br5cqr4r3?a=er&rid="+RecordAddress+"&rl=nvq\">Link 1</a></p><img src=\"https://firebasestorage.googleapis.com/v0/b/voltaicconstruction.appspot.com/o/VC.png?alt=media&amp;token=58122a6a-bed0-4344-875b-8f3a1b4822ad\" alt=\"Voltaic Construction\" style=\"width: 100px; height: auto;\"/></body>";

        // Create and send email
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setTo(homeOwnerEmail);
            helper.setTo("alyssaosborne@voltaicnow.com");
            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setFrom("construction@voltaicnow.com");

//            // Add corporate logo to the email
//            ClassPathResource resource = new ClassPathResource("static/images/VC.png");
//            helper.addInline("VC.png", resource);


            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            return new Greeting(counter.incrementAndGet(), "Failed to send email.");
        }

        // Return response
        return new Greeting(counter.incrementAndGet(), String.format("Automated Email Sent"));
    }




    // ==============================================
    //  *****   PTO
    // ==============================================





    // ==============================================
    //  *****  Meter Spot
    // ==============================================
























//
//    @GetMapping("/email")
//    public Greeting sendEmail(@RequestParam String email, @RequestParam String message, @RequestParam String body) {
//
//      //  /email?email=user@example.com&message=New%20Task!&body=Hey%20there!%20You%20have%20a%20new%20task!
//
//        // Create and send email
//        SimpleMailMessage emailMessage = new SimpleMailMessage();
//        emailMessage.setTo(email);
//        emailMessage.setSubject(message);
//        emailMessage.setFrom("dominiq@unhashlabs.io");
//        emailMessage.setText(body);
//
//        emailSender.send(emailMessage);
//
//        // Return response
//        return new Greeting(counter.incrementAndGet(), String.format("Email sent to %s with message '%s' and body '%s'", email, message, body));
//    }
//
//


























}

