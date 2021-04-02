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

import java.util.ArrayList;

import commandUtils.StructuralPrinting;
import driver.ErrorHandler;
import interpreters.InputInterpreter;
import path.Directory;
import path.Path;
import system.FileSystem;
import system.FileSystemI;
import system.FileSystemManipulation;

/**
 * Ls is a Command where it can print all the folders and files under current
 * working directory or folders and files under a directory with a given path
 * which can be absolute or relative.
 * 
 * @author Roozbeh Yadollahi
 */
public class LsCommand extends Command {

	/**
	 * fs is the instance of the file system. flags is the array of string used 
	 * to hold the flags entered by the user args is the array of strings used to
	 * hold the flags entered by the user
	 */
	private FileSystemI fs = FileSystem.getFileSystemInstance();
	private String[] flags;
	private String[] args;

	/**
	 * Print all the folders and files under the current working folder or files
	 * and directories under one or multiple directories with given path which is
	 * relative or absolute
	 * 
	 * @param inputWords Which is what user enters as an input
	 * @return String that represents the outcome of the commnad that should be
	 *         shown to the user, it returns null in case of an error
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		flags = InputInterpreter.parseOnlyFlags(inputWords);
		args = InputInterpreter.parseOnlyArguments(inputWords);
		String result = "";
		if (flags.length > 1) {
			ErrorHandler.processError(7);
			return null;
		}
		if (flags.length == 0 && withoutFlag(args) != null) {
			result += withoutFlag(args);
		} else if (flags.length == 1 && withFlag(flags, args) != null) {
			result += withFlag(flags, args);
		} else {
			return null;
		}
		if (result.equals("")) {
			return result;
		} else {
			return result + "\n";
		}
	}

	private String withFlag(String[] flags, String[] args) {
		String finalResult = "";
		if (!flags[0].equals("-R")) {
			ErrorHandler.processError(8, flags[0]);
			return null;
		}
		if (args.length == 0) {

			finalResult += StructuralPrinting.printAsTree(fs.getCurrentDirectory());
			return finalResult;
		} else {
			if (validPath(args) == 0) {
				for (String arg : args) {
					Path currentArg = FileSystemManipulation.findFileSystemPath(arg);
					finalResult += StructuralPrinting
							.printAsTree(FileSystemManipulation.findFileSystemPath(arg));
					if (currentArg instanceof Directory) {
						finalResult += "\n";
					}
				}
			} else if (validPath(args) != 0) {
				return null;
			}
		}
		return finalResult;
	}

	private String withoutFlag(String[] args) {
		String finalRes = "";
		if (args.length == 0) {
			return printCurrDir();
		}
		if (args.length > 0) {
			int validArgsCode = validArgs(args);
			if (validArgsCode == 1) {
				return null;
			} else {
				int valCode = validPath(args);
				if (valCode == 0) {
					for (String arg : args) {
						finalRes += printContent(
								FileSystemManipulation.findFileSystemPath(arg));
					}
				} else if (valCode != 0) {
					return null;
				}
			}
		}
		return finalRes;
	}

	private int validArgs(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].contains(" ") || args[i].contains("//")) {
				// it returns an error because the argument is not valid
				ErrorHandler.processError(6, args[i]);
				return 1;
			}
		}
		// Success code (valid arguments) is 0
		return 0;
	}

	private int validPath(String[] args) {
		for (String arg : args) {
			Path dirOrFile = FileSystemManipulation.findFileSystemPath(arg);
			if (dirOrFile == null) {
				ErrorHandler.processError("The path " + arg + " does not exist");
				return 1;
			}
		}
		// Success code (valid paths) is 0
		return 0;
	}

	private String printCurrDir() {
		Directory curreDirectory = fs.getCurrentDirectory();
		String dummy = "";
		ArrayList<Path> currConnet = curreDirectory.getDirectoryContents();
		for (int i = 0; i < currConnet.size(); i++) {
			if (i < currConnet.size() - 1) {
				String name = currConnet.get(i).getRelativePathname();
				dummy += name + "\n";
			} else {
				String name = currConnet.get(i).getRelativePathname();
				dummy += name;
			}

		}
		return dummy;
	}

	private String printContent(Path fileOrDir) {
		String dummy = "";
		if (fileOrDir instanceof Directory) {
			ArrayList<Path> content = ((Directory) fileOrDir).getDirectoryContents();
			dummy += fileOrDir.getAbsolutePathname() + ":" + "\n";
			for (int i = 0; i < content.size(); i++) {
				String name = content.get(i).getRelativePathname();
				dummy += name + " ";
			}
			dummy += "\n\n";
		} else {
			dummy += fileOrDir.getRelativePathname();
		}
		return dummy;
	}

	/**
	 * Returns a String containing the manual for the functionalities of the 'ls'
	 * command.
	 * 
	 * @return the manual of the 'ls' command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\tls - list directory contnents\n\n" + "SYNOPSIS\n"
				+ "\tls [-R] [PATH]\n\n" + "DESCRIPTION\n"
				+ "\tFor each operand that names a file of a type other than "
				+ "directory," + " ls\n" + "\tdisplays its name.\n\n"
				+ "\tFor each operand that names a file of type directory, "
				+ "ls displays the names\n"
				+ "\tof files contained within that directory.\n\n"
				+ "\tIf no operands are given, the contents of the current "
				+ "directory are dis-\n"
				+ "\tplayed. If more than one operand is given, directory operands are"
				+ " with the\n" + "\tname of the directory, followed by a column, on a"
				+ " new line which contains\n" + "\tthe content of the directory "
				+ "divided by a space and an extra new line\n"
				+ "\tnon-directory file operands are displayed with the name of the "
				+ "file, followed \n" + "\tby a column, a new line which is empty "
				+ "followed by an extra empty line.\n\n"
				+ "\tIf the operand one or more of the operands are invalid and "
				+ "appropriate\n" + "\tmessage will be shown to the user.\n\n"
				+ "\tIf the operand starts with a slash (/) it is interpreted as\n"
				+ "\tand abolute path and starts at the root directory.\n\n"
				+ "\tOtherwise, it is interpreted as a relative path to the current\n"
				+ "\twroking directory.\n\n"
				+ "\tIf a flag is given without any argument all subdirectories of\n"
				+ "\tthe currect working directory will be printed as a tree with\n"
				+ "\tan extra new line following it.\n\n"
				+ "\tIf a flag is given with one ore more argument, if one of\n"
				+ "\tthe given paths don't exits and appropriate error message\n"
				+ "\twill be printed, otherwise all of the subdirectories of each\n"
				+ "\tpath will be printed as a tree, which path can be relative\n"
				+ "\tor absolute.\n\n";
	}
}
