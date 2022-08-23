package fridge;

import java.util.HashMap;

public class Fridge {

    private final int capacity;

    private final HashMap<String, Integer> ITEM_MAP = new HashMap<>();

    private int itemCount = 0;

    public Fridge(int capacity) {
        this.capacity = capacity;
    }

    public void addItem(String item) {
        if (itemCount >= capacity) {
            System.out.println("Fridge is full! Cannot add " + item + ".");
            return;
        }

        if (!ITEM_MAP.containsKey(item)) {
            ITEM_MAP.put(item, 1);
        }
        else {
            ITEM_MAP.put(item, ITEM_MAP.get(item) + 1);
        }

        this.itemCount++;
    }

    public void removeItem(String item) {

        if (itemCount <= 0) {
            System.out.println("Fridge is empty!");
        }

        else if (!ITEM_MAP.containsKey(item) || ITEM_MAP.get(item) <= 0) {
            System.out.println("No " + item + " in the fridge to remove!");
        }

        else {
            ITEM_MAP.put(item, ITEM_MAP.get(item) - 1);
            this.itemCount--;
        }
    }

    public String toString() {
        return itemCount + " item(s): " + ITEM_MAP;
    }

    public static void main(String[] args) {
        Fridge fridge = new Fridge(50);
        System.out.println(fridge);
        fridge.addItem("butter");
        System.out.println(fridge);
        fridge.addItem("butter");
        fridge.addItem("milk");
        System.out.println(fridge);
        fridge.removeItem("butter");
        fridge.removeItem("eggs");
        System.out.println(fridge);
    }
}
