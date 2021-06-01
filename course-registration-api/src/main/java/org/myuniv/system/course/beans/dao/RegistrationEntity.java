package org.myuniv.system.course.beans.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Table(name = "registrations") public class RegistrationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue private long id;

    @Column(nullable = false) private Long courseId;

    @Column(nullable = false, length = 50) private String studentId;

    @Column(nullable = false, length = 10) private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
