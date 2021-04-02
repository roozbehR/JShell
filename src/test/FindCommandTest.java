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
import commands.FindCommand;
import system.FileSystemI;
import system.FileSystemManipulation;

public class FindCommandTest {

  private FileSystemI fileSystem;
  private FindCommand findCommand;

  @Before
  public void setUp() throws Exception {
    this.fileSystem = MockFileSystemForFind.getFileSystemInstance();
    Field f = FileSystemManipulation.class.getDeclaredField("fs");
    f.setAccessible(true);
    f.set(null, this.fileSystem);
    this.findCommand = new FindCommand();
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
  public void testExecuteCommandOperationsWithBasicInput() {
    String actual, expected;
    String[] test = {"/dir1", "-type", "f", "-name", "\"file1\""};
    actual = this.findCommand.executeCommandOperations(test);
    expected = "Searching /dir1, found:\n" + "\t/dir1/file1\n"
        + "\t/dir1/dir1/file1\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMultipleDirs() {
    String[] test =
        {"/dir1", "/dir2", "/dir4", "-type", "f", "-name", "\"file1\""};
    String expected = "Searching /dir1, found:\n" + "\t/dir1/file1\n"
        + "\t/dir1/dir1/file1\n" + "Searching /dir2, found:\n"
        + "\t/dir2/file1\n" + "Searching /dir4, found: NO MATCHES FOUND\n";
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMultipleDirsForDir() {
    String[] test =
        {"/dir1", "/dir2", "/dir4", "-type", "d", "-name", "\"dir1\""};
    String expected = "Searching /dir1, found:\n" + "\t/dir1\n"
        + "\t/dir1/dir1\n" + "\t/dir1/dir1/dir1\n" + "Searching /dir2, found:\n"
        + "\t/dir2/dir1\n" + "Searching /dir4, found: NO MATCHES FOUND\n";
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithInvalidType() {
    String[] test =
        {"/dir1", "/dir2", "/dir4", "-type", "s", "-name", "\"dir1\""};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithQuotelessExpression() {
    String[] test = {"/dir1", "/dir2", "/dir4", "-type", "f", "-name", "dir1"};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithNonexistentName() {
    String[] test = {"/dir1", "-type", "f", "-name", "\"dir10\""};
    String expected = "Searching /dir1, found: NO MATCHES FOUND\n";
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(expected, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMissingTypeFlag() {
    String[] test = {"/dir1", "/dir2", "/dir4", "f", "-name", "\"dir1\""};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMissingTypeValue() {
    String[] test = {"/dir1", "/dir2", "/dir4", "-type", "-name", "\"dir1\""};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMisssingNameFlag() {
    String[] test = {"/dir1", "/dir2", "/dir4", "-type", "f", "\"dir1\""};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMissingExpression() {
    String[] test = {"/dir1", "/dir2", "/dir4", "-type", "f", "-name"};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithNoPaths() {
    String[] test = {"-type", "s", "-name", "\"dir1\""};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithNoInput() {
    String[] test = {};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMissingTypeArguments() {
    String[] test = {"/dir1", "/dir2", "/dir4", "-name", "\"dir1\""};
    String actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithMissingNameArguments() {
    String actual;
    String[] test = {"/dir1", "-type", "f"};
    actual = this.findCommand.executeCommandOperations(test);
    assertEquals(null, actual);
  }

  @Test
  public void testExecuteCommandOperationsWithNonexistentDir() {
    String actual;
    String[] test = {"/dir10", "-type", "f", "-name", "\"file1\""};
    actual = this.findCommand.executeCommandOperations(test);
    assertEquals("", actual);
  }

  @Test
  public void testExecuteCommandOperationsWithNonexistentDirs() {
    String actual;
    String[] test =
        {"/dir1", "/dir10", "dir2", "-type", "f", "-name", "\"file1\""};
    actual = this.findCommand.executeCommandOperations(test);
    // assertEquals("", actual);
    System.out.println(actual);
  }

}
