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

import org.junit.Test;

import verification.StringVerifier;

public class StringVerifierTest {

	@Test
	public void testIsValidStringWithEmptyString() {
		assertEquals(false, StringVerifier.isValidString(""));
	}

	@Test
	public void testIsValidStringWithOneChar() {
		assertEquals(true, StringVerifier.isValidString("\"a\""));
	}

	@Test
	public void testIsValidStringWithBetweenQuote() {
		assertEquals(false, StringVerifier.isValidString("\"abc\"e\""));
	}

	@Test
	public void testIsValidStringWithOnlyQuote() {
		assertEquals(false, StringVerifier.isValidString("\""));
	}

	@Test
	public void testIsValidStringWithSingleQuotes() {
		assertEquals(false, StringVerifier.isValidString("'fail'"));
	}

	@Test
	public void testIsValidStringWithProperSentence() {
		assertEquals(true, StringVerifier.isValidString("\"This is good.\""));
	}

	@Test
	public void testIsValidStringWithOneSurrounding() {
		assertEquals(false, StringVerifier.isValidString("missed one\""));
	}
}
