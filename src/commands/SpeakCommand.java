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

package commands;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
//import driver.CommandManager;
import driver.ErrorHandler;
import interpreters.InputInterpreter;
import io.StdIOStream;
import verification.StringVerifier;

/*
 * NOTE: Some starter code for setting up the synthesizer was utilized from:
 * https://www.geeksforgeeks.org/converting-text-speech-java/
 */

/**
 * Speak is a command that takes input as a string or list of text and converts
 * the text in the input to speech. If enter is pressed after speak, then the
 * command will take a list of text until the QUIT key word is entered. No
 * quotes are allowed if in the text transcription mode. Otherwise, if enter is
 * not pressed after speak, then only a single string will be allowed.
 * 
 * @author Rakshit Patel
 */
public class SpeakCommand extends Command {

	/**
	 * Converts the inputText to audio
	 * 
	 * @param inputText The text that will be converted to audio
	 */
	private void convertTextToSpeach(String inputText) {
		try {
			// Set property as Kevin Dictionary
			System.setProperty("freetts.voices",
					"com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

			// Register Engine
			Central.registerEngineCentral(
					"com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
			// Create a Synthesizer
			Synthesizer synthesizer = Central
					.createSynthesizer(new SynthesizerModeDesc(Locale.US));
			// Allocate synthesizer
			synthesizer.allocate();
			// Resume Synthesizer
			synthesizer.resume();

			// Speaks the given text until the queue is empty.
			synthesizer.speakPlainText(inputText, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * If arguments string is given then converts that string to audio Otherwise
	 * takes in input to produce a string that will be converted to audio
	 * 
	 * @param args An array of arguments inputed for the command
	 */
	@Override
	public String executeCommandOperations(String[] inputWords) {
		String[] flag = InputInterpreter.parseOnlyFlags(inputWords);
		String[] arguments = InputInterpreter.parseOnlyArguments(inputWords);

		if (flag.length > 0) {
			ErrorHandler.processError(7);
			return "Error";
		}

		String speakString = "";
		// If user presses enter then take multiple string inputs
		if (inputWords.length == 0) {
			executeMultipleStringInputs(speakString);
		}
		// if user enters string directly beside the speak command,
		// then only take that input
		else {
			speakString = executeSingleInputArgument(arguments);
		}
		return "";
	}

	private void executeMultipleStringInputs(String speakString) {
		String input = StdIOStream.getInputContent();
		// take multiple inputs until QUIT keyword is entered
		while (true) {
			String[] text = input.split(" ");
			for (int i = 0; i < text.length; i++) {
				speakString += text[i] + " ";
				if (speakString.contains("\"")) {
					ErrorHandler.processError("Invalid Input. Quotations '\"' "
							+ "are not valid when transcribing multiple lines.");
					return;
				}
				if (text[i].equals("QUIT") && i == text.length - 1) {
					convertTextToSpeach(
							speakString.substring(0, speakString.length() - 5));
					return;
				}
			}
			input = StdIOStream.getInputContent();
		}
	}

	private String executeSingleInputArgument(String[] arguments) {
		// check if arguments are valid
		String argumentsToString = "";
		for (int i = 0; i < arguments.length; i++) {
			argumentsToString += arguments[i];

		}
		if (StringVerifier.isValidString(argumentsToString) == false) {
			ErrorHandler.processError(9);
			return null;
		}
		for (int i = 0; i < arguments.length; i++) {
			convertTextToSpeach(arguments[i]);
		}
		return argumentsToString;
	}

	/**
	 * @return String that contains the manual for speak command
	 */
	@Override
	public String getManual() {
		// return the instructions on how to use the command
		String manual = "NAME\n" + "\tspeak - convert text to audio\n\n"
				+ "SYNOPSIS\n" + "\tspeak [STRING]\n\n" + "DESCRIPTION\n"
				+ "\tConvert the entered string into audible speech\n\n"
				+ "\tIf the speak command is followed by enter, then\n\n"
				+ "\tmultiple inputs can be taken until the keyword QUIT is\n\n"
				+ "\tinputted to mark the end of transcription.\n";

		return manual;
	}

	/*
	 * public static void main(String[] args) { CommandManager cm = new
	 * CommandManager();
	 * 
	 * cm.processCommand("speak \"Hello, testing testing 1,2,3...\""); //error
	 * cm.processCommand("speak \"Hel\"lo, testing testing 1,2,3...\""); //say 
	 * hey
	 * cm.processCommand("speak\nhey QUIT"); //error
	 * cm.processCommand("speak\nh"ey QUIT");
	 * 
	 * }
	 */

}
