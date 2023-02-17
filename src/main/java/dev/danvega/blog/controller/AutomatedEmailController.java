package dev.danvega.blog.controller;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import  org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.Properties;
//import java.util.concurrent.atomic.AtomicLong;
//@RestController
//public class AutomatedEmailController {
//
//    private static final String template = "Sent an email to %s! It should be there soon.";
//    private final AtomicLong counter = new AtomicLong();
//
//    private final JavaMailSender emailSender;
//
//    public AutomatedEmailController(JavaMailSender emailSender) {
//        this.emailSender = emailSender;
//    }
//
//    @GetMapping("/email")
//    public Greeting email(@RequestParam String email) {
//
//        // Create and send email
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("New Task!");
//        message.setFrom("dominiq@unhashlabs.io");
//        message.setText("Hey there! You have a new task!");
//
//        emailSender.send(message);
//        // Return response
//        return new Greeting(counter.incrementAndGet(), String.format(template, email));
//    }
//
//}
//
