package com.br.spring_security.clinica.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Validações com @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Mapeia campo -> mensagem
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage(),
                        (msg1, msg2) -> msg1, // se houver campo repetido
                        LinkedHashMap::new    // mantém ordem
                ));

        return ResponseEntity.badRequest().body(errors);
    }

    // Violação de unicidade (ex: email duplicado)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String mensagem = "Erro de integridade de dados.";

        if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("email")) {
            mensagem = "Erro: email já cadastrado.";
        }

        Map<String, String> erro = Map.of("email", mensagem);
        return ResponseEntity.badRequest().body(erro);
    }
}