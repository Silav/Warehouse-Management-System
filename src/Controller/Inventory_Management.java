package Controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Inventory;
import model.Product;
import model.Transaction;

public class Inventory_Management {

    static ArrayList<Product> products = new ArrayList<>();
    static Inventory inventory = new Inventory(products);
    static Scanner input = new Scanner(System.in);

    public static void view_Inventory() {
        if (inventory.getProducts().isEmpty()) {
            get_Products_From_File();
        } else {
            for (int i = 0; i < inventory.getProducts().size(); i++) {
                Product product = products.get(i);
                System.out.println(product.toString());
            }
        }
    }
    static String transac_id = "";

    public static void sale_Item() {
        System.out.print("Enter the Product ID  :   ");
        String id = input.next();

        Product product = null;
        boolean check1 = false, check2 = false;
        for (int i = 0; i < products.size(); i++) {
            product = products.get(i);
            if (isInteger(id)) {
                check2 = true;
            }
            if (check2) {
                if (product.getProduct_id() == Integer.parseInt(id)) {
                    check1 = true;
                    break;
                }
            }
        }
        if (check1 && check2) {
            if (product.getNum_of_items() == 1) {
                System.out.println(product.toString());
                System.out.println("This product has only one item left; cannot be sold");
            } else {
                System.out.println(product.toString());
                System.out.println("Enter number of items to sale  :  ");
                String num_items = input.next();
                if (isInteger(num_items)) {
                    int items = Integer.parseInt(num_items);
                    if (product.getNum_of_items() - items <= 0) {
                        System.out.println("One product of each item must always be kept; cannot be sold");
                    } else if (items > product.getNum_of_items() || items <= 0) {
                        System.out.println(items + " items of the product \"" + product.getProduct_name() + "\" is not available");
                    } else {
                        Transaction transac_obj = new Transaction();
                        while (true) {
                            System.out.println("Enter Transaction ID  :  ");
                            transac_id = input.next();
                            if (isInteger(transac_id)) {
                                transac_obj = Transaction_Management.get_Transaction_Object(Integer.parseInt(transac_id));
                                if (transac_obj == null) {
                                    break;
                                } else {
                                    System.out.println("Transaction with this ID already exists");
                                    System.out.println("Transaction ID must be unique");
                                    System.out.println(transac_obj.toString());
                                }
                            } else {
                                System.out.println(transac_obj.toString());
                                System.out.println("Transaction ID must be a number");
                            }
                        }
                        System.out.println("Transaction has been done successfully");
                        final double bill = product.getPrice() * items;
                        final int p_id = product.getProduct_id();
                        System.out.println("Total bill is  $ " + bill);
                        product.setNum_of_items(product.getNum_of_items() - items);
                        try {
                            Thread.sleep(500);
                            Transaction_Management.transactions.add(new Transaction(Integer.parseInt(transac_id), p_id, items, bill));
                            new Thread(() -> {
                                save_all_Products_to_File(products);
                                System.out.println("Product updated");
                            }).start();
                            new Thread(() -> {
                                Transaction_Management.save_Transaction_to_File(transac_id, p_id, items, bill);
                                System.out.println("Transaction saved successfully");
                            }).start();
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }

                    }
                } else {
                    System.out.println("Number of items are invalid");
                }
            }
        } else {
            System.out.println("Product with ID  " + id + "  is not available");
        }
    }

    public static boolean save_all_Products_to_File(ArrayList<Product> products) {
        boolean check = true;
        try {
            FileWriter writer = new FileWriter("Products.txt");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                writer.append(product.getProduct_id() + " " + product.getProduct_name() + " " + product.getNum_of_items() + " " + product.getPrice() + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("Exception in writing to Products file");
            check = false;
        }
        return check;
    }

    public static boolean isInteger(String num) {
        try {
            int value = Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDouble(String num) {
        try {
            double value = Double.parseDouble(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Inventory get_Products_From_File() {
        inventory.getProducts().clear();
        try {
            File myObj = new File("Products.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitted_data = data.split(" ");
                Product obj = new Product();

                obj.setProduct_id(Integer.parseInt(splitted_data[0]));

                obj.setProduct_name(splitted_data[1].replace('_', ' '));

                obj.setNum_of_items(Integer.parseInt(splitted_data[2]));
                obj.setPrice(Double.parseDouble(splitted_data[3]));

                products.add(obj);
            }
            inventory.setProducts(products);
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred while reading Products");
            e.printStackTrace();
        }
        return inventory;
    }

    public static void add_Product_to_File(String id, String name, String num_items, String price) {
        try {
            FileWriter writer = new FileWriter("Products.txt", true);
            writer.append(id + " " + name.trim() + " " + num_items + " " + price + "\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println("Exception in writing to Products file");
        }
    }
    static String num_items = "";
    static String price = "";

    public static void add_Product() {
        get_Products_From_File();
        System.out.println("-----Product Details-----");
        System.out.print("Enter Product ID  :   ");
        String id = input.next();
        Product product = get_Product(Integer.parseInt(id));
        if (product != null) {
            System.out.println("Product with ID  " + id + "  already exists");
            System.out.println(product.toString());
            System.out.println("Do you want to add number of items for this product ?  ");
            System.out.print("Enter option  (Y/N)? :   ");
            String opt = input.next();
            if (!(opt.equalsIgnoreCase("y") || opt.equalsIgnoreCase("n"))) {
                System.out.println("Invalid option");
            } else if (opt.equalsIgnoreCase("y")) {
                System.out.print("Enter number of items to add  :   ");
                String num_items = input.next();
                if (!isInteger(num_items)) {
                    System.out.println("invalid input");
                } else {
                    product.setNum_of_items(product.getNum_of_items() + Integer.parseInt(num_items));
                    new Thread(() -> {
                        save_all_Products_to_File(products);
                    }).start();
                }
            } else {
                return;
            }
        } else {
            input.nextLine();
            System.out.print("Enter Product name  :   ");
            String name = input.nextLine();
            while (true) {
                System.out.print("Enter Number of items  :   ");
                num_items = input.next();
                if (isInteger(num_items)) {
                    break;
                } else {
                    System.out.println("Enter number of items correctly");
                }
            }
            while (true) {
                System.out.print("Enter Price per item  :$  ");
                price = input.next();
                if (isDouble(price)) {
                    break;
                } else {
                    System.out.println("Enter price correctly");
                }
            }

            products.add(new Product(Integer.parseInt(id), name, Integer.parseInt(num_items), Double.parseDouble(price)));
            try {
                Thread.sleep(500);
                new Thread(() -> {
                    String modified_name = name.replace(' ', '_');
                    add_Product_to_File(id, modified_name, num_items, price);
                    System.out.println("New Product added successfully");
                }).start();
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }

    public static Product get_Product(int product_id) {
        if (products.isEmpty()) {
            new Thread(() -> {
                get_Products_From_File();
            }).start();
        }
        for (int i = 0; i < products.size(); i++) {
            Product obj = products.get(i);
            if (obj.getProduct_id() == product_id) {
                return obj;
            }
        }
        return null;
    }
    public static Product get_Product(ArrayList<Product> products , int product_id) {
        if(products.isEmpty()){
            return null;
        }
        for (int i = 0; i < products.size(); i++) {
            Product obj = products.get(i);
            if (obj.getProduct_id() == product_id) {
                return obj;
            }
        }
        return null;
    }
    public static ArrayList<Product>add_all_Products(){
        ArrayList<Product> products_array = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            products_array.add( new Product(product.getProduct_id(),product.getProduct_name(),0,product.getPrice()));
        }        
        return products_array;
    }
    public static void view_all_Sold_out_Products(){
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if(product.getNum_of_items() == 1){
                System.out.println(product.toString());
            }
        }
    }
}
