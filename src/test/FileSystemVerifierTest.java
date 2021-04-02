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

import verification.FileSystemVerifier;

public class FileSystemVerifierTest {

	/*
	 * MockFileSystem instance to be used. Constructor initializes with simple
	 * file system structure.
	 */
	private MockFileSystem mfs;

	/*
	 * Gets a new mock file system instance each time. Sets private static
	 * variable in FileSystemManipulation to mfs instead of fs.
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

	@After
	public void tearDown() throws Exception {
		Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(mfs, null);
	}

	@Test
	public void testContainsInvalidCharactersValidPath() {
		assertEquals(false,
				FileSystemVerifier.containsInvalidCharacters("thisIsOk"));
	}

	@Test
	public void testContainsInvalidCharactersInvalidPath() {
		assertEquals(true, FileSystemVerifier.containsInvalidCharacters("notOk*"));
	}

	@Test
	public void testContainsInvalidCharactersEmptyPath() {
		assertEquals(false, FileSystemVerifier.containsInvalidCharacters(""));
	}

	@Test
	public void testContainsNonSlashInvalidCharactersRootPath() {
		assertEquals(false,
				FileSystemVerifier.containsNonSlashInvalidCharacters("/"));
	}

	@Test
	public void testContainsNonSlashInvalidCharactersValidPath() {
		assertEquals(false,
				FileSystemVerifier.containsNonSlashInvalidCharacters("/test/test2"));
	}

	@Test
	public void testContainsNonSlashInvalidCharactersEmptyPath() {
		assertEquals(false,
				FileSystemVerifier.containsNonSlashInvalidCharacters(""));
	}

	@Test
	public void testIsValidPathExistent() {
		assertEquals(true, FileSystemVerifier.isValidPath("/test"));
	}

	@Test
	public void testIsValidPathNonExistent() {
		assertEquals(false, FileSystemVerifier.isValidPath("/teest"));
	}

	@Test
	public void testIsValidPathInvalidPath() {
		assertEquals(false, FileSystemVerifier.isValidPath("//not/$ok"));
	}

	@Test
	public void testIsValidDirectoryRootDir() {
		assertEquals(true, FileSystemVerifier.isValidDirectory("/"));
	}

	@Test
	public void testIsValidDirectoryDepth() {
		assertEquals(true,
				FileSystemVerifier.isValidDirectory("/test/test2/test3"));
	}

	@Test
	public void testIsValidDirectoryWithFile() {
		assertEquals(false, FileSystemVerifier.isValidDirectory("/test/testFile"));
	}

	@Test
	public void testIsValidDirectoryNonExistent() {
		assertEquals(false, FileSystemVerifier.isValidDirectory("/test/nope"));
	}

	@Test
	public void testIsValidFileExistent() {
		assertEquals(true, FileSystemVerifier.isValidFile("/test/testFile"));
	}

	@Test
	public void testIsValidFileNonExistent() {
		assertEquals(false, FileSystemVerifier.isValidFile("/test/testFile/no"));
	}

	@Test
	public void testIsValidFileWithDirectory() {
		assertEquals(false, FileSystemVerifier.isValidFile("/test/test2"));
	}

}
