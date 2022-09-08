// Write a function that computes the list of the first 100 Fibonacci numbers.
// The first two Fibonacci numbers are 1 and 1. The n+1-st Fibonacci number can
// be computed by adding the n-th and the n-1-th Fibonacci number. The first few
// are therefore 1, 1, 1+1=2, 1+2=3, 2+3=5, 3+5=8.
 
// My solution uses the BigInteger class to prevent overflow that would happen
// with long or int.

import java.math.BigInteger;

public class Problem6Fibonacci {

	private static BigInteger prev = BigInteger.valueOf(1), current = BigInteger.valueOf(1);

	public static void main(String[] args) {

		System.out.print("The first 100 Fibonacci numbers are: " + current);

		for (int i = 1; i < 100; i++) {
			BigInteger temp = new BigInteger(prev.toByteArray());
			prev = current;
			current = new BigInteger(current.toByteArray()).add(temp);
			System.out.print(" " + current);
		}
	}
 }
