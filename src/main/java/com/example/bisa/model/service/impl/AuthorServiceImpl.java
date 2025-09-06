package com.example.bisa.model.service.impl;

import com.example.bisa.db.Author;
import com.example.bisa.dto.*;
import com.example.bisa.model.repository.AuthorRepository;
import com.example.bisa.model.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import static ch.qos.logback.core.util.OptionHelper.isNullOrEmpty;

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

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public AuthorDto createAuthor(AuthorDto authorDto) {
        try {
            if (isNullOrEmpty(authorDto.getName())) {
                throw new NoSuchElementException("El nombre es obligatorio.");
            }

            if (isNullOrEmpty(authorDto.getPaternalSurname())) {
                throw new NoSuchElementException("El apellido paterno es obligatorio.");
            }

            if (isNullOrEmpty(authorDto.getMaternalSurname())) {
                throw new NoSuchElementException("El apellido materno es obligatorio.");
            }

            if (isNullOrEmpty(authorDto.getEmail())) {
                throw new NoSuchElementException("El correo es obligatorio.");
            }

            if (isNullOrEmpty(authorDto.getCountry())) {
                throw new NoSuchElementException("El país es obligatorio.");
            }

            if (authorDto.getBirthdate() == null) {
                throw new NoSuchElementException("La fecha de nacimiento es obligatoria.");
            }

            if (!authorDto.getName().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
            }

            if (!authorDto.getPaternalSurname().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El apellido paterno solo puede contener letras y espacios.");
            }

            if (!authorDto.getMaternalSurname().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El apellido materno solo puede contener letras y espacios.");
            }

            if (!authorDto.getCountry().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El país solo puede contener letras y espacios.");
            }

            if (!authorDto.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
                throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
            }

            Author authorModel = Author.builder()
                    .name(authorDto.getName().trim())
                    .paternalSurname(authorDto.getPaternalSurname())
                    .maternalSurname(authorDto.getMaternalSurname())
                    .birthdate(authorDto.getBirthdate())
                    .email(authorDto.getEmail())
                    .country(authorDto.getCountry())
                    .build();

            authorRepository.save(authorModel);
            return new AuthorDto(authorModel);

        } catch (NoSuchElementException | IllegalArgumentException e) {
            log.error("Error de validación al crear el autor: {}", e.getMessage(), e);
            throw new RuntimeException("Error al crear el autor: " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Error inesperado al crear el autor", e);
            throw new RuntimeException("Se produjo un error inesperado al crear el autor.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDto> getListAuthorAll() {
        try {
            return authorRepository.listAuthorAll();
        } catch (Exception e) {
            log.error("Error al obtener la lista de autores", e);
            return Collections.emptyList();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Author getInformationAuthorById(Long idAuthor) {
        try {
            Author author = authorRepository.findById(idAuthor)
                    .orElseThrow(() -> new NoSuchElementException("No se encontró el autor con id: " + idAuthor));
            return author;
        } catch (EntityNotFoundException e) {
            log.error("No se encontró la entidad del autor con ID {}", idAuthor, e);
            throw new RuntimeException("No se encontró la entidad del autor con ID " + idAuthor, e);
        } catch (Exception e) {
            log.error("Ocurrió un error al obtener el autor con ID {}", idAuthor, e);
            throw new RuntimeException("Ocurrió un error al obtener la información del autor", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<InformationAuthorDto> getListInformationComplete() {
        try {
            List<ListInformationDto> listInformation = authorRepository.getListInformation();

            Map<Long, InformationAuthorDto> authorMap = new HashMap<>();

            for (ListInformationDto info : listInformation) {
                InformationAuthorDto authorDto = authorMap.computeIfAbsent(info.getIdAuthor(), id -> {
                    InformationAuthorDto dto = new InformationAuthorDto();
                    dto.setIdAuthor(info.getIdAuthor());
                    dto.setNameAuthor(info.getNameAuthor());
                    dto.setPaternalSurnameAuthor(info.getPaternalSurnameAuthor());
                    dto.setMaternalSurnameAuthor(info.getMaternalSurnameAuthor());
                    dto.setBirthdateAuthor(info.getBirthdateAuthor());
                    dto.setEmailAuthor(info.getEmailAuthor());
                    dto.setCountryAuthor(info.getCountryAuthor());
                    dto.setBlog(new ArrayList<>());
                    return dto;
                });

                if (info.getIdBlog() != null) {
                    List<InformationBlogDto> blogs = authorDto.getBlog();
                    InformationBlogDto blogDto = blogs.stream()
                            .filter(b -> b.getIdBlog().equals(info.getIdBlog()))
                            .findFirst()
                            .orElseGet(() -> {
                                InformationBlogDto dto = new InformationBlogDto();
                                dto.setIdBlog(info.getIdBlog());
                                dto.setTitleBlog(info.getTitleBlog());
                                dto.setContentBlog(info.getContentBlog());
                                dto.setPeriodicityBlog(info.getPeriodicityBlog());
                                dto.setCommentBlog(info.getCommentBlog());
                                dto.setComment(new ArrayList<>());
                                blogs.add(dto);
                                return dto;
                            });

                    if (info.getIdComment() != null) {
                        InformationCommentDto commentDto = new InformationCommentDto();
                        commentDto.setIdComment(info.getIdComment());
                        commentDto.setNameComment(info.getNameComment());
                        commentDto.setPaternalSurnameComment(info.getPaternalSurnameComment());
                        commentDto.setMaternalSurnameComment(info.getMaternalSurnameComment());
                        commentDto.setEmailComment(info.getEmailComment());
                        commentDto.setCountryComment(info.getCountryComment());
                        commentDto.setPunctuationComment(info.getPunctuationComment());

                        blogDto.getComment().add(commentDto);
                    }
                }
            }

            // Calcular min, max y average para cada blog
            for (InformationAuthorDto author : authorMap.values()) {
                for (InformationBlogDto blog : author.getBlog()) {
                    List<InformationCommentDto> comments = blog.getComment();

                    if (comments != null && !comments.isEmpty()) {
                        IntSummaryStatistics stats = comments.stream()
                                .filter(c -> c.getPunctuationComment() != null)
                                .mapToInt(InformationCommentDto::getPunctuationComment)
                                .summaryStatistics();

                        blog.setMin(stats.getMin());
                        blog.setMax(stats.getMax());
                        blog.setAverage(BigDecimal.valueOf(stats.getAverage()).setScale(2, RoundingMode.HALF_UP));
                    } else {
                        blog.setMin(null);
                        blog.setMax(null);
                        blog.setAverage(null);
                    }
                }
            }

            return new ArrayList<>(authorMap.values());

        } catch (Exception e) {
            log.error("Error al obtener la lista completa de información del autor", e);
            return Collections.emptyList();
        }
    }
}
