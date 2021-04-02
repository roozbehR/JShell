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
import system.CommandHistory;
import system.CommandHistoryI;
import system.CommandManager;

/**
 * This class implements the history command, which allows the user to view a
 * list of recently entered command calls.
 * 
 * @author Farhan Chowdhury
 */
public class HistoryCommand extends Command {

	private static CommandHistoryI commandHistory = CommandHistory
			.getCommandHistoryInstance();

	/**
	 * This method prints the history of commands called in a list according to
	 * parsed user input.
	 * 
	 * @param flags any flags that may have been inputed
	 * @Param arguments any arguments that may have been inputed
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		int stackSize = commandHistory.getStackSize();
		String toOutput = "";
		int numCommands = processInput(inputWords, stackSize);
		if (numCommands == -1) {
			return null;
		}
		// get the commands called from stack in CommandManager
		for (int i = 0; i < numCommands; i++) {
			toOutput = String.format("%d. ", stackSize - i)
					+ commandHistory.getStackCommand(stackSize - 1 - i) + "\n" + 
					toOutput;
		}
		return toOutput;
	}

	private int processInput(String[] inputWords, int stackSize) {
		int numCommands = -1;

		if (inputWords.length == 0) {
			numCommands = stackSize;
		} else if (inputWords.length == 2 && stringIsNumber(inputWords[0])) {
			ErrorHandler.processError("Recieved integer when flag was expected.");
			return -1;
		} else {
			int idx = 0;
			if (stringIsNumber(inputWords[idx])) {
				numCommands = Integer.parseInt(inputWords[idx]);
				if (numCommands > stackSize) {
					numCommands = stackSize;
				} else if (numCommands < 0) {
					ErrorHandler.processError("Negative numbers are not allowed");
					return -1;
				}
				idx++;
			}
			if (idx < inputWords.length
					&& !FlagInterpreter.checkIfRedirectionFlag(inputWords[idx])) {
				if (idx != 0) {
					ErrorHandler.processError("Non numerical input is not allowed.");
				} else {
					ErrorHandler
							.processError("Too many arguments were entered for history");
				}
				return -1;
			}
		}
		return numCommands;
	}

	private boolean stringIsNumber(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * This method returns a manual that explains the functionality of the 
	 * history command.
	 * 
	 * @return String the manual
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\thistory - output a list of commands calls\n\n"
				+ "SYNOPSIS\n" + "\thistory [number]\n\n" + "DESCRIPTION\n"
				+ "\tPrint out recent commands, one command per line.\n\n"
				+ "\tWith no arguments, print out all recent commands.\n\n"
				+ "\tIf number is a non-negative integer, print out most recent"
				+ "command calls where the number of command calls is equal to "
				+ "number.\n";
	}

//  public static void main(String[] args) {
//    System.out.println(new HistoryCommand());
//    CommandManager.processCommand("hello");
//    CommandManager.processCommand("history 0");
//    CommandManager.processCommand("history");
//    CommandManager.processCommand("history -5");
//    CommandManager.processCommand("history 10 20");
//    CommandManager.processCommand("history 100");
//    CommandManager.processCommand("history hello");
//    CommandManager.processCommand("history 10 >> file");
//    CommandManager.processCommand("history 10 > file");
//    // CommandManager.processCommand("cat file");
//    // System.out.println();
//    // HistoryCommand hc = new HistoryCommand();
//    // String[] flags = {">"};
//    // ArbitraryOutputMethod aom = hc.getAppropriateOutputMethod(flags);
//    // CommandManager.processCommand("echo \"hello\" > file");
//    // System.out.println("DONE");
//    // CommandManager.processCommand("history 10 >> file");
//    // System.out.println("DONE");
//    // CommandManager.processCommand("cat file");
//  }
}
