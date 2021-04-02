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
//Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

package commands;

import java.util.ArrayList;
import driver.ErrorHandler;
import interpreters.InputInterpreter;
import system.FileSystemManipulation;
import path.Path;
import path.TextFile;
import path.Directory;
import verification.FileSystemVerifier;

public class MvCommand extends Command {

	// Necessary collaborators of the class
	FileSystemVerifier fsv = new FileSystemVerifier();
	FileSystemManipulation fsm = new FileSystemManipulation();

	private int checkForErrors(String[] flags, String[] arguments) {
		if (flags.length > 0) {
			ErrorHandler.processError(7);
			return 7;
		}
		if (arguments.length != 2) {
			ErrorHandler.processError(5);
			return 5;
		}
		if (!FileSystemVerifier.isValidPath(arguments[0])) {
			ErrorHandler
					.processError("Could not find the path '" + arguments[0] + "'.");
			return -1;
		}
		if (FileSystemVerifier.isValidPath(arguments[1])) {
			Path arg0 = FileSystemManipulation.findFileSystemPath(arguments[0]);
			Path arg1 = FileSystemManipulation.findFileSystemPath(arguments[1]);
			if (FileSystemManipulation.isParentDirectory(arg1, arg0)) {
				ErrorHandler.processError(
						"Cannot move parent path to a sub path of " + "itself.");
				return -1;
			}
		}
		if (FileSystemVerifier.isValidPath(arguments[1])) {
			Path arg0 = FileSystemManipulation.findFileSystemPath(arguments[0]);
			Path arg1 = FileSystemManipulation.findFileSystemPath(arguments[1]);
			if (FileSystemManipulation.isParentDirectory(arg0, arg1)) {
				ErrorHandler.processError(
						"Cannot copy parent path to a sub path of " + "itself.");
				return -1;
			}
		}
		return 0;
	}

	private int makeNewPath(String newPath) {
		MkdirCommand mkdir = new MkdirCommand();
		String[] newPathArray = { newPath };
		// check for error in the argument
		if (mkdir.checkErrors(newPathArray, fsv, 0) != 0) {
			return mkdir.checkErrors(newPathArray, fsv, 0);
		}
		mkdir.executeCommandOperations(newPathArray);
		return 0;
	}

	private Path getDirOrTextFilePath(String pathName) {
		Path path = null;
		if (FileSystemManipulation
				.findFileSystemPath(pathName) instanceof TextFile) {
			path = (TextFile) FileSystemManipulation.findFileSystemPath(pathName);
		}
		if (FileSystemManipulation
				.findFileSystemPath(pathName) instanceof Directory) {
			path = (Directory) FileSystemManipulation.findFileSystemPath(pathName);
		}
		return path;
	}

	private Path setAbsolutePathOfOldDir(Path oldPath, Path newPath) {
		String newPathAbsoluteName = newPath.getAbsolutePathname();
		if (!newPathAbsoluteName.equals("/")) {
			oldPath.setAbsolutePathname(
					newPathAbsoluteName + "/" + oldPath.getRelativePathname());
		} else {
			oldPath.setAbsolutePathname("/" + oldPath.getRelativePathname());
		}
		ArrayList<Path> contentDir = new ArrayList<>();
		if (oldPath instanceof Directory) {
			contentDir = ((Directory) oldPath).getDirectoryContents();
		}

		for (int i = 0; i < contentDir.size(); i++) {
			if (contentDir.get(i) instanceof TextFile) {
				newPathAbsoluteName = newPath.getAbsolutePathname();
				if (!newPathAbsoluteName.equals("/")) {
					oldPath.setAbsolutePathname(
							newPathAbsoluteName + "/" + oldPath.getRelativePathname());
				} else {
					oldPath.setAbsolutePathname("/" + oldPath.getRelativePathname());
				}
			} else {
				return setAbsolutePathOfOldDir(contentDir.get(i), oldPath);
			}

		}
		return null;
	}

	private void deleteOldPath(Path oldPath) {
		Path parentDirOfOldPath = FileSystemManipulation
				.findParentDirectory(oldPath);
		((Directory) parentDirOfOldPath).removeContent(oldPath);
	}

	private String getContentsOfOldFile(Path oldPath) {
		String allContents = "";
		for (Object contents : (ArrayList<?>) ((TextFile) oldPath)
				.getFileContent()) {
			allContents += contents;
		}
		return allContents;
	}

	private String moveToNonExistingNewPath(Path oldPath, String[] arguments) {
		if (makeNewPath(arguments[1]) != 0) {
			return null;
		}
		Path newPath = getDirOrTextFilePath(arguments[1]);

		deleteOldPath(oldPath);
		setAbsolutePathOfOldDir(oldPath, newPath);
		((Directory) newPath).addContent(oldPath);

		return "";
	}

	private Path findSameNamePath(ArrayList<Path> contents, Path oldPath) {
		for (Path p : contents) {
			if (p instanceof Directory
					&& p.getRelativePathname().equals(oldPath.getRelativePathname())) {
				return p;
			}
			if (p instanceof TextFile
					&& p.getRelativePathname().equals(oldPath.getRelativePathname())) {
				return p;
			}
		}
		return null;
	}

	private String moveToExisingNewPath(Path oldPath, String[] arguments) {
		Path newPath = getDirOrTextFilePath(arguments[1]);

		if (newPath instanceof Directory) {
			deleteOldPath(oldPath);
			setAbsolutePathOfOldDir(oldPath, newPath);
			if (findSameNamePath(((Directory) newPath).getDirectoryContents(),
					oldPath) != null) {
				Path p = findSameNamePath(((Directory) newPath).getDirectoryContents(),
						oldPath);
				((Directory) newPath).removeContent(p);
			}
			((Directory) newPath).addContent(oldPath);
			return "";
		} else if (newPath instanceof TextFile && oldPath instanceof TextFile) {
			deleteOldPath(oldPath);
			setAbsolutePathOfOldDir(oldPath, newPath);
			String allContents = getContentsOfOldFile(oldPath);
			((TextFile) newPath).writeContentToFile(allContents);
			return "";
		} else {
			ErrorHandler.processError("Cannot move directory inside TextFile.");
			return null;
		}
	}

	/**
	 * Moves the path with name specified by inputWords[0], to directory with 
	 * name specified by inputWords[1]. Directory with name specified by 
	 * inputWords[1] will be created if non-existent. If the two path names 
	 * belong to TextFiles, the file with name specified by inputWords[0] will be
	 * removed, and the file with name specified by inputWords[1] will have the 
	 * same content and name as file specified by inputWords[0].
	 * 
	 * @param inputWords The array of required arguments to successfully execute
	 *                   the requested command
	 * @return empty string to indicate success, null on error
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		String[] arguments = InputInterpreter.parseOnlyArguments(inputWords);
		String[] flags = InputInterpreter.parseOnlyFlags(inputWords);
		// return if there are errors or the OLDPATH is the same as NEWPATH
		if (checkForErrors(flags, arguments) != 0
				|| arguments[0].equals(arguments[1])) {
			return null;
		}

		Path oldPath = getDirOrTextFilePath(arguments[0]);

		if (!FileSystemVerifier.isValidPath(arguments[1])) {
			return moveToNonExistingNewPath(oldPath, arguments);
		} else {
			return moveToExisingNewPath(oldPath, arguments);
		}
	}

	@Override
	public String getManual() {
		return "NAME\n" + "\tmv - move one path to another\n\n" + "SYNOPSIS\n"
				+ "\tmv OLDPATH NEWPATH\n\n" + "DESCRIPTION\n"
				+ "\tMove file or directory specified by OLDPATH,\n\n"
				+ "\tto directory specified by NEWPATH.\n\n"
				+ "\tDirectory named NEWPATH will be created if it does not exist."
				+ "\n";
	}

}
