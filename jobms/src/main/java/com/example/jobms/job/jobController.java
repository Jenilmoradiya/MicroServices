package com.example.jobms.job;

import com.example.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class jobController {

 private JobService jobService;

    public jobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createjob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Sucessfully",HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> findByJobId(@PathVariable Long id) {
        JobDTO jobwithcompanyDTOs = jobService.findByJobId(id);
        if (jobwithcompanyDTOs != null)
            return new ResponseEntity<>(jobwithcompanyDTOs, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> DeleteJob(@PathVariable Long id){
      Boolean deleted=jobService.DeleteJob(id);
      if (deleted) {
          return new ResponseEntity<>("Job deleted", HttpStatus.OK);
      }
      else {
          return new ResponseEntity<>("Not deleted", HttpStatus.OK);
       }
      }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> UpdateJob(@PathVariable Long id, @RequestBody Job Updatedjob){
        Boolean updated=jobService.updateJob(id,Updatedjob);
        if(updated)
            return new ResponseEntity<>("Job Updated",HttpStatus.OK);
        return new ResponseEntity<>("Not updated",HttpStatus.OK);

    }

}
