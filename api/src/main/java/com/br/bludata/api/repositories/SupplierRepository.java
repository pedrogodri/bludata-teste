package com.br.bludata.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.br.bludata.api.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT supplier FROM Supplier supplier WHERE supplier.id = :id")
    Supplier findBySupplierId(Long id);
    Optional<Supplier> findById(long id);
    Optional<Supplier> findByDocumentCnpjCpf(String findByDocumentCnpjCpf);
    boolean existsByDocumentCnpjCpf(String findByDocumentCnpjCpf);
    boolean existsById(Long id);
}
