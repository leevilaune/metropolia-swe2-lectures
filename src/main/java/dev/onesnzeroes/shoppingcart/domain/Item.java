package dev.onesnzeroes.shoppingcart.domain;

public class Item {

    private double price;
    private int amount;

    public Item (double price, int amount){
        this.price = price;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice(){
        return this.amount*this.price;
    }
}
