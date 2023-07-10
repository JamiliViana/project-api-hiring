package service;

import model.Candidato;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (nome == null || nome.isEmpty() || nome.isBlank()){
            throw new Exception("Nome inválido");
        }
        for (Candidato candidato: candidatos.values()){
            if (candidato.getNome().equals(nome)) {
                throw new Exception("Candidato já participa do processo.");
            }
        }
        Candidato novoCandidato = new Candidato(nextCodCandidato, nome, "Recebido");
        candidatos.put(nextCodCandidato, novoCandidato);
        return nextCodCandidato++;
    }

    public void marcarEntrevista(int codCandidato) throws Exception {
        Candidato verificaCandidatoExiste = candidatos.get(codCandidato);
        if (verificaCandidatoExiste == null || !verificaCandidatoExiste.getStatus().equals("Recebido")){
            throw new Exception("Candidato não encontrado");
        }
        verificaCandidatoExiste.setStatus("Qualificado");
    }

    public void desqualificarCandidato(int codCandidato) throws Exception {
        Candidato verificaCandidato = candidatos.get(codCandidato);
        if (verificaCandidato == null){
            throw new Exception("Candidato não encontrado");
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
            throw new Exception("Candidato não encontrado");
        }
        return verificaCandidatoExiste.getStatus();
    }

    public void aprovarCandidato(int codCandidato) throws Exception {
        Candidato verificaCandidatoExiste = candidatos.get(codCandidato);
        if (verificaCandidatoExiste == null || !verificaCandidatoExiste.getStatus().equals("Qualificado")){
            throw new Exception("Candidato não encontrado");
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
