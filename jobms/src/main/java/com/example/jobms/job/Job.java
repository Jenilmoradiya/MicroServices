package com.example.jobms.job;

import jakarta.persistence.*;

@Entity
@Table(name = "Job_Table")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minsalary;
    private String maxsalary;
    private String location;
    private Long companyId;

//    @ManyToOne
//    private Company company;


    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Job() {
    }

    public Job(Long id, String title, String description, String minsalary, String maxsalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minsalary = minsalary;
        this.maxsalary = maxsalary;
        this.location = location;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinsalary() {
        return minsalary;
    }

    public void setMinsalary(String minsalary) {
        this.minsalary = minsalary;
    }

    public String getMaxsalary() {
        return maxsalary;
    }

    public void setMaxsalary(String maxsalary) {
        this.maxsalary = maxsalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
