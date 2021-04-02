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

package system;

import java.util.Stack;

import path.Directory;

public interface FileSystemI {

	public abstract Stack<Directory> getDirectoryStack();

	/**
	 * Like setCurrentDirectory but does not save previous directory to stack
	 * 
	 * @param newDirectory the new directory
	 */
	public void setCurrentDirectory(Directory newDirectory);

	public Directory getRootDirectory();

	public Directory getCurrentDirectory();

	/**
	 * This method adds directory to the top of the stack of saved directories
	 * 
	 * @param directory the directory to be saved
	 */
	public void pushDirectoryToStack(Directory directory);

	/**
	 * This method gets the most recently saved directory.
	 * 
	 * @return the most recently saved directory
	 */
	public Directory popDirectoryFromStack();

	/**
	 * This method checks if the stack of saved directories is empty.
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean checkDirectoryStackEmpty();

}
