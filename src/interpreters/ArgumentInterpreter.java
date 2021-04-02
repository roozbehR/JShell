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
 * Finds the given arguments in the input
 * 
 * @author Rakshit Patel
 */
public class ArgumentInterpreter {
	/**
	 * Returns an array list of all inputed arguments for the command
	 * 
	 * @param words An array of strings that are surrounded by white space
	 *              characters in the input
	 * @return Array list of arguments
	 */
	public static ArrayList<String> findArguments(String[] words) {
		ArrayList<String> arguments = new ArrayList<String>();
		FlagInterpreter flagInterpreter = new FlagInterpreter();

		boolean stringAdded = false;
		Object doubleQuotedAndIgnoreSection[] = findDoubleQuotedSection(words);
		doubleQuotedAndIgnoreSection = null;

		// index starts at 1 to skip the command
		for (int i = 0; i < words.length; i++) {
			if (doubleQuotedAndIgnoreSection != null) {
				// if the word is not a flag, then it is an argument
				if (!flagInterpreter.checkIfFlag(words[i])
						&& !(i >= (int) doubleQuotedAndIgnoreSection[1]
								&& i <= (int) doubleQuotedAndIgnoreSection[2])) {
					arguments.add(words[i]);
				} else if (!flagInterpreter.checkIfFlag(words[i])
						&& i >= (int) doubleQuotedAndIgnoreSection[1]
						&& i <= (int) doubleQuotedAndIgnoreSection[2] && !stringAdded) {
					arguments.add((String) doubleQuotedAndIgnoreSection[0]);
					stringAdded = true;
				}
			} else {
				if (!flagInterpreter.checkIfFlag(words[i])) {
					arguments.add(words[i]);
				}
			}
		}
		return arguments;
	}

	public static Object[] findDoubleQuotedSection(String[] words) {
		String doubleQuotedSection = "";
		boolean doubleQuotedStartFound = false;
		boolean doubleQuotedEndFound = false;
		int i = 0, j = words.length - 1;
		for (i = 0; i < words.length; i++) {
			if (words[i].startsWith("\"")) {
				// System.out.println(i + " "+ words[i]);
				doubleQuotedStartFound = true;
				break;
			}
		}
		for (j = words.length - 1; j > i; j--) {

			if (words[j].endsWith("\"")) {
				// System.out.println(j + " "+ words[j]);
				doubleQuotedEndFound = true;
				break;
			}
		}
		if (doubleQuotedStartFound && doubleQuotedEndFound) {
			for (int k = i; k <= j; k++) {
				doubleQuotedSection += words[k] + " ";
			}
		} else {
			return null;
		}
		// System.out.println("Double QUOTED: " + doubleQuotedSection);
		Object doubleQuotedAndIgnoreSection[] = { doubleQuotedSection, i, j };

		return doubleQuotedAndIgnoreSection;
	}

	public static void testArgumentInterpreter(String[] words) {
		ArgumentInterpreter ai = new ArgumentInterpreter();
		ArrayList<String> arguments = ai.findArguments(words);
		System.out.println("Arguments Found:");
		for (String i : arguments) {
			System.out.println("\t" + i);
		}
	}

	public static void main(String[] args) {
		String[][] tests = {
				{ "speak", "-a", "command", "-f", ">", ">>", "txt.txt", ">>" },
				{ "history", "100", "200" },
				{ "command", "argumentBefore", ">>", "argumentAfter" }, { "command" },
				{ "command", ">>" }, {},
				{ "command", "this", "is", "not", "quoted", "\"start", "double",
						"quoted,", "end", "double", "quoted\"", "extra" },
				{ "command", "a\"", "\"b", "\"c\"", "d\"" },
				{ "echo", "\"hello", "there", "are", "spaces\"", ">", "test2" } };
		for (String[] test : tests) {
			ArgumentInterpreter.testArgumentInterpreter(test);
			String[] doubleQuotedSection = (String[]) ArgumentInterpreter
					.findDoubleQuotedSection(test);
			System.out.println(doubleQuotedSection);
		}

	}

}
