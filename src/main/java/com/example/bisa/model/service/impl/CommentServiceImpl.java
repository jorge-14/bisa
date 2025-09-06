package com.example.bisa.model.service.impl;

import com.example.bisa.db.Blog;
import com.example.bisa.db.Comment;
import com.example.bisa.dto.CommentDto;
import com.example.bisa.model.repository.CommentRepository;
import com.example.bisa.model.service.BlogService;
import com.example.bisa.model.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import static ch.qos.logback.core.util.OptionHelper.isNullOrEmpty;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogService blogService;

    @Override
    @Transactional
    public CommentDto createComment(Long idBlog, CommentDto commentDto) {
        try {

            Blog blogModel = blogService.informationBlogById(idBlog);

            if (!blogModel.getComment()){
                throw new NoSuchElementException("No se puede colcoar comentarios aun Blog que no permite comentarios.");
            }

            if (isNullOrEmpty(commentDto.getName())) {
                throw new NoSuchElementException("El nombre es obligatorio.");
            }

            if (isNullOrEmpty(commentDto.getPaternalSurname())) {
                throw new NoSuchElementException("El apellido paterno es obligatorio.");
            }

            if (isNullOrEmpty(commentDto.getMaternalSurname())) {
                throw new NoSuchElementException("El apellido materno es obligatorio.");
            }

            if (isNullOrEmpty(commentDto.getEmail())) {
                throw new NoSuchElementException("El correo es obligatorio.");
            }

            if (isNullOrEmpty(commentDto.getCountry())) {
                throw new NoSuchElementException("El país es obligatorio.");
            }

            if (!commentDto.getName().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
            }

            if (!commentDto.getPaternalSurname().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El apellido paterno solo puede contener letras y espacios.");
            }

            if (!commentDto.getMaternalSurname().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El apellido materno solo puede contener letras y espacios.");
            }

            if (!commentDto.getCountry().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El país solo puede contener letras y espacios.");
            }

            if (!commentDto.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
            }

            if (commentDto.getPunctuation() == null) {
                throw new IllegalArgumentException("La puntuación es obligatoria.");
            }

            if (commentDto.getPunctuation() < 0 || commentDto.getPunctuation() > 10) {
                throw new IllegalArgumentException("La puntuación debe estar entre 0 y 10.");
            }

            Comment commentModel = Comment.builder()
                    .name(commentDto.getName().trim())
                    .paternalSurname(commentDto.getPaternalSurname().trim())
                    .maternalSurname(commentDto.getMaternalSurname().trim())
                    .email(commentDto.getEmail().trim())
                    .country(commentDto.getCountry().trim())
                    .punctuation(commentDto.getPunctuation())
                    .blog(blogModel)
                    .build();

            commentRepository.save(commentModel);
            return new CommentDto(commentModel);

        } catch (IllegalArgumentException | NoSuchElementException ex) {
            log.error("Error de validación: {}", ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            log.error("Error inesperado al crear el comentario: {}", ex.getMessage(), ex);
            throw new RuntimeException("Ocurrió un error inesperado al crear el comentario.", ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getListComment() {
        try {
            return commentRepository.listComment();
        } catch (Exception e) {
            log.error("Error al obtener la lista de Comentarios", e);
            return Collections.emptyList();
        }
    }
}
