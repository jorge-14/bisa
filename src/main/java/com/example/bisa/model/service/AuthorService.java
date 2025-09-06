package com.example.bisa.model.service;

import com.example.bisa.db.Author;
import com.example.bisa.dto.AuthorDto;
import com.example.bisa.dto.InformationAuthorDto;
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
public interface AuthorService {

    AuthorDto createAuthor(AuthorDto authorDto);
    List<AuthorDto> getListAuthorAll();
    Author getInformationAuthorById(Long idAuthor);
    List<InformationAuthorDto> getListInformationComplete();
}
