package com.ssim.demo.repository.impl;

import com.ssim.demo.entity.Book;
import com.ssim.demo.mapper.BookRowMapper;
import com.ssim.demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAllBooks() {
        String query = "select * from BOOK";
        return jdbcTemplate.query(query, new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        String query = "insert into BOOK (id, title, author, description) values(?,?,?,?)";
        int id = getLastId() + 1;
        book.setId(id);
        try {
            int rows = jdbcTemplate.update(query, ps -> {
                ps.setInt(1, id);
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getAuthor());
                ps.setString(4, book.getDescription());
            });
            if (rows == 0){
                throw new SQLException("Not added");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    private Integer getLastId() {
        String query = "select id from book order by id desc fetch next 1 rows only";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
