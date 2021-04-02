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
import commands.MkdirCommand;
import path.Path;
import system.FileSystem;
import system.FileSystemManipulation;

public class MkdirCommandTest {

	private MkdirCommand mkdir;
	private FileSystem fs;

	@Before
	public void setUp() throws Exception {
		mkdir = new MkdirCommand();
		fs = FileSystem.getFileSystemInstance();

		Field f = FileSystemManipulation.class.getDeclaredField("fs");
		f.setAccessible(true);
		f.set(null, fs);
	}

	@After
	public void tearDown() throws Exception {
		Field f = FileSystem.class.getDeclaredField("fs");
		f.setAccessible(true);
		f.set(null, null);

		f = FileSystemManipulation.class.getDeclaredField("fs");
		f.setAccessible(true);
		f.set(null, null);
	}

	private String getDirAbsoluteName(String dir) {
		Path potentialDir = FileSystemManipulation.findFileSystemPath(dir);
		if (potentialDir == null) {
			return null;
		} else {
			return potentialDir.getAbsolutePathname();
		}
	}

	@Test
	public void testExecuteCommandOperationsWithRelativeDirName() {
		String[] inputWords = { "dir1" };
		assertFalse(null == mkdir.executeCommandOperations(inputWords));
		assertEquals("/dir1", getDirAbsoluteName("dir1"));
	}

	@Test
	public void testExecuteCommandOperationsWithNoInput() {
		String[] inputWords = {};
		assertEquals(null, mkdir.executeCommandOperations(inputWords));
	}

	@Test
	public void testExecuteCommandOperationsWithMultipleRelativeDirNames() {
		String[] inputWords = { "a", "b", "c" };
		assertFalse(null == mkdir.executeCommandOperations(inputWords));
		assertEquals("/a", getDirAbsoluteName("a"));
		assertEquals("/b", getDirAbsoluteName("b"));
		assertEquals("/c", getDirAbsoluteName("c"));
	}

	@Test
	public void testExecuteCommandOperationsWithExistingDir() {
		String[] inputWords = { "/" };
		assertEquals(null, mkdir.executeCommandOperations(inputWords));
	}

	@Test
	public void testExecuteCommandOperationsWithInvalidDirName() {
		String[] inputWords = { "dir#&" };
		assertEquals(null, mkdir.executeCommandOperations(inputWords));
	}

	@Test
	public void testExecuteCommandOperationsWithMultipleNames() {
		String[] inputWords = { "a", "b", "c", "/a/a1", "/b/b1", "c/c1/c11" };
		assertFalse(null == mkdir.executeCommandOperations(inputWords));
		assertEquals("/a", getDirAbsoluteName("a"));
		assertEquals("/b", getDirAbsoluteName("b"));
		assertEquals("/c", getDirAbsoluteName("c"));
		assertEquals("/a/a1", getDirAbsoluteName("a/a1"));
		assertEquals("/b/b1", getDirAbsoluteName("b/b1"));
		assertEquals(null, getDirAbsoluteName("c/c1/c11"));
	}

	@Test
	public void testExecuteCommandOperationsWithInvalidDirFirst() {
		String[] inputWords = { "/a/a1", "/a" };
		assertFalse(null == mkdir.executeCommandOperations(inputWords));
		assertEquals("/a", getDirAbsoluteName("a"));
		assertEquals(null, getDirAbsoluteName("a/a1"));
	}

	@Test
	public void testExecuteCommandOperationsWithInvalidDirLast() {
		String[] inputWords = { "a", "/a/a1/a11/a111" };
		assertFalse(null == mkdir.executeCommandOperations(inputWords));
		assertEquals("/a", getDirAbsoluteName("a"));
		assertEquals(null, getDirAbsoluteName("/a/a1/a11/a111"));
	}

}
