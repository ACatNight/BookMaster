import java.util.Scanner;

public class Core_Model_Classes {
    public static void main(String[] args) {
        boolean Core = false;
        BookMaster bookmaster = new BookMaster();
        System.out.println("欢迎来到图书管理系统");
        System.out.println("请先登录你的账号");
        String[] usernames = {"admin", "user1", "guest"};//预设用户
        String[] passwords = {"admin123", "pass123", "guest123"};
        Scanner sc = new Scanner(System.in);
        System.out.println("请先输入用户名:");
        String inputUser = sc.nextLine(); //读取
        System.out.println("请先输入密码:");
        String inputPass = sc.nextLine();
        boolean loginSuccess = false;
        int role = -1; // -1=未登录, 0=游客, 1=管理员, 2=普通用户
        int balance = 100;//初始额度

        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(inputUser) && passwords[i].equals(inputPass)) {
                loginSuccess = true;
                if (usernames[i].equals("admin")) {
                    role = 1;
                } else if (usernames[i].equals("user1")) {
                    role = 2;
                } else {
                    role = 0;
                }
                break;
            }
        }
        if (loginSuccess) {
            System.out.println("登录成功！");
            Core = true;
        } else {
            System.out.println("用户名或密码错误！");
        }

        boolean admin_running = false;//管理员系统的开关
        boolean user1_running = false;
        boolean guest_running = false;

        // 根据 role 分配运行权限
        if (role == 1) {
            admin_running = true;
            user1_running = true;
            guest_running = true;
        } else if (role == 2) {
            user1_running = true;
        } else if (role == 0) {
            guest_running = true;
        }

        System.out.println("====================================");
        System.out.println("||||||||||||||||||||||||||||||||||||");
        System.out.println("========欢迎进入图书管理系统后台========");
        System.out.println("====================================");
        System.out.println("||||||||||||||||||||||||||||||||||||");
        int Book_size = 2;// 统一的数量
        int[] book_amount = new int[Book_size]; //书的数量
        String[] isbn = new String[Book_size]; //书籍编码
        String[] title = new String[Book_size]; //图书名字
        double[] price = new double[Book_size]; //图书价格
        int[] amount = new int[Book_size]; //书籍数量
        String[] publisher = new String[Book_size];//出版社
        String[] author = new String[Book_size]; //作者名字

        while (Core) {
            System.out.println("请选择你自己的图书管理系统");
            System.out.println("1·图书管理员系统后台");
            System.out.println("2·用户图书管理系统");
            System.out.println("3·游客图书管理系统(只能查看图书的信息)");
            System.out.println("4·退出系统)");
            int user_id = sc.nextInt();
            switch (user_id) {
                case 1:
                    if (role == 1) {//判断是否管理员
                        while (admin_running) {
                            System.out.println("1·录入图书信息");
                            System.out.println("2·删除图书信息");
                            System.out.println("3·根据出版社名称查询");
                            System.out.println("4·查询所有出版社名称");
                            System.out.println("5·额度充值");
                            System.out.println("6·返回上级菜单");
                            int admin_data = sc.nextInt();
                            sc.nextLine(); //换行

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
                                    boolean found1 = false; //标记没找到
                                    for (int i = 0; i < title.length ; i++) {
                                        System.out.println("以下是当前已有的书籍");
                                        System.out.println("i+1" + title[i]);
                                        String ForSearch_Title = sc.next();
                                        if (title[i] != null && title[i].equals(ForSearch_Title)){
                                            found1 = true;
                                            System.out.println("找到了");
                                            System.out.println("删除该书籍的信息");
                                            title[i] = null;
                                            isbn[i] = null;
                                            amount[i] = 0;
                                            publisher[i] = null;
                                            author[i] = null;
                                            price[i] = 0.0;
                                            break;
                                        }

                                    }
                                    break;
                                case 3:
                                    System.out.println("请输入你要查找出版社");
                                    boolean found = false; //标记为没找到
                                    for (int i = 0; i < publisher.length ; i++) {
                                        System.out.println((i+1) + ":" +publisher[i]);
                                    }
                                    for (int i = 0; i < publisher.length ; i++) {
                                        String ForSearch_publisher = sc.next(); //输入出版社
                                        if (publisher[i] != null && publisher[i].equals(ForSearch_publisher)) { //publisher[i] != null 变量里面的内容不能空  equals（输入的内容）对比
                                            found = true;
                                            System.out.println("找到了");
                                            System.out.println("以下是该社出版的书");
                                            System.out.println((i + 1) + title[i]);
                                        }
                                    }
                                    if (!found){
                                        System.out.println("没找到");
                                    }
                                    break;
                                case 4:
                                    System.out.println("当前现有的出版社：");
                                    for (int i = 0; i < publisher.length ; i++) {
                                        System.out.println((i+1) + ":" + publisher[i]);
                                    }
                                    break;
                                case 5:
                                    System.out.println("请输入你需要充值的额度");
                                    int money = sc.nextInt();
                                    balance = balance + money;
                                    System.out.println("已完成充值,当前额度剩余" + balance);
                                    break;
                                case 6:
                                    admin_running = false;
                                    break;
                            }
                            break;
                        }
                    } else {
                        System.out.println("权限不足 无法进入");
                    }
                    break;
                case 2:
                    if (role == 2 || role == 1) {
                        while (user1_running) {
                            System.out.println("1·查阅图书信息");
                            System.out.println("2·借阅图书");
                            System.out.println("3·额度查询");
                            System.out.println("4·返回上级菜单");
                            int user_data = sc.nextInt();
                            sc.nextLine(); //换行
                            switch (user_data) {
                                case 1:
                                    System.out.println("ISBN编码        书籍名字        作者名字        价格        出版社            书籍数量");
                                    for (int i = 0; i < title.length; i++) {
                                        if (title[i] != null) {
                                            System.out.println(isbn[i] + "        "
                                                    + title[i] + "        "
                                                    + author[i] + "        "
                                                    + price[i] + "        "
                                                    + publisher[i] + "        "
                                                    + amount[i]);
                                        }
                                    }
                                    break;
                                case 2:
                                    System.out.println("以下当前可借的书籍");
                                    System.out.println("书籍名字   书籍数量");
                                    boolean found2 = false;
                                    for (int i = 0; i < title.length; i++) {
                                        if (title[i] != null) {
                                            System.out.println(title[i] + "\t" + amount[i]);
                                        }
                                    }
                                    String ForSearch_Title = sc.next();
                                    for (int i = 0; i < title.length ; i++) {
                                        System.out.println("请输入你要借阅的书籍,以及数量:");
                                        if (title[i] != null && title[i].equals(ForSearch_Title)){
                                            found2 = true;
                                            System.out.println("已找到");
                                            System.out.println("请输入借阅的数量");
                                            int title_amount = sc.nextInt();
                                            if (amount[i] > title_amount) {
                                                amount[i] = amount[i] - title_amount;
                                                System.out.println("已经借当前的" + title_amount);
                                            }else {
                                                System.out.println("已超过数量啦");
                                            }
                                        }
                                        if (!found2){
                                            System.out.println("未找到该书籍信息");
                                        }
                                    }
                                case 3:
                                    System.out.println("您当前的额度为:" + balance);


                                    break;
                                case 4:
                                    user1_running = false;
                                    break;
                            }
                        }
                    }
                    break;
                case 3:
                    if (role == 0 || role == 1) {
                        while (guest_running) {
                            int guest_data = sc.nextInt();
                            System.out.println("1·查看当前图书信息");
                            System.out.println("2·返回上级菜单");
                            switch (guest_data) {
                                case 1:
                                    System.out.println("ISBN编码        书籍名字        作者名字        价格        出版社            书籍数量");
                                    for (int i = 0; i < title.length; i++) {
                                        if (title[i] != null) {
                                            System.out.println(isbn[i] + "        "
                                                    + title[i] + "        "
                                                    + author[i] + "        "
                                                    + price[i] + "        "
                                                    + publisher[i] + "        "
                                                    + amount[i]);
                                        }
                                    }

                                case 2:
                                    guest_running = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("你不是游客用户，无法进入当前的系统");
                    }
                    break;
                case 4:
                    Core = false;
                    System.out.println("欢迎使用图书管理系统");
            }
        }
    }
}