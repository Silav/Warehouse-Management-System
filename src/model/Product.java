package model;

public class Product implements Comparable<Product> {

    int product_id;
    String product_name;
    int num_of_items;
    double price;

    public Product() {
    }

    public Product(int product_id, String product_name, int num_of_items, double price) {

        this.product_id = product_id;
        this.product_name = product_name;
        this.num_of_items = num_of_items;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getNum_of_items() {
        return num_of_items;
    }

    public void setNum_of_items(int num_of_items) {
        this.num_of_items = num_of_items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "Product ID=" + product_id + ", Product Name=" + product_name + ", Number of Items=" + num_of_items + ", Price per item = $ " + price + '}';
    }

    @Override
    public int compareTo(Product p) {
        if (this.getNum_of_items() != p.getNum_of_items()) {
            return this.getNum_of_items() - p.getNum_of_items();
        }
    return 0;
    }
}
