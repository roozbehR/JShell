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
//Student3: Shammo Talukder
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

package commandUtils;

import java.util.ArrayList;
import driver.ErrorHandler;
import path.Directory;
import path.Path;
import path.TextFile;

/**
 * StructurlaPrinting is a class where is used to print all the folders and
 * files under the given node (starting point) in a structured form Ex. Tree
 * structure
 * 
 * @author Roozbeh Yadollahi
 */
public class StructuralPrinting {

	/**
	 * result is the result of the printing that should be returned as a string
	 */
	private static String result = "";

	/**
	 * Print all the folders and files under the starting point in the form of a
	 * tree
	 * 
	 * @param startingPoint which represents where we start our recursion and go
	 *                      further in depth from there
	 * 
	 * @return String that represents the outcome of the method (which is a tree)
	 *         that should be shown to the user and returns null in case of an
	 *         error
	 */
	public static String printAsTree(Path startingPoint) {
		result = "";
		return printAsTreeHelper("", startingPoint, 0);
	}

	private static String printAsTreeHelper(String space, Path sp, int depth) {

		if (sp != null && (sp instanceof Directory || sp instanceof TextFile)) {
			if (!(sp instanceof Directory) && depth == 0) {
				result += space + sp.getRelativePathname();
				return result;
			}
			if (sp.getRelativePathname().equals("/")) {
				result += "\\" + "\n";
			} else {
				result += space + sp.getRelativePathname() + "\n";
			}
			if (sp instanceof Directory) {
				ArrayList<Path> content = ((Directory) sp).getDirectoryContents();
				for (int i = 0; i < content.size(); i++) {
					printAsTreeHelper(space + " ", content.get(i), depth + 1);
				}
			}
		} else if (sp == null
				|| !(sp instanceof Directory || sp instanceof TextFile)) {
			ErrorHandler
					.processError("The node passed is neither a directory nor a file"
							+ "or it doens't exist in the file system");
			return null;
		}
		return result;
	}

}
