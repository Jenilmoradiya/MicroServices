package com.example.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {


    private CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/Companies")
    public ResponseEntity<List<Company>> getCompanyList() {
        return new ResponseEntity<>(companyService.getallcompany(), HttpStatus.OK);
    }

    @PutMapping("/Companies/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
        return new ResponseEntity<>("Updated succesfully", HttpStatus.OK);
    }

    @PostMapping("/Companies")
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        companyService.CreateCompany(company);
        return new ResponseEntity<>("Created succesfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/Companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        Boolean deleted=companyService.DeleteCompanyById(id);
        if(deleted){
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to Delete", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/Companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company=companyService.GetCompanyById(id);
        if(company!=null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}