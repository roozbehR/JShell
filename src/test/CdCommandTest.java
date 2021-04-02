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

import commands.CdCommand;

public class CdCommandTest {

	private CdCommand cd;
	private MockFileSystem mfs;

	/*
	 * Setting private variable file system inside Cd to mock fs. Doing the same
	 * inside file system manipulation, which has been implemented and tested from
	 * part A.
	 */
	@Before
	public void setUp() throws Exception {
		mfs = MockFileSystem.getFileSystemInstance();
		cd = new CdCommand();
		Field cdField = (cd.getClass()).getDeclaredField("fs");
		cdField.setAccessible(true);
		cdField.set(cd, mfs);
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
		cd.executeCommandOperations(args);
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsDepth() {
		String[] args = { "/test/test2/test3" };
		cd.executeCommandOperations(args);
		assertEquals("/test/test2/test3",
				this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsInvalidDir() {
		String[] args = { "/test/test2/ba%d" };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsInvalidDir2() {
		String[] args = { "//" };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsEllipses() {
		String[] args = { "." };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsEllipses2() {
		String[] args = { ".." };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsEllipsesChained() {
		String[] args = { "./test/.." };
		cd.executeCommandOperations(args);
		// Goes back to root
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsEllipsesChainedBad() {
		String[] args = { "/./test/../ba%d" };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsTextFile() {
		String[] args = { "/test/testFile" };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsTooFewArgs() {
		String[] args = {};
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsTooManyArgs() {
		String[] args = { "/test", "/test/test2", "-R" };
		cd.executeCommandOperations(args);
		// Does not change dir, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
	}

}
