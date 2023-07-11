package com.ibm.api.controller;

import com.ibm.api.controller.dto.HiringRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ibm.api.service.HiringService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/hiring")
public class HiringController {

    @Autowired
    public HiringService service;

    @PostMapping("/start")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> iniciarProcesso(@Valid @RequestBody HiringRequestDTO request) throws Exception {
        String nome = request.getNome();
        int codCandidato = this.service.iniciarProcesso(nome);
        return ResponseEntity.ok(codCandidato);
    }

    @PostMapping("/schedule")
    @ResponseStatus(HttpStatus.OK)
    public void marcarEntrevista(@RequestBody HiringRequestDTO request) throws Exception {
        service.marcarEntrevista(request.getCodCandidato());
    }

    @PostMapping("/disqualify")
    @ResponseStatus(HttpStatus.OK)
    public void desqualificarCandidato(@RequestBody HiringRequestDTO request) throws Exception {
        service.desqualificarCandidato(request.getCodCandidato());
    }

    @PostMapping("/approve")
    @ResponseStatus(HttpStatus.OK)
    public void aprovarCandidato(@RequestBody HiringRequestDTO request) throws Exception {
        service.aprovarCandidato(request.getCodCandidato());
    }

    @GetMapping("/status/candidate/{codCandidato}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> verificarStatusCandidato(@PathVariable int codCandidato) throws Exception {
        String status = service.verificarStatusCandidato(codCandidato);
        return ResponseEntity.ok(status);
    }


    @GetMapping("/approved")
    public ResponseEntity<List<String>> obterAprovados() {
        List<String> aprovados = service.obterAprovados();
        if (aprovados.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(aprovados);
        }
    }



}
