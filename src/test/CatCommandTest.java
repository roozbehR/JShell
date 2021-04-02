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
import commands.CatCommand;

public class CatCommandTest {

  private CatCommand cat;
  private MockFileSystem mfs;

  @Before
  public void setUp() throws Exception {
    mfs = MockFileSystem.getFileSystemInstance();
    cat = new CatCommand();
    Field catField = (cat.getClass()).getDeclaredField("fs");
    catField.setAccessible(true);
    catField.set(cat, mfs);
    Field fsField = Class.forName("system.FileSystemManipulation").
        getDeclaredField("fs");
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
  public void testExecuteCommandOperationsFile1Contents() {
    String[] args = {"/test/testFile1"};

    assertEquals("This is testFile1", cat.executeCommandOperations(args));
  }

  @Test
  public void testExecuteCommandOperationsFile2Contents() {
    String[] args = {"/test/testFile2"};

    assertEquals("This is testFile2", cat.executeCommandOperations(args));
  }

  @Test
  public void testExecuteCommandOperationsMultipleFileContents() {
    String[] args = {"/test/testFile1", "/test/testFile2"};

    assertEquals("This is testFile1\n\n\nThis is testFile2", 
        cat.executeCommandOperations(args));
  }

  @Test
  public void testExecuteCommandOperationsInvalidFile() {
    String[] args = {"test"};

    assertEquals("\n", cat.executeCommandOperations(args));
  }

  @Test
  public void testExecuteCommandOperationsOneValidFileOneInvalidFile() {
    String[] args = {"test", "/test/testFile1"};

    assertEquals("This is testFile1", cat.executeCommandOperations(args));
  }


}
