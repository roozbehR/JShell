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

package driver;

import io.StdIOStream;
import system.CommandManager;
import system.FileSystem;

/**
 * This is the main driver to run an instance of the shell.
 * 
 * @author Farhan Chowdhury
 */
public class JShell {

	/**
	 * This method runs the shell process.
	 */
	public static void runShell() {
		RunChecker runChecker = RunChecker.getRunCheckerInstance();
		String input;

		while (runChecker.getRunning()) {
			JShell.promptUser();
			input = JShell.getUserInput();
			if (input.contentEquals("")) {
				continue;
			} else {
				CommandManager.processCommand(input);
			}
		}
	}

	/**
	 * This method will prompt the user for the next input.
	 */
	public static void promptUser() {
		String promptMsg = FileSystem.getFileSystemInstance().getCurrentDirectory()
				.getAbsolutePathname() + "$ ";
		StdIOStream.outputContent(promptMsg);
	}

	/**
	 * This method gets and returns the user input.
	 * 
	 * @return A string representing a line of user input
	 */
	public static String getUserInput() {
		return StdIOStream.getInputContent();
	}

	public static void main(String[] args) {
		JShell.runShell();
	}

}
