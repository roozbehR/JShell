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

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import io.FileIOStream;
import path.Directory;
import path.TextFile;

/**
 * @author Shammo Talukder
 *
 */
public class FileIOStreamTest {

	// Directory class has been implemented and tested from part A.
	Directory testDir;
	TextFile testFile; // the interface File

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testDir = new Directory("test", "/test");
		testFile = new TextFile("testFile", testDir);
	}

	/**
	 * Test method for
	 * {@link io.FileIOStream#outputContent(java.lang.Object, path.File)}.
	 */
	@Test
	public void testOutputContentSingleLine() {
		FileIOStream.outputContent("testing", testFile);
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("testing",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
	}

	/**
	 * Test method for
	 * {@link io.FileIOStream#outputContent(java.lang.Object, path.File)}.
	 */
	@Test
	public void testOutputContentDoubleLine() {
		FileIOStream.outputContent("testing", testFile);
		FileIOStream.outputContent("testing2", testFile);
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 2);
		assertEquals("testing",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
		assertEquals("testing2",
				((ArrayList<String>) this.testFile.getFileContent()).get(1));
	}

	/**
	 * Test method for
	 * {@link io.FileIOStream#outputContentWithOverwrite(java.lang.Object, path.File)}.
	 */
	@Test
	public void testOutputContentWithOverwriteSingleLine() {
		FileIOStream.outputContentWithOverwrite("testing", testFile);
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("testing",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
	}

	/**
	 * Test method for
	 * {@link io.FileIOStream#outputContentWithOverwrite(java.lang.Object, path.File)}.
	 */
	@Test
	public void testOutputContentWithOverwriteMultipleLine() {
		FileIOStream.outputContentWithOverwrite("testing", testFile);
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("testing",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
		FileIOStream.outputContentWithOverwrite("testing again", testFile);
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("testing again",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
	}

	/**
	 * Test method for {@link io.FileIOStream#getInputContent(path.File)}.
	 */
	@Test
	public void testGetInputContentBlankFile() {
		String content = FileIOStream.getInputContent(testFile);
		assertEquals((new ArrayList<String>()).toString(), content);
	}

	/**
	 * Test method for {@link io.FileIOStream#getInputContent(path.File)}.
	 */
	@Test
	public void testGetInputContentTwoLines() {
		testFile.appendContentToFile("hello");
		testFile.appendContentToFile("testing");
		String content = FileIOStream.getInputContent(testFile);
		assertEquals("[hello, testing]", content);
	}

}
