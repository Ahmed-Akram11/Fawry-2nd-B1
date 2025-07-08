package Task2.Models;

import Task2.Interfaces.Emailable;
import Task2.Interfaces.ForSale;
import Task2.Services.MailService;

public class EBook extends Book implements Emailable, ForSale {
    private String fileType;

    public EBook(String isbn, String title, int publishYear, double price, String fileType) {
        this(isbn, title, publishYear, price);
        this.fileType = fileType;
    }

    public EBook(String isbn, String title, int publishYear, double price) {
        super(isbn, title, publishYear, price);
    }

    @Override
    public void email(String email) {

    }

    @Override
    public double buy(String isbn, int quantity, String email, String address) {
        if (quantity < 0) {
            throw new IllegalArgumentException("quantity can't be negative");
        }
        return getPrice() * quantity;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
