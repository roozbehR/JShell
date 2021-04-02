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
import java.util.Iterator;
import java.util.Stack;
import path.Directory;
import path.Path;

/**
 * Tree is a class where iterates thorough file system data structure in dfs
 * approach
 * 
 * @author Roozbeh Yadollahi
 */
public class DepthFirstIterator<E> implements Iterator<E> {

	private Stack<QueueMembers<E>> stack;

	/**
	 * Tree is a class where stores the nodes of the file system with different
	 * fields
	 * 
	 * @author Roozbeh Yadollahi
	 */
	public class QueueMembers<F> {

		/*
		 * 'steps represents how many steps the node is from the root
		 * 
		 * absolutePath represents the absolute path name of the node in the file
		 * system
		 * 
		 * value represents the value that the user wants back from the node stores
		 * as QueueMember which can be string, etc (usage of generic)
		 */
		private int steps;
		private String absolutePath;
		private F value;

		public QueueMembers(int steps, F value) {
			this.steps = steps;
			this.absolutePath = String.valueOf(value);
			this.value = value;
		}

		public int getSteps() {
			return steps;
		}

		public String getAbsolutePath() {
			return absolutePath;
		}
	}

	public DepthFirstIterator(E startingPoint) {
		if (startingPoint != null) {
			stack = new Stack<QueueMembers<E>>();
			QueueMembers<E> qm = new QueueMembers<E>(0, startingPoint);
			stack.add(qm);
		}
	}

	/**
	 * Checks if the file system structure has any more unvisited nodes
	 */
	@Override
	public boolean hasNext() {

		return (!stack.isEmpty());
	}

	private void printQueue() {
		for (QueueMembers<E> qm : stack) {
			System.out.println(qm.absolutePath);
		}
	}

	/**
	 * Returns back the next unvisited node in the file system, such that it
	 * follows a depth first search approach
	 */
	@Override
	public E next() {
		QueueMembers<E> result = null;
		if (hasNext()) {
			QueueMembers<E> qm = stack.pop();
			E currentMemeber = qm.value;
			if (currentMemeber instanceof Directory) {
				ArrayList<Path> directoryContents = ((Directory) currentMemeber)
						.getDirectoryContents();
				if (directoryContents.size() != 0) {
					for (int i = 0; i < directoryContents.size(); i++) {
						QueueMembers<E> content = new QueueMembers(qm.steps + 1,
								directoryContents.get(i));
						stack.add(content);
						// Here
					}
				}
			}
			result = qm;
		}
		return result.value;
	}

}
