package com.example.bisa.model.controller;

import com.example.bisa.dto.AuthorDto;
import com.example.bisa.dto.InformationAuthorDto;
import com.example.bisa.model.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create-author")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        try {
            AuthorDto response = authorService.createAuthor(authorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/lis-all-author")
    public ResponseEntity<List<AuthorDto>> listAllAuthor() {
        try {
            List<AuthorDto> list = authorService.getListAuthorAll();
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/lis-information-author-blog-comment")
    public ResponseEntity<List<InformationAuthorDto>> listAllAuthorBlogComment() {
        try {
            List<InformationAuthorDto> list = authorService.getListInformationComplete();
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
