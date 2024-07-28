package com.br.bludata.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.bludata.api.models.Company;
import com.br.bludata.api.models.Message;
import com.br.bludata.api.repositories.CompanyRepository;
import com.br.bludata.api.services.interfaces.ICompanyService;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    public CompanyRepository companyRepository;

    @Autowired
    public Message message;

    @Override
    public ResponseEntity<?> createCompany(Company company) {
        try {
            if (company.getFantasyName() == null || company.getFantasyName().isEmpty()) {
                message.setMessage("O nome fantasia é obrigatório.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
            if (companyRepository.existsByDocumentCnpj(company.getDocumentCnpj())) {
                message.setMessage("Já existe um jogador com este CNPJ.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
            if (company.getDocumentCnpj() == null || company.getDocumentCnpj().isEmpty()) {
                message.setMessage("O CNPJ é obrigatório.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }

            Company savedCompany = companyRepository.save(company);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
        } catch (Exception e) {
            message.setMessage("Erro ao criar a empresa. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> listAllCompanys() {
        try {
            List<Company> companies = companyRepository.findAll();
            if(companies.isEmpty()) {
                message.setMessage("Não existe nenhuma empresa cadastrado no sistema.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            } else {
                return ResponseEntity.ok(companies);
            }
            
        } catch (Exception e) {
           message.setMessage("Erro ao listar as empresas. " + e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
        }
    }

    @Override
    public ResponseEntity<?> updateCompany(Company company) {
        try {
            if(company.getId() == null) {
             message.setMessage("Id da empresa não informado.");
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
            }
 
            if(companyRepository.existsById(company.getId())) {
             Company existCompany = companyRepository.findByCompanyId(company.getId());
 
             existCompany.setFantasyName(company.getFantasyName());
             existCompany.setFantasyName(company.getDocumentCnpj());
             existCompany.setSuppliers(company.getSuppliers());
 
             return new ResponseEntity<>(existCompany, HttpStatus.OK);
            } else {
             message.setMessage("Empresa não encontrada.");
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
            }
         } catch (Exception e) {
             message.setMessage("Erro ao editar a empresa. " + e.getMessage());
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
         }
    }

    @Override
    public ResponseEntity<?> deleteCompany(Long id) {
        try {
            if (companyRepository.existsById(id)) {

                Company existCompany = companyRepository.findByCompanyId(id);
                companyRepository.delete(existCompany);
                message.setMessage("Empresa " + existCompany.getFantasyName() + " deletada");
    
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.setMessage("Nenhuma empresa encontrada pelo id!");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.setMessage("Erro ao deletar a empresa. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> selectCompanyById(Long id) {
        try {
            if (companyRepository.existsById(id)) {

                Company existCompany= companyRepository.findByCompanyId(id);
    
                return new ResponseEntity<>(existCompany, HttpStatus.OK);
            } else {
                message.setMessage("Nenhuma empresa encontrado pelo id!");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    
            }
        } catch (Exception e) {
            message.setMessage("Erro ao selecionar a empresa. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }
    
}
