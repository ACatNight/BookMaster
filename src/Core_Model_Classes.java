import java.util.Scanner;

public class Core_Model_Classes {
    public static void main(String[] args) {
        boolean running = false;
        BookMaster bookmaster = new BookMaster();
        System.out.println("欢迎来到图书管理系统");
        System.out.println("请先登录你的账号");
        String[] usernames = {"admin", "user1", "guest"};
        String[] passwords = {"admin123", "pass123", "guest123"};
        Scanner sc = new Scanner(System.in);
        System.out.println("请先输入用户名:");
        String inputUser = sc.nextLine(); //读取
        System.out.println("请先输入密码:");
        String inputPass = sc.nextLine();
        boolean loginSuccess = false;
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(inputUser) && passwords[i].equals(inputPass)) {
                loginSuccess = true;
                break;
            }
        }
        if (loginSuccess) {
            System.out.println("登录成功！");
            running = true;
        } else {
            System.out.println("用户名或密码错误！");
        }
        boolean admin_running = false;
        boolean user1_running = false;
        boolean guest_running = false;

        int admin_lp = 0;
        int user1_lp = 0;
        int guest_lp = 0; //权限判断

        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals("admin")) {//判断用户
                admin_lp = 1; //分配管理员权限
                admin_running = true;
            } else if (usernames[i].equals("user1")) {
                user1_lp = 1;
                user1_running = true;
            } else {
                guest_lp = 1;
                guest_running = true;
            }

        }
        System.out.println("====================================");
        System.out.println("||||||||||||||||||||||||||||||||||||");
        System.out.println("========欢迎进入图书管理系统后台========");
        System.out.println("====================================");
        System.out.println("||||||||||||||||||||||||||||||||||||");
        int Book_size = 2;// 统一的数量
        int [] book_amount = new int[Book_size]; //书的数量
        String [] isbn = new String[Book_size]; //书籍编码
        String [] title = new String[Book_size]; //图书名字
        double [] price = new double[Book_size]; //图书价格
        int [] amount = new int[Book_size]; //书籍数量
        String [] publisher = new String[Book_size];//出版社
        String [] author = new String[Book_size]; //作者名字

        System.out.println("请选择你自己的图书管理系统");
        int user_id = sc.nextInt();
        System.out.println("1·图书管理员系统后台");
        System.out.println("2·用户图书管理系统");
        System.out.println("3·游客图书管理系统(只能查看图书的信息)");
        switch (user_id){
            case 1:
                if (admin_lp == 1) {//判断用户
                    while (admin_running) {
                        int admin_data = sc.nextInt();
                        sc.nextLine(); //换行
                        System.out.println("1·录入图书信息");
                        System.out.println("2·删除图书信息");
                        System.out.println("3·根据出版社名称查询");
                        System.out.println("4·查询所有出版社名称");
                        System.out.println("5·返回上级菜单");

                        switch (admin_data) {
                            case 1:
                                for (int i = 0; i < book_amount.length; i++) {
                                    System.out.println("请先录入当前第" + (i + 1) + "个书名的ISBN");
                                    bookmaster.set_Isbn(sc.next());
                                    String get_isbn = bookmaster.get_Isbn();
                                    isbn[i] = get_isbn;
                                    System.out.println("当前ISBN已存储完毕");
                                    System.out.println("请先录入当前第" + (i + 1) + "本书籍名称");
                                    bookmaster.set_Title(sc.next());
                                    String get_Title = bookmaster.get_Title();
                                    title[i] = get_Title;
                                    System.out.println("当前书籍名字已存储完毕");
                                    System.out.println("请先录入当前第" + (i + 1) + "本书籍的价格");
                                    bookmaster.set_Price(sc.nextDouble());
                                    double get_Price = bookmaster.getPrice();
                                    price[i] = get_Price;
                                    System.out.println("当前书籍价格已存储完毕");
                                    System.out.println("请先录入当前第" + (i + 1) + "本书籍的出版社");
                                    bookmaster.set_Publisher(sc.next());
                                    String get_publisher = bookmaster.get_Publisher();
                                    publisher[i] = get_publisher;
                                    System.out.println("当前出版社已存储完毕");
                                    System.out.println("请先录入当前第" + (i + 1) + "本书籍的作者");
                                    bookmaster.set_Author(sc.next());
                                    String get_Author = bookmaster.get_Author();
                                    author[i] = get_Author;
                                    System.out.println("当前书籍作者已存储完毕");
                                    System.out.println("请先录入当前第" + (i + 1) + "本书籍的数量");
                                    bookmaster.setAmount(sc.nextInt());
                                    int get_Amount = bookmaster.getAmount();
                                    amount[i] = get_Amount;
                                    System.out.println("当前书籍数量存储完毕");
                                }
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                admin_running = false;
                                break;

                        }
                        break;

                    }

                }else {
                    System.out.println("权限不足 无法进入");
                }
            case 2:
                if (user1_lp == 1 || admin_lp == 1){
                    while (user1_running){
                        int user_data = sc.nextInt();
                        sc.nextLine(); //换行
                        System.out.println("1·查阅图书信息");
                        System.out.println("2·借阅图书");
                        System.out.println("3·返回上级菜单");
                        switch (user_data){
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;

                        }
                    }
                }
            case 3:
                if (guest_lp == 1){
                    while (guest_running){

                    }
                }else {
                    System.out.println("你不是游客用户，无法进入当前的系统");
                }
                break;
        }
    }
}







