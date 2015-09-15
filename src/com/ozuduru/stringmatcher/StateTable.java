/*
*File: StateTable.java
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

import java.util.HashMap;

// I created a new class to store Delta.
// It has arrays of hashmaps.
// I used hashmap because it is more efficient.
public class StateTable<SigmaType> {
	private HashMap<SigmaType, Integer>[] table;

	public StateTable(int rowNumber, int colNumber) {
		table = new HashMap[rowNumber];
		
		// Initialize hashmaps
		for (int i = 0; i < rowNumber; ++i)
			table[i] = new HashMap<SigmaType, Integer>(colNumber);
	}

	public StateTable(int rowNumber, SigmaType[] sigma) {
		this(rowNumber, sigma.length);
	}

	public StateTable(int rowNumber) {
		table = new HashMap[rowNumber];
		for (int i = 0; i < rowNumber; ++i)
			table[i] = new HashMap<SigmaType, Integer>();
	}

	public int get(int row, SigmaType col) {
		Integer val = table[row].get(col);
		return (val == null) ? 0 : val;
	}

	public void set(int row, SigmaType col, int value) {
		table[row].put(col, value);
	}

	@Override
	public String toString() {
		String s = "";
		for (HashMap<SigmaType, Integer> e : table)
			s = s + e.toString() + "\n";
		return s;
	}
}
