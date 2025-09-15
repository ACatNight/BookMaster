
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private double price;
    private int amount;

    public Book(String isbn, String title, String author, String publisher, double price, int amount) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.amount = amount;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublisher() { return publisher; }
    public double getPrice() { return price; }
    public int getAmount() { return amount; }


    public void setAmount(int amount) {
        this.amount = amount;
    }


    @Override
    public String toString() {
        // 使用 String.format 来对齐文本，使输出更美观
        return String.format("%-15s %-25s %-20s %-20s %-10.2f %-10d",
                isbn, title, author, publisher, price, amount);
    }
}