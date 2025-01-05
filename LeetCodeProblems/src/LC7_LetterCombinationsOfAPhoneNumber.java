import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LC7_LetterCombinationsOfAPhoneNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String digits = sc.next();
		sc.close();
		LC7_LetterCombinationsOfAPhoneNumber program = new LC7_LetterCombinationsOfAPhoneNumber();
		System.out.println(program.letterCombinations(digits));
	}

	public List<String> letterCombinations(String digits) {
		if (digits.equals("")) {
			return new ArrayList<>();
		} else {
			return letterCombinationsNonEmpty(digits);
		}
	}

	public List<String> letterCombinationsNonEmpty(String digits) {
		if (digits.equals("")) {
			ArrayList<String> empty = new ArrayList<>();
			empty.add("");
			return empty;
		}
		char digit = digits.charAt(0);
		String nextDigits = digits.substring(1);
		List<String> a = letterCombinationsNonEmpty(nextDigits);
		List<String> b = letterCombinationsNonEmpty(nextDigits);
		List<String> c = letterCombinationsNonEmpty(nextDigits);
		String aChar = "", bChar = "", cChar = "";
		if (digit == '2') {
			aChar = "a";
			bChar = "b";
			cChar = "c";
		} else if (digit == '3') {
			aChar = "d";
			bChar = "e";
			cChar = "f";
		} else if (digit == '4') {
			aChar = "g";
			bChar = "h";
			cChar = "i";
		} else if (digit == '5') {
			aChar = "j";
			bChar = "k";
			cChar = "l";
		} else if (digit == '6') {
			aChar = "m";
			bChar = "n";
			cChar = "o";
		} else if (digit == '7') {
			aChar = "p";
			bChar = "q";
			cChar = "r";
			String dChar = "s";
			List<String> d = letterCombinationsNonEmpty(nextDigits);
			
			appendBeginning(a, aChar);
			appendBeginning(b, bChar);
			appendBeginning(c, cChar);
			appendBeginning(d, dChar);
			a.addAll(b);
			a.addAll(c);
			a.addAll(d);
			return a;
		} else if (digit == '8') {
			aChar = "t";
			bChar = "u";
			cChar = "v";
		} else {
			aChar = "w";
			bChar = "x";
			cChar = "y";
			String dChar = "z";
			List<String> d = letterCombinationsNonEmpty(nextDigits);
			
			appendBeginning(a, aChar);
			appendBeginning(b, bChar);
			appendBeginning(c, cChar);
			appendBeginning(d, dChar);
			a.addAll(b);
			a.addAll(c);
			a.addAll(d);
			return a;
		}
		appendBeginning(a, aChar);
		appendBeginning(b, bChar);
		appendBeginning(c, cChar);
		a.addAll(b);
		a.addAll(c);
		return a;
	}

	public void appendBeginning(List<String> lst, String c) {
		for (int i = 0; i < lst.size(); i++) {
			lst.set(i, c + lst.get(i));
		}
	}

}
