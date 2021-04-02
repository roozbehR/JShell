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

package commands;

import driver.ErrorHandler;
import io.StdIOStream;
import system.FileSystem;
import system.FileSystemI;

/**
 * Pwd is a command that prints the full path of the working directory
 * 
 * @author Rakshit Patel
 */
public class PwdCommand extends Command {
  /* Necessary collaborators of the class */
  private FileSystemI fs = FileSystem.getFileSystemInstance();

  /**
   * prints the full path of the current working directory
   * 
   * @param inputWords An array of arguments inputed for the command
   */
  @Override
  public String executeCommandOperations(String[] inputWords) {

    if (inputWords.length >= 1) {
      // need to change message later
      ErrorHandler.processError(4);
      return null;
    }

    String workingDirectoryPath =
        FileSystem.getFileSystemInstance().getCurrentDirectory()
        .getAbsolutePathname();

    // if in the root directory then print just the root directory
    if (workingDirectoryPath.contentEquals("/")) {
      return "/\n";
    }
    // otherwise print the full path without the / at the end
    else {
      return "/" + workingDirectoryPath.substring(1, 
          workingDirectoryPath.length()) + "\n";
    }
  }

  /**
   * @return string that contains the manual for Pwd command
   */
  @Override
  public String getManual() {
    // return the instructions on how to use the command
    String manual =
        "NAME\n" + "\tpwd - return current working directory " + "name.\n\n" 
    + "SYNOPSIS\n"
            + "\tpwd\n\n" + "DESCRIPTION\n" 
    + "\tpwd displays the current working directory.\n";
    return manual;
  }

  /*
   * public static void main(String[] args) {
   * 
   * CommandManager cm = new CommandManager(); FileSystem fs = 
   * FileSystem.getFileSystemInstance();
   * 
   * //tests cm.processCommand("pwd");
   * 
   * Directory dir1 = new Directory("a", "/a"); 
   * fs.setCurrentDirectoryWithoutAddingToStack(dir1);
   * cm.processCommand ("pwd");
   * 
   * Directory dir2 = new Directory("b", "/a/b"); 
   * fs.setCurrentDirectoryWithoutAddingToStack(dir2);
   * cm.processCommand ("pwd");
   * 
   * Directory dir3 = new Directory("new", "/new");
   * fs.setCurrentDirectoryWithoutAddingToStack(dir3);
   *  cm.processCommand ("pwd"); }
   */

}
