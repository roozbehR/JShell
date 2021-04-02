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

package commands;

import io.WebFileIOStream;
import io.WebStreamI;
import driver.ErrorHandler;
import interpreters.FlagInterpreter;

/**
 * This class is responsible for accessing and retrieving data from web files.
 * 
 * @author Farhan Chowdhury
 *
 */
public class CurlCommand extends Command {

	private WebStreamI webStream;

	/**
	 * This method creates an instance of the class and sets up the stream used
	 * for IO related to the web file.
	 */
	public CurlCommand() {
		this.webStream = new WebFileIOStream();
	}

	@Override
	public String executeCommandOperations(String[] inputWords) {
		String flag = "";
		String url;

		if (inputWords.length == 0) {
			ErrorHandler.processError("Missing link to web file.");
			return null;
		}
		url = inputWords[0];
		if (inputWords.length > 1) {
			if (!FlagInterpreter.checkIfRedirectionFlag(inputWords[1])) {
				ErrorHandler.processError("Too many arguments were entered for curl.");
				return null;
			}
		}

		if (!(FlagInterpreter.checkIfFlag(flag) || flag == "")) {
			ErrorHandler.processError(5, flag);
			return null;
		}

		String webFileContent = webStream.getInputContent(url);
		if (webFileContent == null) {
			return null;
		}
		return webFileContent;
	}

	@Override
	public String getManual() {
		return "NAME\n" + "\tcurl - output the contents of a web file\n\n"
				+ "SYNOPSIS\n" + "\tcurl URL\n\n" + "DESCRIPTION\n"
				+ "\tPrint out contents of web file at URL.\n\n"
				+ "\tWith no arguments, print out all recent commands.\n";
	}

//  public static void main(String[] args) {
//    CurlCommand cc = new CurlCommand();
//    String[] test1 = {
//        "http://www.cs.utoronto.ca/~trebla/CSCB09-2020-Summer/l08/
//	exercise.html"};
//    System.out.println(cc.executeCommandOperations(test1));
//    String[] test2 = {"invalid link"};
//    System.out.println(cc.executeCommandOperations(test2));
//  }

}
