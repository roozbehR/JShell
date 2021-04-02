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

import commands.EchoCommand;

public class EchoCommandTest {

	/*
	 * Redirection is tested for CommandOutputProducer, which will be handling all
	 * printing and redirecting of output from all commands.
	 */
	private EchoCommand echo;
	private MockFileSystem mfs;

	@Before
	public void setUp() throws Exception {
		echo = new EchoCommand();
		mfs = MockFileSystem.getFileSystemInstance();
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
	public void testExecuteCommandOperationsBasicString() {
		String[] args = { "\"hello\"" };
		assertEquals("hello\n", echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsEmptyString() {
		String[] args = { "" }; // not double-quoted
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsEmptyStringDoubleQuotes() {
		String[] args = { "\"\"" };
		assertEquals("\n", echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsBasicStringWithSpaces() {
		String[] args = { "\"hello world23, test\"" };
		assertEquals("hello world23, test\n", echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsBadFormat() {
		String[] args = { "\"hello" };
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsNoQuotes() {
		String[] args = { "hello" };
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsSingleQuotes() {
		String[] args = { "'hello'" };
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsTooFewArgs() {
		String[] args = {};
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsTooManyArgs() {
		String[] args = { "\"hello\"", ">", "newfile", "goingover" };
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

	@Test
	public void testExecuteCommandOperationsWrongNumArgs() {
		String[] args = { "\"hello\"", ">" };
		// Error, should return null and print appropriate error msg
		assertEquals(null, echo.executeCommandOperations(args));
	}

}
