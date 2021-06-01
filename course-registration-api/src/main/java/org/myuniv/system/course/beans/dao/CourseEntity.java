package org.myuniv.system.course.beans.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity @Table(name = "courses") public class CourseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue private long id;

    @Column(nullable = false, length = 50) private String facultyId;

    @Column(nullable = false, length = 50) private String departmentId;

    @Column(nullable = false, unique = true, length = 50) private String courseTitle;

    @Column(length = 150) private String courseDescription;

    @Column(length = 10) private String term;

    private Integer credits;

    private Integer duration;

    private Integer totalSlots;

    private Integer vacantSlots;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) private List<CourseMaterialEntity> resources;

    private String preRequisiteCourses;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(Integer totalSlots) {
        this.totalSlots = totalSlots;
    }

    public Integer getVacantSlots() {
        return vacantSlots;
    }

    public void setVacantSlots(Integer vacantSlots) {
        this.vacantSlots = vacantSlots;
    }

    public List<CourseMaterialEntity> getResources() {
        return resources;
    }

    public void setResources(List<CourseMaterialEntity> resources) {
        this.resources = resources;
    }

    public String getPreRequisiteCourses() {
        return preRequisiteCourses;
    }

    public void setPreRequisiteCourses(String preRequisiteCourses) {
        this.preRequisiteCourses = preRequisiteCourses;
    }
}
