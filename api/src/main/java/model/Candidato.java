package model;

public class Candidato {
    private Integer codCanditado;
    private String nome;
    private String status;

    public Candidato(Integer codCanditado, String nome, String status) {
        this.codCanditado = codCanditado;
        this.nome = nome;
        this.status = status;
    }

    public Integer getCodCanditado() {
        return codCanditado;
    }

    public void setCodCanditado(Integer codCanditado) {
        this.codCanditado = codCanditado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
