package com.br.bludata.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.bludata.api.controllers.interfaces.ICompanyController;
import com.br.bludata.api.models.Company;
import com.br.bludata.api.services.CompanyService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/company")
public class CompanyController implements ICompanyController{

    @Autowired
    public CompanyService companyService;

    @Override
    @PostMapping()
    public ResponseEntity<?> createCompany(Company company) {
        return companyService.createCompany(company);
    }

    @Override
    @GetMapping()
    public ResponseEntity<?> listAllCompanies() {
        return companyService.listAllCompanys();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(Company company) {
        return companyService.updateCompany(company);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(Long id) {
       return companyService.deleteCompany(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> selectCompanyById(Long id) {
        return companyService.selectCompanyById(id);
    }
    
}
