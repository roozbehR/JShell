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

package system;

import java.util.ArrayList;
import path.Directory;
import path.Path;
import path.TextFile;
import test.MockFileSystem;

/**
 * A class containing methods related to manipulating and using the File System.
 * 
 * @author Roozbeh Yadollahi
 * @author Shammo Talukder
 */
public class FileSystemManipulation {

	/**
	 * The instance of the file system.
	 */
	private static FileSystemI fs = FileSystem.getFileSystemInstance();

	/**
	 * Returns the node in the file system with the given path name, which can be
	 * relative or absolute, if it exits, otherwise returns null.
	 * 
	 * @param pathName the path name of of the Path, which is a node in the file
	 *                 system, that can be absolute or relative
	 * @return the node according to the given path and null if the path is not
	 *         found
	 */
	public static Path findFileSystemPath(String pathName) {

		Directory traversalDirectory;
		// absolute pathname case
		if (pathName.charAt(0) == '/') {
			traversalDirectory = fs.getRootDirectory();
		}

		// relative pathname case
		else {
			traversalDirectory = fs.getCurrentDirectory();
		}

		String[] nameListTemporary = pathName.split("/");
		ArrayList<String> nameList = new ArrayList<String>();
		for (String s : nameListTemporary) {
			if (!s.equals("")) {
				nameList.add(s);
			}
		}
		return findFileSystemPathHelper(traversalDirectory, nameList);

	}

	private static Path findFileSystemPathHelper(Directory root,
			ArrayList<String> arguments) {
		Path currentDir = root;
		for (int i = 0; i < arguments.size(); i++) {
			if (!arguments.get(i).equals("..") && !arguments.get(i).equals(".")) {
				if (currentDir instanceof TextFile && i < arguments.size()) {
					return null;
				}
				int nextDirIndex = ((Directory) currentDir)
						.indexOfFileOrDirectory(arguments.get(i));

				if (nextDirIndex == -1) {
					return null;
				}
				currentDir = ((Directory) currentDir).getDirectoryContents()
						.get(nextDirIndex);
			} else if (arguments.get(i).equals("..")) {
				currentDir = findParentDirectory(currentDir);
			}
		}
		return currentDir;
	}

	private static int matchRelativeName(ArrayList<Path> content,
			String argument) {
		for (int i = 0; i < content.size(); i++) {
			if (content.get(i).getRelativePathname().equals(argument)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * A method that is used in order to find the parent directory of a given 
	 * Path name string.
	 *
	 * @param dirOrFile which the directory or file that we want to find parent
	 *                  directory of it
	 * @return Directory which is the parent directory of the file or directory
	 *         with the given name passed by the user
	 */
	public static Directory findParentDirectory(String dirOrFile) {

		if (dirOrFile.startsWith("/") || (dirOrFile.contains("/"))) {

			String[] splitPath = dirOrFile.split("/");

			if ((splitPath.length == 2) && (splitPath[0].isEmpty())) {
				return fs.getRootDirectory();
			}

			String dirName = dirOrFile.substring(0, dirOrFile.lastIndexOf("/"));

			Directory residingDir = FileSystemManipulation
					.findFileSystemPath(dirName) != null
							? (Directory) FileSystemManipulation.findFileSystemPath(dirName)
							: null;

			return residingDir;
		}

		else {
			return fs.getCurrentDirectory();
		}
	}

	/**
	 * A method that is used in order to find the parent directory of a given 
	 * file or directory and return it
	 *
	 * @param dirOrFile which the directory or file that we want to find parent
	 *                  directory of it
	 * @return Directory which is the parent directory of the file or directory
	 *         passed by the user
	 */
	public static Directory findParentDirectory(Path dirOrFile) {

		String absPathName = dirOrFile.getAbsolutePathname();
		Directory td = fs.getRootDirectory();
		String[] arguments = absPathName.split("/");
		ArrayList<String> nameList2 = new ArrayList<String>();
		for (String s : arguments) {
			if (!s.equals("")) {
				nameList2.add(s);
			}
		}

		if ((td.getAbsolutePathname() == absPathName)
				|| (nameList2.size() - 1 == 0)) {
			return td;
		}
		for (int i = 0; i < nameList2.size() - 1; i++) {
			int nextDirIndex = matchRelativeName(td.getDirectoryContents(),
					nameList2.get(i));
			if (nextDirIndex == -1) {
				return null;
			}
			td = (Directory) td.getDirectoryContents().get(nextDirIndex);
		}
		return (Directory) td;
	}

	/**
	 * Checks if possibleParentDir is a parent(1 or more levels above) of
	 * dirOrFile
	 * 
	 * @param dirOrFile
	 * @param possibleParentDir
	 * @return true if possibleParentDir is parent of dirOrFile
	 */
	public static boolean isParentDirectory(Path dirOrFile,
			Path possibleParentDir) {
	  if(possibleParentDir instanceof TextFile) {
        return false;
      }
	  Path parentOfDirOrFile = findParentDirectory(dirOrFile);
	  if (parentOfDirOrFile.equals(possibleParentDir)) {
		return true;
	  }
	  if (parentOfDirOrFile
			.equals(FileSystemManipulation.findFileSystemPath("/"))) {
		return false;
	  }
	  return isParentDirectory(parentOfDirOrFile, possibleParentDir);
	}

}
