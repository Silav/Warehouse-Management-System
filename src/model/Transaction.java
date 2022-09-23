package model;

public class Transaction {

    int receipt_number;
    int product_id;
    int num_of_items;
    double bill;

    public Transaction() {
    }

    public Transaction(int receipt_number, int product_id, int num_of_items, double bill) {

        this.receipt_number = receipt_number;
        this.product_id = product_id;
        this.num_of_items = num_of_items;
        this.bill = bill;
    }

    public int getReceipt_number() {
        return receipt_number;
    }

    public void setReceipt_number(int receipt_number) {
        this.receipt_number = receipt_number;
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

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "Transaction{" + "Receipt Number=" + receipt_number + ", Product ID=" + product_id + ", Number of Items=" + num_of_items + ", Bill= $ " + bill + '}';
    }

    public void clone(Transaction obj) {
        this.receipt_number = obj.receipt_number;
        this.product_id = obj.product_id;
        this.num_of_items = obj.num_of_items;
        this.bill = obj.bill;
    }
    
}
