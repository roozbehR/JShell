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

package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import driver.ErrorHandler;

/**
 * This class is responsible for retrieving information from web URLs.
 * 
 * @author Farhan Chowdhury
 *
 */
public class WebFileIOStream implements WebStreamI {

	/**
	 * This method gets the content of a web file.
	 * 
	 * @param link the URL of the web file/*
	 * @return String the content in the web file
	 */
	public String getInputContent(String link) {
		String webFileContent = null;
		URL url;

		try {
			// set up connection
			url = new URL(link);
			URLConnection connection = url.openConnection();

			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
					new InputStreamReader(connection.getInputStream()));

			// get content
			String inputLine = br.readLine();
			while (inputLine != null) {
				webFileContent += inputLine + "\n";
				inputLine = br.readLine();
			}
			br.close();
		} catch (MalformedURLException e) {
			ErrorHandler.processError(13, link);
		} catch (IOException e) {
			ErrorHandler.processError(2, link);
		}

		return webFileContent;
	}

}
