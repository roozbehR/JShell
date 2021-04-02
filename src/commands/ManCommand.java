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

import java.util.Hashtable;
import commandUtils.AllCommands;
import driver.ErrorHandler;
import interpreters.InputInterpreter;

/**
 * Man is a Command where it can print the previously stored documentation for
 * each command base on which command user wants to see.
 * 
 * @author Roozbeh Yadollahi
 */

public class ManCommand extends Command {

	/**
	 * Prints a String containing the documentation for the functionalities of 
	 * the command that the user wants to see.
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		String[] flags = InputInterpreter.parseOnlyFlags(inputWords);
		String[] args = InputInterpreter.parseOnlyArguments(inputWords);
		Hashtable<String, Command> listOfCommands = AllCommands
				.getNamesToCommandHashtable();
		if (args.length > 1 || args.length == 0) {
			ErrorHandler.processError(4);
			return null;
		}
		if (flags.length > 0) {
			ErrorHandler
					.processError("The command entered does not accept any flags.");
			;
			return null;
		} else if (!listOfCommands.containsKey(args[0])) {
			ErrorHandler
					.processError("The command " + args[0] + " is not recoginzed");
			return null;
		}

		return listOfCommands.get(args[0]).getManual() + "\n";
	}

	/**
	 * Returns a String containing the documentation for the functionalities of
	 * the 'man' command.
	 * 
	 * @return manual which is the documentation of the 'man' command and null in
	 *         case of an error
	 */
	@Override
	public String getManual() {
		String manual = "NAME\n"
				+ "\tman - format and display the on-line manual pages\n\n"
				+ "\tSYNOPSIS\n" + "\tman Operand\n\n" + "DESCRIPTION\n"
				+ "\tman displays the manual pages of an existing command.\n"
				+ "\tman is followed by a mandatory single opreand.\n"
				+ "\tIf the operand is an existing command name, the manual specific\n"
				+ "\tto that command is displayed, otherwise an appropriate error\n"
				+ "\tmessage is displayed.\n";
		return manual;
	}
	/*
	 * public static void main(String[] args) { CommandManager cm = new
	 * CommandManager(); cm.processCommand("man unknownCommand");
	 * cm.processCommand("man"); cm.processCommand("man command1 command2");
	 * cm.processCommand("man ls"); cm.processCommand("man mkdir");
	 * cm.processCommand("man pwd"); }
	 */
}
