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
import commands.PwdCommand;
import path.Directory;

public class PwdCommandTest {

  private PwdCommand pwd;
  private MockFileSystem mfs;
  private String currentDirName;
  @Before
  public void setUp() throws Exception {
    mfs = MockFileSystem.getFileSystemInstance();
    pwd = new PwdCommand();
    Field pwdField = (pwd.getClass()).getDeclaredField("fs");
    pwdField.setAccessible(true);
    pwdField.set(pwd, mfs);
    Field fsField = Class.forName("system.FileSystemManipulation").
        getDeclaredField("fs");
    fsField.setAccessible(true);
    Object currFs = fsField.get(Class.forName("system.FileSystemManipulation"));
    fsField.set(currFs, mfs);
    currentDirName = mfs.getCurrentDirectory().getAbsolutePathname();
    
  }

  @After
  public void tearDown() throws Exception {
    Field mfsField = (mfs.getClass()).getDeclaredField("mfs");
    mfsField.setAccessible(true);
    mfsField.set(mfs, null);
  }

  @Test
  public void testExecuteCommandOperationsPrintRootDir() {
    String[] args = {};
    System.out.println(pwd.executeCommandOperations(args) + currentDirName);
    assertEquals(currentDirName+"\n", pwd.executeCommandOperations(args));
  }
  /*@Test
  public void testExecuteCommandOperationsPrintTestDir() {
    currentDirName = mfs.getCurrentDirectory().getDirectoryContents().get(0).
        getAbsolutePathname();
    mfs.setCurrentDirectory((Directory)
        (mfs.getCurrentDirectory().getDirectoryContents().get(0)));
    String[] args = {};
    //System.out.println(pwd.executeCommandOperations(args) + currentDirName);
    assertEquals(currentDirName+"\n", pwd.executeCommandOperations(args));
  }*/


}
