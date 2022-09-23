package model;

public class Order {

    int order_id;
    int product_id;
    int num_of_items;

    public Order(int order_id, int product_id, int num_of_items) {

        this.order_id = order_id;
        this.product_id = product_id;
        this.num_of_items = num_of_items;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getNum_of_items() {
        return num_of_items;
    }

    public void setNum_of_items(int num_of_items) {
        this.num_of_items = num_of_items;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", product_id=" + product_id + ", num_of_items=" + num_of_items + '}';
    }
    
}
