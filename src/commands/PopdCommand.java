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
import path.Directory;
import system.FileSystem;
import system.FileSystemI;

/**
 * The Class PopdCommand to implement the functionality of changing directories
 * to the directory on top of the directory stack, and removing it.
 * 
 * @author Shammo Talukder
 */
public class PopdCommand extends Command {

	/** Necessary collaborator of the class */
	private CdCommand cd = new CdCommand();
	private FileSystemI fs = FileSystem.getFileSystemInstance();

	/**
	 * Pops a Directory from top of stack, and changes directory to it.
	 * 
	 * @param inputWords The array of required arguments to successfully execute
	 *                   the requested command
	 * @return empty string to represent success and null if an error occurs
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		if (inputWords.length != 0) {
			ErrorHandler.processError(4);
			return null;
		}

		else if (this.fs.checkDirectoryStackEmpty()) {
			ErrorHandler.processError("The directory stack is currently empty");
			return null;
		}

		else {
			Directory newCurrentDirectory = this.fs.popDirectoryFromStack();
			String[] args = { newCurrentDirectory.getAbsolutePathname() };
			return this.cd.executeCommandOperations(args);
		}

	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'popd' command.
	 * 
	 * @return the manual of the 'popd' command
	 */
	@Override
	public String getManual() {
		return "NAME\n"
				+ "\tpopd - pop directory from directory stack and change directory"
				+ " to it.\n" + "\n" + "SYNOPSIS\n" + "\tpopd\n\n" + "DESCRIPTION\n"
				+ "\tRemove directory from top of directory stack,\n\n"
				+ "\tthen change current working directory to it.\n";
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * FileSystem fsTesting = FileSystem.getFileSystemInstance(); String[] flags
	 *  =
	 * {""}; String[] args2 = {""};
	 * 
	 * PopdCommand pop = new PopdCommand(); pop.executeCommandOperations(flags,
	 * args2); //empty stack
	 * 
	 * pop = new PopdCommand(); flags[0] = ">";
	 * pop.executeCommandOperations(flags, args2); //no flags for popd
	 * 
	 * pop = new PopdCommand(); args2[0] = "?";
	 * pop.executeCommandOperations(flags, args2); //no args
	 * 
	 * flags[0] = ""; args2[0] = "";
	 * 
	 * fsTesting.setCurrentDirectory(new Directory("test", "/test")); //valid
	 * pop.executeCommandOperations(flags, args2); }
	 */
}
