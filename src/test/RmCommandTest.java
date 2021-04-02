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

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.RmCommand;
import path.Directory;

public class RmCommandTest {

	private RmCommand rm;
	private MockFileSystem mfs;

	/*
	 * Setting private variable file system inside Cd to mock fs. Doing the same
	 * inside file system manipulation, which has been implemented and tested from
	 * part A.
	 */
	@Before
	public void setUp() throws Exception {
		mfs = MockFileSystem.getFileSystemInstance();
		rm = new RmCommand();
		Field rmField = (rm.getClass()).getDeclaredField("fs");
		rmField.setAccessible(true);
		rmField.set(rm, mfs);
		Field fsField = Class.forName("system.FileSystemManipulation")
				.getDeclaredField("fs");
		fsField.setAccessible(true);
		Object currFs = fsField.get(Class.forName("system.FileSystemManipulation"));
		fsField.set(currFs, mfs);
	}

	@After
	public void tearDown() throws Exception {
		Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(mfs, null);
	}

	@Test
	public void testExecuteCommandOperationsRootDirectory() {
		String[] args = { "/" };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsCurrentDirectory() {
		String[] args = { "." };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsEllipses() {
		String[] args = { ".././" };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsInvalidDir() {
		String[] args = { "../no&e/" };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsValidDirNoChild() {
		String[] args = { "/test/test2/test3" };
		rm.executeCommandOperations(args);
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(((Directory) ((Directory) this.mfs.getRootDirectory()
				.getDirectoryContents().get(0)).getDirectoryContents().get(0))
						.getDirectoryContents().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsTopLevelCurrentDir() {
		String[] args = { "test" };
		rm.executeCommandOperations(args);
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(
				this.mfs.getCurrentDirectory().getDirectoryContents().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsInvalidInCurrentDir() {
		String[] args = { "test2" };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(
				this.mfs.getCurrentDirectory().getDirectoryContents().size() == 1);
	}

	@Test
	public void testExecuteCommandOperationsTooFewArgs() {
		String[] args = {};
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(
				this.mfs.getCurrentDirectory().getDirectoryContents().size() == 1);
	}

	@Test
	public void testExecuteCommandOperationsTooManyArgs() {
		String[] args = { "test", "test/test2" };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(
				this.mfs.getCurrentDirectory().getDirectoryContents().size() == 1);
	}

	@Test
	public void testExecuteCommandOperationsTextFile() {
		String[] args = { "/test/testFile" };
		rm.executeCommandOperations(args);
		// Error, no change to file system, print appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(
				this.mfs.getCurrentDirectory().getDirectoryContents().size() == 1);
	}

}
