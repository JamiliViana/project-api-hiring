package com.ibm.api.advice;


import com.ibm.api.advice.exception.CandidatoJaParticipaException;
import com.ibm.api.advice.exception.CandidatoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(CandidatoJaParticipaException.class)
    public ResponseEntity<String> CandidatoJaParticipaException(Exception ex) {
        return ResponseEntity.badRequest().body("Candidato já participa do processo.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> NomeInvalidoException(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body("Nome Inválido");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CandidatoNaoEncontradoException.class)
    public ResponseEntity<String> CandidatoNaoEncontradoException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Candidato não encontrado");
    }


}
