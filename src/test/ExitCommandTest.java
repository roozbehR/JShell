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
import commands.ExitCommand;
import driver.RunChecker;

public class ExitCommandTest {

	private ExitCommand exitCommand;
	private RunChecker runChecker;

	@Before
	public void setUp() throws Exception {
		exitCommand = new ExitCommand();
		runChecker = RunChecker.getRunCheckerInstance();
		runChecker.setRunning(true);
	}

	@After
	public void tearDown() throws Exception {
		Field f = this.runChecker.getClass().getDeclaredField("instance");
		f.setAccessible(true);
		f.set(null, null);
	}

	@Test
	public void testExecuteCommandOperationsWithValidInput() {
		String[] input1 = {};

		assertEquals("", exitCommand.executeCommandOperations(input1));
		assertTrue(runChecker.getRunning() == false);
	}

	@Test
	public void testExecuteCommandOperationsWithOneArgument() {
		String[] input = { "oneArg" };
		assertEquals(null, exitCommand.executeCommandOperations(input));
		assertTrue(runChecker.getRunning());
	}

	@Test
	public void testExecuteCommandOperationsWithMultipleArguments() {
		String[] input = { "firstArg", "secondArg", "thirdArg" };
		assertEquals(null, exitCommand.executeCommandOperations(input));
		assertTrue(runChecker.getRunning());
	}

}
