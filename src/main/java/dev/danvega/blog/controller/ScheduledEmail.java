package dev.danvega.blog.controller;

import java.time.LocalDateTime;

public class ScheduledEmail {
    private String email;
    private String subject;
    private String body;
    private LocalDateTime scheduledDateTime;

    public ScheduledEmail(String email, String subject, String body, LocalDateTime scheduledDateTime) {
        this.email = email;
        this.subject = subject;
        this.body = body;
        this.scheduledDateTime = scheduledDateTime;
    }

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }
}
