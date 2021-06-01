package org.myuniv.system.course.beans.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "users") public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue private long id;

    @Column(nullable = true, length = 50, unique = true) private String userName;

    @Column(nullable = false, length = 120, unique = true) private String email;

    @Column(nullable = false, unique = true, length = 50) private String userId;

    @Column(nullable = false, length = 10) private String type;

    @Column(nullable = false) private String encryptedPassword;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
