package com.br.bludata.api.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.br.bludata.api.models.Company;

public interface ICompanyService {
    ResponseEntity<?> createCompany(Company company);
    ResponseEntity<?> listAllCompanys();
    ResponseEntity<?> updateCompany(Company company);
    ResponseEntity<?> deleteCompany(Long id);
    ResponseEntity<?> selectCompanyById(Long id);
}
