package com.br.bludata.api.models;

import java.util.List;

import com.br.bludata.api.models.enums.UfEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    
    @Column(name = "fantasyName")
    public String fantasyName;

    @Column(name = "documentCnpj")
    public String documentCnpj;

    @Column(name = "uf")
    public UfEnum uf;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Supplier> suppliers;
}
