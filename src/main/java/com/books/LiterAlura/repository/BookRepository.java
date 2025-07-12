package com.books.LiterAlura.repository;

import com.books.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleAndAuthorName(String title, String name);

    List<Book> findByAuthorNameContainingIgnoreCase(String name);

    @Query("SELECT b FROM Book b ORDER BY b.downloadCount DESC limit 1")
    Book findMostDownloadedBook();

    @Query("SELECT DISTINCT b.language FROM Book b")
    List<String> findAllLanguages();

    @Query("SELECT b FROM Book b WHERE b.language = :language")
    List<Book> findByGivenLanguage(String language);
}
