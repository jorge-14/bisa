package com.example.bisa.dto;

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
public class InformationCommentDto {

    private Long idComment;
    private String nameComment;
    private String paternalSurnameComment;
    private String maternalSurnameComment;
    private String emailComment;
    private String countryComment;
    private Integer punctuationComment;
}
