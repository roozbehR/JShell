// **********************************************************
// Assignment2:
// Student1: Farhan Chowdhury
// UTORID user_name: chowd601
// UT Student #: 1006068176
// Author: Farhan Chowdhury
//
// Student2: Rakshit Patel
// UTORID user_name: patel939
// UT Student #: 1005918152
// Author: Rakshit Patel
//
// Student3: Shammo Talukder
// UTORID user_name: talukd29
// UT Student #: 1006317237
// Author: Shammo Talukder
//
// Student4: Roozbeh Yadollahi
// UTORID user_name: yadolla6
// UT Student #: 1005230992
// Author: Roozbeh Yadollahi
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package verification;

/**
 * A class to validate double-quoted string input
 * 
 * @author Farhan Chowdhury
 * @author Rakshit Patel
 */
public class StringVerifier {

	private static boolean verifyDoubleQuotedInput(String input) {
		return input.startsWith("\"") && input.endsWith("\"");
	}

	private static boolean verifyNoBetweenQuotes(String input) {
		return !(input.substring(1, input.length() - 1).contains("\""));
	}

	/**
	 * This method verifies that the input is a proper string surrounded by 
	 * double quotes.
	 *
	 * @param input the input String
	 * @return true, if is valid string, false otherwise
	 */
	public static boolean isValidString(String input) {
		if (input.length() == 1) {
			return false;
		}

		return verifyDoubleQuotedInput(input) && verifyNoBetweenQuotes(input);
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * should output true, false, false, false System.out.println(
	 * StringVerifier.verifyDoubleQuotedInput("\"hello world\"" ));
	 * System.out.println(
	 * StringVerifier.verifyDoubleQuotedInput("hello world\"")) ;
	 * System.out.println(
	 * StringVerifier.verifyDoubleQuotedInput("\"hello world")) ;
	 * System.out.println(StringVerifier.verifyDoubleQuotedInput("hello world"));
	 * System.out.println(); // should output false, false, false, false, true
	 * System.out.println(StringVerifier.isValidString("\"hello\" world\""));
	 * System.out.println(StringVerifier.isValidString("hello\" w\"orld\""));
	 * System.out.println(StringVerifier.isValidString("\"hello w\"orld"));
	 * System.out.println(StringVerifier.isValidString("he\"llo world\""));
	 * System.out.println(StringVerifier.isValidString("\"hello world\""));
	 * System.out.println(); /* //"hello" - true //"hell"o - false
	 * //"hell"o" - false //"he"ll"o" - false //"""hello"" - false
	 * //he"llo - false //hel""lo - false //hello - false
	 * System.out.println(StringVerifier.isValidString("\"hello\""));
	 * System.out.println(StringVerifier.isValidString("\"hell\"o"));
	 * System.out.println(StringVerifier.isValidString("\"hell\"o\""));
	 * System.out.println(StringVerifier.isValidString("\"\"\"hello\"\""));
	 * System.out.println(StringVerifier.isValidString("he\"llo"));
	 * System.out.println(StringVerifier.isValidString("hel\"\"lo"));
	 * System.out.println(StringVerifier.isValidString("hello"));
	 * 
	 * // should print true, false, false, false System.out.println(
	 * StringVerifier.verifySingleQuotedInput("\'hello world\'" ));
	 * System.out.println(
	 * StringVerifier.verifySingleQuotedInput("hello world\'")) ;
	 * System.out.println(
	 * StringVerifier.verifySingleQuotedInput("\'hello world")) ;
	 * System.out.println( StringVerifier.verifySingleQuotedInput("hello world"));
	 * 
	 * // should print true, true, false
	 * System.out.println(StringVerifier.verifyQuotedInput("\"hello world\""));
	 * System.out.println(StringVerifier.verifyQuotedInput("\'hello world\'"));
	 * System.out.println(StringVerifier.verifyQuotedInput("hello world"));
	 * 
	 * }
	 */
}
