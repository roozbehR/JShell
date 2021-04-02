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
import interpreters.InputInterpreter;
import path.Directory;
import system.FileSystem;
import verification.FileSystemVerifier;

/**
 * Mkdir is a command that creates a directory given the full path or relative
 * path This command only takes in 1 argument
 * 
 * @author Roozbeh Yadollahi
 * @author Rakshit
 */
public class MkdirCommand extends Command {

	private void createDirectory(Directory currentDirectory, String dirName) {
		String currentAbsolutePath = currentDirectory.getAbsolutePathname();
		Directory newDir;
		if (currentAbsolutePath.equals("/")) {
			newDir = new Directory(dirName, currentAbsolutePath + dirName);
		} else {
			newDir = new Directory(dirName, currentAbsolutePath + "/" + dirName);
		}
		currentDirectory.addContent(newDir);
	}

	private int checkForFullPathErrors(FileSystemVerifier fsv, String goToPath,
			String path, int index) {

		// see if the path in which the directory will be made is valid
		if (FileSystemVerifier.isValidPath(goToPath) == false) {
			ErrorHandler.processError(2, goToPath);
			return 1;
		}
		// check for invalid characters
		if (FileSystemVerifier
				.containsInvalidCharacters(path.substring(index)) == true) {
			ErrorHandler.processError(3, path.substring(index, path.length()));
			return 11;
		}
		return 0;
	}

	private void goToRequiredDirAndCreate(Directory currentDirectory,
			String[] goToPath, String path, int i, FileSystem fileSystem,
			CdCommand cd) {
		// go the desired directory and make the new directory
		// String[] emptyArrayForCd;
		// emptyArrayForCd = {};
		// create directory at the given location
		cd.executeCommandOperations(goToPath);
		Directory changedDirectory = fileSystem.getCurrentDirectory();
		createDirectory(changedDirectory, path.substring(i, path.length()));
		// come back to the original directory
		goToPath[0] = currentDirectory.getAbsolutePathname();
		cd.executeCommandOperations(goToPath);
	}

	private void createIfPathHasSlash(Directory currentDirectory,
			String[] goToPath, String path, FileSystem fileSystem, CdCommand cd,
			FileSystemVerifier fsv) {
		for (int i = path.length(); i > 0; i--) {
			if (path.substring(0, i).endsWith("/")) {
				goToPath[0] = path.substring(0, i);
				if (checkForFullPathErrors(fsv, goToPath[0], path, i) != 0) {
					return;
				}
				goToRequiredDirAndCreate(currentDirectory, goToPath, path, i,
						fileSystem, cd);
				break;
			}
		}
	}

	/**
	 * Checks if there is > 1 arguments entered and checks if the directories
	 * already exist
	 * 
	 * @param args
	 * @param fsv
	 * @param i
	 * @return The error code for the error or 0 for no errors
	 */
	protected int checkErrors(String[] args, FileSystemVerifier fsv, int i) {
		// No input or too many inputs error
		if (args.length == 0) {
			ErrorHandler.processError(4);
			return 5;
		}
		// Check if file already exists
		if (FileSystemVerifier.isValidPath(args[i]) == true) {
			ErrorHandler.processError(
					"Could not create directory. " + "A path with the specified name '"
							+ args[i] + "' " + "already exists.");
			return 10;
		}
		return 0;
	}

	private String makeOneDirectory(String[] args, int i) {
		FileSystem fileSystem = FileSystem.getFileSystemInstance();
		Directory currentDirectory = fileSystem.getCurrentDirectory();
		CdCommand cd = new CdCommand();
		FileSystemVerifier fsv = new FileSystemVerifier();

		// check for error in the argument
		if (checkErrors(args, fsv, i) != 0) {
			return null;
		}

		String path = args[i];
		String[] goToPath = { "" };
		// if the path is absolute or relative to the current directory
		// then create a directory at the specifed location
		if (path.startsWith("/") || path.contains("/")) {
			createIfPathHasSlash(currentDirectory, goToPath, path, fileSystem, cd,
					fsv);
		}
		// otherwise create a directory in the current directory
		else {
			if (FileSystemVerifier.containsInvalidCharacters(path) == true) {
				ErrorHandler.processError(3, path);
				return null;
			}
			createDirectory(currentDirectory, path);
		}
		return "";
	}

	/**
	 * creates a new directory at the specified location if given the full path 
	 * or in the current directory if given the relative path
	 * 
	 * @param args An array of arguments inputed for the command
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		String[] args = InputInterpreter.parseOnlyArguments(inputWords);
		String returnString = null;
		for (int i = 0; i < args.length; i++) {
			returnString = makeOneDirectory(args, i);
		}
		return returnString;
	}

	/**
	 * @return string that contains the manual for Mkdir command
	 */
	public String getManual() {
		String manual = "NAME\n" + "\tmkdir - make directories\n\n" + "SYNOPSIS\n"
				+ "\tmkdir DIR\n\n" + "DESCRIPTION\n"
				+ "\tmkdir creates new directories.\n"
				+ "\tDIR must be a valid absolute path name or the name of\n"
				+ "\the new directory.\n"
				+ "\tIf DIR begins with a slash (/), then it is interpreted\n"
				+ "\tOtherwise, it is interpreted as a relative path to the\n"
				+ "\tcurrent directory.\n";
		return manual;
	}

	/*
	 * public static void main(String[] args) { CommandManager cm = new
	 * CommandManager(); FileSystem fs = FileSystem.getFileSystemInstance();
	 * cm.processCommand("mkdir a"); cm.processCommand("mkdir /b");
	 * cm.processCommand("ls"); cm.processCommand("mkdir /a/c");
	 * System.out.println("Inside a:"); cm.processCommand("cd a");
	 * cm.processCommand("ls"); System.out.println("No such path error:");
	 * cm.processCommand("mkdir n/m"); System.out.println("Inside /:");
	 * cm.processCommand("cd .."); cm.processCommand("ls");
	 * cm.processCommand("mkdir a/v"); System.out.println("Inside a:");
	 * cm.processCommand("cd a"); cm.processCommand("ls"); }
	 */
}
