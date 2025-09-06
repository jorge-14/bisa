package com.example.bisa.model.service.impl;

import com.example.bisa.db.Author;
import com.example.bisa.db.Blog;
import com.example.bisa.db.enums.TypePeriodicity;
import com.example.bisa.dto.BlogDto;
import com.example.bisa.dto.UpdateBlogDto;
import com.example.bisa.model.repository.BlogRepository;
import com.example.bisa.model.service.AuthorService;
import com.example.bisa.model.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
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
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    @Transactional
    public BlogDto createBlog(Long idAuthor, BlogDto blogDto) {
        try {
            if (isNullOrEmpty(blogDto.getTitle())) {
                throw new IllegalArgumentException("El Título es obligatorio");
            }

            if (isNullOrEmpty(blogDto.getPeriodicity())) {
                throw new IllegalArgumentException("La Periodicidad es obligatoria");
            }

            if (blogDto.getContent() == null) {
                throw new IllegalArgumentException("El Contenido es obligatorio");
            }

            if (blogDto.getIdAuthor() == null) {
                throw new IllegalArgumentException("El Autor es obligatorio");
            }

            Author author = authorService.getInformationAuthorById(idAuthor);

            String contentJson;
            try {
                contentJson = new ObjectMapper().writeValueAsString(blogDto.getContent());
            } catch (JsonProcessingException e) {
                log.error("Error al serializar el contenido del blog: {}", e.getMessage(), e);
                throw new RuntimeException("Error al serializar el contenido del blog", e);
            }

            Blog blogModel = Blog.builder()
                    .title(blogDto.getTitle())
                    .comment(blogDto.getComment())
                    .periodicity(TypePeriodicity.valueOf(blogDto.getPeriodicity()))
                    .content(contentJson)
                    .author(author)
                    .build();

            blogRepository.save(blogModel);

            try {
                return new BlogDto(blogModel);
            } catch (JsonProcessingException e) {
                log.error("Error al deserializar el contenido del blog: {}", e.getMessage(), e);
                throw new RuntimeException("Error al deserializar el contenido del blog", e);
            }

        } catch (IllegalArgumentException e) {
            log.error("Error de validación al crear el blog: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear el blog: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al crear el blog", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<BlogDto> getListBlogAll() {
        try {
            return blogRepository.listBlogAll();
        } catch (Exception e) {
            log.error("Error al obtener la lista de Blog", e);
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Blog informationBlogById(Long idBlog) {
        try {
            Blog blog = blogRepository.findById(idBlog)
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el blog con id: " + idBlog));
            return blog;
        } catch (
                EntityNotFoundException e) {
            log.error("No se encontró la entidad del blog con ID {}", idBlog, e);
            throw new RuntimeException("No se encontró la entidad del blog con ID " + idBlog, e);
        } catch (Exception e) {
            log.error("Ocurrió un error al obtener el blog con ID {}", idBlog, e);
            throw new RuntimeException("Ocurrió un error al obtener la información del blog", e);
        }
    }

    @Override
    @Transactional
    public UpdateBlogDto updateComment(Long idBlog, UpdateBlogDto updateBlogDto) {
        try {
            if (isNullOrEmpty(updateBlogDto.getTitle())) {
                throw new IllegalArgumentException("El Título es obligatorio");
            }

            if (isNullOrEmpty(updateBlogDto.getPeriodicity())) {
                throw new IllegalArgumentException("La Periodicidad es obligatoria");
            }

            if (updateBlogDto.getContent() == null) {
                throw new IllegalArgumentException("El Contenido es obligatorio");
            }

            Blog blogModel = blogRepository.findById(idBlog)
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el blog con id: " + idBlog));

            String contentJson;
            try {
                contentJson = new ObjectMapper().writeValueAsString(updateBlogDto.getContent());
            } catch (JsonProcessingException e) {
                log.error("Error al serializar el contenido del blog: {}", e.getMessage(), e);
                throw new RuntimeException("Error al serializar el contenido del blog", e);
            }

            blogModel.setTitle(updateBlogDto.getTitle());
            blogModel.setContent(contentJson);
            blogModel.setPeriodicity(TypePeriodicity.valueOf(updateBlogDto.getPeriodicity()));
            blogModel.setComment(updateBlogDto.getComment());

            blogRepository.save(blogModel);

            try {
                return new UpdateBlogDto(blogModel);
            } catch (JsonProcessingException e) {
                log.error("Error al deserializar el contenido del blog: {}", e.getMessage(), e);
                throw new RuntimeException("Error al deserializar el contenido del blog", e);
            }
        } catch (IllegalArgumentException e) {
            log.error("Error de validación al actualizar el blog: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al crear el blog: {}", e.getMessage(), e);
            throw new RuntimeException("Error inesperado al actualizar el blog", e);
        }
    }
}
