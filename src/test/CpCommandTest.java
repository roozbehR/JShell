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
import commands.CpCommand;
import system.FileSystemI;
import system.FileSystemManipulation;

public class CpCommandTest {
  
  private FileSystemI fileSystem;
  private CpCommand cpCommand;

  @Before
  public void setUp() throws Exception {
    this.fileSystem = MockFileSystemForCp.getFileSystemInstance();
    Field f = FileSystemManipulation.class.getDeclaredField("fs");
    f.setAccessible(true);
    f.set(null, this.fileSystem);
    this.cpCommand = new CpCommand();
  }

  @After
  public void tearDown() throws Exception {
    Field f = fileSystem.getClass().getDeclaredField("fs");
    f.setAccessible(true);
    f.set(null, null);

    f = FileSystemManipulation.class.getDeclaredField("fs");
    f.setAccessible(true);
    f.set(null, this.fileSystem);
  }

  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir/a/a1txt", "a1dir"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir/a/a1txt", "a2txt"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir/a/a1txt", "dir/b/b1dir"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir", "dir/b/b1dir"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir", "dir/a/a1txt"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir", "dir2"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"txt", "dir"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir/a/a1dir", "dir/b"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
  
  @Test
  public void testExecuteCommandOperations() {
    String[] input = {"dir/a", "/"};
    assertFalse(null == cpCommand.executeCommandOperations(input));
  }
}
