package org.myuniv.system.course.beans.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Table(name = "notifications") public class NotificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue private long   id;
    private                     String summary;
    private                     String message;
    private                     String senderId; //{{InstructorId}}
    private                     String receiverGroup; // "AllStudents"

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
