// Write a function that takes a list of strings an prints them, one per line,
// in a rectangular frame.
 
// For example the list ["Hello", "World", "in", "a", "frame"] gets printed as:

// *********
// * Hello *
// * World *
// * in    *
// * a     *
// * frame *
// *********

public class Problem7FramedStrings {

	/* Printer class */

	private String[] strings;

	private int maxStringLength = 0;

	public Problem7FramedStrings(String[] strings) {

		this.strings = strings;

		// Find the length of the longest string
		for (String string : strings) {
			int stringLength = string.length();
			if (stringLength > maxStringLength) maxStringLength = stringLength;
		}
	}

	public String toString() {

		String output = getHorizontalBorder();

		for (String string : strings) {
			output += "* " + string;
			for (int i = 0; i < maxStringLength - string.length(); i++)
				output += ' ';
			output += " *\n";
		}

		return output + getHorizontalBorder();
	}

	public void print() {
		System.out.println(this);
	}

	private String getHorizontalBorder() {

		String output = "";

		for (int i = 0; i < this.maxStringLength + 4; i++)
			output += '*';

		return output + "\n";
	}

	/* Demo */

	private static final String[] EXAMPLE_LIST = {
		"Hello", "World", "in", "a", "frame"
	};

	public static void main(String[] args) {
		new Problem7FramedStrings(EXAMPLE_LIST).print();
	}
}
