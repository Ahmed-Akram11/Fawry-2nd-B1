package Task2.Models;

import Task2.Interfaces.ForSale;
import Task2.Interfaces.Shippable;
import Task2.Services.ShippingService;

public class PaperBook extends Book implements Shippable, ForSale {
    private int stockNumber;

    public PaperBook(String isbn, String title, int publishYear, double price, int stockNumber) {
        this(isbn, title, publishYear, price);
        this.stockNumber = stockNumber;
    }

    public PaperBook(String isbn, String title, int publishYear, double price) {
        super(isbn, title, publishYear, price);
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        if (stockNumber < 0) {
            throw new IllegalArgumentException("quantity in stock can't be negative");
        }
        this.stockNumber = stockNumber;
    }

    @Override
    public void ship(String address) {

    }

    @Override
    public double buy(String isbn, int quantity, String email, String address) {
        if (stockNumber < quantity) {
            throw new IllegalArgumentException("can't buy this book , out of stock");
        }
        stockNumber -= quantity;
        return getPrice() * quantity;
    }

}
