//Connor Durkin
//CS-570-WA - Fall 2020
//September 23, 2020
//Homework 1

package homework1;

//IMPORT JAVA UTIL

	import java.util.*;

// CLASS DECLARATION

	public class BinaryNumber {

// PARAMETER DECLARATIONS
		
	private int[] data;
	private boolean overflow = false; 
	
// CONSTRUCTOR - USING INTEGER

	public BinaryNumber(int length) {
		int[] data = new int[length];
		for (int i = 0; i < length; i++)
			data[i] = 0;
		this.data = data; 
	}

// FULLY PARAMETERIZED CONSTRUCTOR - USING STRING 

	public BinaryNumber(String str) throws Exception {
		
		//Check to see string is only 1 and 0, throw exception otherwise

		if (!(isValid(str))) {
			throw new Exception("To create a binary number, the inputted string may only include the following digits: 1, 0.");
		} else {
		
		//Create array based on string length
		
		int[] data = new int[str.length()];
		
		//Place individual digits from string into the array
		
		for (int i=0; i < str.length(); i++) {
			
			if (str.charAt(i)=='0') {
				data[i] = 0;
			}
			else data[i] = 1;
		}
		this.data = data;
	
		}
		
	}
	
// GETTER METHODS 
	
	public int getLength() {
		return data.length;
	}
	
	public int getDigit(int index) throws Exception {
		if (index > (data.length - 1) || index < 0) {
			throw new Exception ("The index provided falls outsides the bounds of this binary number.");
		}
		else {
			return data[index];
		}
	}

// SHIFT FUNCTION

	public void shiftR(int amount) throws Exception {
		
		if (amount <= 0) {
			throw new Exception ("The provided amount is less than or equal to zero. Please provide a positive integer value to shift the binary number.");
		} else {
		
		//Create temporary array and expand size of data array
		
		int[] temp = data; 
		data = new int[amount + temp.length];
		
		//Fill new digits with 0
		for (int i = 0; i < amount; i++) {
			data[i] = 0;
		}
		
		//Fill array with old array 
		for (int i = 0; i < temp.length; i++) {
			data[i+amount] = temp[i];
		}
		
		}
		
	}

// ADD FUNCTION

	void add(BinaryNumber aBinaryNumber) throws Exception {
		
	//Check to see lengths coincide 
		
		if (data.length != aBinaryNumber.getLength()) {
			throw new Exception("This program can only add binary numbers that coincide, meaning those of the same length.");
		}
			
	//Initialize array to be returned 
		
		int[] temp = new int[data.length]; 
		
	//Initialize carry over value
		
		int carry = 0; 
		
	//Create loop that counts down index, starting with rightmost value 
		
		int i = data.length - 1; 
		
		while (i >=0) {
			
			//initialize sum 
			
			int sum = carry; 
			
			//determine sum based on the considered digits and the carry over
			
			sum += data[i]; 
			sum += aBinaryNumber.data[i];
			
			//place digit in new array
			//use remainder function to determine the binary result
			
			temp[i] = sum % 2; 
			
			//fix carry - any 0.5 values are rounded down to zero
			
			carry = sum / 2; 
			
			//decrease counts in loop 
			
			i--;
			
		}
		
		//Add carry to result if carry != 0, leave as if not
		
		if (carry != 0) {
			overflow = true;
		} else {data = temp;}

	}	

// TO STRING 
	
	public String toString() {
		if (overflow == false) {
		StringBuilder returnValue = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			returnValue.append(data[i]);
		}
		return returnValue.toString();
		}
		else {
			String returnValue = "Overflow";
			return returnValue;
		}
	}	

// TO DECIMAL 

	public int toDecimal() {
		
		double decimal = 0;
		
		for (int i = 0; i < data.length; i++) {
			decimal += (data[i]*Math.pow(2, i));		
		
		}
		
		int finaldecimal = (int)decimal;
		
		return finaldecimal;
	}	
	
// CLEAR OVERFLOW 

	public void clearOverflow() {
		overflow = false;
	}
	
// isValid Function

	public static boolean isValid(String str) {

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '1' && str.charAt(i) != '0') 
				return false;
		}
		return true;
	}
	
}