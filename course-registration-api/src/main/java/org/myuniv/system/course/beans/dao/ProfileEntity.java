package org.myuniv.system.course.beans.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "profiles") public class ProfileEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue private long id;

    @Column(nullable = false, unique = true, length = 50) private String userId;

    @Column(nullable = false, length = 50) private String firstName;

    @Column(nullable = false, length = 50) private String lastName;

    @Column(length = 150) private String address;

    @Column(nullable = false, length = 10) private String phoneNumber;

    private Boolean feesPaid;

    private String term;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getFeesPaid() {
        return feesPaid;
    }

    public void setFeesPaid(Boolean feesPaid) {
        this.feesPaid = feesPaid;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
