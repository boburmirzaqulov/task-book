package com.ssim.demo.service.impl;

import com.ssim.demo.dto.AuthorDTO;
import com.ssim.demo.dto.BookDTO;
import com.ssim.demo.entity.Book;
import com.ssim.demo.repository.BookRepository;
import com.ssim.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookRepository.getAllBooks().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription()
                ))
                .sorted((o1, o2) -> o2.getTitle().compareTo(o1.getTitle())).
                collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<BookDTO> addBook(BookDTO bookDTO) {
        Book book = new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getDescription()
        );
        book = bookRepository.save(book);
        bookDTO.setId(book.getId());
        return ResponseEntity.ok(bookDTO);
    }

    @Override
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<AuthorDTO> authors = new ArrayList<>();
        bookRepository.getAllBooks().stream()
                .map(book -> new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription()
                ))
                .collect(Collectors.groupingBy(BookDTO::getAuthor))
                .forEach((author, books) -> {
                    books.forEach(b -> b.setAuthor(null));
                    authors.add(new AuthorDTO(author, books));
                });
        return ResponseEntity.ok(authors);
    }
}
