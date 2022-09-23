package view;

import Controller.Inventory_Management;
import Controller.Order_Management;
import Controller.Transaction_Management;
import static view.MainPage.ClearConsole;
import static view.MainPage.clearScreen;
import static view.MainPage.input;

public class Cashier_Menu {

    public static void cashier_Menu() {
        System.out.println("\t\t\t-----Welcome to Cashier Menu-----");
        while (true) {

            System.out.println("1. View inventory\n2. Sale an item\n3. Return an item\n4. Make an order of items\n5. View all sold out products.\n6. Return to main menu");
            input.nextLine();
            System.out.print("\nEnter Option  :   ");
            String opt = input.next();
            ClearConsole();
            clearScreen();

            if (opt.equals("1")) {
                Inventory_Management.view_Inventory();
            } else if (opt.equals("2")) {
                Inventory_Management.sale_Item();
            } else if (opt.equals("3")) {
                Transaction_Management.return_an_Item();
            } else if (opt.equals("4")) {
                Order_Management.make_an_Order();
            } else if (opt.equals("5")) {
                Inventory_Management.view_all_Sold_out_Products();
            } else if (opt.equals("6")) {
                break;
            } else {
                System.out.println("Enter choice from above menu");
            }
        }
    }

}
