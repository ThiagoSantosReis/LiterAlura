package com.books.LiterAlura.repository;

import com.books.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthorName(String title, String name);
}
