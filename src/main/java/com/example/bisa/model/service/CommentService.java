package com.example.bisa.model.service;

import com.example.bisa.dto.CommentDto;
import java.util.List;

/*
 *----------------------------------------
 *   Código de Aplicación:
 *   Código de Objeto:
 *   Descripción:
 *   Author Prog: Jorge Luis Choque Callizaya
 *----------------------------------------
 *   Fecha | Autor | Comentario
 *   06.09.2025 | Jorge Luis Choque Callizaya | Creación Inicial
 *----------------------------------------
 */
public interface CommentService {

    CommentDto createComment(Long idBlog, CommentDto commentDto);
    List<CommentDto> getListComment();
}
