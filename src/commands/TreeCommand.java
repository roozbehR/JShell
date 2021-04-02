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

import commandUtils.StructuralPrinting;
import driver.ErrorHandler;
import interpreters.InputInterpreter;
import path.Directory;
import path.Path;
import system.FileSystem;
import system.FileSystemI;

/**
 * Tree is a Command where it can print all the folders and files in the file
 * system in a form of a tree
 * 
 * @author Roozbeh Yadollahi
 */
public class TreeCommand extends Command {

	/**
	 * fs is the instance of the file system.
	 */
	private FileSystemI fs = FileSystem.getFileSystemInstance();

	/**
	 * Print all the folders and files under in the file system in form of a tree
	 * 
	 * @param inputWords Which is what user enters as an input
	 * @return String that represents the outcome of the command that should be
	 *         shown to the user which is a tree in for this command It returns
	 *         null in case of an error
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		String[] flags = InputInterpreter.parseOnlyFlags(inputWords);
		String[] args = InputInterpreter.parseOnlyArguments(inputWords);

		// Check the validity of the arguments
		if (flags.length > 0 || args.length > 0) {
			ErrorHandler.processError(
					"The command entered does not accept " + "any arguments or flags");
			return null;
		}
		// Passed the validity of the arguments and flags
		// Check if the root is null or not
		Directory rootDir = fs.getRootDirectory();
		// Terminates the program if root doesn't exist
		if (rootDir == null) {
			return null;
		}
		return StructuralPrinting.printAsTree(rootDir) + "\n";
	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'tree' command.
	 * 
	 * @return the manual of the 'tree' command
	 */
	@Override
	public String getManual() {
		String manual = "NAME\n" + "\ttree - shows file system in form of a "
				+ "tree\n\n" + "SYNOPSIS\n" + "\ttree Operand\n\n" + "DESCRIPTION\n"
				+ "\tThe Tree Command start from the root directory \n"
				+ "\tdisplay the entire file system as a tree. \n"
				+ "\tFor every level of the tree, there is an indent\n"
				+ "\tby a space character.\n\n" + "\tExample: " + "\n\t\troot\n"
				+ "\t\t docement\n" + "\t\t  some_file.txt\n" + "\t\t Download\n";
		return manual;
	}

}
