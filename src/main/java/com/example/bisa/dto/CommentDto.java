package com.example.bisa.dto;

import com.example.bisa.db.Comment;
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
public class CommentDto {

    private Long id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String email;
    private String country;
    private Integer punctuation;
    private Long idBlog;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.name = comment.getName();
        this.paternalSurname = comment.getPaternalSurname();
        this.maternalSurname = comment.getMaternalSurname();
        this.email = comment.getEmail();
        this.country = comment.getCountry();
        this.punctuation = comment.getPunctuation();
        this.idBlog = comment.getBlog() != null ? comment.getId() : null;
    }
}
