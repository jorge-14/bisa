package com.example.bisa.dto;

import com.example.bisa.db.Blog;
import com.example.bisa.db.b.BlogContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

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

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {

    private Long id;
    private String title;
    private BlogContent content;
    private String periodicity;
    private Boolean comment;
    private Long idAuthor;

    public BlogDto(Blog blog) throws JsonProcessingException {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = new ObjectMapper().readValue(blog.getContent(), BlogContent.class);
        this.periodicity = blog.getPeriodicity().getValue();
        this.comment = blog.getComment();
        this.idAuthor = blog.getAuthor() != null ? blog.getAuthor().getId() : null;
    }
}
