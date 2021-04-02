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

public class InputInterpreter {
	/**
	 * This method will parse user input into a 3 element array of String arrays,
	 * where each element represent (in order) the command name, flags, and
	 * arguments.
	 * 
	 * @param input The input string from the user
	 * @return The array of String arrays that represent the parsed input
	 */
	public static String[][] parseCommandInput(String input) {
		String[] words = split(input);

		ArrayList<String> flags = FlagInterpreter.findFlags(words);
		ArrayList<String> arguments = ArgumentInterpreter.findArguments(words);

		ArrayList<ArrayList<String>> commandParts = 
				new ArrayList<ArrayList<String>>();

		ArrayList<String> command = new ArrayList<String>();
		command.add(words[0]);

		// create an array list consisting of the command, flags and arguments
		commandParts.add(command);
		commandParts.add(flags);
		commandParts.add(arguments);

		// create and array from the array list
		String[][] commandPartsArray = new String[3][];
		commandPartsArray[0] = commandParts.get(0)
				.toArray(new String[commandParts.get(0).size()]);
		commandPartsArray[1] = commandParts.get(1)
				.toArray(new String[commandParts.get(1).size()]);
		commandPartsArray[2] = commandParts.get(2)
				.toArray(new String[commandParts.get(2).size()]);

		return commandPartsArray;
	}

	/**
	 * This method will parse just the flags in the given array of inputed
	 * words/flags.
	 * 
	 * @param words The input string array created from the user input
	 * @return A string array of flags
	 */
	public static String[] parseOnlyFlags(String[] words) {

		ArrayList<String> flags = FlagInterpreter.findFlags(words);

		String[] flagPartsArray = new String[flags.size()];
		flagPartsArray = flags.toArray(new String[flags.size()]);

		return flagPartsArray;
	}

	/**
	 * This method will parse just the arguments in the given array of inputed
	 * words/flags.
	 * 
	 * @param words The input string array created from the user input
	 * @return A string array of arguments
	 */
	public static String[] parseOnlyArguments(String[] words) {

		ArrayList<String> arguments = ArgumentInterpreter.findArguments(words);

		String[] argumentPartsArray = new String[arguments.size()];
		argumentPartsArray = arguments.toArray(new String[arguments.size()]);

		return argumentPartsArray;
	}

	public static String[] split(String input) {
		input = input.trim();
		String[] words = null;
		ArrayList<String> wordsList = new ArrayList<>();
		int quoteStartIndex = -1;
		int quoteEndIndex = -1;

		quoteStartIndex = findStartQuoteIndex(input, quoteStartIndex);

		if (quoteStartIndex != -1) {
			quoteEndIndex = findQuoteEndIndex(input, quoteEndIndex, quoteStartIndex);
		}

		if (quoteStartIndex != 0 && quoteEndIndex != 0
				&& quoteStartIndex != quoteEndIndex) {
			words = createQuotedWordsArray(words, input, quoteEndIndex,
					quoteStartIndex, wordsList);
		} else {
			words = input.split("\\s");
		}
		words = getRidOfEmptyStrings(words);
		return words;
	}

	private static String[] createQuotedWordsArray(String[] words, String input,
			int quoteEndIndex, int quoteStartIndex, ArrayList<String> wordsList) {

		String[] splitWordsBeforeQuotes = input.substring(0, quoteStartIndex - 1)
				.split("\\s");

		String[] splitWordsAfterQuotes = null;
		if (quoteEndIndex < input.length() - 1) {
			splitWordsAfterQuotes = input.substring(quoteEndIndex + 1).split("\\s");
		}

		String quotedSection = input.substring(quoteStartIndex, quoteEndIndex + 1);
		for (int i = 0; i < splitWordsBeforeQuotes.length; i++) {
			wordsList.add(splitWordsBeforeQuotes[i]);
		}
		wordsList.add(quotedSection);
		if (splitWordsAfterQuotes != null) {
			for (int i = 0; i < splitWordsAfterQuotes.length; i++) {
				wordsList.add(splitWordsAfterQuotes[i]);
			}
		}

		words = wordsList.toArray(new String[wordsList.size()]);
		return words;
	}

	private static int findStartQuoteIndex(String input, int quoteStartIndex) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '"' && i == 0) {
				quoteStartIndex = i;
				break;
			} else if (i > 0 && Character.isWhitespace(input.charAt(i - 1))
					&& input.charAt(i) == '"') {
				quoteStartIndex = i;
				break;
			}

		}
		return quoteStartIndex;
	}

	private static int findQuoteEndIndex(String input, int quoteEndIndex,
			int quoteStartIndex) {
		for (int j = input.length() - 1; j >= quoteStartIndex; j--) {
			if (input.charAt(j) == '"' && j == input.length() - 1) {
				quoteEndIndex = j;
				break;
			} else if (j < input.length() - 1
					&& Character.isWhitespace(input.charAt(j + 1))
					&& input.charAt(j) == '"') {
				quoteEndIndex = j;
				break;
			}
		}
		return quoteEndIndex;
	}

	private static String[] getRidOfEmptyStrings(String[] words) {

		int sizeCount = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() >= 1) {
				sizeCount++;

			}
		}
		String[] wordsCopy = new String[sizeCount];
		int j = 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() >= 1) {
				wordsCopy[j] = words[i];
				j++;
			}
		}
		return wordsCopy;
	}

	/*
	 * public static String[] splitInputIntoWords(String input) { String words[]
	 *  =
	 * null; Object doubleQuotedAndIgnoreSection[] =
	 * findDoubleQuotedSection(input); if (doubleQuotedAndIgnoreSection != null) 
	 * {
	 * String beforeQuotes = input.substring(0, (int)
	 * doubleQuotedAndIgnoreSection[1]); String afterQuotes = input
	 * .substring((int) doubleQuotedAndIgnoreSection[2]); String
	 * doubleQuotedSection = (String) doubleQuotedAndIgnoreSection[0];
	 * beforeQuotes = beforeQuotes.replaceAll("\\s+", " "); afterQuotes =
	 * afterQuotes.replaceAll("\\s+", " "); String[] wordsBefore =
	 * beforeQuotes.split(" "); String[] wordsAfter = afterQuotes.split(" ");
	 * words = new String[wordsBefore.length + 1 + wordsAfter.length];
	 * 
	 * int i = 0; for (i = 0; i < wordsBefore.length; i++) { words[i] =
	 * wordsBefore[i]; } words[i] = doubleQuotedSection; i++; for (int j = 0; j <
	 * wordsAfter.length; j++) { words[j + i] = wordsAfter[j]; } } else { input =
	 * input.replaceAll("\\s+", " "); words = input.split(" "); } words =
	 * getRidOfEmptyStrings(words); return words; } private static Object[]
	 * findStartIndexAndFoundStatus(int i, boolean doubleQuotedStartFound, String
	 * input) { for (i = 0; i < input.length(); i++) { if (input.charAt(i) == '"'
	 * )
	 * { if (i != 0 && input.charAt(i - 1) == ' ') { doubleQuotedStartFound =
	 * true; } else if (i == 0) { doubleQuotedStartFound = true; } break; } }
	 * Object[] startIndexAndFoundStatus = { i, doubleQuotedStartFound }; return
	 * startIndexAndFoundStatus; }
	 * 
	 * private static Object[] findEndIndexAndFoundStatus(int i, int j, boolean
	 * doubleQuotedEndFound, String input) { for (j = input.length() - 1; j > i;
	 * j--) { if (input.charAt(j) == '"') { if (j != input.length() - 1 &&
	 * input.charAt(j + 1) == ' ') { doubleQuotedEndFound = true; } else if (j ==
	 * input.length() - 1) { doubleQuotedEndFound = true; } break; } } Object[]
	 * endIndexAndFoundStatus = { i, j, doubleQuotedEndFound }; return
	 * endIndexAndFoundStatus; }
	 * 
	 * private static Object[] findSubstringAndIndex(boolean
	 * doubleQuotedStartFound, boolean doubleQuotedEndFound, int i, int j, String
	 * input) { String doubleQuotedSection = ""; if (doubleQuotedStartFound &&
	 * doubleQuotedEndFound) { if (j == input.length() - 1) { doubleQuotedSection
	 * = input.substring(i, j + 1); } else { j = j + 1; doubleQuotedSection =
	 * input.substring(i, j); } } else { return null; } Object[] substringAndInde
	 * x
	 * = { doubleQuotedSection, i, j }; return substringAndIndex; }
	 * 
	 * private static Object[] findDoubleQuotedSection(String input) { boolean
	 * doubleQuotedStartFound = false; boolean doubleQuotedEndFound = false; int 
	 * i
	 * = 0, j = input.length() - 1;
	 * 
	 * Object[] startIndexAndFoundStatus = findStartIndexAndFoundStatus(i,
	 * doubleQuotedStartFound, input); i = (int) startIndexAndFoundStatus[0];
	 * doubleQuotedStartFound = (Boolean) startIndexAndFoundStatus[1]; if
	 * (doubleQuotedStartFound == false) { return null; }
	 * 
	 * Object[] endIndexAndFoundStatus = findEndIndexAndFoundStatus(i, j,
	 * doubleQuotedEndFound, input); i = (int) endIndexAndFoundStatus[0]; j =
	 * (int) endIndexAndFoundStatus[1]; doubleQuotedEndFound = (Boolean)
	 * endIndexAndFoundStatus[2]; if (doubleQuotedEndFound == false) { return
	 * null; }
	 * 
	 * Object[] substringAndIndex = findSubstringAndIndex(doubleQuotedStartFound,
	 * doubleQuotedEndFound, i, j, input); Object doubleQuotedAndIgnoreSection[] =
	 * { (String) substringAndIndex[0], (int) substringAndIndex[1], (int)
	 * substringAndIndex[2] + 1 }; return doubleQuotedAndIgnoreSection; }
	 */

	/**
	 * This method will print 2D String array according to format of return value
	 * of parseCommandInput() in human friendly notation
	 * 
	 * @param parsedInput 2D String array formatted according to return value of
	 *                    parseCommandInput()
	 */
	/*
	 * public static void printParsedInput(String[][] parsedInput) { for (int i =
	 * 0; i < parsedInput.length; i++) { for (int j = 0; j <
	 * parsedInput[i].length; j++) { System.out.print(parsedInput[i][j] + "  ");}
	 * System.out.println(); } }
	 * 
	 * public static void main(String[] args) { String[][] parsed =
	 * InputInterpreter.parseCommandInput("mkdir /\"dir 1\"");
	 * InputInterpreter.printParsedInput(parsed); }
	 */
}
