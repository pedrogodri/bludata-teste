package com.br.bludata.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.bludata.api.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT company FROM Company company WHERE company.id = :id")
    Company findByCompanyId(Long id);
    Optional<Company> findById(long id);
    Optional<Company> findByDocumentCnpj(String documentCnpj);
    boolean existsByDocumentCnpj(String documentCnpj);
    boolean existsById(Long id);
}
