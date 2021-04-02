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
import commandUtils.CommandOutputProducer;
import path.File;
import system.FileSystemManipulation;

public class CommandOutputProducerTest {

	@Before
	public void setUp() throws Exception {
		Field f = FileSystemManipulation.class.getDeclaredField("fs");
		f.setAccessible(true);
		f.set(null, MockFileSystem.getFileSystemInstance());
	}

	@After
	public void tearDown() throws Exception {
		Field f = MockFileSystem.class.getDeclaredField("mfs");
		f.setAccessible(true);
		f.set(null, null);

		FileSystemManipulation.class.getDeclaredField("fs");
		f.setAccessible(true);
		f.set(null, null);
	}

	@Test
	public void testGetAppropriateOutputMethodWithFileWrite() {
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		String[] inputWords = { ">>", "/test/testFile" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		assertEquals("[hello world]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithFileNewWriteFullPath() {
		String[] inputWords = { ">", "/myFile" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation.findFileSystemPath("/myFile");
		assertEquals("[hello world]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithFileNewWriteRelativePath() {
		String[] inputWords = { ">", "myFile" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation.findFileSystemPath("/myFile");
		assertEquals("[hello world]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithMultipleFlags() {
		String[] inputWords = { ">>", ">", "/test/testFile" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		assertEquals("[]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithFileNameBeforeFlag() {
		String[] inputWords = { "/test/testFile", ">>" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		assertEquals("[]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithOverwriteExistingFile() {
		String[] inputWords = { ">", "/test/testFile" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		assertEquals("[hello world]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithArgumentsBefore() {
		String[] inputWords = { "arg1", "arg2", "arg3", ">>", "/test/testFile" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		assertEquals("[hello world]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithArgumentsAfter() {
		String[] inputWords = { ">>", "/test/testFile", "arg1", "arg2", "arg3" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		assertEquals("[]", file.getFileContent().toString());
	}

	@Test
	public void testGetAppropriateOutputMethodWithArgumentsBeforeAndAfter() {
		String[] inputWords = { "arg1", ">>", "/test/testFile", "arg1" };
		CommandOutputProducer.processCommandOutput(inputWords, "hello world");
		File file = (File) FileSystemManipulation
				.findFileSystemPath("/test/testFile");
		assertEquals("[]", file.getFileContent().toString());
	}

}
