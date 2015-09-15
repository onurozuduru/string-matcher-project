/*
*File: Main.java
*Author: Onur Ozuduru
*   e-mail: onur.ozuduru { at } gmail.com
*   github: github.com/onurozuduru
*   twitter: twitter.com/OnurOzuduru
*
*License: This work is licensed under the
*   Creative Commons Attribution-ShareAlike 4.0 International License.
*   To view a copy of this license,
*   visit http://creativecommons.org/licenses/by-sa/4.0/.
*/

package com.ozuduru.stringmatcher;

import java.awt.EventQueue;

public class Main {

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringMatcherGUI window = new StringMatcherGUI();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
