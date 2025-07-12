package com.books.LiterAlura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner reader = new Scanner(System.in);
    private final String BASE_URL = "https://gutendex.com/books/?search=";
    public void showMenu(){
        String menu = """
                0 - Exit
                1 - Search a book
                >
                """;
        int option = -1;

        while(option != -1){
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
    }
}
