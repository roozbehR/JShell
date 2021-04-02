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
 * A class to implement the behaviours of a directory. Subclass of Path.
 * 
 * @author Farhan Chowdhury
 * @author Shammo Talukder
 */
public class Directory extends Path {

	/**
	 * This field stores all of the subpaths (files or directories) that exists 
	 * in the directory.
	 */
	private ArrayList<Path> directoryContents;

	public Directory(String relativePathname, String absolutePathname) {
		super(relativePathname, absolutePathname);
		this.directoryContents = new ArrayList<Path>();
	}

	/**
	 * This method adds a new Path as a subpath of the Directory.
	 * 
	 * @param newPath the new Path
	 */
	public void addContent(Path newPath) {
		this.directoryContents.add(newPath);
	}

	/**
	 * This method removes an existing Path from the contents of the Directory.
	 *
	 * @param toRemove the content to remove
	 */
	public void removeContent(Path toRemove) {
		int indexToRemove = this
				.indexOfFileOrDirectory(toRemove.getRelativePathname());
		if (indexToRemove != -1)
			this.directoryContents.remove(indexToRemove);
	}

	/**
	 * This method returns index of file or directory matching the specified name
	 * within this directory.
	 *
	 * @param fileOrDirName the file or directory to check for
	 * @return non-negative number if found, -1 otherwise
	 */
	public int indexOfFileOrDirectory(String fileOrDirName) {
		for (int i = 0; i < directoryContents.size(); i++) {
			if (this.directoryContents.get(i).relativePathname
					.equals(fileOrDirName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This method gets the content directory matching relativePathname, null if
	 * not found.
	 *
	 * @param relativePathname the pathname
	 * @return the content directory corresponding to given pathname, null if not
	 *         found
	 */
	public Path getContentDirectory(String relativePathname) {
		int index = this.indexOfFileOrDirectory(relativePathname);
		try {
			return directoryContents.get(index);
		} catch (IndexOutOfBoundsException i) {
			return null;
		}
	}

	public ArrayList<Path> getDirectoryContents() {
		return directoryContents;
	}

	@Override
	public boolean equals(Object dir) {
		if (!((Directory) dir).getRelativePathname()
				.equals(this.getRelativePathname())) {
			return false;
		}

		else if (!((Directory) dir).getDirectoryContents()
				.equals(this.getDirectoryContents())) {
			return false;
		}

		return true;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * }
	 */
}
