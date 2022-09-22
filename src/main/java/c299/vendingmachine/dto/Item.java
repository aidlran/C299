package c299.vendingmachine.dto;

import lombok.Data;

public @Data class Item {
    /**
     * Using integer (number of pennies)
     * rather than risk float inaccuracies.
     */
    private int price;
    private String name;
    private int quantity;

    public void decrementQuantity() {
        quantity--;
    }
}
