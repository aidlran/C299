// Write a function that translates a text to Pig Latin and back. English is
// translated to Pig Latin by taking the first letter of every word, moving it
// to the end of the word and adding ‘ay’. “The quick brown fox” becomes
// “Hetay uickqay rownbay oxfay”.

public class Problem10PigLatin {

	public static void main(String[] args) {
		System.out.println(pigLatinToEnglish(englishToPigLatin("This is pig latin")));
	}

	public static String englishToPigLatin(String englishString) {

		String output = "";
		boolean once = false;

		for (String word : englishString.split(" ")) {
			word = word.trim();
			if (word.length() > 0) {
				if (once) output += " ";
				else once = true;
				output += word.substring(1) + word.charAt(0) + "ay";
			}
		}

		return output.toLowerCase();
	}

	public static String pigLatinToEnglish(String pigLatinString) {

		String output = "";
		boolean once = false;

		for (String word : pigLatinString.split(" ")) {
			word = word.trim();
			int len = word.length();
			if (len > 0) {
				if (once) output += " ";
				else once = true;
				output += word.charAt(len - 3) + word.substring(0, len - 3);
			}
		}

		return output.toLowerCase();
	}
}
