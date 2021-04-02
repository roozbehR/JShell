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
 * Parent class of Directory and TextFile. Holds relative and absolute path
 * names.
 * 
 * @author Farhan Chowdhury
 */
public class Path implements java.io.Serializable {

	/**
	 * This field stores the relative pathname of the path
	 */
	protected String relativePathname;

	/**
	 * This field stores the full pathname for the path
	 */
	protected String absolutePathname;

	/**
	 * This method creates an instance of Path and stores the relative and
	 * absolute pathnames.
	 * 
	 * @param relativePathname the relative pathname
	 * @param absolutePathname the absolute pathname
	 */
	public Path(String relativePathname, String absolutePathname) {
		this.absolutePathname = absolutePathname;
		this.relativePathname = relativePathname;
	}

	public String getAbsolutePathname() {
		return this.absolutePathname;
	}

	public String getRelativePathname() {
		return this.relativePathname;
	}

	public void setAbsolutePathname(String absolutePathname) {
		this.absolutePathname = absolutePathname;
	}

	@Override
	public String toString() {
		return this.absolutePathname;
	}

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method 
	 * stub
	 * 
	 * }
	 */

}
