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

package system;

import java.util.Stack;
import path.Directory;

/**
 * The class FileSystem to operate a mock file system inside the JShell.
 * Designed to act as a singleton.
 * 
 * @author Farhan Chowdhury
 * @author Shammo Talukder
 *
 */
public class FileSystem implements FileSystemI, java.io.Serializable {

	private static FileSystem fs = null;
	private Directory rootDirectory;
	private Directory currentDirectory;
	private Stack<Directory> directoryStack;

	private FileSystem() {
		this.rootDirectory = new Directory("/", "/");
		this.currentDirectory = this.rootDirectory;
		this.directoryStack = new Stack<Directory>();
	}

	/**
	 * This method will always return the same instance of FileSystem, allowing 
	 * it to act as a singleton.
	 * 
	 * @return FileSystem the instance of FileSystem
	 */
	public static FileSystem getFileSystemInstance() {
		if (fs == null) {
			fs = new FileSystem();
		}
		return fs;
	}

	@Override
	public Stack<Directory> getDirectoryStack() {
		return this.directoryStack;
	}

	/**
	 * Sets the current Directory
	 * 
	 * @param newDirectory the new directory
	 */
	@Override
	public void setCurrentDirectory(Directory newDirectory) {
		this.currentDirectory = newDirectory;
	}

	@Override
	public Directory getRootDirectory() {
		return this.rootDirectory;
	}

	@Override
	public Directory getCurrentDirectory() {
		return this.currentDirectory;
	}

	/**
	 * This method adds directory to the top of the stack of saved directories
	 * 
	 * @param directory the directory to be saved
	 */
	@Override
	public void pushDirectoryToStack(Directory directory) {
		this.directoryStack.push(directory);
	}

	/**
	 * This method gets the most recently saved directory.
	 * 
	 * @return the most recently saved directory
	 */
	@Override
	public Directory popDirectoryFromStack() {
		return this.directoryStack.pop();
	}

	/**
	 * This method checks if the stack of saved directories is empty.
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean checkDirectoryStackEmpty() {
		return this.directoryStack.empty();
	}

	/*
	 * public static void main(String[] args) { }
	 */

}
