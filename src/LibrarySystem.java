import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet; // 用来高效地获取不重复的出版社名称

public class LibrarySystem {


    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Book> library = new ArrayList<>();
    private static int balance = 100; // 初始额度
    private static int role = -1; // -1=未登录, 0=游客, 1=管理员, 2=普通用户

    // 预设的用户和密码
    private static final String[] usernames = {"admin", "user1", "guest"};
    private static final String[] passwords = {"admin123", "pass123", "guest123"};

    public static void main(String[] args) {
        // 添加一些初始数据方便测试
        initializeLibrary();

        System.out.println("欢迎来到图书管理系统");
        System.out.println("请先登录你的账号");

        if (!login()) {
            System.out.println("登录失败，程序退出。");
            return; // 如果登录失败，直接退出程序
        }

        boolean isRunning = true;
        while (isRunning) {
            showMainMenu();
            int choice = getIntInput();
            sc.nextLine(); // 消耗掉 getIntInput 后留下的换行符

            switch (choice) {
                case 1:
                    runAdminSystem();
                    break;
                case 2:
                    runUserSystem();
                    break;
                case 3:
                    runGuestSystem();
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("感谢使用图书管理系统，再见！");
                    break;
                default:
                    System.out.println("无效选择，请输入1-4之间的数字。");
            }
        }
    }

    // --- 登录与菜单显示方法 ---

    private static boolean login() {
        System.out.println("请输入用户名:");
        String inputUser = sc.nextLine();
        System.out.println("请输入密码:");
        String inputPass = sc.nextLine();

        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(inputUser) && passwords[i].equals(inputPass)) {
                System.out.println("登录成功！");
                // 根据用户名分配角色
                if ("admin".equals(usernames[i])) role = 1;
                else if ("user1".equals(usernames[i])) role = 2;
                else role = 0;
                return true;
            }
        }
        System.out.println("用户名或密码错误！");
        return false;
    }

    private static void showMainMenu() {
        System.out.println("\n========欢迎进入图书管理系统后台========");
        System.out.println("1. 图书管理员系统后台");
        System.out.println("2. 用户图书管理系统");
        System.out.println("3. 游客图书管理系统");
        System.out.println("4. 退出系统");
        System.out.print("请选择: ");
    }

    // --- 各个子系统的运行逻辑 ---

    private static void runAdminSystem() {
        if (role != 1) { // 权限判断
            System.out.println("权限不足，无法进入！");
            return;
        }
        boolean adminRunning = true;
        while (adminRunning) {
            System.out.println("\n--- 管理员菜单 ---");
            System.out.println("1. 录入图书信息");
            System.out.println("2. 删除图书信息");
            System.out.println("3. 根据出版社名称查询");
            System.out.println("4. 查询所有出版社名称");
            System.out.println("5. 额度充值");
            System.out.println("6. 返回上级菜单");
            System.out.print("请选择: ");
            int choice = getIntInput();
            sc.nextLine(); // 消耗换行符

            switch (choice) {
                case 1: addBook(); break;
                case 2: deleteBook(); break;
                case 3: findBooksByPublisher(); break;
                case 4: showAllPublishers(); break;
                case 5: rechargeBalance(); break;
                case 6: adminRunning = false; break;
                default: System.out.println("无效选择。");
            }
        }
    }

    private static void runUserSystem() {
        if (role != 1 && role != 2) { // 管理员和普通用户都可以进入
            System.out.println("权限不足，无法进入！");
            return;
        }
        boolean userRunning = true;
        while (userRunning) {
            System.out.println("\n--- 用户菜单 ---");
            System.out.println("1. 查阅所有图书信息");
            System.out.println("2. 借阅图书");
            System.out.println("3. 额度查询");
            System.out.println("4. 返回上级菜单");
            System.out.print("请选择: ");
            int choice = getIntInput();
            sc.nextLine(); // 消耗换行符

            switch (choice) {
                case 1: displayAllBooks(); break;
                case 2: borrowBook(); break;
                case 3: System.out.println("您当前的额度为: " + balance); break;
                case 4: userRunning = false; break;
                default: System.out.println("无效选择。");
            }
        }
    }

    private static void runGuestSystem() {
        if (role != 1 && role != 0) { // 管理员和游客都可以进入
            System.out.println("权限不足，无法进入！");
            return;
        }
        boolean guestRunning = true;
        while(guestRunning) {
            System.out.println("\n--- 游客菜单 ---");
            System.out.println("1. 查看当前图书信息");
            System.out.println("2. 返回上级菜单");
            System.out.print("请选择: ");
            int choice = getIntInput();
            sc.nextLine(); // 消耗换行符

            switch(choice) {
                case 1: displayAllBooks(); break;
                case 2: guestRunning = false; break;
                default: System.out.println("无效选择。");
            }
        }
    }

    // --- 功能实现方法 ---

    private static void displayAllBooks() {
        if (library.isEmpty()) {
            System.out.println("图书馆目前没有藏书。");
            return;
        }
        System.out.println("\n------------------------------------- 图书列表 -----------------------------------------");
        System.out.printf("%-15s %-25s %-20s %-20s %-10s %-10s%n",
                "ISBN", "书名", "作者", "出版社", "价格", "数量");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Book book : library) {
            System.out.println(book.toString());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    private static void addBook() {
        System.out.println("请输入ISBN:");
        String isbn = sc.nextLine();
        System.out.println("请输入书籍名称:");
        String title = sc.nextLine();
        System.out.println("请输入作者:");
        String author = sc.nextLine();
        System.out.println("请输入出版社:");
        String publisher = sc.nextLine();
        System.out.println("请输入价格:");
        double price = getDoubleInput();
        System.out.println("请输入数量:");
        int amount = getIntInput();
        sc.nextLine(); // 消耗换行符

        library.add(new Book(isbn, title, author, publisher, price, amount));
        System.out.println("《" + title + "》已成功录入！");
    }

    private static void deleteBook() {
        System.out.println("请输入要删除的书籍名称:");
        String titleToDelete = sc.nextLine();

        Book bookFound = null;
        // 修正后的查找逻辑：先找到要操作的对象
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(titleToDelete)) { // equalsIgnoreCase忽略大小写
                bookFound = book;
                break; // 找到后就跳出循环
            }
        }

        // 循环结束后再进行操作
        if (bookFound != null) {
            library.remove(bookFound); // 直接从ArrayList中移除对象
            System.out.println("书籍《" + titleToDelete + "》已成功删除。");
        } else {
            System.out.println("未找到名为《" + titleToDelete + "》的书籍。");
        }
    }

    private static void findBooksByPublisher() {
        System.out.println("请输入要查找的出版社:");
        String publisherToFind = sc.nextLine();
        boolean found = false;
        System.out.println("\n查询结果:");
        for (Book book : library) {
            if (book.getPublisher().equalsIgnoreCase(publisherToFind)) {
                System.out.println(" - " + book.getTitle() + " (作者: " + book.getAuthor() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("未找到该出版社的任何书籍。");
        }
    }

    private static void showAllPublishers() {
        // 使用 HashSet 自动处理重复的出版社名称
        HashSet<String> publishers = new HashSet<>();
        for (Book book : library) {
            publishers.add(book.getPublisher());
        }

        if (publishers.isEmpty()) {
            System.out.println("系统中没有书籍，无法查询出版社。");
            return;
        }

        System.out.println("当前所有出版社列表:");
        for (String pub : publishers) {
            System.out.println(" - " + pub);
        }
    }

    private static void borrowBook() {
        displayAllBooks();
        System.out.println("请输入你要借阅的书籍名称:");
        String titleToBorrow = sc.nextLine();

        Book bookFound = null;
        // 同样，先找到目标书籍
        for (Book book : library) {
            if (book.getTitle().equalsIgnoreCase(titleToBorrow)) {
                bookFound = book;
                break;
            }
        }

        if (bookFound != null) {
            System.out.println("找到书籍:《" + bookFound.getTitle() + "》，当前库存: " + bookFound.getAmount());
            System.out.println("请输入借阅的数量:");
            int borrowAmount = getIntInput();
            sc.nextLine(); // 消耗换行符

            if (borrowAmount <= 0) {
                System.out.println("借阅数量必须大于0。");
            } else if (bookFound.getAmount() >= borrowAmount) {
                // 更新书籍对象的数量
                bookFound.setAmount(bookFound.getAmount() - borrowAmount);
                System.out.println("成功借阅 " + borrowAmount + " 本《" + bookFound.getTitle() + "》。");
                System.out.println("剩余库存: " + bookFound.getAmount());
            } else {
                System.out.println("库存不足，借阅失败！");
            }
        } else {
            System.out.println("未找到名为《" + titleToBorrow + "》的书籍。");
        }
    }

    private static void rechargeBalance() {
        System.out.println("请输入你需要充值的额度:");
        int money = getIntInput();
        sc.nextLine(); // 消耗换行符
        if (money > 0) {
            balance += money;
            System.out.println("已完成充值，当前额度剩余: " + balance);
        } else {
            System.out.println("充值金额必须大于0。");
        }
    }

    // --- 工具方法 ---

    private static void initializeLibrary() {
        library.add(new Book("978-0321765723", "数据结构", "我", "牛批出版社", 69.99, 5));
        library.add(new Book("978-0132350884", "操作系统", "你", "不牛皮出版社", 45.50, 3));
        library.add(new Book("978-0134685991", "计算机网络", "他", "出版出版社", 55.00, 7));
    }

    // 保险输入方法，防止用户输入非数字时程序崩溃
    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.println("输入无效，请输入一个整数:");
            sc.next(); // 丢弃无效的输入
        }
        return sc.nextInt();
    }

    // 获取小数输入的方法
    private static double getDoubleInput() {
        while (!sc.hasNextDouble()) {
            System.out.println("输入无效，请输入一个数字:");
            sc.next(); // 丢弃无效的输入
        }
        return sc.nextDouble();
    }
}