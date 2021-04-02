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
import commands.LsCommand;
import commands.TreeCommand;
import system.FileSystemManipulation;

public class TestLsCommand {

	LsCommand lsCommand;
	MockFileSystem mockFileSystem;

	/*
	 * sets a new environment/fileSystem for lsCommand before every test
	 */
	@Before
	public void setUp() throws Exception {
		lsCommand = new LsCommand();
		mockFileSystem = MockFileSystem.getFileSystemInstance();
		Field field = (lsCommand.getClass()).getDeclaredField("fs");
		field.setAccessible(true);
		field.set(lsCommand, MockFileSystem.getFileSystemInstance());
		Field f = Class.forName("system.FileSystemManipulation")
				.getDeclaredField("fs");
		f.setAccessible(true);
		Object oldVal = f.get(Class.forName("system.FileSystemManipulation"));
		f.set(oldVal, MockFileSystem.getFileSystemInstance());
	}

	/*
	 * After every test is run, it kills the mock file system singleton in order
	 * for setUp to create a brand new fileSystem for the next test
	 */
	@After
	public void tearDown() throws Exception {
		Field field = Class.forName("test.MockFileSystem").getDeclaredField("mfs");
		field.setAccessible(true);
		Object oldVal = field.get(Class.forName("test.MockFileSystem"));
		field.set(oldVal, null);
	}

	/*
	 * tests the result of the ls command when no input is entered by the user and
	 * expects the content of the current directory to be printed out
	 */
	@Test
	public void testLsCommandWithNoInput() {
		String[] input = {};
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = "test" + "\n";
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests the result of the ls command when no flag is entered by the user and
	 * the user enters a mix of both relative and absolute path which exists
	 * Expects the content of the each path be shown
	 */
	@Test
	public void testLsCommandWithMultipleArgNoFlagExistingPath() {
		String[] input = { "test", "/test/test2" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = "/test:" + "\n" + "test2 testFile " + "\n\n"
				+ "/test/test2:" + "\n" + "test3 " + "\n\n\n";
		assertEquals(actualOutput, expected);
	}

	/*
	 * tests the result of the ls command when no flag is entered by the user and
	 * the user enters a mix of both relative and absolute path which don't exist
	 * Expects a null output since paths entered don't exist
	 */
	@Test
	public void testLsCommandMultipleArgNoFlagPathNotExist() {
		String[] input = { "test5", "/Deson't/Exist" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests the result of the ls command when no flag is entered by the user and
	 * the user enters a mix of both relative and absolute path which some exist
	 * and some don't Expects a null output since paths entered don't exist
	 */
	@Test
	public void testLsCommandMultipleArgNoFlagSomeExistSomeNotExist() {
		String[] input = { "/test", "/Deson't/Exist" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if all sub directories of the current working directory are
	 * returned in form of a tree, when the user enters only one valid flag and no
	 * other arguments
	 */
	@Test
	public void testLsCommandOneValidFlagNoArg() {
		String[] input = { "-R" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = "\\" + "\n" + " test" + "\n" + "  test2" + "\n"
				+ "   test3" + "\n" + "  testFile" + "\n\n";
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns null when one invalid flag is entered by
	 * the user
	 */
	@Test
	public void testLsCommandOneInvalidFlag() {
		String[] input = { ">>" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns null when multiple valid flags are
	 * entered by the user
	 */
	@Test
	public void testLsCommandMultipleValidFlag() {
		String[] input = { "-R", "-R", "-R" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns null when multiple invalid flags are
	 * entered by the user
	 */
	@Test
	public void testLsCommandMultipleInvalidFlag() {
		String[] input = { ">>", "$", "%" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns null when multiple invalid and valid
	 * flags are entered by the user
	 */
	@Test
	public void testLsCommandMultipleValidAndInvalidFlag() {
		String[] input = { ">>", "-R", "%" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns all sub directories and files of each
	 * existing path entered by the user in form of a tree for each of the given
	 * paths when one valid flag and mutiple existing paths are entered by the
	 * user
	 */
	@Test
	public void testLsCommandOneValidFlagMultipleArgExistingPathAllDirectories() {
		String[] input = { "-R", "test", "test/test2" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = "test" + "\n" + " test2" + "\n" + "  test3" + "\n"
				+ " testFile" + "\n\n" + "test2\n" + " test3\n\n\n";
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns null when one valid flag and multiple non
	 * existing paths are entered by the user
	 */
	@Test
	public void testLsCommandOneFlagMultipleArgNonExistingPath() {
		String[] input = { "-R", "test5", "test/Exist" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests that if the program returns null when one valid flag and multiple non
	 * existing and existing paths are entered by the user
	 */
	@Test
	public void testLsCommandOneFlagMultipleArgNonExistingAndExistingPath() {
		String[] input = { "-R", "/test", "test/Exist" };
		String actualOutput = lsCommand.executeCommandOperations(input);
		String expected = null;
		assertEquals(expected, actualOutput);
	}
}
