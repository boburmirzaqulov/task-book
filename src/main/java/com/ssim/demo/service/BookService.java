package com.ssim.demo.service;

import com.ssim.demo.dto.AuthorDTO;
import com.ssim.demo.dto.BookDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {

    ResponseEntity<List<BookDTO>> getAllBooks();

    ResponseEntity<BookDTO> addBook(BookDTO bookDTO);

    ResponseEntity<List<AuthorDTO>> getAuthors();
}
