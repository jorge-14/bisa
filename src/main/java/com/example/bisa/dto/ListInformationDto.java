package com.example.bisa.dto;

import com.example.bisa.db.Author;
import com.example.bisa.db.Blog;
import com.example.bisa.db.Comment;
import com.example.bisa.db.b.BlogContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import java.util.Date;

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
public class ListInformationDto {

    private Long idAuthor;
    private String nameAuthor;
    private String paternalSurnameAuthor;
    private String maternalSurnameAuthor;
    private Date birthdateAuthor;
    private String emailAuthor;
    private String countryAuthor;

    private Long idBlog;
    private String titleBlog;
    private BlogContent contentBlog;
    private String periodicityBlog;
    private Boolean commentBlog;

    private Long idComment;
    private String nameComment;
    private String paternalSurnameComment;
    private String maternalSurnameComment;
    private String emailComment;
    private String countryComment;
    private Integer punctuationComment;

    public ListInformationDto(Author author, Blog blog, Comment comment) throws JsonProcessingException {

        if (author != null) {
            this.idAuthor = author.getId();
            this.nameAuthor = author.getName();
            this.paternalSurnameAuthor = author.getPaternalSurname();
            this.maternalSurnameAuthor = author.getMaternalSurname();
            this.birthdateAuthor = author.getBirthdate();
            this.emailAuthor = author.getEmail();
            this.countryAuthor = author.getCountry();
        }

        if (blog != null) {
            this.idBlog = blog.getId();
            this.titleBlog = blog.getTitle();

            String contentStr = blog.getContent();
            if (contentStr != null) {
                this.contentBlog = new ObjectMapper().readValue(contentStr, BlogContent.class);
            } else {
                this.contentBlog = null;
            }

            this.periodicityBlog = blog.getPeriodicity() != null ? blog.getPeriodicity().getValue() : null;
            this.commentBlog = blog.getComment();
        }

        if (comment != null) {
            this.idComment = comment.getId();
            this.nameComment = comment.getName();
            this.paternalSurnameComment = comment.getPaternalSurname();
            this.maternalSurnameComment = comment.getMaternalSurname();
            this.emailComment = comment.getEmail();
            this.countryComment = comment.getCountry();
            this.punctuationComment = comment.getPunctuation();
        }
    }
}
