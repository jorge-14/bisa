package com.example.bisa.model.repository;

import com.example.bisa.db.Blog;
import com.example.bisa.dto.BlogDto;
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
 *   06.09.2025 | Jorge Luis Choque Callizaya | Creación Inicial
 *----------------------------------------
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("SELECT new com.example.bisa.dto.BlogDto(b) " +
            "FROM Blog b ")
    List<BlogDto> listBlogAll();
}
