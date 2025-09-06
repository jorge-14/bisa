package com.example.bisa.model.repository;

import com.example.bisa.db.Author;
import com.example.bisa.dto.AuthorDto;
import com.example.bisa.dto.ListInformationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/*
 *----------------------------------------
 *   Código de Aplicación:
 *   Código de Objeto:
 *   Descripción:
 *   Author Prog: Jorge Luis Choque Callizaya
 *----------------------------------------
 *   Fecha | Autor | Comentario
 *   05.09.2025 | Jorge Luis Choque Callizaya | Creación Inicial
 *----------------------------------------
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT new com.example.bisa.dto.AuthorDto(a) " +
            "FROM Author a")
    List<AuthorDto> listAuthorAll();

    @Query("SELECT new com.example.bisa.dto.ListInformationDto(a, b, c) " +
            "FROM Author a " +
            "LEFT JOIN Blog b ON a.id = b.author.id " +
            "LEFT JOIN Comment c ON b.id = c.blog.id")
    List<ListInformationDto> getListInformation();
}
