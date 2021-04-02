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

/**
 * This is the parent class for all commands and defines methods all commands
 * must encompass.
 * 
 * @author Farhan Chowdhury
 */
public abstract class Command {

	/**
	 * This method executes the command operations.based on parsed user input and
	 * handle any errors from inappropriate input.
	 * 
	 * @param inputWords the arguments parsed from user input
	 * 
	 * @return TODO
	 * @return String the String interpretation of the command's results
	 */
	public abstract String executeCommandOperations(String[] inputWords);

	/**
	 * This method will return a String representation of the manual that 
	 * explains the command.
	 * 
	 * @return the manual for the command.
	 */
	public abstract String getManual();

	@Override
	public String toString() {
		return this.getManual();
	}

}
