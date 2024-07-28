package com.br.bludata.api.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.bludata.api.models.Supplier;
import com.br.bludata.api.models.enums.UfEnum;
import com.br.bludata.api.models.Message;
import com.br.bludata.api.repositories.SupplierRepository;
import com.br.bludata.api.services.interfaces.ISupplierService;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    public SupplierRepository supplierRepository;

    @Autowired
    public Message message;

    public ResponseEntity<?> createSupplier(Supplier supplier) {
        try {
            switch (supplier.getTypePerson()) {
                case PF:
                    if (supplierRepository.existsByDocumentCnpjCpf(supplier.getDocumentCnpjCpf())) {
                        message.setMessage("Já existe um fornecedor com este CPF/CNPJ.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    if (supplier.getDocumentCnpjCpf() == null || supplier.getDocumentCnpjCpf().isEmpty()) {
                        message.setMessage("O CPF/CNPJ é obrigatório.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    if (supplier.getDocumentRg() == null || supplier.getDocumentRg().isEmpty()) {
                        message.setMessage("O RG é obrigatório para pessoa física.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    if (supplier.getBirthDate() == null) {
                        message.setMessage("A data de nascimento é obrigatória para pessoa física.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    if ((supplier.getCompany().getUf() == UfEnum.PR) && isMinor(supplier.getBirthDate())) {
                        message.setMessage("Não é permitido cadastrar fornecedor pessoa física menor de idade para empresas do Paraná.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    break;
                case PJ:
                    if (supplierRepository.existsByDocumentCnpjCpf(supplier.getDocumentCnpjCpf())) {
                        message.setMessage("Já existe um fornecedor com este CPF/CNPJ.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    if (supplier.getDocumentCnpjCpf() == null || supplier.getDocumentCnpjCpf().isEmpty()) {
                        message.setMessage("O CPF/CNPJ é obrigatório.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
                    }
                    break;
                default:
                    message.setMessage("Tipo de pessoa inválido.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
            if (supplier.getName() == null || supplier.getName().isEmpty()) {
                message.setMessage("O nome é obrigatório.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
            if (supplier.getPhone() == null || supplier.getPhone().equals("") || supplier.getPhone() < 0) {
                message.setMessage("O nome é obrigatório.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }

            Supplier savedSupplier = supplierRepository.save(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
        } catch (Exception e) {
            message.setMessage("Erro ao criar o fornecedor. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> listAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierRepository.findAll();
            if(suppliers.isEmpty()) {
                message.setMessage("Não existe nenhum fornecedor cadastrado no sistema.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            } else {
                return ResponseEntity.ok(suppliers);
            }
            
        } catch (Exception e) {
           message.setMessage("Erro ao listar os fornecedores. " + e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
        }
    }

    @Override
    public ResponseEntity<?> updateSupplier(Supplier supplier) {
        try {
            if(supplier.getId() == null) {
             message.setMessage("Id do fornecedor não informado.");
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
            }
 
            if(supplierRepository.existsById(supplier.getId())) {
             Supplier existSupplier = supplierRepository.findBySupplierId(supplier.getId());
 
             existSupplier.setName(supplier.getName());
             existSupplier.setDocumentCnpjCpf(supplier.getDocumentCnpjCpf());
             existSupplier.setDocumentRg(supplier.getDocumentRg());
 
             return new ResponseEntity<>(existSupplier, HttpStatus.OK);
            } else {
             message.setMessage("Fornecedor não encontrado.");
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
            }
         } catch (Exception e) {
             message.setMessage("Erro ao editar o fornecedor. " + e.getMessage());
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message); 
         }
    }

    @Override
    public ResponseEntity<?> deleteSupplier(Long id) {
        try {
            if (supplierRepository.existsById(id)) {

                Supplier existSupplier = supplierRepository.findBySupplierId(id);
                supplierRepository.delete(existSupplier);
                message.setMessage("Fornecedor " + existSupplier.getName() + " deletado");
    
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                message.setMessage("Nenhumo forncedor encontrado pelo id!");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            message.setMessage("Erro ao deletar o fornecedor. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    @Override
    public ResponseEntity<?> selectSupplierById(Long id) {
        try {
            if (supplierRepository.existsById(id)) {

                Supplier existSupplier= supplierRepository.findBySupplierId(id);
    
                return new ResponseEntity<>(existSupplier, HttpStatus.OK);
            } else {
                message.setMessage("Nenhum fornecedor encontrado pelo id!");
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    
            }
        } catch (Exception e) {
            message.setMessage("Erro ao selecionar o fornecedor. " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
    }

    private boolean isMinor(Date birthDate) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -18);
        Date eighteenYearsAgo = cal.getTime();
        return birthDate.after(eighteenYearsAgo);
    }
}