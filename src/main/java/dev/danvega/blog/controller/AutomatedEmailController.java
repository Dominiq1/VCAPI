package dev.danvega.blog.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
@RestController
public class AutomatedEmailController {

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




    @PostMapping("/fipassed")
    public Greeting fiPassed(@RequestBody Map<String, String> request) {

        String HomeAddress = request.get("HomeAddress");
        String HomeOwnerName = request.get("HomeOwnerName");
        String HomeownerEmail = request.get("HomeownerEmail");

        String subject = "Final Inspection just passed!";
        String body = "Final Inspection just passed for " + HomeOwnerName + " \n\n At Home Address: "+ HomeAddress +"\n\nHomeowner email: " + HomeownerEmail;


        // Create and send email
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setTo("alyssaosborne@voltaicnow.com");
        emailMessage.setSubject(subject);
        emailMessage.setFrom("construction@voltaicnow.com");
        emailMessage.setText(body);

        emailSender.send(emailMessage);

        //Can then send email to homeowner

        // Return response
        return new Greeting(counter.incrementAndGet(), String.format("FI Passed Automated Emails sent to %s with message '%s' and body '%s'", "dominiq", subject, body));
    }












//    @GetMapping("/fipassed")
//    public Greeting fiPassed(@RequestParam String HomeAddress, @RequestParam String HomeOwnerName,@RequestParam String HomeownerEmail ) {
//
//        String subject = "Final Inspection just passed!";
//        String body = "Final Inspection just passed for " +HomeOwnerName + " \nHomeAddress:  "+ HomeAddress;
//        // Create and send email
//        SimpleMailMessage emailMessage = new SimpleMailMessage();
////        emailMessage.setTo("alyssaosborne@voltaicnow.com");
//        emailMessage.setTo("dominiqmartinez@voltaicnow.com");
//        emailMessage.setSubject(subject);
//        emailMessage.setFrom("construction@voltaicnow.com");
//        emailMessage.setText(body);
//
//        emailSender.send(emailMessage);
//
//        //Can then send email to homeowner
//
//        // Return response
//        return new Greeting(counter.incrementAndGet(), String.format("FI Passed Automated Emails sent to %s with message '%s' and body '%s'", "dominiq", subject, body));
//    }


































}
//
