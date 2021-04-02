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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import driver.ErrorHandler;
import system.CommandHistory;
import system.CommandHistoryI;
import system.FileSystem;
import system.FileSystemI;

/**
 * The class SaveCommand to implement the functionality of saving the program's
 * current state
 * 
 * @author Shammo Talukder
 */
public class SaveCommand extends Command {

	// Necessary collaborators of the class
	private FileSystemI fs = FileSystem.getFileSystemInstance();
	private CommandHistoryI cmdHistory = CommandHistory
			.getCommandHistoryInstance();

	private String fileNamer(String input) {
		int indexOfLastSlash = (input.lastIndexOf('/') != -1)
				? input.lastIndexOf('/')
				: 0;

		if (input.substring(indexOfLastSlash).contains(".")) {
			input = input.replace(input.substring(input.lastIndexOf('.')), ".ser");
			return input;
		}

		else
			return input + ".ser";

	}

	/**
	 * Captures and saves the current state of the program to given file path.
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

		String fileName = this.fileNamer(inputWords[0]);

		try {

			FileOutputStream fileWriter = new FileOutputStream(fileName);
			ObjectOutputStream objectWriter = new ObjectOutputStream(fileWriter);

			objectWriter.writeObject(this.fs.getRootDirectory());
			objectWriter.writeObject(this.fs.getCurrentDirectory());
			objectWriter.writeObject(this.fs.getDirectoryStack());
			objectWriter.writeObject(this.cmdHistory.getStack());

			objectWriter.close();
			fileWriter.close();

			return "";

		} catch (IOException e) {
			ErrorHandler.processError(
					"Could not save to the file, enter a valid file path and try "
					+ "again.");
			return null;
		}

	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'save' command.
	 * 
	 * @return the manual of the 'save' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\tsave - save the current state of the JShell\n\n"
				+ "SYNOPSIS\n" + "\tsave FILE \n\n" + "DESCRIPTION\n"
				+ "\tSaves the current state of the JShell which includes:\n\n"
				+ "\t\t- the entire file system structure and contents\n"
				+ "\t\t- the directory stack\n" + "\t\t- the command stack\n\n"
				+ "\tto specified FILE.\n"
				+ "\tThe FILE specified must be a valid path on the directory\n"
				+ "\tof the computer. " + "The file will be saved as a .ser file.\n";
	}

}
