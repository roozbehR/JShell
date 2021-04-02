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

import java.util.Stack;
import path.Directory;
import path.TextFile;
import system.FileSystemI;

public class MockFileSystemForFind implements FileSystemI {

	private static MockFileSystemForFind fs;
	private Directory rootDirectory;
	private Directory currentDirectory;
	private Stack<Directory> directoryStack;

	/*
	 * ***THIS IS THE STRUCTURE OF THE MOCK FILE SYSTEM***
	 * 
	 * / /dir1 /dir1/dir1 /dir1/dir1/dir1 /dir1/dir1/dir2 /dir1/dir1/file1
	 * /dir1/dir1/file2 /dir1/dir2 /dir1/file1 /dir2 /dir2/dir1 /dir2/file1 /dir3
	 * /dir3/dir1 /dir3/file1 /dir4
	 * 
	 */

	private Directory setUpDir1() {
		// set up /dir1
		Directory dir1 = new Directory("dir1", "/dir1");
		Directory dir1dir1 = new Directory("dir1", "/dir1/dir1");
		dir1.addContent(dir1dir1);
		dir1.addContent(new Directory("dir2", "/dir1/dir2"));
		new TextFile("file1", dir1);

		// set up /dir1/dir1
		dir1dir1.addContent(new Directory("dir1", "/dir1/dir1/dir1"));
		dir1dir1.addContent(new Directory("dir2", "/dir1/dir1/dir2"));
		new TextFile("file1", dir1dir1);
		new TextFile("file2", dir1dir1);
		return dir1;
	}

	private Directory setUpDir2() {
		// set up /dir2
		Directory dir2 = new Directory("dir2", "/dir2");
		dir2.addContent(new Directory("dir1", "/dir2/dir1"));
		new TextFile("file1", dir2);
		return dir2;
	}

	private Directory setUpDir3() {
		// set up /dir3
		Directory dir3 = new Directory("dir3", "/dir3");
		dir3.addContent(new Directory("dir1", "/dir3/dir1"));
		new TextFile("file1", dir3);
		return dir3;
	}

	private Directory setUpDir4() {
		// set up /dir4
		return new Directory("dir4", "/dir4");
	}

	private MockFileSystemForFind() {
		this.rootDirectory = new Directory("/", "/");
		this.rootDirectory.addContent(setUpDir1());
		this.rootDirectory.addContent(setUpDir2());
		this.rootDirectory.addContent(setUpDir3());
		this.rootDirectory.addContent(setUpDir4());
		this.currentDirectory = this.rootDirectory;
		this.directoryStack = new Stack<Directory>();
	}

	public static MockFileSystemForFind getFileSystemInstance() {
		if (MockFileSystemForFind.fs == null) {
			MockFileSystemForFind.fs = new MockFileSystemForFind();
		}
		return MockFileSystemForFind.fs;
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

	@Override
	public boolean checkDirectoryStackEmpty() {
		return this.directoryStack.isEmpty();
	}

	public static void main(String[] args) {
		MockFileSystemForFind.getFileSystemInstance();
	}

}
