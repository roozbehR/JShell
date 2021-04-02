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

package path;

/**
 * An interface to encompass behaviours of every type of File.
 * 
 * @author Shammo Talukder
 */
public interface File {

	/**
	 * A method to simply add content to a file.
	 */
	public abstract void appendContentToFile(Object newContent);

	/**
	 * A method to clear, then add content to a file.
	 */
	public abstract void writeContentToFile(Object newContent);

	/**
	 * A method to retrieve content of a file.
	 */
	public abstract Object getFileContent();

}
