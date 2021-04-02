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
//Student3: Shammo Talukder
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

import path.Directory;
import system.FileSystem;
import system.FileSystemI;
import system.FileSystemManipulation;
import verification.FileSystemVerifier;
import driver.ErrorHandler;

/**
 * The Class CdCommand to implement the functionality of changing directories.
 * 
 * @author Shammo Talukder
 */
public class CdCommand extends Command {

	/* Necessary collaborators of the class */
	private FileSystemI fs = FileSystem.getFileSystemInstance();

	/**
	 * Changes current working directory to specified directory.
	 *
	 * @param inputWords The array of required arguments to successfully execute
	 *                   the requested command
	 * @return empty string to indicate success, null on error
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		if (inputWords.length != 1) {
			ErrorHandler.processError(5);
			return null;
		}

		// set current directory if valid
		if (FileSystemVerifier.isValidDirectory(inputWords[0])
				&& !(inputWords[0].contains("//"))) {
			Directory newCurrentDirectory = (Directory) FileSystemManipulation
					.findFileSystemPath(inputWords[0]);
			this.fs.setCurrentDirectory(newCurrentDirectory);
			return "";
		}

		else {
			ErrorHandler.processError(2, inputWords[0]);
			return null;
		}

	}

	/**
	 * Returns a String containing the manual for the functionalities of the 'cd'
	 * command.
	 * 
	 * @return the manual of the 'cd' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\tcd - change working directory\n\n" + "SYNOPSIS\n"
				+ "\tcd DIRECTORY\n\n" + "DESCRIPTION\n"
				+ "\tChange current working directory to specified DIRECTORY." + "\n";
	}

	/*
	 * Testing error handling public static void main(String[] args) {
	 * 
	 * 
	 * String[] flags = {"-"}; String[] arg = {"/ee/eef/flasd"}; String[2] args =
	 * {arg[0], "//"};
	 * 
	 * CdCommand c = new CdCommand(); PwdCommand pdir = new PwdCommand();
	 * 
	 * c.executeCommandOperations(flags, arg);
	 * pdir.executeCommandOperations(flags, arg);
	 * 
	 * c = new CdCommand();
	 * 
	 * flags[0] = "";
	 * 
	 * c.executeCommandOperations(flags, arg);
	 * pdir.executeCommandOperations(flags, arg);
	 * 
	 * c = new CdCommand();
	 * 
	 * arg[0] = "//";
	 * 
	 * c.executeCommandOperations(flags, arg);
	 * pdir.executeCommandOperations(flags, arg);
	 * 
	 * c = new CdCommand();
	 * 
	 * arg[0] = "//";
	 * 
	 * c.executeCommandOperations(flags, arg);
	 * pdir.executeCommandOperations(flags, arg);
	 * 
	 * }
	 */
}
