package Task2;

import Task2.Models.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Add books
        Book paperBook1 = new PaperBook("P1", "paperBook1", 2025, 49.99, 5);
        Book paperBook2 = new PaperBook("P2", "paperBook2", 2012, 59.99, 3);
        Book ebook1 = new EBook("E1", "ebook1", 2023, 19.99, "PDF");
        Book demoBook = new DemoBook("S1", "demoBook", 1995, 10);

        inventory.addBook(paperBook1);
        inventory.addBook(paperBook2);
        inventory.addBook(ebook1);
        inventory.addBook(demoBook);


        // Buy paper book
        try {
            double amount = inventory.buySingleBook("P1", 2, "1@gmail.com", "123 Street");
            System.out.println("Paid $" + amount + " for PaperBook.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Buy eBook
        try {
            double amount = inventory.buySingleBook("E1", 1, "2@gmail.com", "");
            System.out.println("Paid $" + amount + " for EBook.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // buy demoBook (can't buy this book)
        try {
            inventory.buySingleBook("S1", 1, "3@gmail.com", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ArrayList<Book> removedBooks = inventory.remove(10);
        System.out.println("Removed Books : ");
        for (Book book : removedBooks) {
            System.out.println("book :  " + book.getTitle() + " ," + book.getPublishYear());
        }
        // paid Books
        inventory.paidBooks();


        System.out.println("case : book is already sold");
        try {
            double amount = inventory.buySingleBook("P1", 10, "1@gmail.com", "123 Street");
            System.out.println("Paid $" + amount + " for PaperBook.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("case : book is out of stock");
        try {
            inventory.addBook(paperBook1);
            double amount = inventory.buySingleBook("P1", 10, "1@gmail.com", "123 Street");
            System.out.println("Paid $" + amount + " for PaperBook.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
