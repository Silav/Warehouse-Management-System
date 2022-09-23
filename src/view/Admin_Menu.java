package view;

import Controller.Admin_Management;
import Controller.Inventory_Management;
import Controller.Order_Management;
import Controller.Transaction_Management;
import Controller.User_Management;
import java.util.Scanner;
import static view.MainPage.ClearConsole;
import static view.MainPage.clearScreen;

public class Admin_Menu {

    static Scanner input = new Scanner(System.in);

    public static void admin_Menu() {
        System.out.println("\t\t\t-----Welcome to Admin Menu-----");
        while (true) {
            System.out.println("1. View inventory\n"
                    + "2. Add product\n"
                    + "3. View finalized order list\n"
                    + "4. View best selling product statistics\n"
                    + "5. View all transactions\n"
                    + "6. Pay salaries\n"
                    + "7. View all employees\n"
                    + "8. Add new User\n"
                    + "9. Remove order from order list\n"
                    + "10. Update password\n"
                    + "11. Return to main menu");
            System.out.print("Enter Option  :   ");
            String opt = input.next();
            ClearConsole();
            clearScreen();
            if (opt.equals("1")) {
                Inventory_Management.view_Inventory();
            } else if (opt.equals("2")) {
                Inventory_Management.add_Product();
            } else if (opt.equals("3")) {
                Order_Management.view_finalized_Orders();
            } else if (opt.equals("4")) {
                System.out.println("Best Selling Products Statistics : ");
                Transaction_Management.best_Selling_Products();
            } else if (opt.equals("5")) {
                Transaction_Management.view_all_Transactions();
            } else if (opt.equals("6")) {
                User_Management.pay_salary();
            } else if (opt.equals("7")) {
                try {
                    Thread.sleep(600);
                    User_Management.view_Users();
                } catch (Exception e) {
                }
            } else if (opt.equals("8")) {
                User_Management.add_User();
            } else if (opt.equals("9")) {
                Order_Management.remove_finalized_Order();
            } else if (opt.equals("10")) {
                Admin_Management.update_Password();
            } else if (opt.equals("11")) {
                break;
            } else {
                System.out.println("Enter choice from above menu");
            }
        }
    }
}
