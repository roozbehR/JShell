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
package test;

import java.util.ArrayList;
import java.util.Stack;

import path.Directory;
import path.TextFile;
import system.FileSystemI;

public class MockFileSystem implements FileSystemI, java.io.Serializable {

	private static MockFileSystem mfs = null;
	private Directory rootDirectory;
	private Directory currentDirectory;
	private Stack<Directory> directoryStack;

	/*
	 *    /
	 *      /test
	 *         /test/test2
	 *         /test/testFile1
	 *             /test/test2/test3
	 *         /test/testFile2
	 *      
	 */

	private MockFileSystem() {
		this.rootDirectory = new Directory("/", "/");
		this.rootDirectory.addContent(new Directory("test", "/test"));
		((Directory) this.rootDirectory.getDirectoryContents().get(0))
				.addContent(new Directory("test2", "/test/test2"));
		
		TextFile textFile1 = new TextFile("testFile1",
				(Directory) this.rootDirectory.getDirectoryContents().get(0));
		ArrayList<String> contentsTestFile1  = new ArrayList<>();
		contentsTestFile1.add("This is testFile1");
		//contents.add("testFile1");
		textFile1.setFileContent(contentsTestFile1);
		TextFile textFile2 = new TextFile("testFile2",
            (Directory) this.rootDirectory.getDirectoryContents().get(0));
		ArrayList<String> contentsTestFile2  = new ArrayList<>();
		contentsTestFile2.add("This is testFile2"); 
		textFile2.setFileContent(contentsTestFile2);
		
		((Directory) ((Directory) this.rootDirectory.getDirectoryContents().get(0))
				.getDirectoryContents().get(0))
						.addContent(new Directory("test3", "/test/test2/test3"));
		this.currentDirectory = this.rootDirectory;
		this.directoryStack = new Stack<Directory>();
	}

	/**
	 * This method will always return the same instance of MockFileSystem,
	 * allowing it to act as a singleton.
	 * 
	 * @return MockFileSystem the instance of MockFileSystem
	 */
	public static MockFileSystem getFileSystemInstance() {
		if (mfs == null) {
			mfs = new MockFileSystem();
		}
		return mfs;
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
	public void setCurrentDirectory(Directory newDirectory) {
		this.currentDirectory = newDirectory;
	}

	public Directory getRootDirectory() {
		return rootDirectory;
	}

	public Directory getCurrentDirectory() {
		return currentDirectory;
	}

	/**
	 * This method adds directory to the top of the stack of saved directories
	 * 
	 * @param directory the directory to be saved
	 */
	public void pushDirectoryToStack(Directory directory) {
		this.directoryStack.push(directory);
	}

	/**
	 * This method gets the most recently saved directory.
	 * 
	 * @return the most recently saved directory
	 */
	public Directory popDirectoryFromStack() {
		return this.directoryStack.pop();
	}

	/**
	 * This method checks if the stack of saved directories is empty.
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean checkDirectoryStackEmpty() {
		return this.directoryStack.empty();
	}
}
