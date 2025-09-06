package com.example.bisa.db;

import com.example.bisa.db.enums.TypePeriodicity;
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
@Table(name = "BLOG")
public class Blog {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_BLOG_ID_GENERATOR", sequenceName = "SEQ_BLOG_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BLOG_ID_GENERATOR")
    private Long id;

    @Basic
    @Column(name = "TITLE")
    private String title;

    @Lob
    @Column(name = "CONTENT", columnDefinition = "CLOB")
    private String content;

    @Basic
    @Column(name = "PERIODICITY")
    @Enumerated(EnumType.STRING)
    private TypePeriodicity periodicity;

    @Basic
    @Column(name = "COMMENT")
    private Boolean comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", name = "ID_AUTHOR")
    private Author author;

}
