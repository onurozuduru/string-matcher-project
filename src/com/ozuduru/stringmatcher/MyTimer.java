/*
*File: MyTimer.java
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

// It is a simple class that keeps start time and total time.
public class MyTimer {
	private long startTime, totalTime;

	public MyTimer() {
		startTime = 0;
		totalTime = 0;
	}

	public void reset() {
		startTime = 0;
		totalTime = 0;
	}

	// Set start time.
	public void start() {
		startTime = System.nanoTime();
	}
	
	// Add time difference to totaltime
	public void stop() {
		long endTime = System.nanoTime();
		totalTime += (endTime - startTime);
	}
	
	// It keeps time as nanoseconds but while it is needed to get the total time that function converts miliseconds.
	public double getTotalTimeInMilis() {
		return totalTime / 1000000.0;
	}

}
