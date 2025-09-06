package com.example.bisa.model.service;

import com.example.bisa.db.Blog;
import com.example.bisa.dto.BlogDto;
import com.example.bisa.dto.UpdateBlogDto;
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
public interface BlogService {

    BlogDto createBlog(Long idAuthor, BlogDto blogDto);
    List<BlogDto> getListBlogAll();
    Blog informationBlogById(Long idBlog);
    UpdateBlogDto updateComment(Long idBlog, UpdateBlogDto updateBlogDto);
}
