package com.books.LiterAlura.principal;

import com.books.LiterAlura.model.*;
import com.books.LiterAlura.repository.AuthorRepository;
import com.books.LiterAlura.repository.BookRepository;
import com.books.LiterAlura.service.Converter;
import com.books.LiterAlura.service.GutendexBookApiService;

import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    private Converter cvs = new Converter();
    private Scanner reader = new Scanner(System.in);
    private final String BASE_URL = "https://gutendex.com/books/?search=";

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void showMenu(){
        String menu = """
                0 - Exit
                1 - Search a book
                >
                """;
        int option = -1;

        while(option != 0){
            System.out.println(menu);
            option = Integer.parseInt(reader.nextLine());

            switch (option){
                case 1:
                    bookSelection();
                break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void bookSelection() {
        Book book = getBookData();
        Optional<Book> existingBook = bookRepository.findByTitleAndAuthorName(book.getTitle(), book.getAuthor().getName());
        if(existingBook.isPresent()){
            System.out.println("This book is already in the database.");
        }else{
            bookRepository.save(book);
        }
    }

    private Book getBookData(){
        System.out.println("Enter a book: ");
        String name = reader.nextLine();
        String json = GutendexBookApiService.getData(BASE_URL+name);
        BookResponse bookResponse = cvs.getData(json, BookResponse.class);
        Book book= new Book(bookResponse.books().get(0));
        Optional<Author> authorOptional = authorRepository.findByName(book.getAuthor().getName());

        if(authorOptional.isEmpty()){
            authorRepository.save(book.getAuthor());
        }else{
            book.setAuthor(authorOptional.get());
        }
        return book;
    }
}
