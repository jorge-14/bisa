package com.example.bisa.db;

import jakarta.persistence.*;
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

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "COMMENT")
public class Comment {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_COMMENT_ID_GENERATOR", sequenceName = "SEQ_COMMENT_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMMENT_ID_GENERATOR")
    private Long id;

    @Basic
    @Column(name = "NAME")
    private String name;

    @Basic
    @Column(name = "PATERNAL_SURNAME")
    private String paternalSurname;

    @Basic
    @Column(name = "MATERNAL_SURNAME")
    private String maternalSurname;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @Basic
    @Column(name = "COUNTRY")
    private String country;

    @Basic
    @Column(name = "PUNCTUATION")
    private Integer punctuation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", name = "ID_BLOG")
    private Blog blog;
}
