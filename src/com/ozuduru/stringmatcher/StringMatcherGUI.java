/*
*File: StringMatcherGUI.java
*Author: Onur Ozuduru
*   e-mail: onur.ozuduru { at } gmail.com
*   github: github.com/onurozuduru
*   twitter: twitter.com/OnurOzuduru
*
*License: MIT
*   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
*   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
*   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
*   SOFTWARE.
*/

package com.ozuduru.stringmatcher;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;

// It creates user interface.
// I created this class with WindowBuilder (https://eclipse.org/windowbuilder/) 
// I added Listeners, where are placed at end of this file, to generated code.
// To see how search is performed, go to SearchButtonListener class which is end of this file.
public class StringMatcherGUI {

	private JFrame frame;
	private JTextField textPath;
	private JTextField textFileName;
	private JTextField textNaiveTime, textFiniteTime;
	private DefaultListModel<String> listModelNaive, listModelFinite;// It will keep results of the functions on these list model.
	private JCheckBox chckbxLowerCase, chckbxUpperCase, chckbxNums, chckbxSpace, chckbxNotations;

	private ArrayList<String> text;
	private StringMatcherEngine matcher;


	/**
	 * Create the application.
	 */
	public StringMatcherGUI() {
		matcher = new StringMatcherEngine();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Initialize the frame.
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 680, 425);
		getFrame().setResizable(false);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelSearch = new JPanel();// It includes a label, search bar and search button.

		JPanel panelFile = new JPanel();// It includes a label, a textfield to show file name, open file button.

		JPanel panelNaive = new JPanel();// It will show results of naive matcher
		panelNaive.setBorder(new TitledBorder(null, "Naive String Matcher Results",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelFinite = new JPanel();// It will show results of finite automata matcher
		panelFinite.setBorder(new TitledBorder(null, "Finite Automata Matcher Results",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFinite.setLayout(new BorderLayout(0, 0));

		JPanel panelSigma = new JPanel();// It includes checkboxes which shows character list to user to pick one or more from there.
		panelSigma.setBorder(new TitledBorder(null, "Set Sigma",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getFrame().getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelSearch, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
								.addComponent(panelFile, GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panelNaive, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panelFinite, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panelSigma, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
								.addContainerGap())
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(panelSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelFile, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(panelNaive, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelFinite, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE))
										.addComponent(panelSigma, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(32, Short.MAX_VALUE))
		);
		panelSigma.setLayout(new GridLayout(5, 1, 0, 0));

		chckbxLowerCase = new JCheckBox("[a..z]");
		chckbxLowerCase.setSelected(true);
		panelSigma.add(chckbxLowerCase);

		chckbxUpperCase = new JCheckBox("[A..Z]");
		panelSigma.add(chckbxUpperCase);

		chckbxNums = new JCheckBox("[0..9]");
		panelSigma.add(chckbxNums);

		chckbxSpace = new JCheckBox("Space");
		panelSigma.add(chckbxSpace);

		chckbxNotations = new JCheckBox("Notations");
		panelSigma.add(chckbxNotations);

		listModelFinite = new DefaultListModel<>();
		JList<String> listFinite = new JList<>(listModelFinite);
		listFinite.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelFinite.add(new JScrollPane(listFinite), BorderLayout.CENTER);

		JPanel panelFiniteRes = new JPanel();
		panelFinite.add(panelFiniteRes, BorderLayout.SOUTH);

		JLabel lblRunTimeFinite = new JLabel("Run Time:");
		panelFiniteRes.add(lblRunTimeFinite);

		textFiniteTime = new JTextField();
		textFiniteTime.setEditable(false);
		panelFiniteRes.add(textFiniteTime);
		textFiniteTime.setColumns(12);

		JLabel lblFiniteMs = new JLabel("ms.");
		panelFiniteRes.add(lblFiniteMs);
		panelNaive.setLayout(new BorderLayout(0, 0));

		listModelNaive = new DefaultListModel<>();
		JList<String> listNaive = new JList<>(listModelNaive);
		listNaive.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelNaive.add(new JScrollPane(listNaive), BorderLayout.CENTER);

		JPanel panelNaiveRes = new JPanel();
		panelNaive.add(panelNaiveRes, BorderLayout.SOUTH);

		JLabel lblRunTimeNaive = new JLabel("Run Time:");
		panelNaiveRes.add(lblRunTimeNaive);

		textNaiveTime = new JTextField();
		textNaiveTime.setEditable(false);
		panelNaiveRes.add(textNaiveTime);
		textNaiveTime.setColumns(12);

		JLabel lblNaiveMs = new JLabel("ms.");
		panelNaiveRes.add(lblNaiveMs);
		panelFile.setLayout(new BorderLayout(0, 0));

		JLabel lblFileName = new JLabel("File Name:");
		panelFile.add(lblFileName, BorderLayout.WEST);

		textFileName = new JTextField();
		textFileName.setEditable(false);
		panelFile.add(textFileName, BorderLayout.CENTER);
		textFileName.setColumns(10);

		JButton btnOpenFile = new JButton("Open File");
		btnOpenFile.addActionListener(new FileButtonListener());
		panelFile.add(btnOpenFile, BorderLayout.EAST);
		panelSearch.setLayout(new BorderLayout(0, 0));

		JLabel lblPath = new JLabel("Path:");
		panelSearch.add(lblPath, BorderLayout.WEST);
		lblPath.setFont(new Font("Dialog", Font.BOLD, 14));

		textPath = new JTextField();
		panelSearch.add(textPath, BorderLayout.CENTER);
		textPath.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new SearchButtonListener());
		panelSearch.add(btnSearch, BorderLayout.EAST);
		getFrame().getContentPane().setLayout(groupLayout);
	}

	private void resetResults() {
		listModelNaive.clear();
		listModelFinite.clear();
		textNaiveTime.setText("");
		textFiniteTime.setText("");
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Listeners.
	 */
	private class FileButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<String> lines = FileReaderGUI.readFileWithGUI(getFrame(), textFileName);
			if (!lines.isEmpty())
				text = lines; 
			resetResults();
		}
	}

	// Actually search performs on that listener.
	private class SearchButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//Clear lists.
			resetResults();
			if (text == null)
				return;
			if (textPath.getText().isEmpty())
				return;
			//Create new timer.
			MyTimer timer = new MyTimer();
			int textSize = text.size();

			Character[] sigma = generateSigma();
			String P = textPath.getText();
			StateTable<Character> delta = matcher.generateTransitionTable(P, sigma);
			
			//Naive String Matcher
			for (int lineInd = 0; lineInd < textSize; ++lineInd) {
				timer.start();
				ArrayList<Integer> res = matcher.naiveStringMatcher(text.get(lineInd), P);
				timer.stop();
				if (!res.isEmpty())
					listModelNaive.addElement("Line "
							+ String.valueOf(lineInd + 1) + ": "
							+ String.valueOf(res.size() + " occurrence(s)"));
			}
			textNaiveTime.setText(String.valueOf(timer.getTotalTimeInMilis()));

			timer.reset();// Reset Timer since we will use same timer.

			// Finite Automata Matcher
			for (int lineInd = 0; lineInd < textSize; ++lineInd) {
				timer.start();
				ArrayList<Integer> res = matcher.finiteAutomatonMatcher(
						text.get(lineInd), delta, P.length());
				timer.stop();
				if (!res.isEmpty())
					listModelFinite.addElement("Line "
							+ String.valueOf(lineInd + 1) + ": "
							+ String.valueOf(res.size() + " occurrence(s)"));
			}
			textFiniteTime.setText(String.valueOf(timer.getTotalTimeInMilis()));
		}//END OF actionPerformed.
		
		// It takes start and end characters and a list then adds all characters between to the list. (Thanks to ASCII table) 
		private void addCharsToList(char startChar, char endChar, ArrayList<Character> list) {
			if (list == null)
				return;
			for (char c = startChar; c <= endChar; c++)
				list.add(new Character(c));
		}

		// It checks values of checkboxes and it creates a Character array depends on them.
		private Character[] generateSigma() {
			ArrayList<Character> chars = new ArrayList<>();

			if (chckbxLowerCase.isSelected())
				addCharsToList('a', 'z', chars);
			if (chckbxUpperCase.isSelected())
				addCharsToList('A', 'Z', chars);
			if (chckbxSpace.isSelected())
				chars.add(new Character(' '));
			if (chckbxNums.isSelected())
				addCharsToList('0', '9', chars);
			if (chckbxNotations.isSelected()) {
				addCharsToList('!', '/', chars);
				addCharsToList(':', '@', chars);
				addCharsToList('[', '`', chars);
				addCharsToList('{', '~', chars);
			}

			return chars.toArray(new Character[chars.size()]);
		}
	}
}
