package com.example.companyms.company.impl;

import com.example.companyms.company.Company;
import com.example.companyms.company.CompanyRepository;
import com.example.companyms.company.CompanyService;
import com.example.companyms.company.clients.ReviewClient;
import com.example.companyms.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {


    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;

    public CompanyServiceImpl(CompanyRepository companyRepository,ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient=reviewClient;
    }

    @Override
    public List<Company> getallcompany() {
        return companyRepository.findAll();
    }

    @Override
    public Boolean updateCompany(Long id,Company Updatedcompany) {
        Optional<Company> optionalCompany=companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company company=optionalCompany.get();
            company.setName(Updatedcompany.getName());
            company.setDescription(Updatedcompany.getDescription());
//            company.setJobs(Updatedcompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void CreateCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Boolean DeleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Company GetCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
      System.out.println(reviewMessage.getDescription());
      Company company=companyRepository.findById(reviewMessage.getCompanyId())
              .orElseThrow(()->new NotFoundException("Company not found" + reviewMessage.getCompanyId()));

      double averageRating=reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
      company.setRating(averageRating);
      companyRepository.save(company);
    }


}
