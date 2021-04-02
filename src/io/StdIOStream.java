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

package io;

import java.util.Scanner;

/**
 * A class to handle input from and output to the console
 * 
 * @author Farhan Chowdhury
 */
public class StdIOStream {

	// JUnit tests are not applicable for this class as it
	// simply outputs to console, and takes input.

	/**
	 * This method prints the given String to stdout
	 * 
	 * @param outputText A String to output
	 */
	public static void outputContent(String outputText) {
		System.out.print(outputText);
	}

	/**
	 * This method prints the given String to stdout and adds a newline character
	 * at the end.
	 * 
	 * @param outputText A String to output
	 */
	public static void outputContentWithNewLine(String outputText) {
		StdIOStream.outputContent(outputText + "\n");
	}

	/**
	 * This method reads one line of input from stdin and returns it as a String
	 * 
	 * @return String the input received
	 */
	public static String getInputContent() {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		return line;
	}

}
