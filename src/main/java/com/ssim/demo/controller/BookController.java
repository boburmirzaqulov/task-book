package com.ssim.demo.controller;

import com.ssim.demo.dto.AuthorDTO;
import com.ssim.demo.dto.BookDTO;
import com.ssim.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        return bookService.addBook(bookDTO);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAuthors(){
        return bookService.getAuthors();
    }
}
