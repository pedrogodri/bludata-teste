package com.br.bludata.api.models.enums;

import lombok.Getter;
@Getter
public enum TypePersonEnum {
    PF("Pessoa Física"),
    PJ("Pessoa Jurídica");

    private final String nome;

    TypePersonEnum(String nome) {
        this.nome = nome;
    }
}
