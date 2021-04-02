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
import driver.ErrorHandler;
import interpreters.InputInterpreter;
import io.StdIOStream;
import path.TextFile;
import system.FileSystem;
import system.FileSystemI;
import system.FileSystemManipulation;
import verification.FileSystemVerifier;

/**
 * Cat is a command that prints the contents of the given files with three line
 * breaks between the contents of each file.
 * 
 * @author Rakshit Patel
 */
public class CatCommand extends Command {
  
    /* Necessary collaborators of the class */
    private FileSystemI fs = FileSystem.getFileSystemInstance();
	/**
	 * prints the contents of the given file
	 * 
	 * @param name A file with text inside
	 */
	private String readFile(TextFile name) {
		ArrayList<String> fileContent = (ArrayList<String>) name.getFileContent();
		String fileContents = "";
		int size = fileContent.size();
		for (int i = 0; i < size; i++) {
			fileContents += fileContent.get(i);
		}
		// StdIOStream.outputContentWithNewLine();
		return fileContents;
	}

	/**
	 * Finds the desired files and prints the contents with 3 newlines between the
	 * contents of each file.
	 * 
	 * @param inputWords An array of arguments inputed for the command
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		String allFileContents = "";
		String[] arguments = InputInterpreter.parseOnlyArguments(inputWords);

		if (arguments.length == 0) {
			ErrorHandler.processError(5);
			return null;
		}
		/*
		 * // check if any arguments are invalid for (int i = 0; i <
		 * arguments.length; i++) { if (!fsv.isValidPath(arguments[i]) || !fsv.
		 * isValidFile(arguments[i])) { ErrorHandler.processError(1); return; } }
		 */
		for (int i = 0; i < arguments.length; i++) {
			// check if the argument is invalid
			if (!FileSystemVerifier.isValidPath(arguments[i])
					|| !FileSystemVerifier.isValidFile(arguments[i])) {
				ErrorHandler.processError(1, arguments[i]);
				continue;
			}

			TextFile file = (TextFile) FileSystemManipulation
					.findFileSystemPath(arguments[i]);
			if (i == arguments.length - 1) {
				allFileContents += readFile((TextFile) file);
				return allFileContents;
			}
			// if printing files other than the last file,
			// then print three new lines
			else {
				allFileContents += readFile((TextFile) file);
				allFileContents += "\n\n\n";
			}
		}
		return allFileContents + "\n";
	}

	/**
	 * @return string that contains the manual for Cat command
	 */
	@Override
	public String getManual() {
		return "NAME\n" + "\tcat - concatenate and print files\n\n" + "SYNOPSIS"
				+ "\n\tcat File1 [FILE2 ...]\n\n" + "DESCRIPTION\n"
				+ "\tPrints the contents of the specified files with three\n"
				+ "\tline breaks separating the contents of each file.\n";
	}

	// created for testing
	/*
	 * public static void main(String[] args) { Directory testDir = new
	 * Directory("test");
	 * 
	 * 
	 * File testFile1 = new File("Test1"); testFile1.
	 * addContent("This is Test1\nhello this is line 1\nand this is line 2, ");
	 * testFile1. addContent("here is some more content on line 2\nyou are now
	 * reading line 3" ); File testFile2 = new File("Test2"); testFile2.
	 * addContent("This is Test2\nhello this is line 1\nand this is line 2, ");
	 * testFile2. addContent("here is some more content on line 2\nyou are now
	 * reading line 3" ); File testFile3 = new File("Test3"); testFile2.
	 * addContent("This is Test3\nhello this is line 1\nand this is line 2, ");
	 * testFile2. addContent("here is some more content on line 2\nyou are now
	 * reading line 3" );
	 * 
	 * //String inputFiles[] = {} //Read(testFile); }
	 */

}
