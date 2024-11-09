package com.aula.biblioteca.controller;

import com.aula.biblioteca.dto.LivroDto;
import com.aula.biblioteca.service.LivroService;
import jakarta.validation.Valid;
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
@RequestMapping(path = "/livros",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
public class LivroController {

    private final LivroService livroService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<LivroDto>> findAll(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(livroService.findAll(pagination));
    }


    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable String id){
        return ResponseEntity.ok(livroService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestBody @Valid LivroDto livroDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(livroDto));
    }

}
