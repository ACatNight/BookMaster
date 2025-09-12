public class BookMaster {
    private String department; //部门
    private String isbn; //书籍编码
    private String title; //书籍名字
    private double price; //书籍价格
    private int amount;
    private String publisher; //出版社
    private String author; //作者名字

    public void set_department(String department){

        this.department = department;
    }
    public String get_Departments(){

        return department;
    }


    public void set_Isbn(String isbn) {
        this.isbn = isbn;
    }

    public String get_Isbn() {

        return isbn;
    }
    public void set_Title(String title) {

        this.title = title;
    }

    public String get_Title() {

        return title;
    }

    public void set_Price(double price) {
        if (price >= 0){
            this.price = price;
        }else {
            System.out.println("非法数据");
        }


    }

    public double getPrice() {
        return price;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return amount;
    }

    public void set_Publisher(String publisher) {

        this.publisher = publisher;
    }

    public String get_Publisher() {

        return publisher;
    }

    public void set_Author(String author) {

        this.author = author;
    }

    public String get_Author() {

        return author;
    }
}
