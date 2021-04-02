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

package system;

import java.util.Arrays;
import java.util.Hashtable;
import commandUtils.AllCommands;
import commandUtils.CommandOutputProducer;
import commands.Command;
import driver.ErrorHandler;
import interpreters.InputInterpreter;

/**
 * This class will map parsed user input to appropriate commands and call them
 * with the parsed flags and arguments.
 * 
 * @author Farhan Chowdhury
 *
 */
public class CommandManager {
	private static CommandHistory commandHistory = CommandHistory
			.getCommandHistoryInstance();
	private static Hashtable<String, Command> commands = AllCommands
			.getNamesToCommandHashtable();

	/**
	 * This method returns the Command paired with the input commandID, or null 
	 * if invalid commandID is given.
	 * 
	 * @param commandIdentifier the commandID
	 * @return the corresponding command (or null if commandID is invalid)
	 */
	protected static Command getCommand(String commandIdentifier) {
		return CommandManager.commands.get(commandIdentifier);
	}

	/**
	 * This method parses user input and attempts to call appropriate command
	 * based on input, and records the command call in a stack.
	 * 
	 * @param userInput the user input
	 */
	public static void processCommand(String userInput) {
		CommandManager.commandHistory.addCommandToStack(userInput);
		processCommandWithoutAddingToStack(userInput);
	}

	/**
	 * This method parses user input and attempts to call appropriate command
	 * based on input.
	 * 
	 * @param userInput the user input
	 */
	public static void processCommandWithoutAddingToStack(String userInput) {
		try {
			String output;
			String[] inputWords = InputInterpreter.split(userInput);
			if (commands.containsKey(inputWords[0])) {
				output = CommandManager.getCommand(inputWords[0])
						.executeCommandOperations(
								Arrays.copyOfRange(inputWords, 1, inputWords.length));
				if (output != null) {
					CommandOutputProducer.processCommandOutput(inputWords, output);
				}
			} else {
				ErrorHandler
						.processError("The command entered is not recognized by JShell.");
			}
		} catch (Exception e) {
			ErrorHandler.processError("Invalid input was entered for the command.");
		}
	}

	/*
	 * public static void main(String[] args) { CommandManager cm = new
	 * CommandManager();
	 * 
	 * do history cm.processCommand("history"); cm.processCommand("history 2");
	 * cm.processCommand("hello -f -g world");
	 * cm.processCommand("echo -f -g world");
	 * cm.processCommand("dist -f -g world"); cm.processCommand("history 2");
	 * cm.processCommand("history");
	 */

	/*
	 * do echo cm.processCommand("echo hello");
	 * cm.processCommand("echo \"hello\"");
	 * cm.processCommand("echo \"hello there\""); // Do further testing in JShell
	 * 
	 * }
	 */
}
