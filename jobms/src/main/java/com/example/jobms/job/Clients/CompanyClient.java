package com.example.jobms.job.Clients;

import com.example.jobms.job.external.Company;
import jakarta.ws.rs.GET;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Name;

@FeignClient(name="COMPANYMS",
 url = "${companyms.url}")
public interface CompanyClient {

    @GetMapping("Companies/{id}")
    Company getCompany(@PathVariable Long id);

}
