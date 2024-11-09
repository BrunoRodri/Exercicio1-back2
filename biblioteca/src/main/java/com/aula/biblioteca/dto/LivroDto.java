package com.aula.biblioteca.dto;

import com.aula.biblioteca.model.Autor;
import com.aula.biblioteca.model.Livro;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LivroDto(
        String id,
        @NotBlank(message = "não pode ser vazio") String titulo,
        @NotBlank String descricao,
        @NotNull @Min(0) Integer qtdePaginas,
        @NotNull @Min(0) @Max(10) Float nota,
        List<Autor> autors
        ) {
    public LivroDto(Livro livro){
        this(livro.getId(), livro.getTitulo(), livro.getDescricao(), livro.getQtdePaginas(), livro.getNota(), livro.getAutors());
    }
}
