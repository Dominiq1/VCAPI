package dev.danvega.blog.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSchedulerController {

    @Autowired
    private JavaMailSender emailSender;

    @PostMapping("/schedule-email")
    public String scheduleEmail(@RequestParam String email) {
        // Schedule the email to be sent 2 minutes later
        LocalDateTime scheduledDateTime = LocalDateTime.now().plusMinutes(2);
        String subject = "Reminder: Task is due in 2 minutes";
        String body = "Hey there! Just a friendly reminder that your task is due in 2 minutes.";

        ScheduledEmail scheduledEmail = new ScheduledEmail(email, subject, body, scheduledDateTime);
        scheduleEmail(scheduledEmail);

        // Return success response
        return "Email scheduled to be sent in 2 minutes.";
    }

    @PostMapping("/send-scheduled-emails")
    public void sendScheduledEmails() {
        // Get all scheduled emails that are due
        List<ScheduledEmail> scheduledEmails = getScheduledEmailsDue();

        // Send each email
        for (ScheduledEmail scheduledEmail : scheduledEmails) {
            sendEmail(scheduledEmail.getEmail(), scheduledEmail.getSubject(), scheduledEmail.getBody());
            // Remove the scheduled email from the database
            removeScheduledEmail(scheduledEmail);
        }
    }

    @Scheduled(fixedDelay = 60000) // check every minute for scheduled emails
    public void checkScheduledEmails() {
        // TODO: Implement a method to check for scheduled emails every minute
        // and send them if they are due
    }

    private void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        emailSender.send(message);
    }

    private List<ScheduledEmail> getScheduledEmailsDue() {
        // TODO: Query the database to get all scheduled emails that are due
        // and return them as a list of ScheduledEmail objects
        return new ArrayList<>();
    }

    private void removeScheduledEmail(ScheduledEmail scheduledEmail) {
        // TODO: Remove the scheduled email from the database
    }

    private void scheduleEmail(ScheduledEmail scheduledEmail) {
        // TODO: Save the scheduled email to the database
    }
}



