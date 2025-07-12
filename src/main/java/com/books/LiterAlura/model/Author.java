package com.books.LiterAlura.model;

import java.util.List;

public class Author {
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    private List<Book> books;

    public Author(){}

    public Author(AuthorData data){
        this.name = data.name();
        this.birthYear = data.birthYear();
        this.deathYear = data.deathYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    @Override
    public String toString(){
        return """
                %s
                BirthYear: %d
                DeathYear: %d
                """.formatted(name, birthYear, deathYear);
    }

}
