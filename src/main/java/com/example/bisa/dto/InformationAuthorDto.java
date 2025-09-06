package com.example.bisa.dto;

import lombok.*;
import java.util.Date;
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

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationAuthorDto {

    private Long idAuthor;
    private String nameAuthor;
    private String paternalSurnameAuthor;
    private String maternalSurnameAuthor;
    private Date birthdateAuthor;
    private String emailAuthor;
    private String countryAuthor;
    private List<InformationBlogDto> blog;
}
