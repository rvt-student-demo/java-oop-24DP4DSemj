package rvt;

import java.util.HashMap;
import java.util.Map;

public class Shopping_card {

    private Map<String, Items> items;

    public Shopping_card() {
        this.items = new HashMap<>();
    }

    public void add(String product, int price) {
        if (this.items.containsKey(product)) {
            this.items.get(product).increaseQty();
        } else {
            this.items.put(product, new Items(product, 1, price));
        }
    }

    public int price() {
        int totalPrice = 0;
        for (Items item : this.items.values()) {
            totalPrice += item.price();
        }
        return totalPrice;
    }

    public void print() {
        for (Items item : this.items.values()) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Shopping_card cart = new Shopping_card();
        cart.add("Coffee", 5);
        cart.add("Tea", 3);
        cart.add("Coffee", 5);

        System.out.println("Items in cart:");
        cart.print();
        System.out.println("Total price: " + cart.price());
    }
}