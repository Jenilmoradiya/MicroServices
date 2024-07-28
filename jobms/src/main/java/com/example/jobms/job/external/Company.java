package com.example.jobms.job.external;


import java.util.List;

public class Company {

    private long id;
    private String name;
    private String description;

//    @JsonIgnore
//    @OneToMany(mappedBy = "company")
//    private List<Job> jobs;
//
//
//    @OneToMany(mappedBy = "company")
//    private List<Review> reviews;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


}
