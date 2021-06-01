package org.myuniv.system.course.beans.requests;

import org.myuniv.system.course.beans.dto.CourseMaterialDto;

import java.util.List;

public class CourseRequest {
    private String                  facultyId;
    private String                  departmentId;
    private String                  courseTitle;
    private String                  courseDescription;
    private String                  term;
    private Integer                 credits;
    private Integer                 duration; // time in hours
    private Integer                 totalSlots;
    private Integer                 vacantSlots;
    private List<CourseMaterialDto> resources; // videos or textbooks or chapters to cover
    private String                  preRequisiteCourses;

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

    public List<CourseMaterialDto> getResources() {
        return resources;
    }

    public void setResources(List<CourseMaterialDto> resources) {
        this.resources = resources;
    }

    public String getPreRequisiteCourses() {
        return preRequisiteCourses;
    }

    public void setPreRequisiteCourses(String preRequisiteCourses) {
        this.preRequisiteCourses = preRequisiteCourses;
    }
}
