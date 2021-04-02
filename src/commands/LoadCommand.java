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
//UTORID user_name: talukd29
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.Stack;

import driver.ErrorHandler;
import system.CommandHistory;
import system.CommandHistoryI;
import system.FileSystem;
import system.FileSystemI;

/**
 * The class LoadCommand to implement the functionality of re-initializing a
 * saved state of the program.
 * 
 * @author Shammo Talukder
 */
public class LoadCommand extends Command {

	// Necessary collaborators of the class
	private FileSystemI fs = FileSystem.getFileSystemInstance();
	private CommandHistoryI cmdHistory = CommandHistory
			.getCommandHistoryInstance();

	private void setFsField(FileInputStream fileReader,
			ObjectInputStream objectReader, FileSystemI fileSystem, String field) {
		try {
			Field fsField = (fileSystem.getClass()).getDeclaredField(field);
			fsField.setAccessible(true);
			fsField.set(fileSystem, objectReader.readObject());
		}

		catch (Exception e) {
			ErrorHandler.processError(
					"Could not load the file system, enter a valid file name and try "
							+ "again.");
		}
	}

	private void setCmdStack(FileInputStream fileReader,
			ObjectInputStream objectReader, CommandHistoryI cmdHistory) {

		try {
			Stack<String> loadCmdStack = (Stack<String>) objectReader.readObject();
			cmdHistory.setStack(loadCmdStack);
		}

		catch (Exception e) {
			ErrorHandler.processError(
					"Could not load the file system, enter a valid file name and try "
							+ "again.");
		}
	}

	/**
	 * Reads and initializes a saved state of the program from given file path.
	 * 
	 * @param inputWords The array of required arguments to successfully execute
	 *                   the requested command
	 * @return empty string to represent success and null if an error occurs
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {

		if (cmdHistory.getStackSize() > 1) {
			ErrorHandler.processError(
					"This command can only execute immediately after starting the "
							+ "JShell");
		}

		else if (inputWords.length != 1) {
			ErrorHandler.processError(5);
		}

		else {
			try {
				FileInputStream fileReader = new FileInputStream(inputWords[0]);
				ObjectInputStream objectReader = new ObjectInputStream(fileReader);

				this.setFsField(fileReader, objectReader, this.fs, "rootDirectory");
				this.setFsField(fileReader, objectReader, this.fs, "currentDirectory");
				this.setFsField(fileReader, objectReader, this.fs, "directoryStack");
				this.setCmdStack(fileReader, objectReader, this.cmdHistory);

				objectReader.close();
				fileReader.close();
				return "";

			} catch (IOException e) {
				ErrorHandler.processError(1, inputWords[0]);
			} catch (Exception e) {
				ErrorHandler.processError(
						"Could not load the file, enter a valid file name and try again.");
			}
		}
		return null;
	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'load' command.
	 * 
	 * @return the manual of the 'load' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\tload - load a saved state of the JShell\n\n"
				+ "SYNOPSIS\n" + "\tload FILE \n\n" + "DESCRIPTION\n"
				+ "\tLoads a saved state of the JShell which includes:\n\n"
				+ "\t\t- the entire file system structure and contents\n"
				+ "\t\t- the directory stack\n" + "\t\t- the command stack\n\n"
				+ "\tfrom specified FILE.\n"
				+ "\tThe FILE specified must be a valid path on the directory\n"
				+ "\tof the computer. " + "This command will execute only if it is\n"
				+ "\tcalled immediately after running a new JShell program.\n";
	}

}
