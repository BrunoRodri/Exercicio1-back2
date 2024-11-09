package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.AutorDto;
import com.aula.biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/autores")
public class AutorController {

    private final AutorService autorService;

    @Operation(summary = "Listar todos os autores", description = "Listar todos os autores com paginação da base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de autores", content = @Content(mediaType = "application/json",schema=@Schema(implementation = Page.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<AutorDto>> findAll(@PageableDefault(size = 5)Pageable pageable){
        return ResponseEntity.ok(autorService.findAll(pageable));
    }

    @Operation(summary = "Adicionar autores", description = "Adicionar os autores a base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor adicionado", content = @Content(mediaType = "application/json",schema=@Schema(implementation = Page.class)))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> save(@RequestBody AutorDto autorDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(autorService.save(autorDto));
    }

    @Operation(summary = "Atualizar autores", description = "Atualizar os autores a base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor atualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> update(@PathVariable String id, @RequestBody AutorDto autorDto){
        return ResponseEntity.ok(autorService.update(id, autorDto));
    }
}
