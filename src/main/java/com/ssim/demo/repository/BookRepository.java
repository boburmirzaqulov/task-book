package com.ssim.demo.repository;

import com.ssim.demo.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository {
    List<Book> getAllBooks();

    Book save(Book book);
}
