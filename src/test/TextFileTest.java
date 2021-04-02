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

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import path.Directory;
import path.TextFile;

public class TextFileTest {

	/*
	 * Test file and test directory to store it. Directory has been implemented
	 * and tested from part A. Basic getters and setters have been excluded from
	 * tests.
	 */
	private Directory testDirectory;
	private TextFile testFile;

	@Before
	public void setUp() throws Exception {
		this.testDirectory = new Directory("test", "/test");
		this.testFile = new TextFile("testFile", this.testDirectory);
	}

	@Test
	public void testTextFileCreation() {
		assertEquals("testFile", this.testFile.getRelativePathname());
		assertEquals("/test/testFile", this.testFile.getAbsolutePathname());
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 0);
	}

	@Test
	public void testWriteContentToFileEmpty() {
		this.testFile.writeContentToFile("Testing.");
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("Testing.",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
	}

	@Test
	public void testWriteContentToFileExistingContent() {
		this.testFile.writeContentToFile("Testing.");
		this.testFile.writeContentToFile("It's just this string inside now.");
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("It's just this string inside now.",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
	}

	@Test
	public void testAppendContentToFileEmpty() {
		this.testFile.appendContentToFile("Testing.");
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 1);
		assertEquals("Testing.",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
	}

	@Test
	public void testAppendContentToFileExistingContent() {
		this.testFile.appendContentToFile("Testing.");
		this.testFile.appendContentToFile("It's not just this string inside now.");
		assertTrue(
				((ArrayList<String>) this.testFile.getFileContent()).size() == 2);
		assertEquals("Testing.",
				((ArrayList<String>) this.testFile.getFileContent()).get(0));
		assertEquals("It's not just this string inside now.",
				((ArrayList<String>) this.testFile.getFileContent()).get(1));
	}

}
