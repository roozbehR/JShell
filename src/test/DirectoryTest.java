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

import org.junit.Before;
import org.junit.Test;

import path.Directory;
import path.TextFile;

public class DirectoryTest {

	/*
	 * This class uses TextFile for testing purposes. TextFile has been fully
	 * implemented and tested from part A. Basic getters and setters have been
	 * excluded from tests.
	 */
	private Directory testDirectory;

	@Before
	public void setUp() throws Exception {
		this.testDirectory = new Directory("testDir", "/testDir");
	}

	@Test
	public void testDirectoryCreation() {
		assertEquals("testDir", this.testDirectory.getRelativePathname());
		assertEquals("/testDir", this.testDirectory.getAbsolutePathname());
		assertTrue(this.testDirectory.getDirectoryContents().size() == 0);
	}

	@Test
	public void testAddContentDirectory() {
		this.testDirectory
				.addContent(new Directory("testDir2", "/testDir/testDir2"));
		assertTrue(this.testDirectory.getDirectoryContents().size() == 1);
		assertEquals(new Directory("testDir2", "/dummyDir/testDir2"),
				this.testDirectory.getDirectoryContents().get(0));
	}

	@Test
	public void testAddContentTextFile() {
		// TextFile constructor uses Directory.addContent() to add itself
		new TextFile("testFile", this.testDirectory);
		assertEquals(1, this.testDirectory.getDirectoryContents().size());
		assertEquals(new TextFile("testFile", this.testDirectory),
				this.testDirectory.getDirectoryContents().get(0));
	}

	@Test
	public void testRemoveContentDirectory() {
		Directory testDir = new Directory("testDir2", "/testDir/testDir2");
		this.testDirectory.addContent(testDir);
		this.testDirectory.removeContent(testDir);
		assertEquals(0, this.testDirectory.getDirectoryContents().size());
	}

	@Test
	public void testRemoveContentTextFile() {
		// TextFile constructor uses Directory.addContent() to add itself
		TextFile testFile = new TextFile("testFile", this.testDirectory);
		this.testDirectory.removeContent(testFile);
		assertEquals(0, this.testDirectory.getDirectoryContents().size());
	}

	@Test
	public void testIndexOfFileOrDirectory() {
		Directory testDir = new Directory("testDir2", "/testDir/testDir2");
		this.testDirectory.addContent(testDir);
		assertEquals(0, this.testDirectory.indexOfFileOrDirectory("testDir2"));
	}

	@Test
	public void testIndexOfFileOrDirectoryNonExistent() {
		assertEquals(-1, this.testDirectory.indexOfFileOrDirectory("testDir2"));
	}

	@Test
	public void testGetContentDirectory() {
		Directory testDir = new Directory("testDir2", "/testDir/testDir2");
		this.testDirectory.addContent(testDir);
		assertEquals(new Directory("testDir2", "/dummyDir/testDir2"),
				this.testDirectory.getContentDirectory("testDir2"));
	}

	@Test
	public void testGetContentDirectoryTextFile() {
		// TextFile constructor uses Directory.addContent() to add itself
		TextFile testFile = new TextFile("testFile", this.testDirectory);
		Directory dummyDir = new Directory("dummyDir", "/dummyDir");
		assertEquals(new TextFile("testFile", dummyDir),
				this.testDirectory.getContentDirectory("testFile"));
	}

	@Test
	public void testGetContentDirectoryNonExistent() {
		assertEquals(null, this.testDirectory.getContentDirectory("testDir2"));
	}

}
