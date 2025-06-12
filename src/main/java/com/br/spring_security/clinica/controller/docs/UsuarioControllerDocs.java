package com.br.spring_security.clinica.controller.docs;

import com.br.spring_security.clinica.Request.UsuarioRequest; // Importe UsuarioRequest
import com.br.spring_security.clinica.model.Usuario; // Importe a entidade Usuario
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

public interface UsuarioControllerDocs {

    @Operation(summary = "Adiciona um novo Usuário", tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "201", content = @Content(schema = @Schema(implementation = Usuario.class))),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Usuario> salvar(@RequestBody UsuarioRequest request);

    @Operation(summary = "Lista todos os Usuários", tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Usuario.class)))
                    }),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<List<Usuario>> listarTodos();


    @Operation(summary = "Obtém um Usuário por ID", tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = Usuario.class))),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Usuario> obterPorId(@PathVariable Long id);

    @Operation(summary = "Atualiza um Usuário", tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(schema = @Schema(implementation = Usuario.class))), // Retorna o usuário atualizado
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest request);

    @Operation(summary = "Exclui um Usuário", tags = {"Usuários"},
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Requisição Inválida", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Não Autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Não Encontrado", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Erro Interno do Servidor", responseCode = "500", content = @Content)
            })
    ResponseEntity<Void> deletar(@PathVariable Long id);
}