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

package io;

import path.File;

/**
 * This class is responsible for modifying and retrieving file content.
 * 
 * @author Farhan Chowdhury
 *
 */
public class FileIOStream {

	/**
	 * This method adds content to a given file.
	 * 
	 * @param outputContent The Object to be added as file content
	 * @param file          the File to be modified
	 */
	public static void outputContent(Object outputContent, File file) {
		file.appendContentToFile(outputContent);
	}

	/**
	 * This method overwrites original content of a given file and adds new
	 * content the file.
	 * 
	 * @param outputContent The Object to be added as file content
	 * @param file          the File to be modified
	 */
	public static void outputContentWithOverwrite(Object outputContent,
			File file) {
		file.writeContentToFile(outputContent);
	}

	/**
	 * This method retrieves content from a given file
	 * 
	 * @param file the File to retrieve content from
	 * @return the input content as a String
	 */
	public static String getInputContent(File file) {
		return String.valueOf(file.getFileContent());
	}

}
