package com.example.bisa.dto;

import com.example.bisa.db.Author;
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

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private Date birthdate;
    private String email;
    private String country;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.paternalSurname = author.getPaternalSurname();
        this.maternalSurname = author.getMaternalSurname();
        this.birthdate = author.getBirthdate();
        this.email = author.getEmail();
        this.country = author.getCountry();
    }

}
