package com.br.bludata.api.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.bludata.api.models.Company;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface ICompanyController {
    ResponseEntity<?> createCompany(@RequestBody Company company);
    ResponseEntity<?> listAllCompanies();
    ResponseEntity<?> updateCompany(@RequestBody Company company);
    ResponseEntity<?> deleteCompany(@PathVariable Long id);
    ResponseEntity<?> selectCompanyById(@PathVariable Long id);
}
