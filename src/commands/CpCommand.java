// **********************************************************
// Assignment2:
// Student1: Farhan Chowdhury
// UTORID user_name: chowd601
// UT Student #: 1006068176
// Author: Farhan Chowdhury
//
// Student2: Rakshit Patel
// UTORID user_name: patel939
//UT Student #: 1005918152
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

import java.util.ArrayList;
import driver.ErrorHandler;
import interpreters.InputInterpreter;
import system.FileSystem;
import system.FileSystemI;
import system.FileSystemManipulation;
import path.Path;
import path.TextFile;
import path.Directory;
import verification.FileSystemVerifier;
import system.DepthFirstIterator;

public class CpCommand extends Command {

	// Necessary collaborators of the class
    private FileSystemI fs = FileSystem.getFileSystemInstance();
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
						"Cannot copy parent path to a sub path of " + "itself.");
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

	private void setAbsolutePathOfOldDir(Path oldPath, Path newPath) {
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
				setAbsolutePathOfOldDir(contentDir.get(i), oldPath);
			}
		}
	}

	private String getContentsOfOldFile(Path oldPath) {
		String allContents = "";
		for (Object contents : (ArrayList<?>) ((TextFile) oldPath)
				.getFileContent()) {
			allContents += contents;
		}
		return allContents;
	}

	private String copyToNonExistingNewPath(Path oldPathCopy,
			String[] arguments) {
		if (makeNewPath(arguments[1]) != 0) {
			return null;
		}

		Path newPath = getDirOrTextFilePath(arguments[1]);
		setAbsolutePathOfOldDir(oldPathCopy, newPath);
		((Directory) newPath).addContent(oldPathCopy);
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

	private String copyToExisingNewPath(Path oldPathCopy, String[] arguments) {
		Path newPath = getDirOrTextFilePath(arguments[1]);

		if (newPath instanceof Directory) {

			setAbsolutePathOfOldDir(oldPathCopy, newPath);
			if (findSameNamePath(((Directory) newPath).getDirectoryContents(),
					oldPathCopy) != null) {
				Path p = findSameNamePath(((Directory) newPath).getDirectoryContents(),
						oldPathCopy);
				((Directory) newPath).removeContent(p);
			}

			if (oldPathCopy instanceof TextFile) {

				TextFile textFile = new TextFile(oldPathCopy.getRelativePathname(),
						(Directory) newPath);

				textFile.setFileContent(
						(ArrayList<String>) ((TextFile) oldPathCopy).getFileContent());
			} else {
				((Directory) newPath).addContent(oldPathCopy);
			}

			return "";
		} else if (newPath instanceof TextFile && oldPathCopy instanceof TextFile) {
			setAbsolutePathOfOldDir(oldPathCopy, newPath);
			String allContents = getContentsOfOldFile(oldPathCopy);
			((TextFile) newPath).writeContentToFile(allContents);
			return "";
		} else {
			ErrorHandler.processError("Cannot copy directory inside TextFile.");
			return null;
		}
	}

	private void createCopyOfOldPath(Path oldPath, Directory oldPathCopy) {
		for (Path p : ((Directory) oldPath).getDirectoryContents()) {
			Directory newCopyDir = null;
			if (p instanceof TextFile) {

				TextFile textFile = new TextFile(p.getRelativePathname(), oldPathCopy);

				textFile.setFileContent(
						(ArrayList<String>) ((TextFile) p).getFileContent());

				continue;
			}

			if (p instanceof Directory) {

				newCopyDir = new Directory(p.getRelativePathname(),
						p.getAbsolutePathname());
				oldPathCopy.addContent(newCopyDir);
				createCopyOfOldPath(p, newCopyDir);
			}

		}
	}

	/**
	 * Makes a copy of the Path specified by inputWords[0], named the name
	 * specified by inputWords[1]. The new copy will have the exact same contents
	 * as the old.
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
		Path oldPathCopy = null;
		if (oldPath instanceof Directory) {
			oldPathCopy = new Directory(oldPath.getRelativePathname(),
					oldPath.getAbsolutePathname());
			createCopyOfOldPath(oldPath, (Directory) oldPathCopy);
		} else {
			if (!FileSystemVerifier.isValidPath(arguments[1])) {
				return copyToNonExistingNewPath(oldPath, arguments);
			} else {
				return copyToExisingNewPath(oldPath, arguments);
			}
		}

		if (!FileSystemVerifier.isValidPath(arguments[1])) {
			return copyToNonExistingNewPath(oldPathCopy, arguments);
		} else {
			return copyToExisingNewPath(oldPathCopy, arguments);
		}
	}

	@Override
	public String getManual() {
		return "NAME\n" + "\tcp - make a copy of a directory or textfile\n\n"
				+ "SYNOPSIS\n" + "\tcp OLDPATH NEWPATH\n\n" + "DESCRIPTION\n"
				+ "\tMake a copy of file or directory specified by OLDPATH,\n\n"
				+ "\tnamed NEWPATH.\n\n"
				+ "\tContents of NEWPATH will be the same as OLDPATH." + "\n";
	}

}
