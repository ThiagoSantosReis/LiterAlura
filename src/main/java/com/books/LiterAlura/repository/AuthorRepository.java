package com.books.LiterAlura.repository;

import com.books.LiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findAuthorAliveByGiverYear(int year);

    @Query("SELECT a FROM Book b JOIN b.author a GROUP BY a ORDER BY COUNT(b) DESC LIMIT 1")
    Author findAuthorMostPublishedBooks();
}
