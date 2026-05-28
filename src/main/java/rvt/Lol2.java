package rvt;

import java.util.HashMap;
import java.util.Map;

public class Lol2 {
    private Map<String, Integer> prices;
    private Map<String, Integer> stock;

    public Lol2() {
        this.prices = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public void addProduct(String product, int price, int stock) {
        this.prices.put(product, price);
        if (this.stock.containsKey(product)) {
            int currentStock = this.stock.get(product);
            this.stock.put(product, currentStock + stock);
        } else {
            this.stock.put(product, stock);
        }
    }

    public int price(String product) {
        if (this.prices.containsKey(product)) {
            return this.prices.get(product);
        }
        return -99;
    }

    public int stock(String product) {
        if (this.stock.containsKey(product)) {
            return this.stock.get(product);
        }
        return 0;
    }

    public boolean take(String product) {
        if (this.stock.containsKey(product)) {
            int currentStock = this.stock.get(product);
            if (currentStock > 0) {
                currentStock--;
                this.stock.put(product, currentStock);
                return true;
            }
        }
        return false;
    }
}