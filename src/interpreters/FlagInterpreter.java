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

package interpreters;

import java.util.ArrayList;

/**
 * Finds the given flags in the input
 * 
 * @author Rakshit Patel
 */
public class FlagInterpreter {

	// list of allowed flags
	private static String[] redirectionFlags = { ">", ">>" };
	private static String[] commandCustomizationFlags = { "-R" };
	private static String[] flagList = concatArrays(redirectionFlags,
			commandCustomizationFlags);

	private static String[] concatArrays(String[] first, String[] second) {
		String[] both = new String[first.length + second.length];
		System.arraycopy(first, 0, both, 0, first.length);
		System.arraycopy(second, 0, both, first.length, second.length);
		return both;
	}

	private static boolean checkWithFlagArray(String flagCheck, String[] flags) {
		for (String i : flags) {
			if (i.contentEquals(flagCheck)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * looks through the flagList array to see if the input string is valid flag
	 * 
	 * @param flagCheck The string that will be checked to see if its a valid 
	 * flag
	 * @return true if flagCheck is valid flag, false otherwise
	 */
	public static boolean checkIfFlag(String flagCheck) {
		return FlagInterpreter.checkWithFlagArray(flagCheck, flagList);
	}

	/**
	 * looks through the flagList array to see if the input string is valid flag
	 * and flag is used for redirection
	 * 
	 * @param flagCheck The string that will be checked to see if its a valid 
	 * flag
	 * @return true if flagCheck is valid flag, false otherwise
	 */
	public static boolean checkIfRedirectionFlag(String flagCheck) {
		return FlagInterpreter.checkWithFlagArray(flagCheck, redirectionFlags);
	}

	/**
	 * Returns an array list of all inputed flags for the command
	 * 
	 * @param words An array of strings that are surrounded by white space
	 *              characters in the input
	 * @return Array list of flags
	 */
	public static ArrayList<String> findFlags(String[] words) {
		ArrayList<String> flags = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {
			if (checkIfFlag(words[i])) {
				flags.add(words[i]);
			}
		}
		return flags;
	}

	public static boolean testFlagInterpreter(String inputCommand,
			String[] correctFlags) {
		String[] words = (inputCommand.replaceAll("\\s+", " ")).split(" ");
		FlagInterpreter fi = new FlagInterpreter();
		ArrayList<String> foundFlags = FlagInterpreter.findFlags(words);
		if (correctFlags.length != foundFlags.size()) {
			return false;
		}
		for (int i = 0; i < correctFlags.length; i++) {
			if (!correctFlags[i].contentEquals(foundFlags.get(i))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * public static void main(String[] args) { String[] tests = {
	 * "speak -a command -f >> txt.txt >>", "echo hello > file",
	 * "echo hello >> file", "dasdasdas >> -noflag", "hello world" }; String[][]
	 * allAns = { { ">>", ">>" }, { ">" }, { ">>" }, { ">>" }, {} }; for (int i =
	 * 0; i < tests.length; i++) { System.out
	 * .println(FlagInterpreter.testFlagInterpreter(tests[i], allAns[i])); }
	 * 
	 * }
	 */
}
