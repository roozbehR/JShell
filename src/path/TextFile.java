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

import java.util.ArrayList;

/**
 * A class to accommodate behaviors of a text file. Subclass of File.
 * 
 * @author Shammo Talukder
 */
public class TextFile extends Path implements File {

	/**
	 * To manage the file contents
	 */
	private ArrayList<String> fileContent;

	/**
	 * Constructor to create new TextFile, sets filename and absolutePathname
	 * according to superclass. Initializes an empty ArrayList to hold file
	 * contents.
	 * 
	 * @param filename          the file's name
	 * @param residingDirectory the current Directory which the file will be
	 *                          stored in
	 */
	public TextFile(String filename, Directory residingDirectory) {
		super(filename, residingDirectory.absolutePathname + "/" + filename);
		residingDirectory.addContent(this);
		this.fileContent = new ArrayList<String>();
	}

	@Override
	public Object getFileContent() {
		return this.fileContent;
	}

	public void setFileContent(ArrayList<String> fileContent) {
		this.fileContent = fileContent;
	}

	/**
	 * Add newContent (string) to arraylist fileContent.
	 *
	 * @param newContent the new content
	 */
	@Override
	public void appendContentToFile(Object newContent) {
		this.fileContent.add((String) newContent);
	}

	/**
	 * Clear arraylist fileContent, then add newContent (string) to fileContent.
	 *
	 * @param newContent the new content
	 */
	@Override
	public void writeContentToFile(Object newContent) {
		this.fileContent.clear();
		this.fileContent.add((String) newContent);
	}

	@Override
	public boolean equals(Object textFile) {
		if (!((TextFile) textFile).getRelativePathname()
				.equals(this.getRelativePathname())) {
			return false;
		}

		else if (!((TextFile) textFile).getFileContent()
				.equals(this.getFileContent())) {
			return false;
		}

		return true;
	}

}
