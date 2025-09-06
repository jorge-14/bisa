package com.example.bisa.db;

import jakarta.persistence.*;
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
 *   05.09.2025 | Jorge Luis Choque Callizaya | Creación Inicial
 *----------------------------------------
 */

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTHOR")
public class Author {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SEQ_AUTHOR_ID_GENERATOR", sequenceName = "SEQ_AUTHOR_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTHOR_ID_GENERATOR")
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
    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Basic
    @Column(name = "EMAIL")
    private String email;

    @Basic
    @Column(name = "COUNTRY")
    private String country;
}
