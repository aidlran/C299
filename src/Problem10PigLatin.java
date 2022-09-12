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

		for (String s : englishString.split(" ")) if (s.trim().length() > 0) {
			if (once) output += " ";
			else once = true;
			output += s.substring(1) + s.charAt(0) + "ay";
		}

		return output.toLowerCase();
	}

	public static String pigLatinToEnglish(String pigLatinString) {

		String output = "";
		boolean once = false;

		for (String s : pigLatinString.split(" ")) if (s.trim().length() > 0) {
			if (once) output += " ";
			else once = true;
			output += s.charAt(s.length() - 3) + s.substring(0, s.length() - 3);
		}

		return output.toLowerCase();
	}
}
