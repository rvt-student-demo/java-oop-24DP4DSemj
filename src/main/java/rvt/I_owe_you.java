package rvt;

import java.util.HashMap;
import java.util.Map;

public class I_owe_you {
    private final Map<String, Double> debts = new HashMap<>();

    public I_owe_you() {
    }

    public void setSum(String toWhom, double amount) {
        debts.put(toWhom, amount);
    }

    public double howMuchDoIOweTo(String toWhom) {
        return debts.getOrDefault(toWhom, 0.0);
    }

    public static void main(String[] args) {
        I_owe_you iou = new I_owe_you();
        iou.setSum("Alice", 50);
        iou.setSum("Bob", 20.5);
        System.out.println(iou);
        System.out.println("How much do I owe to Alice? " + iou.howMuchDoIOweTo("Alice"));
        System.out.println("How much do I owe to Charlie? " + iou.howMuchDoIOweTo("Charlie"));
        iou.setSum("Alice", 75);
        System.out.println(iou);
    }
}