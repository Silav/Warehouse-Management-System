package Controller;

import Enums.User_Type;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.User;

public class User_Management {

    static Scanner input = new Scanner(System.in);
    static ArrayList<User> users = new ArrayList<>();

    public static boolean User_Login(boolean check) {

        ArrayList<User> array;

        if (check) {
            array = get_All_Cashiers(users);
        } else {
            array = get_All_Managers(users);
        }

        System.out.println("User name doesn't contain spaces");
        System.out.print("Enter User name  :   ");
        String user_name = input.next();

        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
        User user = getUser_with_Username(array, user_name);
        if (user == null) {
            System.out.println("No user with such user name");
            return false;
        } else {
            System.out.println("Welcome  "+user.getName());
            System.out.print("Enter password  :   ");
            String password = input.next();

            if (user.getPassword().equals(password)) {
                System.out.println("password matched");
                return true;
            } else {
                System.out.println("wrong password");
                return false;
            }
        }
    }

    public static User getUser(String name) {
        for (int i = 0; i < users.size(); i++) {
            User obj = users.get(i);
            if (obj.getName().equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }

    public static User getUser_with_Username(ArrayList<User> users, String user_name) {
        for (int i = 0; i < users.size(); i++) {
            User obj = users.get(i);
            if (obj.getUser_name().equalsIgnoreCase(user_name)) {
                return obj;
            }
        }
        return null;
    }

    public static void add_User() {
        User_Type user_type;

        while (true) {
            System.out.println("Select User type  :   ");
            System.out.println("1. Cashier\t\t\t2. Manager");
            String opt = input.next();
            if (opt.equalsIgnoreCase("1") || opt.equalsIgnoreCase("2")) {
                if (opt.equalsIgnoreCase("1")) {
                    user_type = User_Type.Cashier;
                } else {
                    user_type = User_Type.Manager;
                }
                break;
            } else {
                System.out.println("Enter from above choice");
            }
            System.out.println("");
        }
        if (user_type == User_Type.Cashier) {
            System.out.println("Enter Cashier Details");
        } else {
            System.out.println("Enter Manager Details");
        }

        input.nextLine();
        System.out.print("Enter Full name  :   ");
        String full_name = input.nextLine();
        System.out.print("Enter User name (without spaces) :   ");
        String user_name = input.next();
        input.nextLine();
        System.out.print("Enter password :   ");
        String pass = input.nextLine();
        User new_user = new User(full_name, full_name, pass, user_type);

        try {
            Thread.sleep(500);
            new Thread(() -> {
                try {
                    FileWriter writer = new FileWriter("Users.txt", true);
                    System.out.println(full_name.trim());
                    String updated_full_name = full_name.replace(' ', '_');
                    String modified_password = pass.replace(' ', '_');
                    writer.append(updated_full_name + " " + user_name.trim() + " " + modified_password + " " + user_type + "\n");
                    writer.close();
                    System.out.println("User added");
                    users.add(new User(full_name,user_name,pass,user_type));
                } catch (Exception ex) {
                    System.out.println("Error in saving data to Users file");
                }
            }).start();
            Thread.sleep(500);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public static void get_All_Users_from_File() {
        try {
            users.clear();
            File file_name = new File("Users.txt");
            Scanner file_obj = new Scanner(file_name);
            while (file_obj.hasNextLine()) {
                String line = file_obj.nextLine();
                String[] splittedData = line.split(" ");
                User user = new User();
                user.setName(splittedData[0].replace('_', ' '));
                user.setUser_name(splittedData[1]);
                user.setPassword(splittedData[2].replace('_', ' '));
                if (splittedData[3].equalsIgnoreCase("Manager")) {
                    user.setType(User_Type.Manager);
                } else {
                    user.setType(User_Type.Cashier);
                }
                users.add(user);
            }
            file_obj.close();
        } catch (Exception ex) {
            System.out.println("Error in reading Users.txt file");
        }
    }

    public static void view_Users() {
        if (users.isEmpty()) {
                get_All_Users_from_File();
        } else {
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                System.out.println(user.toString());
            }
        }
    }

    public static void pay_salary() {
        System.out.println("All Employees:");
        for (User user : users) {
            System.out.println("Employee Name  :  " + user.getName());
        }
        System.out.print("Enter Employee Name  :   ");
        String name = input.nextLine();
        User usr = getUser(name);
        if (usr == null) {
            System.out.println("No employee with this name.");
        } else {
            while (true) {
                System.out.print("Enter salary amount $ :   ");
                String amount = input.next();
                if (Inventory_Management.isInteger(amount)) {
                    break;
                } else {
                    System.out.println("please enter salary correctly");
                }
            }
            try {
                Thread.sleep(600);
                System.out.println("Salary is been paid successfully");
            } catch (Exception e) {
            }
        }
    }

    public static ArrayList<User> get_All_Managers(ArrayList<User> users) {
        ArrayList<User> managers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getType() == User_Type.Manager) {
                managers.add(user);
            }
        }
        return managers;
    }

    public static ArrayList<User> get_All_Cashiers(ArrayList<User> users) {
        ArrayList<User> cashiers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getType() == User_Type.Cashier) {
                cashiers.add(user);
            }
        }
        return cashiers;
    }

}
