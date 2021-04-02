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

package commandUtils;

import driver.ErrorHandler;
import io.FileIOStream;
import io.StdIOStream;
import path.Directory;
import path.File;
import path.Path;
import path.TextFile;
import system.FileSystemManipulation;
import verification.FileSystemVerifier;

/**
 * This class is responsible for managing proper redirection from commands.
 * 
 * @author Farhan Chowdhury
 *
 */
public class CommandOutputProducer {

	/**
	 * This method will return a method that will direct the output through the
	 * appropriate stream. The method will always take two parameters: the 
	 * content to be added and the File to which it should be added (or null 
	 * if stdout).
	 * 
	 * @param inputWords The flags to base the redirection off of
	 * @return ArbitraryOutputMethod the method that can execute redirecting
	 */
	public static void processCommandOutput(String[] inputWords, String output) 
	{
		boolean[] flagTypes = { false, false }; // 1->, 2->>
		String dest = null;
		for (int i = 0; i < inputWords.length; i++) {
			if (inputWords[i].equals(">")) {
				flagTypes[0] = true;
				if (i < inputWords.length - 1) {
					dest = inputWords[i + 1];
				} else {
					ErrorHandler.processError("Redirection file was not found in "
							+ "input.");
					return;
				}
			} else if (inputWords[i].contentEquals(">>")) {
				flagTypes[1] = true;
				if (i < inputWords.length - 1) {
					dest = inputWords[i + 1];
				} else {
					ErrorHandler.processError("Redirection file was not found in "
							+ "input.");
					return;
				}
				if (inputWords.length > i + 2) {
					ErrorHandler.processError(
							"Only redirection filename can follow redirection flag.");
					return;
				}
			}
		}
		feedOutput(output, flagTypes, dest);
	}

	private static void feedOutput(String output, boolean[] flagTypes,
			String dest) {
		if (flagTypes[0] && flagTypes[1]) {
			ErrorHandler.processError("Redirection can only accept one flag.");
			return;
		} else if (flagTypes[0]) {
			if (dest != null) {
				outputToFile(output, dest, 'w');
			} else {
				ErrorHandler.processError("Redirection filename is invalid.");
				return;
			}
		} else if (dest != null && !FileSystemVerifier.isValidPath(dest)) {
			ErrorHandler.processError("Redirection filename is invalid.");
			return;
		} else if (flagTypes[1]) {
			CommandOutputProducer.outputToFile(output, dest, 'a');
		} else {
			CommandOutputProducer.outputToStdout(output);
		}
	}

	private static boolean outputToFile(Object content, String fileName,
			char mode) {
		if (content.equals("")) {
			return true;
		}
		if (FileSystemVerifier.containsNonSlashInvalidCharacters(fileName))
			return false;
		Path potentialFile = FileSystemManipulation.findFileSystemPath(fileName);
		if (potentialFile instanceof Directory) {
			ErrorHandler.processError(fileName + " already exists as a directory.");
			return false;
		}
		if (mode == 'w') {
			if (!(potentialFile instanceof File)) {
				Directory parent = FileSystemManipulation.findParentDirectory
						(fileName);
				String relativeName = fileName.substring(fileName.lastIndexOf('/') + 1,
						fileName.length());
				// System.out.println(relativeName);
				potentialFile = new TextFile(relativeName, parent);
			}
			FileIOStream.outputContentWithOverwrite(content, (File) potentialFile);
			return true;
		} else if (mode == 'a') {
			if (!(potentialFile instanceof File)) {
				ErrorHandler.processError(1, fileName);
				return false;
			}
			FileIOStream.outputContent(content, (File) potentialFile);
			return true;
		} else {
			return false;
		}
	}

	private static boolean outputToStdout(Object outputContent) {
		StdIOStream.outputContent(String.valueOf(outputContent));
		return true;
	}
}
