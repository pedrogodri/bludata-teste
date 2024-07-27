package com.br.bludata.api.models;

import java.util.Date;

import com.br.bludata.api.models.enums.TypePersonEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;
    
    @Column(name = "name")
    public String name;

    @Column(name = "typePerson")
    public TypePersonEnum typePerson;

    @Column(name = "documentCnpjCpf")
    public String documentCnpjCpf;

    @Column(name = "documentRg")
    public String documentRg;

    @Column(name = "birthDate")
    public Date birthDate;
    
    @Column(name = "createdAt")
    public Date createdAt;

    @Column(name = "phone")
    private Long phone;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

}
