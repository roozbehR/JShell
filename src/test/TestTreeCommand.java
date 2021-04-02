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
import commands.TreeCommand;
import system.FileSystemI;

public class TestTreeCommand {
	TreeCommand treeCommand;
	MockFileSystem mockFileSystem;

	/*
	 * sets a new environment/fileSystem for treeCommand before every test
	 */
	@Before
	public void setUp() throws Exception {
		treeCommand = new TreeCommand();
		Field field = (treeCommand.getClass()).getDeclaredField("fs");
		field.setAccessible(true);
		field.set(treeCommand, MockFileSystem.getFileSystemInstance());
		mockFileSystem = MockFileSystem.getFileSystemInstance();
	}

	/*
	 * After every test is run, it kills the mock file system singleton in order
	 * for setUp to create a brand new fileSystem for the next test
	 */
	@After
	public void tearDown() throws Exception {
		Field field = (mockFileSystem.getClass().getDeclaredField("mfs"));
		field.setAccessible(true);
		field.set(mockFileSystem, null);
	}

	/*
	 * tests if file system is returned in form of a tree if no argument or flag
	 * is passed to the tree command
	 */
	@Test
	public void testTreeCommandNoArgNoFlag() {
		String[] input = {};
		String actualOutput = treeCommand.executeCommandOperations(input);
		String expected = "\\" + "\n" + " test" + "\n" + "  test2" + "\n"
				+ "   test3" + "\n" + "  testFile" + "\n\n";
		assertEquals(expected, actualOutput);
	}

	/*
	 * tests if null is returned if multiple argument and no flag is passed to the
	 * tree command
	 */
	@Test
	public void testTreeCommandMultipleArgNoFlag() {
		String[] input = { "/cscb07/", "random", "More than one argumnet" };
		String actualOutput = treeCommand.executeCommandOperations(input);
		assertEquals(null, actualOutput);
	}

	/*
	 * tests if null is returned if no argument and multiple flag is passed to the
	 * tree command
	 */
	@Test
	public void testTreeCommandNoArgMultipleFlag() {
		String[] input = { "-R", ">>" };
		String actualOutput = treeCommand.executeCommandOperations(input);
		assertEquals(null, actualOutput);
	}

	/*
	 * tests if null is returned if multiple argument and multiple flag is passed
	 * to the tree command
	 */
	@Test
	public void testTreeCommandMultipleArgMultipleFlag() {
		String[] input = { "-R", "Hi", "/CSCB07/CSCB09", "Random Argumnet", ">" };
		String actualOutput = treeCommand.executeCommandOperations(input);
		assertEquals(null, actualOutput);
	}
}
