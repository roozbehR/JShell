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

package driver;

/**
 * This class is designed to keep track of whether JShell should continue
 * running.
 * 
 * @author Farhan Chowdhury
 *
 */
public class RunChecker {
	private boolean isRunning;
	private static RunChecker instance = null;

	private RunChecker() {
		this.isRunning = true;
	}

	/**
	 * This method will always return the same instance of RunChecker, allowing 
	 * it to act as a singleton.
	 * 
	 * @return RunChecker the instance of RunChecker
	 */
	public static RunChecker getRunCheckerInstance() {
		if (RunChecker.instance == null) {
			RunChecker.instance = new RunChecker();
		}
		return RunChecker.instance;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean getRunning() {
		return this.isRunning;
	}
}
