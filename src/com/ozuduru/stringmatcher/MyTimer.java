/*
*File: MyTimer.java
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
