package com.example.jobms.job.Mapper;

import com.example.jobms.job.Job;
import com.example.jobms.job.dto.JobDTO;
import com.example.jobms.job.external.Company;
import com.example.jobms.job.external.Review;

import java.util.List;

public class Jobmapper {
    public static JobDTO mapToJobWithCompanyDTO(
            Job job,
            Company company,
            List<Review> reviews
    ){
      JobDTO jobwithcompanyDTOs = new JobDTO();
       jobwithcompanyDTOs.setId(job.getId());
       jobwithcompanyDTOs.setTitle(job.getTitle());
       jobwithcompanyDTOs.setDescription(job.getDescription());
       jobwithcompanyDTOs.setLocation(job.getLocation());
       jobwithcompanyDTOs.setMaxsalary(job.getMaxsalary());
       jobwithcompanyDTOs.setMinsalary(job.getMinsalary());
       jobwithcompanyDTOs.setCompany(company);
       jobwithcompanyDTOs.setReviews(reviews);
       return jobwithcompanyDTOs;
    }

}
