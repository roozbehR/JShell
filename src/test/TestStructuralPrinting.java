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
import java.nio.file.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import commandUtils.StructuralPrinting;
import commands.LsCommand;
import path.Directory;
import system.FileSystemManipulation;

public class TestStructuralPrinting {

	/*
	 * sets a new environment/fileSystem for structural printing before every test
	 */
	@Before
	public void setUp() throws Exception {
		MockFileSystem mockFileSystem;
		mockFileSystem = MockFileSystem.getFileSystemInstance();
		Field f = Class.forName("system.FileSystemManipulation")
				.getDeclaredField("fs");
		f.setAccessible(true);
		Object oldVal = f.get(Class.forName("system.FileSystemManipulation"));
		f.set(oldVal, MockFileSystem.getFileSystemInstance());
	}

	/*
	 * After every test is run, it kills the mock file system singleton in order
	 * for setUp to create a brand new fileSystem for the next test
	 */
	@After
	public void tearDown() throws Exception {
		Field field = Class.forName("test.MockFileSystem").getDeclaredField("mfs");
		field.setAccessible(true);
		Object oldVal = field.get(Class.forName("test.MockFileSystem"));
		field.set(oldVal, null);
	}

	/*
	 * test if the directory and all sub directories of the given existing path is
	 * returned/printed out as a tree
	 */
	@Test
	public void testGivenAValidNode() {
		path.Path path = FileSystemManipulation.findFileSystemPath(MockFileSystem
				.getFileSystemInstance().getCurrentDirectory().getAbsolutePathname());
		String actualOutput = StructuralPrinting.printAsTree((path.Path) path);
		String expected = "\\" + "\n" + " test" + "\n" + "  test2" + "\n"
				+ "   test3" + "\n" + "  testFile" + "\n";
		assertEquals(actualOutput, expected);
	}
}
