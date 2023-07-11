package com.ibm.api.service;

import com.ibm.api.advice.ErrorHandler;
import com.ibm.api.advice.exception.CandidatoJaParticipaException;
import com.ibm.api.advice.exception.CandidatoNaoEncontradoException;
import com.ibm.api.advice.exception.NomeInvalidoException;
import com.ibm.api.model.Candidato;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HiringService {
    private Candidato candidato;
    private Integer nextCodCandidato;
    private Map<Integer, Candidato> candidatos;

    public HiringService() {
        this.nextCodCandidato = 1;
        this.candidatos = new HashMap<>();
    }

    public int iniciarProcesso(String nome) throws Exception {
        for (Candidato candidato: candidatos.values()){
            if (candidato.getNome().equals(nome)) {
                throw new CandidatoJaParticipaException();
            }
        }
        Candidato novoCandidato = new Candidato(nextCodCandidato, nome, "Recebido");
        candidatos.put(nextCodCandidato, novoCandidato);
        return nextCodCandidato++;
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        Candidato verificaCandidatoExiste = candidatos.get(codCandidato);
        if (verificaCandidatoExiste == null || !verificaCandidatoExiste.getStatus().equals("Recebido")){
            throw new CandidatoNaoEncontradoException();
        }
        verificaCandidatoExiste.setStatus("Qualificado");
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        Candidato verificaCandidato = candidatos.get(codCandidato);
        if (verificaCandidato == null){
            throw new CandidatoNaoEncontradoException();
        }else {
            candidatos.remove(codCandidato);
            verificaCandidato.setNome(null);
            verificaCandidato.setStatus(null);
            verificaCandidato.setCodCanditado(null);
        }
    }

    public String verificarStatusCandidato(int codCandidato) throws Exception {
        Candidato verificaCandidatoExiste = candidatos.get(codCandidato);
        if (verificaCandidatoExiste == null){
            throw new CandidatoNaoEncontradoException();
        }
        return verificaCandidatoExiste.getStatus();
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        Candidato verificaCandidatoExiste = candidatos.get(codCandidato);
        if (verificaCandidatoExiste == null || !verificaCandidatoExiste.getStatus().equals("Qualificado")){
            throw new CandidatoNaoEncontradoException();
        }
        verificaCandidatoExiste.setStatus("Aprovado");
    }

    public List<String> obterAprovados() {
        List<String> aprovados = new ArrayList<>();
        for(Candidato candidato: candidatos.values()){
            if (candidato.getStatus().equals("Aprovado")){
                aprovados.add(candidato.getNome());
            }
        }
        return aprovados;
    }
}
