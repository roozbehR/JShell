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

package verification;

import java.util.ArrayList;
import path.Directory;
import path.TextFile;
import system.FileSystem;
import system.FileSystemManipulation;

/**
 * A class to verify valid path, directory, and file names Checks for existence
 * and validity.
 * 
 * @author Farhan Chowdhury
 * @author Shammo Talukder
 */
public class FileSystemVerifier {

	private static char[] invalidCharacters = { '/', '.', '!', '@', '#', '$', 
			'%', '&', '*', '(', ')', '{', '}', '~', '|', '<', '>', '?', '\"' };

	/**
	 * This method checks if a relative pathname contains invalid characters.
	 *
	 * @param relativePath the path
	 * @return true, if invalid chars found, false otherwise
	 */
	public static boolean containsInvalidCharacters(String relativePath) {
		for (int i = 0; i < relativePath.length(); i++) {
			char currChar = relativePath.charAt(i);
			if (currChar == '/' && i == 0) {
				continue;
			}
			if (FileSystemVerifier.charIsInvalid(currChar)
					|| Character.isWhitespace(currChar)) {
				return true;
			}
		}
		return false;
	}

	private static boolean charIsInvalid(char character) {
		for (int i = 0; i < FileSystemVerifier.invalidCharacters.length; i++) {
			if (FileSystemVerifier.invalidCharacters[i] == character) {
				return true;
			}
		}
		return false;
	}

	public static boolean containsNonSlashInvalidCharacters(String absolutePath) 
	{
		for (int i = 0; i < absolutePath.length(); i++) {
			char currChar = absolutePath.charAt(i);
			if (currChar == '/') {
				continue;
			}
			if (currChar == '.' && i != absolutePath.length() - 1
					&& absolutePath.charAt(i + 1) == '.') {
				i += 1;
				continue;
			}
			if (charIsInvalid(currChar) || Character.isWhitespace(currChar)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This command checks if a given path is valid and exists in the FileSystem
	 * to be verified.
	 * 
	 * @param path A potential path in the FileSystem to be verified
	 * @return boolean true if path exists in FileSystem to be verified, false
	 *         otherwise
	 */
	public static boolean isValidPath(String path) {
		if (path.equals("") || path.equals(null)) {
			return false;
		}
		return FileSystemManipulation.findFileSystemPath(path) != null;
	}

	/**
	 * This command checks if a given path is valid and exists in the FileSystem
	 * to be verified as a directory.
	 * 
	 * @param path A potential directory in the FileSystem to be verified
	 * @return boolean true if path exists in FileSystem to be verified as a
	 *         directory, false otherwise
	 */
	public static boolean isValidDirectory(String dirPath) {
		return FileSystemManipulation
				.findFileSystemPath(dirPath) instanceof Directory;
	}

	/**
	 * This command checks if a given path is valid and exists in the FileSystem
	 * to be verified as a file.
	 * 
	 * @param path A potential file in the FileSystem to be verified
	 * @return boolean true if path exists in FileSystem to be verified as a file
	 * ,false otherwise
	 */
	public static boolean isValidFile(String filePath) {
		return FileSystemManipulation
				.findFileSystemPath(filePath) instanceof TextFile;
	}

	/*
	 * public static void main(String[] args) { FileSystemVerifier fsv = new
	 * FileSystemVerifier(FileSystem.getFileSystemInstance());
	 * System.out.println(fsv.containsInvalidCharacters("a/b"));
	 * 
	 * }
	 */
}
