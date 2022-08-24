import java.util.HashMap;

public class Fridge {

    // Capacity of fridge, set during construction
    private final int capacity;

    // Hash map that tracks quantity of each item
    private final HashMap<String, Integer> ITEM_MAP = new HashMap<>();

    // Total count of items in fridge
    private int itemCount = 0;

    /**
     * Fridge Constructor.
     * @param capacity How many items the fridge can hold.
     */
    public Fridge(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Add an item to the fridge.
     * Checks capacity and updates values if checks pass.
     * @param item Name of item to add.
     */
    public void addItem(String item) {

        item = item.toLowerCase();

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

    /**
     * Remove an item from the fridge.
     * Checks item count and updates values if checks pass.
     * @param item Name of the item to remove.
     */
    public void removeItem(String item) {

        item = item.toLowerCase();

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

    // Program entrypoint
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
