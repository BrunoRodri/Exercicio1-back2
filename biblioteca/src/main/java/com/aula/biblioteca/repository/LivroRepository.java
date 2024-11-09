package com.aula.biblioteca.repository;

import com.aula.biblioteca.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String> {

    @Query("{'titulo': ?0}")
    Optional<Livro> findByTitulo(String titulo);

    @Query("{'nota': {'$gte': ?0}}")
    Page<Livro> findByNotaGreaterThanEqual(Float nota, Pageable pagination);

    @Query("{'$and': [{'qtdePaginas': {'$gte': ?0}}, {'nota': {'$gte': ?1}}]}")
    Page<Livro> findByNotaAndQtdePaginasGreaterThanEqual(Integer qtdePaginas, Float nota, Pageable pagination);
}
