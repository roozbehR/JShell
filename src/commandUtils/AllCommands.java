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

package commandUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import commands.*;

/**
 * A class containing a mapping of all command names to an instance of the
 * command. Also contains list of output producing commands.
 * 
 * @author Farhan Chowdhury
 * @author Shammo Talukder
 */
public class AllCommands {

	private static ArrayList<String> outputProducingCommands = 
			new ArrayList<String>() {
		{
			add("history");
			add("pwd");
			add("ls");
			add("cat");
			add("echo");
			add("man");
			add("curl");
			add("find");
			add("tree");
		}
	};

	/**
	 * This method gets all a Hashtable that maps the String input that the user
	 * would use to call a command to an instance of said command object.
	 * 
	 * @return Hashtable<String, Command> the hashtable that maps String input to
	 *         command instances
	 */
	public static Hashtable<String, Command> getNamesToCommandHashtable() {
		Hashtable<String, Command> allCommandsHashtable = new Hashtable<String, 
				Command>();
		allCommandsHashtable.put("history", new HistoryCommand());
		allCommandsHashtable.put("speak", new SpeakCommand());
		allCommandsHashtable.put("pwd", new PwdCommand());
		allCommandsHashtable.put("mkdir", new MkdirCommand());
		allCommandsHashtable.put("cd", new CdCommand());
		allCommandsHashtable.put("ls", new LsCommand());
		allCommandsHashtable.put("cat", new CatCommand());
		allCommandsHashtable.put("pushd", new PushdCommand());
		allCommandsHashtable.put("popd", new PopdCommand());
		allCommandsHashtable.put("echo", new EchoCommand());
		allCommandsHashtable.put("man", new ManCommand());
		allCommandsHashtable.put("exit", new ExitCommand());
		allCommandsHashtable.put("curl", new CurlCommand());
		allCommandsHashtable.put("save", new SaveCommand());
		allCommandsHashtable.put("load", new LoadCommand());
		allCommandsHashtable.put("find", new FindCommand());
		allCommandsHashtable.put("mv", new MvCommand());
		allCommandsHashtable.put("cp", new CpCommand());
		allCommandsHashtable.put("rm", new RmCommand());
		allCommandsHashtable.put("tree", new TreeCommand());
		return allCommandsHashtable;
	}

	/**
	 * Checks whether the given command named commandName produces output
	 * 
	 * @param the name of the command
	 * @return true if the command produces output, false otherwise
	 */
	public static boolean producesOutput(String commandName) {
		return outputProducingCommands.contains(commandName);
	}

}
