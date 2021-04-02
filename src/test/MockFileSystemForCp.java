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

import java.util.Stack;
import path.Directory;
import path.TextFile;
import system.FileSystemI;

public class MockFileSystemForCp implements FileSystemI {
  
  private static MockFileSystemForCp fs;
  private Directory rootDirectory;
  private Directory currentDirectory;
  private Stack<Directory> directoryStack;
  
  private void setUpDirA(Directory dir) {
    Directory a = new Directory("a", "/dir/a");
    new TextFile("a1txt", a);
    new TextFile("a2txt", a);
    Directory a1dir = new Directory("a1dir", "/dir/a/a1dir");
    a1dir.addContent(new Directory("a11dir", "/dir/a/a1dir/a11dir"));
    a.addContent(a1dir);
    dir.addContent(a);
  }
  
  private void setUpDirB(Directory dir) {
    Directory b = new Directory("b", "/dir/b");
    Directory b1dir = new Directory("b1dir", "/dir/b/b1dir");
    new TextFile("b1txt", b1dir);
    new TextFile("b2txt", b1dir);
    b.addContent(b1dir);
    b.addContent(new Directory("a1dir", "/dir/b/a1dir"));
    dir.addContent(b);
  }
  
  private MockFileSystemForCp() {
    this.rootDirectory = new Directory("/", "/");
    Directory dir = new Directory("dir", "/dir");
    this.rootDirectory.addContent(dir);
    setUpDirA(dir);
    setUpDirB(dir);
    this.currentDirectory = this.rootDirectory;
  }

  public static MockFileSystemForCp getFileSystemInstance() {
    if (MockFileSystemForCp.fs == null) {
      MockFileSystemForCp.fs = new MockFileSystemForCp();
    }
    return MockFileSystemForCp.fs;
  }

  @Override
  public Stack<Directory> getDirectoryStack() {
    return this.directoryStack;
  }

  /**
   * Sets the current Directory
   * 
   * @param newDirectory the new directory
   */
  public void setCurrentDirectory(Directory newDirectory) {
      this.currentDirectory = newDirectory;
  }

  @Override
  public Directory getRootDirectory() {
    return this.rootDirectory;
  }

  @Override
  public Directory getCurrentDirectory() {
    return this.currentDirectory;
  }

  /**
   * This method adds directory to the top of the stack of saved directories
   * 
   * @param directory the directory to be saved
   */
  public void pushDirectoryToStack(Directory directory) {
      this.directoryStack.push(directory);
  }

  /**
   * This method gets the most recently saved directory.
   * 
   * @return the most recently saved directory
   */
  public Directory popDirectoryFromStack() {
      return this.directoryStack.pop();
  }

  @Override
  public boolean checkDirectoryStackEmpty() {
    return this.directoryStack.isEmpty();
  }

}
