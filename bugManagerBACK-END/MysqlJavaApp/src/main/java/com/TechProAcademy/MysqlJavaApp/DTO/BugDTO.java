// BugDTO.java
package com.TechProAcademy.MysqlJavaApp.DTO;

public class BugDTO {
    private int bugId;
    private String name;
    private String description;
    private Integer severity;

    // Getters and setters

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }
}
