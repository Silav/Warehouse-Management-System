package Controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Order;
import model.Product;

public class Order_Management {

    static ArrayList<Order> orders = new ArrayList<>();
    static ArrayList<Order> finalized_orders = new ArrayList<>();
    static Scanner input = new Scanner(System.in);
    static String num_items = "";

    public static void make_an_Order() {

        Inventory_Management.view_Inventory();
        System.out.println("Enter Order ID  :  ");
        String order_id = input.next();
        System.out.println("Enter Product ID  :  ");
        String product_id = input.next();
        Product product = Inventory_Management.get_Product(Integer.parseInt(product_id));

        if (product == null) {
            System.out.println("No Product exists with such ID  " + product_id);
        } else {
            while (true) {
                System.out.println("Enter Number of items to purchase  :  ");
                num_items = input.next();
                if (Inventory_Management.isInteger(num_items)) {
                    break;
                } else {
                    System.out.println("Please enter number of items correctly");
                }
            }
            if (Inventory_Management.isInteger(product_id) && Inventory_Management.isInteger(num_items)) {
                orders.add(new Order(Integer.parseInt(order_id), Integer.parseInt(product_id), Integer.parseInt(num_items)));
                try {
                    Thread.sleep(500);
                    new Thread(() -> {
                        save_Order_to_File(Integer.parseInt(order_id), Integer.parseInt(product_id), Integer.parseInt(num_items));
                        System.out.println("Order has been recorded.");
                    }).start();
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            } else {
                System.out.println("Values are incorrectly entered");
            }
        }
    }

    public static void fetch_all_Orders_from_File(String file_name) {

        int checker = 0;
        if (file_name.equals("Order_List.txt")) {
            orders.clear();
            checker = 1;
        } else if (file_name.equals("Ordered_Items.txt")) {
            finalized_orders.clear();
            checker = 0;
        }

        try {
            File myObj = new File(file_name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitted_data = data.split(" ");

                int order_id = Integer.parseInt(splitted_data[0]);
                int product_id = Integer.parseInt(splitted_data[1]);
                int num_items = Integer.parseInt(splitted_data[2]);
                Order order = new Order(order_id, product_id, num_items);
                if (checker == 0) {
                    finalized_orders.add(order);
                } else {
                    orders.add(order);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred while reading Order_List.txt");
            e.printStackTrace();
        }
    }

    public static void save_Order_to_File(int order_id, int product_id, int num_of_items) {
        try {
            FileWriter writer = new FileWriter("Order_List.txt", true);
            writer.append(order_id + " " + product_id + " " + num_of_items + "\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println("Exception in writing to Order_List file");
        }
    }

    public static void Order_the_Item() {
        view_all_Orders();
        System.out.print("Enter the Order ID  :   ");
        String order_id = input.next();
        if (!Inventory_Management.isInteger(order_id)) {
            System.out.println("Please enter order id correctly");
        } else {
            int int_order_id = Integer.parseInt(order_id);
            Order order = get_Order(orders, int_order_id);
            if (order == null) {
                System.out.println("No Order with such Order ID " + order_id + "  exist");
            } else {
                System.out.println("The following order has been ordered \n" + order.toString());
                try {
                    Thread.sleep(500);
                    new Thread(() -> {
                        save_Ordered_Item_to_File(order);
                        System.out.println("Saved the order");
                        finalized_orders.add(order);
                        orders.remove(order);
                        try {
                            Thread.sleep(600);
                            save_all_Orders_to_File();
                            Thread.sleep(600);
                        } catch (Exception e) {
                        }
                    }).start();
                    Thread.sleep(500);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void save_Ordered_Item_to_File(Order order) {
        try {
            FileWriter writer = new FileWriter("Ordered_Items.txt", true);
            writer.append(order.getOrder_id() + " " + order.getProduct_id() + " " + order.getNum_of_items() + "\n");
            writer.close();
        } catch (Exception ex) {
            System.out.println("Exception in writing to Ordered_Items file");
        }

    }

    public static Order get_Order(ArrayList<Order> orders, int order_id) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.getOrder_id() == order_id) {
                return order;
            }
        }
        return null;
    }

    public static void view_all_Orders() {
        if (orders.isEmpty()) {
//            new Thread(() -> {
            fetch_all_Orders_from_File("Order_List.txt");
//            }).start();
        }
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println(order.toString());
        }
    }

    public static void view_finalized_Orders() {
        if (finalized_orders.isEmpty()) {
            fetch_all_Orders_from_File("Ordered_Items.txt");
        }
        for (int i = 0; i < finalized_orders.size(); i++) {
            Order order = finalized_orders.get(i);
            System.out.println(order.toString());
        }
    }

    public static void save_all_Orders_to_File() {
        try {
            FileWriter writer = new FileWriter("Order_List.txt");
            for (int i = 0; i < orders.size(); i++) {
                Order obj = orders.get(i);
                writer.append(obj.getOrder_id() + " " + obj.getProduct_id() + " " + obj.getNum_of_items() + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception in writing to Order list file");
        }
    }

    public static void save_all_finalized_Orders_to_File() {
        try {
            FileWriter writer = new FileWriter("Ordered_Items.txt");
            for (int i = 0; i < finalized_orders.size(); i++) {
                Order obj = finalized_orders.get(i);
                writer.append(obj.getOrder_id() + " " + obj.getProduct_id() + " " + obj.getNum_of_items() + "\n");
            }
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception in writing to ordered items file");
        }
    }

    public static void remove_finalized_Order() {
        view_finalized_Orders();
        System.out.print("Enter the Order ID  :   ");
        String order_id = input.next();
        if (!Inventory_Management.isInteger(order_id)) {
            System.out.println("Please enter order id correctly");
        } else {
            int int_order_id = Integer.parseInt(order_id);
            Order order = get_Order(finalized_orders, int_order_id);
            if (order == null) {
                System.out.println("No Order with such Order ID " + order_id + "  exists");
            } else {
                System.out.println(order.toString());
                finalized_orders.remove(order);
                try {
                    Thread.sleep(600);
                    save_all_finalized_Orders_to_File();
                    Thread.sleep(600);
                    System.out.println("Order removed successfully");
                } catch (Exception e) {
                }
            }
        }
    }
}
