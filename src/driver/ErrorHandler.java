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

import io.StdIOStream;

/**
 * A class to handle errors and report the appropriate error messages to the
 * user.
 * 
 * @author Farhan Chowdhury
 * @author Shammo Talukder
 */
public class ErrorHandler {

	/**
	 * This method will output an error message caused by an invalid String to
	 * explain the error based on a code.
	 * 
	 * @param errorCode          the code
	 * @param errorInducingValue String that caused the error
	 */
	public static void processError(int errorCode, String errorInducingValue) {
		String valueString = " '" + errorInducingValue + "' ";
		ErrorHandler.executeErrorProcessingOperations(errorCode, valueString);
	}

	/**
	 * This method will output an error message caused by an invalid char to
	 * explain the error based on a code.
	 * 
	 * @param errorCode          the code
	 * @param errorInducingValue char that caused the error
	 */
	public static void processError(int errorCode, char errorInducingValue) {
		ErrorHandler.processError(errorCode, String.valueOf(errorInducingValue));
	}

	/**
	 * This method will output an error message caused by an invalid int to
	 * explain the error based on a code.
	 * 
	 * @param errorCode          the code
	 * @param errorInducingValue int that caused the error
	 */
	public static void processError(int errorCode, int errorInducingValue) {
		String valueString = " " + String.valueOf(errorInducingValue) + " ";
		ErrorHandler.executeErrorProcessingOperations(errorCode, valueString);
	}

	/**
	 * This method will output a specific error message when the error cases do
	 * not cover the error the command encountered.
	 *
	 * @param customErrorMsg the custom error msg to print
	 */
	public static void processError(String customErrorMsg) {
		ErrorHandler.executeErrorProcessingOperations(99, customErrorMsg);
	}

	/**
	 * This method will output an error message to explain the error based on a
	 * code.
	 * 
	 * @param errorCode the code
	 */
	public static void processError(int errorCode) {
		ErrorHandler.executeErrorProcessingOperations(errorCode, "");
	}

	private static void executeFirstHalfErrors(int errorCode,
			String errorInducingValueOrMsg) {
		String msg;
		switch (errorCode) {
		case 1:
			msg = "Could not find the file " + errorInducingValueOrMsg;
			break;
		case 2:
			msg = "Could not find the directory " + errorInducingValueOrMsg;
			break;
		case 3:
			msg = "Could not create the file or directory specified. The name "
					+ errorInducingValueOrMsg + " contains invalid characters.";
			break;
		case 4:
			msg = "The command entered does not accept any arguments.";
			break;
		case 5:
			msg = "The command entered does not accept the number of arguments "
					+ "entered";
		default:
			msg = errorInducingValueOrMsg;
		}
		msg = "Error: " + msg;
		StdIOStream.outputContentWithNewLine(msg);
	}

	private static void executeSecondHalfErrors(int errorCode,
			String errorInducingValueOrMsg) {
		String msg;
		switch (errorCode) {
		case 6:
			msg = "The command entered does not accept " + errorInducingValueOrMsg
					+ " as an argument.";
			break;
		case 7:
			msg = "The command entered does not accept the number of flags entered.";
			break;
		case 8:
			msg = "The command entered does not accept " + errorInducingValueOrMsg
					+ " as a flag";
			break;
		case 9:
			msg = "The argument provided must be a valid string surrounded by double"
					+ " quotes";
			break;
		default:
			msg = errorInducingValueOrMsg;
		}
		msg = "Error: " + msg;
		StdIOStream.outputContentWithNewLine(msg);
	}

	private static void executeErrorProcessingOperations(int errorCode,
			String errorInducingValueOrMsg) {
		if (errorCode <= 5)
			ErrorHandler.executeFirstHalfErrors(errorCode, errorInducingValueOrMsg);

		else
			ErrorHandler.executeSecondHalfErrors(errorCode, errorInducingValueOrMsg);
	}

	/*
	 * public static void main(String[] args) { try { Integer.parseInt("0"); }
	 * catch (NumberFormatException e) { System.out.println("false"); }
	 * System.out.println("true"); }
	 */
}
