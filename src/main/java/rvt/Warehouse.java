package rvt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warehouse {
    private Map<String,Integer> prices;
    private Map<String,Integer> stock;
    
    public Warehouse(){
        this.prices = new HashMap<>();
        this.stock = new HashMap<>();
    }

    public void addProduct(String product, int price, int stock){
        this.prices.put(product, price);
        this.stock.put(product, stock);
    }

    public int price(String product){
        if(this.prices.containsKey(product)){
            return this.prices.get(product);
        }
        return -99;
    }

    
}