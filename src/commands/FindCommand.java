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

import java.util.ArrayList;
import driver.ErrorHandler;
import path.Directory;
import path.File;
import path.Path;
import system.DepthFirstIterator;
import system.FileSystemManipulation;

/**
 * Find is a command that is used to print all (either) files or directories
 * with the name that is passed by the user
 * 
 * @author Roozbeh Yadollahi
 * @author Farhan Chowdhury
 * 
 */
public class FindCommand extends Command {

	private String type;
	private String name;
	private String[] paths;

	/**
	 * Print all the (either) directories and files in the file system which 
	 * their name matches the name passed by the user
	 * 
	 * @param inputWords Which is what user enters as an input
	 * @return String that represents the outcome of the command that should be
	 *         shown to the user, it return null in case of an error
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		String toOutput = "";
		ArrayList<String> foundPaths;

		if (processArguments(inputWords) == -1) {
			return null;
		}
		for (String path : paths) {
			if (path == null) {
				break;
			}
			foundPaths = new ArrayList<String>();
			if (type.equals("f")) {
				foundPaths = processSearchForFileOrDirectory(path, name, foundPaths,
						true);
			} else {
				foundPaths = processSearchForFileOrDirectory(path, name, foundPaths,
						false);
			}
			// format and add to output String
			toOutput = formatFoundPaths(toOutput, foundPaths, path);
		}
		return toOutput;
	}

	private ArrayList<String> processSearchForFileOrDirectory(String pathName,
			String fileName, ArrayList<String> foundPaths, boolean searchFile) {
		Path startPath = FileSystemManipulation.findFileSystemPath(pathName);
		if (startPath == null) {
			return null;
		}
		DepthFirstIterator<Path> dfs = new DepthFirstIterator<Path>(startPath);
		while (dfs.hasNext()) {
			Path candidatePath = dfs.next();
			boolean isValid = searchFile && candidatePath instanceof File
					|| !searchFile && candidatePath instanceof Directory;
			if (candidatePath.getRelativePathname().equals(fileName) && isValid) {
				foundPaths.add(candidatePath.getAbsolutePathname());
			}
		}
		return foundPaths;
	}

	private int processTypeArguments(int idx, String[] inputWords) {
		if (!(inputWords[idx].equals("-type"))) {
			ErrorHandler.processError(20, "Inavlid Flags: Recieved " + 
		inputWords[idx]
					+ " when expecting -type");
			return -1;
		}
		idx++;
		this.type = inputWords[idx];
		if (!type.equals("f") && !type.equals("d")) {
			ErrorHandler.processError(5, type);
			return -1;
		}
		return idx + 1;
	}

	private int processNameArguments(int idx, String[] inputWords) {
		if (!(inputWords[idx].equals("-name"))) {
			ErrorHandler.processError(20, "Inavlid Flags: Recieved " + 
		inputWords[idx]
					+ " when expecting -name");
			return -1;
		}
		idx++;
		if (inputWords[idx].startsWith("\"") && inputWords[idx].endsWith("\"")) {
			int start = inputWords[idx].indexOf('\"');
			int end = inputWords[idx].lastIndexOf('\"');
			this.name = inputWords[idx].substring(start + 1, end);
		} else {
			ErrorHandler.processError("Non-quoted expression was entered.");
			return -1;
		}
		return idx + 1;
	}

	private int processPathArguments(int idx, String[] inputWords) {
		this.paths = new String[inputWords.length];
		while (!inputWords[idx].startsWith("-")) {
			this.paths[idx] = inputWords[idx];
			idx++;
			if (inputWords.length == idx) {
				ErrorHandler.processError(20,
						"Invalid Flags: The command entered is missing required flags");
				return -1;
			}
		}
		return idx;
	}

	private int processArguments(String[] inputWords) {
		int idx = 0;
		if (inputWords.length == 0) {
			ErrorHandler.processError("Invalid Arguments:"
					+ "The command entered requires at least 5 arguments.");
			return -1;
		}
		idx = processPathArguments(idx, inputWords);
		if (idx == -1) {
			return -1;
		}
		if (inputWords.length < idx + 4) {
			ErrorHandler.processError(20,
					"Invalid Flags: The command entered is missing required flags "
							+ "and corresponding values");
			return -1;
		}
		idx = processTypeArguments(idx, inputWords);
		if (idx == -1) {
			return -1;
		}
		idx = processNameArguments(idx, inputWords);
		if (idx == -1) {
			return -1;
		}
		return 1;
	}

	private String formatFoundPaths(String toOutput, ArrayList<String> 
	foundPaths, String path) {
		if (foundPaths == null) {
			ErrorHandler.processError(2, path);
			return toOutput;
		}
		toOutput += "Searching " + path + ", found:";
		if (foundPaths.size() == 0) {
			return toOutput + " NO MATCHES FOUND\n";
		}
		toOutput += "\n";
		for (String foundPath : foundPaths) {
			toOutput += "\t" + foundPath + "\n";
		}
		return toOutput;
	}

	/**
	 * Returns a String containing the manual for the functionalities of the
	 * 'find' command.
	 * 
	 * @return the manual of the 'find' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\tfind - finds all either directories or files"
				+ "that match the given  name by the user\n\n" + "SYNOPSIS\n"
				+ "\tfind path ... -type [f|d] -name expression\n\n" + "DESCRIPTION\n"
				+ "\tfind the node named 'expression' in the directories\n"
				+ "\tgiven by path. There can be multiple paths given.\n "
				+ "\tIf type is 'f' then find a file with name expression"
				+ ",\n\tand if type is 'd' then find a directory with name "
				+ "expression\n\n";
	}

}
