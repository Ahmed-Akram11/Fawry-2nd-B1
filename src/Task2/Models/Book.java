package Task2.Models;

public abstract class Book {
    protected String isbn;
    protected String title;
    protected int publishYear;
    protected double price;

    public Book(String isbn, String title, int publishYear, double price) {
        this.isbn = isbn;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
