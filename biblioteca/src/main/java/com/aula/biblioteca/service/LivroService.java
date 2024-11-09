package com.aula.biblioteca.service;

import com.aula.biblioteca.dto.LivroDto;
import com.aula.biblioteca.model.Livro;
import com.aula.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public Page<LivroDto> findAll(Pageable pagination){
        return livroRepository.findAll(pagination).map(LivroDto::new);
    }

    public LivroDto findById(String id){
        Optional<Livro> optionalLivro = livroRepository.findById(id);
        if(optionalLivro.isPresent()){
            return new LivroDto(optionalLivro.get());
        }
        throw new NoSuchElementException("Livro de id: " + id + ", não encontrado");
    }

    public Page<LivroDto> findByNotaGreaterThanEqual(Float nota, Pageable pagination){
        return livroRepository.findByNotaGreaterThanEqual(nota, pagination).map(LivroDto::new);
    }

    public LivroDto findByTitulo(String titulo){
        Optional<Livro> optionalLivro = livroRepository.findByTitulo(titulo);
        if(optionalLivro.isPresent()){
            return new LivroDto(optionalLivro.get());
        }
        throw new NoSuchElementException("Livro de título: " + titulo + ", não encontrado");
    }

    public Page<LivroDto> findByNotaAndQtdePaginasGreaterThanEqual(Float nota, Integer qtdePaginas, Pageable pagination){
        return livroRepository.findByNotaAndQtdePaginasGreaterThanEqual(qtdePaginas, nota, pagination).map(LivroDto::new);
    }

    public LivroDto save(LivroDto livroDto){
        Livro livro = Livro.fromDto(livroDto);
        return new LivroDto(livroRepository.save(livro));
    }

    public LivroDto update(String id, LivroDto livroDto){
        Livro livro = Livro.fromDto(livroDto);
        livro.setId(id);
        return new LivroDto(livroRepository.save(livro));
    }

    public void delete(String id){
        livroRepository.deleteById(id);
    }
}
