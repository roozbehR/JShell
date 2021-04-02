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
import commands.PopdCommand;
import path.Directory;

public class PopdCommandTest {

	/*
	 * Popd is dependent on Cd, which has been fully implemented and tested from
	 * part A.
	 */
	private PopdCommand popd;
	private CdCommand cd;
	private MockFileSystem mfs;

	@Before
	public void setUp() throws Exception {
		mfs = MockFileSystem.getFileSystemInstance();
		cd = new CdCommand();
		popd = new PopdCommand();
		Field fsField = Class.forName("system.FileSystemManipulation")
				.getDeclaredField("fs");
		fsField.setAccessible(true);
		Object currFs = fsField.get(Class.forName("system.FileSystemManipulation"));
		fsField.set(currFs, mfs);
		Field cdField = (cd.getClass()).getDeclaredField("fs");
		cdField.setAccessible(true);
		cdField.set(cd, mfs);
		Field popdField = (popd.getClass()).getDeclaredField("fs");
		popdField.setAccessible(true);
		popdField.set(popd, mfs);
		Field pushdFieldCd = (popd.getClass()).getDeclaredField("cd");
		pushdFieldCd.setAccessible(true);
		pushdFieldCd.set(popd, cd);
	}

	@After
	public void tearDown() throws Exception {
		Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(mfs, null);
	}

	@Test
	public void testExecuteCommandOperationsEmptyStack() {
		String[] args = {};
		popd.executeCommandOperations(args);
		// Error, remains at root, prints appropriate error msg
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsRootDir() {
		this.mfs.pushDirectoryToStack(this.mfs.getRootDirectory());
		String[] args = {};
		popd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsCurrentDir() {
		this.mfs.pushDirectoryToStack(this.mfs.getCurrentDirectory());
		String[] args = {};
		popd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsValidDir() {
		this.mfs.pushDirectoryToStack(
				(Directory) this.mfs.getRootDirectory().getDirectoryContents().get(0));
		String[] args = {};
		popd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/test", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsMultiple() {
		this.mfs.pushDirectoryToStack(
				(Directory) this.mfs.getRootDirectory().getDirectoryContents().get(0));
		this.mfs.pushDirectoryToStack(
				(Directory) ((Directory) this.mfs.getRootDirectory()
						.getDirectoryContents().get(0)).getDirectoryContents().get(0));
		this.mfs.pushDirectoryToStack(
				(Directory) ((Directory) ((Directory) this.mfs.getRootDirectory()
						.getDirectoryContents().get(0)).getDirectoryContents().get(0))
								.getDirectoryContents().get(0));
		String[] args = {};
		popd.executeCommandOperations(args);
		assertEquals("/test/test2/test3",
				this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 2);
		popd.executeCommandOperations(args);
		assertEquals("/test/test2",
				this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
		popd.executeCommandOperations(args);
		assertEquals("/test", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 0);
	}

	@Test
	public void testExecuteCommandOperationsArgs() {
		this.mfs.pushDirectoryToStack(
				(Directory) this.mfs.getRootDirectory().getDirectoryContents().get(0));
		String[] args = { "/test" };
		popd.executeCommandOperations(args);
		// Virtually no effect, but must still execute proper steps
		assertEquals("/", this.mfs.getCurrentDirectory().getAbsolutePathname());
		assertTrue(this.mfs.getDirectoryStack().size() == 1);
	}

	/**/
}
