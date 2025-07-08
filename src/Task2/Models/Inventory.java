package Task2.Models;

import Task2.Interfaces.ForSale;
import Task2.Services.MailService;
import Task2.Services.ShippingService;

import java.time.LocalDate;
import java.util.*;

public class Inventory {
    private Map<Integer, ArrayList<Book>> books = new HashMap<>();
    private NavigableSet<Integer> years = new TreeSet<>();
    private Map<String, Book> booksWithIsbn = new HashMap<>();
    private ArrayList<Book> paidBooks = new ArrayList<>();

    public void addBook(Book book) {
        ArrayList<Book> currentBooks = books.get(book.getPublishYear());
        if (currentBooks == null) {
            ArrayList<Book> cur = new ArrayList<>();
            cur.add(book);
            books.put(book.getPublishYear(), cur);
        } else {
            currentBooks.add(book);
        }
        years.add(book.getPublishYear());
        booksWithIsbn.put(book.getIsbn(), book);
    }


    public ArrayList<Book> remove(int outDateYears) {
        ArrayList<Book> toRemove = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        int diff = currentYear - outDateYears;

        NavigableSet<Integer> st = years.headSet(diff, false);

        for (Integer i : st) {
            toRemove.addAll(books.get(i));
            removeBooks(i);
        }

        st.clear();
        return toRemove;
    }

    public double buySingleBook(String isbn, int quantity, String email, String address) {
        Book book = booksWithIsbn.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException("book not in inventory");
        }
        double total = 0;
        if (book instanceof ForSale) {
            if (book instanceof PaperBook) {
                ShippingService.Shipping(address);
                total = ((PaperBook) book).buy(isbn, quantity, email, address);
            } else {
                MailService.EmailAddress(email);
                total = ((EBook) book).buy(isbn, quantity, email, address);
            }
            paidBooks.add(book);
            removeBook(book);
        } else {
            throw new IllegalArgumentException("can't by this book");
        }

        return total;
    }

    public void paidBooks() {
        if (paidBooks.isEmpty()) {
            System.out.println("No books have been purchased yet.");
            return;
        }
        System.out.println("Paid Books:" + paidBooks.toArray().length);
        for (Book book : paidBooks) {
            System.out.println("Book details: " +
                    "Title: " + book.getTitle() +
                    ", ISBN: " + book.getIsbn() +
                    ", Year: " + book.getPublishYear() +
                    ", Price: " + book.getPrice());
        }

    }

    private void removeBooks(int year) {
        ArrayList<Book> arr = books.get(year);
        arr.clear();
    }

    private void removeBook(Book book) {
        int year = book.getPublishYear();
        String isbn = book.getIsbn();
        booksWithIsbn.remove(isbn);
        ArrayList<Book> arr = books.get(year);
        arr.remove(book);
        if (arr.isEmpty()) {
            years.remove(year);
            books.remove(year);
        }
    }

}
