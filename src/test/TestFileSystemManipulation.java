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
import path.Directory;
import path.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commands.LsCommand;
import system.FileSystemManipulation;

public class TestFileSystemManipulation {

	private MockFileSystem mfs;

	/*
	 * sets a new environment/fileSystem for FileSystemManipulation before every
	 * test
	 */
	@Before
	public void setUp() throws Exception {
		mfs = MockFileSystem.getFileSystemInstance();
		Field fsField = Class.forName("system.FileSystemManipulation")
				.getDeclaredField("fs");
		fsField.setAccessible(true);
		Object currFs = fsField.get(Class.forName("system.FileSystemManipulation"));
		fsField.set(currFs, mfs);
	}

	/*
	 * After every test is run, it kills the mock file system singleton in order
	 * for setUp to create a brand new fileSystem for the next test
	 */
	@After
	public void tearDown() throws Exception {
		Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(mfs, null);
	}

	/*
	 * tests if null is returned when a non existing path is passed to the program
	 */
	@Test
	public void testFindFileSystemPathWithNonExistingPath() {
		String expected = null;
		Path actualOutput = FileSystemManipulation
				.findFileSystemPath("/Doesn't/Exist");
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(expected, actualOutputName);
	}

	/*
	 * tests if correct node of the file system is returned when an existing path
	 * is passed to the program, and the entered path has '.' and '..' in it which
	 * mean current directory and parent directory respectively
	 */
	@Test
	public void testFindFileSystemPathWithExistingPath() {
		String expected = "/test";
		Path actualOutput = FileSystemManipulation
				.findFileSystemPath("test/test2/test3/.././..");
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(expected, actualOutputName);
	}

	/*
	 * tests if correct node of the file system is returned when an existing path
	 * is passed to the program, and the entered path doesn't have '.' and '..' in
	 * it which mean current directory and parent directory respectively
	 */
	@Test
	public void testFindFileSystemPathWithExistingPath2() {
		String expected = "/test/test2";
		Path actualOutput = FileSystemManipulation.findFileSystemPath("test/test2");
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(actualOutputName, expected);
	}

	/*
	 * tests if root of the file system is returned when existing path passed to
	 * the program is the root of the file system
	 */
	@Test
	public void testFindFileSystemPathByPassingRootDirectory() {
		String expected = "/";
		Path actualOutput = FileSystemManipulation.findFileSystemPath("/");
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(actualOutputName, expected);
	}

	/*
	 * tests if null is returned when a non existing path relative or absolute
	 * name which is a string is passed to the find parent method
	 */
	@Test
	public void testFindParentDirectoryWithNonExistingPath() {
		String expected = null;
		Path actualOutput = FileSystemManipulation
				.findParentDirectory("/Doesn't/Exist");
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(expected, actualOutputName);
	}

	/*
	 * tests if correct parent is returned when a relative or absolute path name
	 * of an existing path (which is a string) passed to the find parent method
	 */
	@Test
	public void testFindParentDirectoryWithExistingPath() {
		String expected = "/test/test2";
		Path actualOutput = FileSystemManipulation
				.findParentDirectory("/test/test2/test3");
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(expected, actualOutputName);
	}

	/*
	 * tests if correct parent is returned when an actual node of the file system
	 * which exists is passed to the find parent directory is passed to the find
	 * parent method
	 */
	@Test
	public void testFindParentDirectoryWithExistingPath2() {
		String expected = "/test/test2";
		Path path = FileSystemManipulation.findFileSystemPath("/test/test2/test3");
		Path actualOutput = FileSystemManipulation.findParentDirectory(path);
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(expected, actualOutputName);
	}

	/*
	 * tests if null is returned when an actual node which doesn't exist in the
	 * file system is passed to the find parent directory is passed to the find
	 * parent method
	 */
	@Test
	public void testFindParentDirectoryWithNonExistingPath2() {
		String expected = null;
		Path path = new Directory("Doesn't", "/Doesn't/Exist");
		Path actualOutput = FileSystemManipulation.findParentDirectory(path);
		String actualOutputName = null;
		if (actualOutput != null) {
			actualOutputName = actualOutput.getAbsolutePathname();
		}
		assertEquals(expected, actualOutputName);
	}
}
