package dev.anjalee.gym_management_system.events;

public class EmailEventDTO {

    private String to;
    private String subject;
    private String body;
    private String attachmentPath;

    public EmailEventDTO() {
    }

    public EmailEventDTO(String to, String subject, String body, String attachmentPath) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.attachmentPath = attachmentPath;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }


}
