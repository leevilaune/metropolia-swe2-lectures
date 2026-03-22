package dev.onesnzeroes.shoppingcart.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items;

    public ShoppingCart(){
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getTotal(){
        return items.stream().mapToDouble(Item::getTotalPrice).sum();
    }
    public void addItem(double price, int amount){
        this.items.add(new Item(price,amount));
    }

}
