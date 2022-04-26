package com.example.smartcity.Models;

public class JobseekerModel {
    private String jobseekerName;
    private String jobseekerDescription;
    private String id;
    private String NumberOfVacancies;

    // TODO - location

    public String getJobseekerName() {
        return jobseekerName;
    }

    public String getNumberOfVacancies() {
        return NumberOfVacancies;
    }

    public void setJobseekerName(String name) {
        this.jobseekerName = name;
    }

    public void setNumberOfVacancies(String vacancies) {
        this.NumberOfVacancies = vacancies;
    }

    public String getJobseekerDescription() {
        return jobseekerDescription;
    }

    public void setJobseekerDescription(String description) {
        this.jobseekerDescription = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
