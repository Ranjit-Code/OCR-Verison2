package org.myuniv.system.course.beans.requests;

public class NotificationRequest {
    private String summary;
    private String message;
    private String senderId; //{{InstructorId}}
    private String receiverGroup; // "AllStudents"

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
