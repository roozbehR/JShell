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
//UT Student #: 1006317237
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
import path.TextFile;
import system.FileSystem;
import system.FileSystemI;
import system.FileSystemManipulation;
import verification.FileSystemVerifier;

/**
 * The class RmCommand to implement the functionality of removing a directory
 * and its children from the file system.
 * 
 * @author Shammo Talukder
 *
 */
public class RmCommand extends Command {

	// Necessary collaborators of the class
	private FileSystemI fs = FileSystem.getFileSystemInstance();

	private String relativePathHandler(String dirPath) {
		int pathIndex = this.fs.getCurrentDirectory()
				.indexOfFileOrDirectory(dirPath);

		if ((pathIndex == -1) || ((this.fs.getCurrentDirectory()
				.getContentDirectory(dirPath) instanceof TextFile))) {
			ErrorHandler.processError("Could not find the directory " + dirPath
					+ " inside the current directory.");
			return null;
		}

		else {
			Directory toRemove = (Directory) FileSystemManipulation
					.findFileSystemPath(dirPath);
			this.fs.getCurrentDirectory().removeContent(toRemove);
			return "";
		}
	}

	/**
	 * Removes the specified directory and its children from the filesystem.
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
		} else if (!(inputWords[0].contains("/") || (inputWords[0].contains("."))
				|| inputWords[0].contains("..")))
			return this.relativePathHandler(inputWords[0]);

		else if (!FileSystemVerifier.isValidDirectory(inputWords[0])) {
			ErrorHandler.processError(2, inputWords[0]);
			return null;
		}

		Directory toRemove = (Directory) FileSystemManipulation
				.findFileSystemPath(inputWords[0]);
		Directory parentDir = FileSystemManipulation.findParentDirectory(toRemove);

		if (this.fs.getRootDirectory().equals(toRemove))
			ErrorHandler.processError("Cannot remove the root directory");

		else if (this.fs.getCurrentDirectory().equals(toRemove))
			ErrorHandler.processError("Cannot remove the current directory");

		else if (fs.getCurrentDirectory().getAbsolutePathname()
				.contains(toRemove.getAbsolutePathname())) {
			ErrorHandler.processError(
					"Cannot remove the directory specified, current directory is a "
							+ "descendant of it.");
		}

		else {
			parentDir.removeContent(toRemove);
			return "";
		}

		return null;
	}

	/**
	 * Returns a String containing the manual for the functionalities of the 'rm'
	 * command.
	 * 
	 * @return the manual of the 'rm' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\trm - remove a directory from the file system\n\n"
				+ "SYNOPSIS\n" + "\trm DIRECTORY \n\n" + "DESCRIPTION\n"
				+ "\tRemoves the specified DIRECTORY from the file system,\n"
				+ "\tand all its contents, if applicable.\n";
	}

}
