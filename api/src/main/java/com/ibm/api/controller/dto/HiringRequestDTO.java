package com.ibm.api.controller.dto;

import jakarta.validation.constraints.NotBlank;

public class HiringRequestDTO {

    @NotBlank(message = "Nome Inválido")
    private String nome;

    private Integer codCandidato;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodCandidato() {
        return codCandidato;
    }

    public void setCodCandidato(Integer codCandidato) {
        this.codCandidato = codCandidato;
    }
}
