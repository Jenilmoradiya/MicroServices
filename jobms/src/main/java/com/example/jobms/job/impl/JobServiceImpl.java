package com.example.jobms.job.impl;


import com.example.jobms.job.Clients.CompanyClient;
import com.example.jobms.job.Clients.ReviewClient;
import com.example.jobms.job.Mapper.Jobmapper;
import com.example.jobms.job.dto.JobDTO;
import com.example.jobms.job.external.Company;
import com.example.jobms.job.Job;
import com.example.jobms.job.JobRepository;
import com.example.jobms.job.JobService;
import com.example.jobms.job.external.Review;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
 //private long nextId = 1;
    //private List<Job> Jobs =new ArrayList<>();

    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;
    int attempt;

    private CompanyClient companyClient;
    private ReviewClient reviewClient;

    public JobServiceImpl(JobRepository jobRepository,CompanyClient companyClient,
                          ReviewClient reviewClient)
    {
        this.jobRepository = jobRepository;
        this.companyClient=companyClient;
        this.reviewClient=reviewClient;
    }
    @Override
//    @CircuitBreaker(name = "companyBreaker",fallbackMethod ="companyBreakerFallback")
//    @Retry(name = "companyBreaker",fallbackMethod ="companyBreakerFallback")
    @RateLimiter(name = "companyBreaker",fallbackMethod ="companyBreakerFallback")
    public List<JobDTO> findAll(){
        System.out.println("Attempt"+ attempt++);
    List<Job> jobs = jobRepository.findAll();
    List<JobDTO> jobDTOS =new ArrayList<>();

        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
       // RestTemplate restTemplate = new RestTemplate();
//        for (Job job : jobs) {
//            JobwithcompanyDto jobwithcompanyDTO =new JobwithcompanyDto();
//            jobwithcompanyDTO.setJob(job);
//
//            Company company=restTemplate.getForObject
//                    ("http://COMPANYMS:8081/Companies/"+job.getCompanyId(),Company.class);
//         jobwithcompanyDTO.setCompany(company);
//         jobwithcompanyDTOs.add(jobwithcompanyDTO);
//        }
//        return jobwithcompanyDTOs;
//    }
    }
     public List<String> companyBreakerFallback(Exception e){
        List<String> list=new ArrayList<>();
        list.add("Dummy");
        return list;
     }

        private JobDTO convertToDto(Job job){
//            JobwithcompanyDto jobwithcompanyDTO =new JobwithcompanyDto();
//            jobwithcompanyDTO.setJob(job);

            //RestTemplate Method
//            Company company=restTemplate.getForObject
//                    ("http://COMPANYMS:8081/Companies/"+job.getCompanyId(),Company.class);

            //OpenFeign Method
            Company company=companyClient.getCompany(job.getCompanyId());
//            //We want a list so we are using exchange method

//            ResponseEntity<List<Review>> reviewResponse =restTemplate.exchange(
//                    "http://REVIEWMS:8083/reviews?companyId="
//            +job.getCompanyId(), HttpMethod.GET,null,
//                    new ParameterizedTypeReference<List<Review>>() {});
            //It will store review in list
//            List<Review> reviews = reviewResponse.getBody();


            //OpenFeign Method
            List<Review> reviews=reviewClient.getReviews(job.getCompanyId());

            JobDTO jobDTO = Jobmapper.mapToJobWithCompanyDTO
                    (job,company,reviews);
         jobDTO.setCompany(company);
        return jobDTO;
        }


    public void createJob(Job job){
     jobRepository.save(job);
    }

//    @Override
//    public Job findByJobId(Long id) {
//        return jobRepository.findById(id).orElse(null);
//        }


    @Override
    public JobDTO findByJobId(Long id) {
        Job job=jobRepository.findById(id).orElse(null);
        return convertToDto(job);
        }

    @Override
    public Boolean DeleteJob(Long id) {
       try {
           jobRepository.deleteById(id);
           return true;
       }catch (Exception e){
           return false;
       }
    }

    @Override
    public Boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> OptionalJob=jobRepository.findById(id);
            if(OptionalJob.isPresent()){
                Job job=OptionalJob.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinsalary(updatedJob.getMinsalary());
                job.setMaxsalary(updatedJob.getMaxsalary());
                job.setLocation(updatedJob.getLocation());
                jobRepository.save(job);
                return true;
            }

        return false;
    }
}
