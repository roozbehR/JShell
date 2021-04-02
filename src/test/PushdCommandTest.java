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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.CdCommand;
import commands.PushdCommand;

public class PushdCommandTest {

	/*
	 * Pushd is dependent on Cd, which has been fully implemented and tested from
	 * part A.
	 */
	private PushdCommand pushd;
	private CdCommand cd;
	private MockFileSystem mfs;

	@Before
	public void setUp() throws Exception {
		mfs = MockFileSystem.getFileSystemInstance();
		cd = new CdCommand();
		pushd = new PushdCommand();
		Field fsField = Class.forName("system.FileSystemManipulation")
				.getDeclaredField("fs");
		fsField.setAccessible(true);
		Object currFs = fsField.get(Class.forName("system.FileSystemManipulation"));
		fsField.set(currFs, mfs);
		Field cdField = (cd.getClass()).getDeclaredField("fs");
		cdField.setAccessible(true);
		cdField.set(cd, mfs);
		Field pushdField = (pushd.getClass()).getDeclaredField("fs");
		pushdField.setAccessible(true);
		pushdField.set(pushd, mfs);
		Field pushdFieldCd = (pushd.getClass()).getDeclaredField("cd");
		pushdFieldCd.setAccessible(true);
		pushdFieldCd.set(pushd, cd);
	}

	@After
	public void tearDown() throws Exception {
		Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(mfs, null);
	}

	@Test
	public void testExecuteCommandOperationsRootDir() {
		String[] args = { "/" };
		pushd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		assertEquals("/",
				this.mfs.getDirectoryStack().get(0).getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsCurrDir() {
		String[] args = { "." };
		pushd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		assertEquals("/",
				this.mfs.getDirectoryStack().get(0).getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsEllipses() {
		String[] args = { ".." };
		pushd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		assertEquals("/",
				this.mfs.getDirectoryStack().get(0).getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsChainedEllipses() {
		String[] args = { "/.././test/test2" };
		pushd.executeCommandOperations(args);
		assertEquals("/test/test2",
				this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		assertEquals("/",
				this.mfs.getDirectoryStack().get(0).getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsValidDir() {
		String[] args = { "test" };
		pushd.executeCommandOperations(args);
		assertEquals("/test", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		assertEquals("/",
				this.mfs.getDirectoryStack().get(0).getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsMultiple() {
		String[] args = new String[1];
		args[0] = "test";
		pushd.executeCommandOperations(args);
		assertEquals("/test", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		assertEquals("/",
				this.mfs.getDirectoryStack().get(0).getAbsolutePathname());
		args[0] = "test2";
		pushd.executeCommandOperations(args);
		assertEquals("/test/test2",
				this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 2);
		assertEquals("/test",
				this.mfs.getDirectoryStack().get(1).getAbsolutePathname());
		args[0] = "test3";
		pushd.executeCommandOperations(args);
		assertEquals("/test/test2/test3",
				this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 3);
		assertEquals("/test/test2",
				this.mfs.getDirectoryStack().get(2).getAbsolutePathname());
	}

	@Test
	public void testExecuteCommandOperationsInvalidDir() {
		String[] args = { "/wher?e" };
		pushd.executeCommandOperations(args);
		// Error, remains at root dir, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsTextFile() {
		String[] args = { "/test/testFile" };
		pushd.executeCommandOperations(args);
		// Error, remains at root dir, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsTooFewArgs() {
		String[] args = {};
		pushd.executeCommandOperations(args);
		// Error, remains at root dir, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsTooManyArgs() {
		String[] args = { "/", "/test", "/test/test2" };
		pushd.executeCommandOperations(args);
		// Error, remains at root dir, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}
}
