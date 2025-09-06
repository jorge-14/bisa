package com.example.bisa.model.controller;

import com.example.bisa.dto.BlogDto;
import com.example.bisa.dto.UpdateBlogDto;
import com.example.bisa.model.service.BlogService;
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
 *   06.09.2025 | Jorge Luis Choque Callizaya | Creación Inicial
 *----------------------------------------
 */


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create-blog/{idAuthor}")
    public ResponseEntity<BlogDto> createBlog(@RequestBody BlogDto blogDto,
                                                  @PathVariable Long idAuthor) {
        try {
            BlogDto response = blogService.createBlog(idAuthor, blogDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/lis-all-blog")
    public ResponseEntity<List<BlogDto>> listAllBlog() {
        try {
            List<BlogDto> list = blogService.getListBlogAll();
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update-blog/{idBlog}")
    public ResponseEntity<UpdateBlogDto> updateBlog(@RequestBody UpdateBlogDto updateBlogDto,
                                                    @PathVariable Long idBlog) {
        try {
            UpdateBlogDto response = blogService.updateComment(idBlog, updateBlogDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
