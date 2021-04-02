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

import static org.junit.Assert.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.LoadCommand;
import commands.SaveCommand;

public class LoadCommandTest {

	/*
	 * Will be loading from a previously saved serialized file.
	 */
	private LoadCommand load;
	private MockFileSystem mfs;
	private MockCommandHistory mch;
	private File loadFile;

	@Before
	public void setUp() throws Exception {
		mfs = MockFileSystem.getFileSystemInstance();
		mch = MockCommandHistory.getCommandHistoryInstance();
		Field mchField = (mch.getClass().getDeclaredField("stack"));
		mchField.setAccessible(true);
		// setting cmd stack to 0 so load can run
		mchField.set(mch, new Stack<String>());
		load = new LoadCommand();
		Field loadFieldFs = (load.getClass()).getDeclaredField("fs");
		loadFieldFs.setAccessible(true);
		loadFieldFs.set(load, mfs);
		Field loadFieldCh = (load.getClass()).getDeclaredField("cmdHistory");
		loadFieldCh.setAccessible(true);
		loadFieldCh.set(load, mch);
	}

	@After
	public void tearDown() throws Exception {
		Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(mfs, null);
		Field mchField = (mch.getClass()).getDeclaredField("mch");
		mchField.setAccessible(true);
		mchField.set(mch, null);
	}

	@Test
	public void testExecuteCommandOperationsInvalidPath() {
		String[] args = { "C:\\Users\\invaliduser\\invalidfilename.txt" };
		load.executeCommandOperations(args);
		// Error, keep everything default,
		// do not load any file, print appropriate error msg
		assertTrue(
				this.mfs.getCurrentDirectory().equals(this.mfs.getRootDirectory()));
		assertTrue(this.mfs.getRootDirectory().getDirectoryContents().size() == 1);
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
		assertTrue(this.mch.getStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsValidAbsolutePath() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved.ser" };
		load.executeCommandOperations(args);
		assertTrue(
				this.mfs.getCurrentDirectory().getAbsolutePathname().equals("/"));
		assertTrue(this.mfs.getDirectoryStack().size() == 1); // from saved file
		assertTrue(this.mch.getStack().size() == 3);
		// file should still exist
		this.loadFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertTrue(loadFile.exists());
		assertTrue(loadFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsValidRelativePath() {
		String[] args = { "saved.ser" };
		load.executeCommandOperations(args);
		assertTrue(
				this.mfs.getCurrentDirectory().getAbsolutePathname().equals("/"));
		assertTrue(this.mfs.getDirectoryStack().size() == 1); // from saved file
		assertTrue(this.mch.getStack().size() == 3);
		// file should still exist
		this.loadFile = new File("saved.ser");
		assertTrue(loadFile.exists());
		assertTrue(loadFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsTooFewArgs() {
		String[] args = {};
		load.executeCommandOperations(args);
		// Error, keep everything default,
		// do not load any file, print appropriate error msg
		assertTrue(
				this.mfs.getCurrentDirectory().equals(this.mfs.getRootDirectory()));
		assertTrue(this.mfs.getRootDirectory().getDirectoryContents().size() == 1);
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
		assertTrue(this.mch.getStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsTooManyArgs() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved.ser",
				"C:\\Users\\invaliduser\\invalidfilename.txt" };
		load.executeCommandOperations(args);
		// Error, keep everything default,
		// do not load any file, print appropriate error msg
		assertTrue(
				this.mfs.getCurrentDirectory().equals(this.mfs.getRootDirectory()));
		assertTrue(this.mfs.getRootDirectory().getDirectoryContents().size() == 1);
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
		assertTrue(this.mch.getStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsTooLate() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved.ser" };
		this.mch.addCommandToStack("echo invalid");
		this.mch.addCommandToStack("echo invalid");
		/*
		 * Need to insert two commands to stack; stack is added to in jshell
		 * immediately after command is called, before command executes its
		 * operations, so load checks for stack size > 1 to disable itself
		 */
		System.out.println(this.mch.getStackSize());
		load.executeCommandOperations(args);
		// Error, keep everything default,
		// do not load any file, print appropriate error msg
		assertTrue(
				this.mfs.getCurrentDirectory().equals(this.mfs.getRootDirectory()));
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
		assertTrue(this.mch.getStack().size() == 2);
	}

}
