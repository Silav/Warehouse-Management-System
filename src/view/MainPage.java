package view;

import Controller.Admin_Management;
import Controller.Inventory_Management;
import Controller.Order_Management;
import Controller.Transaction_Management;
import Controller.User_Management;
import java.util.Scanner;

public class MainPage {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        User_Management.get_All_Users_from_File();
        Inventory_Management.get_Products_From_File();
        Transaction_Management.fetch_all_Transactions_from_File();
        Order_Management.fetch_all_Orders_from_File("Order_List.txt");
        Order_Management.fetch_all_Orders_from_File("Ordered_Items.txt");

        while (true) {
            System.out.println("\t\t\t-----Select the User Type-----");
            System.out.println("1. Cashier\t\t2. Manager\t\t3. Admin\t\t4. Close System");
            System.out.print("Enter User Type  :   ");
            String opt = input.next();

            if (opt.equals("1")) {
                if (User_Management.User_Login(true)) {
                    Cashier_Menu.cashier_Menu();
                }
            } else if (opt.equals("2")) {
                if (User_Management.User_Login(false)) {
                    Manager_Menu.manager_Menu();
                }
            } else if (opt.equals("3")) {
                if (Admin_Management.admin_Login()) {
                    Admin_Menu.admin_Menu();
                }
            } else if (opt.equals("4")) {
                ClearConsole();
                clearScreen();
                System.exit(0);
            } else {
                System.out.println("Enter choice from above menu");
            }
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void ClearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
