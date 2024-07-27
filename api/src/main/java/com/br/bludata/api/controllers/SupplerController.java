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

import com.br.bludata.api.controllers.interfaces.ISupplierController;
import com.br.bludata.api.models.Supplier;
import com.br.bludata.api.services.SupplierService;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/supplier")
public class SupplerController implements ISupplierController{

    @Autowired
    public SupplierService supplierService;

    @Override
    @PostMapping()
    public ResponseEntity<?> createSupplier(Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    @Override
    @GetMapping()
    public ResponseEntity<?> listAllSuppliers() {
        return supplierService.listAllSuppliers();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(Long id) {
       return supplierService.deleteSupplier(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> selectSupplierById(Long id) {
        return supplierService.selectSupplierById(id);
    }
    
}
