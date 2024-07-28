package com.example.jobms.job;

import com.example.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {

  List<JobDTO> findAll();
  void createJob(Job job);
//  Job findByJobId(Long id);
  JobDTO findByJobId(Long id);
  Boolean DeleteJob(Long id);
  Boolean updateJob(Long id,Job updatedJob);
}
