package com.books.LiterAlura.principal;

import com.books.LiterAlura.model.*;
import com.books.LiterAlura.repository.AuthorRepository;
import com.books.LiterAlura.repository.BookRepository;
import com.books.LiterAlura.service.Converter;
import com.books.LiterAlura.service.GutendexBookApiService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

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
                2 - List all books
                3 - Find all books by given author
                4 - Most downloaded book
                5 - Show books by language
                6 - List authors alive during a given period
                7 - List all authors
                8 - Author with most published works
                >
                """;
        int option = -1;

        while(option != 0){
            System.out.println(menu);
            option = Integer.parseInt(reader.nextLine());

            if(option == 0){
                break;
            }

            switch (option){
                case 1:
                    bookSelection();
                break;
                case 2:
                    listAllBooks();
                break;
                case 3:
                    listAllBooksByAuthor();
                break;
                case 4:
                    showMostDownloadedBook();
                break;
                case 5:
                    showBooksByLanguage();
                break;
                case 6:
                    listAuthorAlive();
                break;
                case 7:
                    listAllAuthors();
                break;
                case 8:
                    getAuthorWithMostPublishedWorks();
                break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void getAuthorWithMostPublishedWorks() {
        System.out.println("AUTHOR WITH MOST PUBLISHED BOOKS:");
        Author author = authorRepository.findAuthorMostPublishedBooks();
        System.out.println(author);
    }

    private void listAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        System.out.println("AUTHORS: ");
        authors.forEach(a -> System.out.println(a.getName()));
    }

    private void listAuthorAlive() {
        System.out.println("Enter a year: ");
        int year = Integer.parseInt(reader.nextLine());
        List<Author> authors = authorRepository.findAuthorAliveByGiverYear(year);
        authors.forEach(System.out::println);
    }

    private void showBooksByLanguage() {
        List<String> languages = bookRepository.findAllLanguages();
        for(int i = 0; i < languages.size(); i++){
            System.out.println((i+1)+" - "+languages.get(i));
        }
        System.out.println("Enter language's code: ");
        int languageCode = Integer.parseInt(reader.nextLine());
        String language = languages.get(languageCode -1);
        System.out.println("ALL BOOKS IN: "+language);
        List<Book> books = bookRepository.findByGivenLanguage(language);
        books.forEach(System.out::println);
    }

    private void showMostDownloadedBook() {
        Book book = bookRepository.findMostDownloadedBook();
        System.out.println("Most downloaded book: \n"+book);
    }

    private void listAllBooksByAuthor() {
        System.out.println("Enter author's name: ");
        String name = reader.nextLine();
        List<Book> books = bookRepository.findByAuthorNameContainingIgnoreCase(name);
        books.forEach(System.out::println);
    }

    private void listAllBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(b -> System.out.println(b.getTitle() +" by "+ b.getAuthor().getName().toUpperCase()));
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
        name = name.contains(" ") ? name.replaceAll(" ", "+") : name;
        String json = GutendexBookApiService.getData(BASE_URL+name);
        BookResponse bookResponse = cvs.getData(json, BookResponse.class);
        if (bookResponse.books().isEmpty()) {
            System.out.println("No books found for this search.");
            return null;
        }

        Book book = new Book(bookResponse.books().get(0));
        Optional<Author> authorOptional = authorRepository.findByName(book.getAuthor().getName());

        if (authorOptional.isPresent()) {
            book.setAuthor(authorOptional.get());
        } else {
            Author savedAuthor = authorRepository.save(book.getAuthor());
            book.setAuthor(savedAuthor);
        }

        return book;
    }
}
