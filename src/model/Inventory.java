package model;

import java.util.ArrayList;

public class Inventory {

    ArrayList<Product> products;

    public Inventory(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    
}
