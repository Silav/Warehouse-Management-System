package Controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.Product;
import model.Transaction;

public class Transaction_Management {

    static ArrayList<Transaction> transactions = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static Transaction prev_obj = new Transaction();

    public static void return_an_Item() {

        view_all_Transactions();
        System.out.print("Enter the Receipt Number  :   ");
        String receipt_no = input.next();

        if (Inventory_Management.isInteger(receipt_no)) {
            Transaction obj = get_Transaction_Object(Integer.parseInt(receipt_no));
            if (obj == null) {
                System.out.println("Could not find Transaction with Transaction ID  " + receipt_no);
            } else {
                prev_obj.clone(obj);
                System.out.println(obj);
                System.out.println("Enter Number of items to return  :   ");
                String num = input.next();
                if (Inventory_Management.isInteger(num)) {
                    int value = Integer.parseInt(num);
                    if (value > obj.getNum_of_items() || value < 0) {
                        System.out.println("Invalid number of items");
                    } else {

                        Product product = Inventory_Management.get_Product(obj.getProduct_id());
                        obj.setBill(obj.getBill() - (value * product.getPrice()));
                        product.setNum_of_items(product.getNum_of_items() + value);
                        obj.setNum_of_items(obj.getNum_of_items() - value);

                        if (obj.getNum_of_items() == 0) {
                            transactions.remove(obj);
                            System.out.println("Complete Transaction reversed. All items are added back to inventory");
                        }
                        try {
                            new Thread(() -> {
                                Inventory_Management.save_all_Products_to_File(Inventory_Management.products);
                            }).start();
                            new Thread(() -> {
                                save_all_Transactions_to_File();
                            }).start();
                            Thread.sleep(500);
                            System.out.println("Transaction updated");
                            System.out.println("Previous Transaction  :  " + prev_obj.toString());
                            System.out.println("Updated Transaction  :  " + obj.toString());
                        } catch (Exception e) {
                        }
                    }
                } else {
                    System.out.println("Invalid number of items");
                }
            }
        } else {
            System.out.println("Invalid Transaction ID");
        }
    }

    public static Transaction get_Transaction_Object(int receipt_id) {
        if (transactions.isEmpty()) {
            fetch_all_Transactions_from_File();
        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction obj = transactions.get(i);
            if (obj.getReceipt_number() == receipt_id) {
                return obj;
            }
        }
        return null;
    }

    public static void save_Transaction_to_File(String transaction_id, int product_id, int num_of_items, double bill) {
        try {
            FileWriter writer = new FileWriter("Transactions.txt", true);
            writer.append(transaction_id + " " + product_id + " " + num_of_items + " " + bill + "\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println("Exception in writing to Transactions file");
        }
    }

    public static void fetch_all_Transactions_from_File() {
        try {
            transactions.clear();
            File myObj = new File("Transactions.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitted_data = data.split(" ");

                int transac_id = Integer.parseInt(splitted_data[0]);
                int product_id = Integer.parseInt(splitted_data[1]);
                int num_items = Integer.parseInt(splitted_data[2]);
                double bill = Double.parseDouble(splitted_data[3]);
                Transaction transac = new Transaction(transac_id, product_id, num_items, bill);

                transactions.add(transac);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred while reading Transactions.txt");
            e.printStackTrace();
        }
    }

    public static void view_all_Transactions() {
        if (transactions.isEmpty()) {
            fetch_all_Transactions_from_File();
        }
        for (int i = 0; i < transactions.size(); i++) {
            Transaction obj = transactions.get(i);
            System.out.println(obj.toString());
        }
    }

    public static void save_all_Transactions_to_File() {
        try {
            FileWriter writer = new FileWriter("Transactions.txt");
            for (int i = 0; i < transactions.size(); i++) {
                Transaction obj = transactions.get(i);
                writer.append(obj.getReceipt_number() + " " + obj.getProduct_id() + " " + obj.getNum_of_items() + " " + obj.getBill() + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("Exception in writing to Transactions file");
        }
    }

    public static ArrayList<Product> best_Selling_Products() {
        ArrayList<Product> products = Inventory_Management.add_all_Products();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transac_obj1 = transactions.get(i);
            System.out.println(transac_obj1.toString());
            int num_of_items = transac_obj1.getNum_of_items();
            int product_id = transac_obj1.getProduct_id();
            Product product = Inventory_Management.get_Product(products, product_id);
            if (product == null) {
                products.add(product);
            } else {
                product.setNum_of_items(product.getNum_of_items() + num_of_items);
            }
        }
        Product[] product_array = new Product[products.size()];        
        for (int j = 0; j < products.size(); j++) {
            product_array[j] = products.get(j);
        }
        Arrays.sort(product_array);
        System.out.println("\n\nProduct ID\tProduct\t\tNumber of items sold");
        for (int i = product_array.length-1; i >=0; i--) {
            Product product = product_array[i];
            System.out.println(product.getProduct_id()+"\t\t"+product.getProduct_name()+"\t\t"+product.getNum_of_items());
        }
        System.out.println("\n");
        return products;
    }
}
