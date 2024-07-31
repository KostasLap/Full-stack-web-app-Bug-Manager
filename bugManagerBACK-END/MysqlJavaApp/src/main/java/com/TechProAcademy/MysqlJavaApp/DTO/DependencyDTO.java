// DependencyDTO.java
package com.TechProAcademy.MysqlJavaApp.DTO;

public class DependencyDTO {
    private Integer dependencyId;
    private Integer bugOnId;
    private Integer dependsOnBugId;

    // Getters and setters

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }

    public Integer getBugOnId() {
        return bugOnId;
    }

    public void setBugOnId(Integer bugOnId) {
        this.bugOnId = bugOnId;
    }

    public Integer getDependsOnBugId() {
        return dependsOnBugId;
    }

    public void setDependsOnBugId(Integer dependsOnBugId) {
        this.dependsOnBugId = dependsOnBugId;
    }
}
