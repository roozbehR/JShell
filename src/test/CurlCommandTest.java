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
import org.junit.Before;
import org.junit.Test;
import commands.CurlCommand;

public class CurlCommandTest {

	private CurlCommand curl;

	@Before
	public void setUp() throws Exception {
		this.curl = new CurlCommand();
		Field f = curl.getClass().getDeclaredField("webStream");
		f.setAccessible(true);
		f.set(this.curl, new MockWebFileIOStream());
	}

	@Test
	public void testExecuteCommandOperationsWithValidInput() {
		String[] test1 = { "validLink" };
		assertEquals(MockWebFileIOStream.getWebContent(),
				this.curl.executeCommandOperations(test1));
	}

	@Test
	public void testExecuteCommandOperationsWithInvalidLink() {
		String[] test = { "invalid link" };
		assertEquals(null, this.curl.executeCommandOperations(test));
	}

	@Test
	public void testExecuteCommandOperationsWithMultipleArguments() {
		String[] test = { "validLink", "input2" };
		assertEquals(null, this.curl.executeCommandOperations(test));
	}

	@Test
	public void testExecuteCommandOperationsWithNoInput() {
		String[] test = {};
		assertEquals(null, this.curl.executeCommandOperations(test));
	}

}
