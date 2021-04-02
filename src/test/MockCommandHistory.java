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
package test;

import java.util.Stack;

import system.CommandHistoryI;

public class MockCommandHistory
		implements CommandHistoryI, java.io.Serializable {

	private static MockCommandHistory mch = null;
	private Stack<String> stack;

	private MockCommandHistory() {
		this.stack = new Stack<String>();
		this.stack.push("invalidCommand argument");
		this.stack.push("invalidCommand");
		this.stack.push("echo \"hello world\"");
	}

	/**
	 * This method will always return the same instance of CommandHistory,
	 * allowing it to act as a singleton.
	 * 
	 * @return CommandHistory the reference to the instance of CommandHistory
	 */

	public static MockCommandHistory getCommandHistoryInstance() {
		if (mch == null) {
			mch = new MockCommandHistory();
		}
		return mch;
	}

	/**
	 * This method gets the input command at given index of the stack or empty
	 * string if index is out of bounds. It returns an empty string if the index
	 * specified is invalid.
	 * 
	 * @param stackIndex the index at which the stack resides.
	 * @return String the input command (empty String if index is invalid)
	 */
	public String getStackCommand(int stackIndex) {
		if (stackIndex < 0 || stackIndex > this.getStackSize()) {
			return "";
		}
		return this.stack.get(stackIndex);
	}

	/**
	 * This method returns an int representing the number of command inputs in the
	 * stack.
	 * 
	 * @return the stack size
	 */
	public int getStackSize() {
		return this.stack.size();
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> newStack) {
		this.stack = newStack;
	}

	@Override
	public void addCommandToStack(String command) {
		this.stack.add(command);
	}

}
