package com.books.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private Long downloadCount;
    private Boolean copywriting;
    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public Book(){

    }

    public Book(BookData data){
        this.title = data.title();
        this.language = data.languages().get(0);
        this.downloadCount = data.downloadCount();
        this.copywriting = data.copyright();
        this.author = new Author(data.authors().get(0));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Long downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Boolean getCopywriting() {
        return copywriting;
    }

    public void setCopywriting(Boolean copywriting) {
        this.copywriting = copywriting;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return """
                Title: %s
                Language: %s
                Download Count: %d
                Copyright: %b
                Author: %s
                """.formatted(title, language, downloadCount, copywriting, author);
    }
}
