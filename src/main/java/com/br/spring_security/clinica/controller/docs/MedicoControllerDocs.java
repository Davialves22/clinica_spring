package com.br.spring_security.clinica.controller.docs;

import com.br.spring_security.clinica.Request.MedicoRequest;
import com.br.spring_security.clinica.model.Medico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MedicoControllerDocs {

    @Operation(summary = "Adiciona um novo Médico", description = "Adiciona um novo médico ao sistema", tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "201", content = @Content(schema = @Schema(implementation = Medico.class))),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Medico> salvar(@RequestBody MedicoRequest request);

    @Operation(summary = "Lista todos os Médicos", description = "Lista todos os médicos cadastrados no sistema", tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Medico.class)))
                    }),
                    @ApiResponse(description = "Conteúdo Não Encontrado", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    List<Medico> listarTodos();

    @Operation(summary = "Obtém um Médico por ID", description = "Obtém os detalhes de um médico específico usando seu ID", tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = Medico.class))),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Medico> obterPorId(@PathVariable Long id);

    @Operation(summary = "Atualiza um Médico", description = "Atualiza as informações de um médico existente usando seu ID", tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody MedicoRequest request);

    @Operation(summary = "Exclui um Médico", description = "Exclui um médico do sistema usando seu ID", tags = {"Médicos"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Void> deletar(@PathVariable Long id);
}