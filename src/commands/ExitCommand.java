// **********************************************************
// Assignment2:
// Student1: Farhan Chowdhury
// UTORID user_name: chowd601
// UT Student #: 1006068176
// Author: Farhan Chowdhury
//
// Student2: Rakshit Patel
// UTORID user_name: patel939
//UT Student #: 1005918152
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
import driver.RunChecker;

/**
 * This class allows the user to exit JShell.
 * 
 * @author Farhan Chowdhury
 *
 */
public class ExitCommand extends Command {

	/**
	 * This method sets the checker that JShell uses to false, so JShell will 
	 * stop running after this command is processed, thus terminating the 
	 * program.
	 * 
	 * @param inputWords any input arguments that the user might have provided
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		if (inputWords.length != 0) {
			ErrorHandler.processError("Too many arguments were entered for exit.");
			return null;
		}
		RunChecker.getRunCheckerInstance().setRunning(false);
		return "";
	}

	@Override
	public String getManual() {
		return "NAME\n" + "\texit - exit the JShell\n\n" + "SYNOPSIS\n"
				+ "\texit \n\n" + "DESCRIPTION\n"
				+ "\tExit the JShell and terminate the program.\n";
	}

	/*
	 * public static void main(String[] args) { RunChecker rc =
	 * RunChecker.getRunCheckerInstance(); String[] empty = {}; String[] flags =
	 * {"-f"}; String[] arguments = {"hello"}; ExitCommand ec = new 
	 * ExitCommand();
	 * 
	 * rc.setRunning(true); ec.executeCommandOperations(empty, empty);
	 * System.out.println(rc.getRunning());
	 * 
	 * rc.setRunning(true); ec.executeCommandOperations(flags, empty);
	 * System.out.println(rc.getRunning());
	 * 
	 * rc.setRunning(true); ec.executeCommandOperations(empty, arguments);
	 * System.out.println(rc.getRunning());
	 * 
	 * rc.setRunning(true); ec.executeCommandOperations(flags, arguments);
	 * System.out.println(rc.getRunning()); }
	 */
}
