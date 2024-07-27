package com.br.bludata.api.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.br.bludata.api.models.Supplier;

public interface ISupplierService {
    ResponseEntity<?> createSupplier(Supplier supplier);
    ResponseEntity<?> listAllSuppliers();
    ResponseEntity<?> updateSupplier(Supplier supplier);
    ResponseEntity<?> deleteSupplier(Long id);
    ResponseEntity<?> selectSupplierById(Long id);
}
