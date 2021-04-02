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

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import commandUtils.AllCommands;
import commands.LsCommand;
import commands.ManCommand;

public class TestManCommand {

	ManCommand manCommand;
	AllCommands allCommand;

	/*
	 * sets a new environment/fileSystem for manCommand before every test
	 */
	@Before
	public void setUp() throws Exception {
		manCommand = new ManCommand();
		allCommand = new AllCommands();
	}

	/*
	 * check if man returns null when we enter argument or flag
	 */
	@Test
	public void testInvalidInput() {
		String[] input = { "-R", ">>", "tree" };
		String actualOutput = manCommand.executeCommandOperations(input);
		assertEquals(null, actualOutput);
	}

	/*
	 * check if man returns correct manual when we enter correct argument
	 */
	@Test
	public void testValidInput() {
		String[] input = { "tree" };
		String actualOutput = manCommand.executeCommandOperations(input);
		String manual = "NAME\n"
				+ "\ttree - shows file system in form of a tree\n\n" + "SYNOPSIS\n"
				+ "\ttree Operand\n\n" + "DESCRIPTION\n"
				+ "\tThe Tree Command start from the root directory \n"
				+ "\tdisplay the entire file system as a tree. \n"
				+ "\tFor every level of the tree, there is an indent\n"
				+ "\tby a space character.\n\n" + "\tExample: " + "\n\t\troot\n"
				+ "\t\t docement\n" + "\t\t  some_file.txt\n" + "\t\t Download\n";
		assertEquals(manual, actualOutput);
	}

	@Test
	public void testValidInput2() {
		String[] input = { "cat" };
		String actualOutput = manCommand.executeCommandOperations(input);
		String manual = "NAME\n" + "\tcat - concatenate and print files\n\n"
				+ "SYNOPSIS" + "\n\tcat File1 [FILE2 ...]\n\n" + "DESCRIPTION\n"
				+ "\tPrints the contents of the specified files with three\n"
				+ "\tline breaks separating the contents of each file.\n";
		assertEquals(manual, actualOutput);
	}

	@Test
	public void testValidInput3() {
		String[] input = { "cd" };
		String actualOutput = manCommand.executeCommandOperations(input);
		String manual = "NAME\n" + "\tcd - change working directory\n\n"
				+ "SYNOPSIS\n" + "\tcd DIRECTORY\n\n" + "DESCRIPTION\n"
				+ "\tChange current working directory to specified DIRECTORY." + "\n";
		assertEquals(manual, actualOutput);
	}

	@Test
	public void testValidInput4() {
		String[] input = { "man" };
		String actualOutput = manCommand.executeCommandOperations(input);
		String manual = "NAME\n"
				+ "\tman - format and display the on-line manual pages\n\n"
				+ "\tSYNOPSIS\n" + "\tman Operand\n\n" + "DESCRIPTION\n"
				+ "\tman displays the manual pages of an existing command.\n"
				+ "\tman is followed by a mandatory single opreand.\n"
				+ "\tIf the operand is an existing command name, the manual specific\n"
				+ "\tto that command is displayed, otherwise an appropriate error\n"
				+ "\tmessage is displayed.\n";
		assertEquals(manual, actualOutput);
	}

	@Test
	public void testValidInput5() {
		String[] input = { "find" };
		String actualOutput = manCommand.executeCommandOperations(input);
		String manual = "NAME\n" + "\tfind - finds all either directories or files"
				+ "that match the given  name by the user\n\n" + "SYNOPSIS\n"
				+ "\tfind path ... -type [f|d] -name expression\n\n" + "DESCRIPTION\n"
				+ "\tfind the node named 'expression' in the directories\n"
				+ "\tgiven by path. There can be multiple paths given.\n "
				+ "\tIf type is 'f' then find a file with name expression"
				+ ",\n\tand if type is 'd' then find a directory with name "
				+ "expression\n\n";
		assertEquals(manual, actualOutput);
	}

}
