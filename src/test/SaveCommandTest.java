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

import java.io.File;
import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import commands.SaveCommand;
import path.Directory;

public class SaveCommandTest {

	private SaveCommand save;
	private MockFileSystem mfs;
	private MockCommandHistory mch;
	private File savedFile;

	@Before
	public void setUp() throws Exception {
		this.mfs = MockFileSystem.getFileSystemInstance();
		this.mch = MockCommandHistory.getCommandHistoryInstance();
		this.mfs.pushDirectoryToStack(
				(Directory) this.mfs.getRootDirectory().getDirectoryContents().get(0));
		save = new SaveCommand();
		Field saveFieldFs = (save.getClass()).getDeclaredField("fs");
		saveFieldFs.setAccessible(true);
		saveFieldFs.set(save, this.mfs);
		Field saveFieldCh = (save.getClass()).getDeclaredField("cmdHistory");
		saveFieldCh.setAccessible(true);
		saveFieldCh.set(save, this.mch);
	}

	@After
	public void tearDown() throws Exception {
		Field mfsField = (this.mfs.getClass()).getDeclaredField("mfs");
		mfsField.setAccessible(true);
		mfsField.set(this.mfs, null);
		Field mchField = (this.mch.getClass()).getDeclaredField("mch");
		mchField.setAccessible(true);
		mchField.set(this.mch, null);
		if (savedFile.exists() && savedFile.isFile())
			savedFile.delete();
	}

	@Test
	public void testExecuteCommandOperationsInvalidFilePath() {
		String[] args = { "C:\\Users\\invaliduser\\invalidfilename.txt" };
		save.executeCommandOperations(args);
		// Error, do not create any file, print appropriate error msg
		this.savedFile = new File("C:\\Users\\invaliduser\\invalidfilename.txt");
		assertFalse(savedFile.exists());
		assertFalse(savedFile.isDirectory());
	}

	@Test
	public void testExecuteCommandOperationsValidAbsolutePath() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved.ser" };
		save.executeCommandOperations(args);
		this.savedFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertTrue(savedFile.exists());
		assertTrue(savedFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsValidRelativePath() {
		String[] args = { "saved.ser" };
		save.executeCommandOperations(args);
		this.savedFile = new File("saved.ser");
		assertTrue(savedFile.exists());
		assertTrue(savedFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsWrongExtension() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved.txt" };
		save.executeCommandOperations(args);
		// Should strip .txt and set extension as .ser
		savedFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertTrue(savedFile.exists());
		assertTrue(savedFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsNoExtension() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved" };
		save.executeCommandOperations(args);
		// Set extension as .ser
		savedFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertTrue(savedFile.exists());
		assertTrue(savedFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsTooFewArgs() {
		String[] args = {};
		save.executeCommandOperations(args);
		// Error, do not create any file, print appropriate error msg
		savedFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertFalse(savedFile.exists());
		assertFalse(savedFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsTooManyArgs() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved",
				"C:\\Users\\samya\\Desktop\\saveagain" };
		save.executeCommandOperations(args);
		// Error, do not create any file, print appropriate error msg
		savedFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertFalse(savedFile.exists());
		assertFalse(savedFile.isFile());
	}

	@Test
	public void testExecuteCommandOperationsOverWrite() {
		String[] args = { "C:\\Users\\samya\\Desktop\\saved.ser" };
		save.executeCommandOperations(args);
		savedFile = new File("C:\\Users\\samya\\Desktop\\saved.ser");
		assertTrue(savedFile.exists());
		assertTrue(savedFile.isFile());
		// File should still exist
		save.executeCommandOperations(args);
		assertTrue(savedFile.exists());
		assertTrue(savedFile.isFile());
	}

}
