package com.books.LiterAlura.principal;

import com.books.LiterAlura.model.Book;
import com.books.LiterAlura.model.BookData;
import com.books.LiterAlura.model.BookResponse;
import com.books.LiterAlura.service.Converter;
import com.books.LiterAlura.service.GutendexBookApiService;

import java.util.Scanner;

public class Principal {
    private Converter cvs = new Converter();
    private Scanner reader = new Scanner(System.in);
    private final String BASE_URL = "https://gutendex.com/books/?search=";
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
        getBookData();
    }

    private Book getBookData(){
        System.out.println("Enter a book: ");
        String name = reader.nextLine();
        String json = GutendexBookApiService.getData(BASE_URL+name);
        BookResponse bookResponse = cvs.getData(json, BookResponse.class);
        BookData book = bookResponse.books().get(0);
        return new Book(book);
    }
}
