/*********************************************************************************
*File: FileReaderGUI.java
*Author: Onur Ozuduru
*   e-mail: onur.ozuduru { at } gmail.com
*   github: github.com/onurozuduru
*   twitter: twitter.com/OnurOzuduru
*
*License: The MIT License (MIT)
*
*   Copyright (c) 2015 Onur Ozuduru
*   Permission is hereby granted, free of charge, to any person obtaining a copy
*   of this software and associated documentation files (the "Software"), to deal
*   in the Software without restriction, including without limitation the rights
*   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
*   copies of the Software, and to permit persons to whom the Software is
*   furnished to do so, subject to the following conditions:
*  
*   The above copyright notice and this permission notice shall be included in all
*   copies or substantial portions of the Software.
*  
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*********************************************************************************/

package com.ozuduru.stringmatcher;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

// This class is needed for choosing files with UI.
public class FileReaderGUI {

	// It reads file and returns lines of the file as arraylist of strings.
	// It takes component parent for JFileChooser, it might be null.
	// And it takes JTextField to show name of the file on it.
	public static ArrayList<String> readFileWithGUI(Component parent, JTextField nameField) {
		ArrayList<String> lines = new ArrayList<>();
		File file = FileReaderGUI.createFileChooser(parent);// Get file
		
		// If file is null return an empty list.
		if (file == null)
			return lines;
		// Show file name to the user.
		nameField.setText(file.getName());
		
		try {
			// Create a new bufferedreader to read text file line by line.
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			
			// Add lines to the list until there is a line.
			while ((line = reader.readLine()) != null)
				lines.add(line);
			
			reader.close();// close reader.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	// It creates a JFileChooser and returns users selection.
	// If user does not choose a file it returns null.
	private static File createFileChooser(Component parent) {
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files (*.txt)", "txt");// Filter the .txt files.
		filechooser.setFileFilter(filter);
		
		int retVal = filechooser.showOpenDialog(parent);
		
		if (retVal == JFileChooser.APPROVE_OPTION)
			return filechooser.getSelectedFile();
		else
			return null;
	}

}
