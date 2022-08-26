public class SummativeSums {

	private static final int[][] TEST_ARRAYS = {
		{1, 90, -33, -55, 67, -16, 28, -55, 15},
		{999, -60, -77, 14, 160, 301},
		{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99}
	};

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++)
			System.out.println("#" + (i + 1) + " Array Sum: " + sumArray(TEST_ARRAYS[i]));
	}

	private static int sumArray(int[] integerArray) {
		int sum = 0;
		for (int i : integerArray) sum += i;
		return sum;
	}
}
