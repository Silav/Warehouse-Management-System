package view;

import Controller.Inventory_Management;
import Controller.Order_Management;
import static view.MainPage.ClearConsole;
import static view.MainPage.clearScreen;

public class Manager_Menu {

    public static void manager_Menu() {
        System.out.println("\t\t\t-----Welcome to Manager Menu-----");
        while (true) {
            System.out.println("1. View inventory\n"
                    + "2. View order list\n"
                    + "3. Make an order of items\n"
                    + "4. Return to main menu");
            System.out.print("\nEnter Option  :   ");
            String opt = MainPage.input.next();
            ClearConsole();
            clearScreen();
            if (opt.equals("1")) {
                Inventory_Management.view_Inventory();
            } else if (opt.equals("2")) {
                Order_Management.view_all_Orders();
            } else if (opt.equals("3")) {
                Order_Management.Order_the_Item();
            } else if (opt.equals("4")) {
                break;
            } else {
                System.out.println("Enter choice from above menu");
            }
        }
    }
}
