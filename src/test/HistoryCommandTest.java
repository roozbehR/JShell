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
import org.junit.BeforeClass;
import org.junit.Test;
import commands.HistoryCommand;
import system.FileSystemManipulation;

public class HistoryCommandTest {

	private HistoryCommand historyCommand;
	private static String defaultHistoryOutput;

	@BeforeClass
	public static void beforeAll() {
		// prep dummy history
		String[] dummyCommands = { "invalidCommand argument", "invalidCommand",
				"echo \"hello world\"" };
		int idx = 1;
		defaultHistoryOutput = "";
		for (String comm : dummyCommands) {
			defaultHistoryOutput += String.valueOf(idx) + ". " + comm + "\n";
			idx++;
		}
	}

	@Before
	public void setUp() throws Exception {
		// set up mock command history
		historyCommand = new HistoryCommand();
		Field commandHistoryInstance = historyCommand.getClass()
				.getDeclaredField("commandHistory");
		commandHistoryInstance.setAccessible(true);
		commandHistoryInstance.set(null,
				MockCommandHistory.getCommandHistoryInstance());

		// set up mock file system
		Field f = FileSystemManipulation.class.getDeclaredField("fs");
		f.setAccessible(true);
		f.set(null, MockFileSystem.getFileSystemInstance());
	}

	@After
	public void tearDown() throws Exception {
		Field f = MockFileSystem.class.getDeclaredField("mfs");
		f.setAccessible(true);
		f.set(null, null);

		f = MockCommandHistory.class.getDeclaredField("mch");
		f.setAccessible(true);
		f.set(null, null);
	}

	@Test
	public void testExecuteCommandOperationsWithNoInput() {
		String[] input1 = {};
		assertEquals(defaultHistoryOutput,
				historyCommand.executeCommandOperations(input1));
	}

	@Test
	public void testExecuteCommandOperationsWithZeroOnly() {
		String[] input = { "0" };
		assertEquals("", historyCommand.executeCommandOperations(input));
	}

	@Test
	public void testExecuteCommandOperationsWithStackSizeOnly() {
		String[] input = { String.valueOf(
				MockCommandHistory.getCommandHistoryInstance().getStackSize()) };
		assertEquals(defaultHistoryOutput,
				historyCommand.executeCommandOperations(input));
	}

	@Test
	public void testExecuteCommandOperationsWithMoreThanStackSizeOnly() {
		String[] input = { String.valueOf(
				MockCommandHistory.getCommandHistoryInstance().getStackSize() + 1) };
		assertEquals(defaultHistoryOutput,
				historyCommand.executeCommandOperations(input));
	}

	@Test
	public void testExecuteCommandOperationsWithNegativeNumber() {
		String[] input = { "-5" };
		assertEquals(null, historyCommand.executeCommandOperations(input));
	}

	@Test
	public void testExecuteCommandOperationsWithMultipleNumbers() {
		String[] input = { "2", "1" };
		assertEquals(null, historyCommand.executeCommandOperations(input));
	}
}
