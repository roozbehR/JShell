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

package commands;

import driver.ErrorHandler;
import interpreters.FlagInterpreter;
import verification.FileSystemVerifier;
import verification.StringVerifier;
import commandUtils.CommandOutputProducer;

/**
 * The Class EchoCommand to implement the functionality of printing inputWords
 * to the shell. Arguments may be written, or appended to files, through the 
 * use of flags.
 * 
 * @author Shammo Talukder
 */
public class EchoCommand extends Command {

	/**
	 * Print a String to the screen by default. flag '>' : Create text file with
	 * provided name if necessary, otherwise clear its contents, and write to it
	 * flags '>>' : Same as '>', but will not clear contents, only append to the
	 * end of the file.
	 *
	 * @param flags      The array of required flags to successfully execute the
	 *                   requested command
	 * @param inputWords The array of required inputWords to successfully execute
	 *                   the requested command
	 * @return the valid string accepted as input
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		if ((inputWords.length == 2) || (inputWords.length > 3)
				|| (inputWords.length == 0)) {
			ErrorHandler.processError(5);
		}

		else if ((inputWords.length >= 1)
				&& !(StringVerifier.isValidString(inputWords[0]))) {
			ErrorHandler.processError(9, inputWords[0]);
		}

		else {
			return inputWords[0].substring(1, inputWords[0].length() - 1) + "\n";
		}

		return null;
	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'echo' command.
	 * 
	 * @return the manual of the 'echo' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\techo - ouptut string input to shell or file\n\n"
				+ "SYNOPSIS\n" + "\techo STRING [OPTION] [FILE]\n\n" + "DESCRIPTION\n"
				+ "\tPrint STRING to shell.\n\n"
				+ "\tWith no OPTION and FILE, print STRING to shell.\n\n"
				+ "\tEither both OPTION and FILE are specified, or neither.\n\n"
				+ "\tIf option is >, clear content of FILE, and write STRING "
				+ "to FILE.\n\n"
				+ "\tIf option is >>, append STRING to end of FILE.\n\n"
				+ "\tIn either case, FILE will be created if it does not exist." + 
				"\n";
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * String input = "\"hello world\""; String[] ag = {}; String[] ag2 = 
	 * {input};
	 * 
	 * EchoCommand e = new EchoCommand();
	 * 
	 * e.executeCommandOperations(ag, ag2);
	 * 
	 * e = new EchoCommand();
	 * 
	 * e.executeCommandOperations(ag, ag);
	 * 
	 * System.out.println(e.getManual()); }
	 */
}
