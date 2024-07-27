package com.br.bludata.api.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.bludata.api.models.Supplier;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface ISupplierController {
    ResponseEntity<?> createSupplier(@RequestBody Supplier supplier);
    ResponseEntity<?> listAllSuppliers();
    ResponseEntity<?> updateSupplier(@RequestBody Supplier supplier);
    ResponseEntity<?> deleteSupplier(@PathVariable Long id);
    ResponseEntity<?> selectSupplierById(@PathVariable Long id);
}
