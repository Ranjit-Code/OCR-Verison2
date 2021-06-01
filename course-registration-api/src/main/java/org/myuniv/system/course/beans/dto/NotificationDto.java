package org.myuniv.system.course.beans.dto;

public class NotificationDto {
    private long   id;
    private String summary;
    private String message;
    private String senderId; //{{InstructorId}}
    private String receiverGroup; // "AllStudents"

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverGroup() {
        return receiverGroup;
    }

    public void setReceiverGroup(String receiverGroup) {
        this.receiverGroup = receiverGroup;
    }
}
