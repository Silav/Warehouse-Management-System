package Controller;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Admin_Management {

    static Scanner input = new Scanner(System.in);
    static String admin_user_name = "admin";
    static String saved_password = "";

    public static boolean admin_Login() {
        System.out.println("User name must not contain spaces");
        System.out.print("Enter username  :  ");
        String user_name = input.next();

        if (!user_name.equalsIgnoreCase(admin_user_name)) {
            System.out.println("invalid user name");
            System.out.println("User name is  " + admin_user_name);
            return false;
        } else {
            System.out.println("User name matched");
            input.nextLine();
            System.out.print("Enter password  :   ");
            String password = input.nextLine();
            saved_password = get_Admin_Password_from_File();
            if (password.equals(saved_password)) {
                System.out.println("Password matched");
                System.out.println("Login Successfull");
                return true;
            } else {
                System.out.println("invalid password");
                return false;
            }
        }
    }

    public static String get_Admin_Password_from_File() {
        String password = "";
        try {
            File file_name = new File("Admin_password.txt");
            Scanner file_obj = new Scanner(file_name);
            while (file_obj.hasNextLine()) {
                password = file_obj.nextLine();
            }
            file_obj.close();
        } catch (Exception e) {
            System.out.println("Error in reading admin password");
        }
        String modified_password = password.replace('_', ' ');
        return modified_password;
    }

    public static void update_Admin_Password_in_File(String password) {
        try {
            FileWriter writer = new FileWriter("Admin_password.txt");
            String modified_password = password.replace(' ', '_');
            writer.append(modified_password);
            writer.close();
            System.out.println("Successfully updated password");
        } catch (Exception e) {
            System.out.println("Error in updating password");
        }
    }

    public static void update_Password() {
        System.out.print("Enter the old password  :   ");
        String password = input.nextLine();
        String old_password = get_Admin_Password_from_File();

        if (!password.equals(old_password)) {
            System.out.println("old password didn't match");
        } else {

            System.out.println("Old password matched");
            System.out.println("Password is Case Sensitive. White spaces count");
            System.out.println("Enter new password  :   ");
            String new_password = input.nextLine();
            update_Admin_Password_in_File(new_password);
        }
    }
}
