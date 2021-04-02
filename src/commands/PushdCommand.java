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
import system.FileSystem;
import system.FileSystemI;
import verification.FileSystemVerifier;

/**
 * The Class PushdCommand to implement the functionality of adding current
 * directory, to directory stack, and changing directory to specified directory.
 * 
 * @author Shammo Talukder
 */
public class PushdCommand extends Command {

	/** Necessary collaborators of the class */
	private CdCommand cd = new CdCommand();
	private FileSystemI fs = FileSystem.getFileSystemInstance();

	/**
	 * Push current working directory to directory stack. Then, change current
	 * working directory to specified directory.
	 * 
	 * @param inputWords The array of required arguments to successfully execute
	 *                   the requested command
	 * @return empty string to represent success and null if an error occurs
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		if (inputWords.length != 1) {
			ErrorHandler.processError(5);
			return null;
		}

		else {
			// push current dir to stack, call cd, if valid dir given
			if (FileSystemVerifier.isValidDirectory(inputWords[0])
					&& !(inputWords[0].contains("//"))) {
				this.fs.pushDirectoryToStack(fs.getCurrentDirectory());
				return this.cd.executeCommandOperations(inputWords);
			}

			else {
				ErrorHandler.processError(2, inputWords[0]);
				return null;
			}
		}
	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'pushd' command.
	 * 
	 * @return the manual of the 'pushd' command
	 */
	@Override
	public String getManual() {
		return "NAME\n"
				+ "\tpushd - push current working directory to directory stack,\n\n"
				+ "\tand change current working directory.\n\n" + "SYNOPSIS\n"
				+ "\tpushd DIRECTORY\n\n" + "DESCRIPTION\n"
				+ "\tPush current directory to directory stack,\n\n"
				+ "\tthen change current directory to DIRECTORY.\n";
	}

	/*
	 * BASIC SANITY CHECK TESTS public static void main(String[] args) {
	 * 
	 *
	 * PushdCommand pushdTest = new PushdCommand(); String[] flags = {""};
	 * String[] args2 = {""};
	 * 
	 * pushdTest.executeCommandOperations(flags, args2); //invalid
	 * 
	 * pushdTest = new PushdCommand(); flags[0] = ">";
	 * pushdTest.executeCommandOperations(flags, args2); //invalid
	 * 
	 */
}
