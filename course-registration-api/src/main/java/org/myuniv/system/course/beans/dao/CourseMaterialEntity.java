package org.myuniv.system.course.beans.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Table(name = "courseMaterials") public class CourseMaterialEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id private String resourceId;

    @Column(unique = true, length = 50) private String resourceName;

    @Column(length = 10) private String type;

    private Boolean isMandatory;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getMandatory() {
        return isMandatory;
    }

    public void setMandatory(Boolean mandatory) {
        isMandatory = mandatory;
    }
}
