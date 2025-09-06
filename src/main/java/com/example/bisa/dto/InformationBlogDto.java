package com.example.bisa.dto;

import com.example.bisa.db.b.BlogContent;
import lombok.*;
import java.math.BigDecimal;
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
public class InformationBlogDto {

    private Long idBlog;
    private String titleBlog;
    private BlogContent contentBlog;
    private String periodicityBlog;
    private Boolean commentBlog;
    private List<InformationCommentDto> comment;
    private Integer min;
    private Integer max;
    private BigDecimal average;
}
