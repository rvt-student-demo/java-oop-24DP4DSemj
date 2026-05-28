package rvt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set; // Šim ir jābūt!

public class Store {
    private Map<String, Integer> prices;
    private Map<String, Integer> stock;

    public Store() {
        this.prices = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public int price(String product) {
        return this.prices.getOrDefault(product, -99);
    }

    public int stock(String product) {
        return this.stock.getOrDefault(product, 0);
    }

    public void addProduct(String product, int price, int stock) {
        this.prices.put(product, price);
        this.stock.put(product, this.stock.getOrDefault(product, 0) + stock);
    }

    public boolean take(String product) {
        if (this.stock.getOrDefault(product, 0) > 0) {
            this.stock.put(product, this.stock.get(product) - 1);
            return true;
        }
        return false;
    }

    // Šī ir metode, kuru Store meklē:
    public Set<String> products() {
        return this.prices.keySet();
    }
}