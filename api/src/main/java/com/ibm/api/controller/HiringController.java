package com.ibm.api.controller;

import com.ibm.api.controller.dto.HiringRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ibm.api.service.HiringService;
import org.springframework.web.bind.annotation.ResponseStatus;

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

}
