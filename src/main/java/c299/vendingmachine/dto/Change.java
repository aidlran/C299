package c299.vendingmachine.dto;

import java.util.HashMap;
import java.util.Map;

// Change is calculated using integers corresponding to pennies rather than floats.

public class Change {

	public static final int
		PENNY = 1,
		NICKEL = 5,
		DIME = 10,
		QUARTER = 25;

	public enum Coin {

		QUARTER(Change.QUARTER),
		DIME(Change.DIME),
		NICKEL(Change.NICKEL),
		PENNY(Change.PENNY);

		private int value;

		Coin(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static Coin[] valuesOrdered() {
			Coin[] values = {QUARTER, DIME, NICKEL, PENNY};
			return values;
		}
	}

	// Each change object uses a hashmap that has a count of each coin type
	private Map<Coin, Integer> changeMap;

	public Change(int pennies) {	

		changeMap = new HashMap<>();
		int quotient;

		Coin[] coins = Coin.valuesOrdered();

		// Calculating quarters, nickels & dimes
		for (int i = 0; i < 3; i++) {
			quotient = pennies / coins[i].value;
			changeMap.put(coins[i], quotient);
			pennies -= quotient * coins[i].value;
		}

		// Remaining pennies
		changeMap.put(coins[3], pennies);
	}

	public int getPennies() {
		return changeMap.get(Coin.PENNY);
	}

	public int getNickels() {
		return changeMap.get(Coin.NICKEL);
	}

	public int getDimes() {
		return changeMap.get(Coin.DIME);
	}

	public int getQuarters() {
		return changeMap.get(Coin.QUARTER);
	}
}
