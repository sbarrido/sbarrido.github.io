## Introduction to Formatting and Design
Standard exercise to recognize character sets and practice the use of formatting
```
/**
	@Author Sam Barrido/sbarrido
	Lasted Edited: Jan. 18, 2018
	Lab2Integer
*/

/** =================== Design Process ===============
	1. Program must have single argument of type char
	2. Create variable to store character set of given char
	3. Format string to include both the argument and character set
	4. Print formatted string to console
*/

public class Lab2Integer
{
	// Formats String to include both the given char and the corresponding 
	// character set value
	public static String characterSet(char args)
	{
		int charValue = ( (int) args ); // Casts Int data type from the given char
		String sentence = String.format("The character %c has the value %d%n", args, charValue); // formatting String	
		return sentence; // method returns formatted String
	}
	// main method uses characterSet method 
	public static void main(String[] args)
	{
		// prints capital character
		String bigAValue = characterSet('A'); 
		System.out.println(bigAValue);
		String bigBValue = characterSet('B');
		System.out.println(bigBValue);
		String bigCValue = characterSet('C');
		System.out.println(bigCValue);
		
		// prints lower case character
		String aValue = characterSet('a');
		System.out.println(aValue);
		String bValue = characterSet('b');
		System.out.println(bValue);
		String cValue = characterSet('c');
		System.out.println(cValue);
		
		// prints number character
		String zeroValue = characterSet('0');
		System.out.println(zeroValue);
		String oneValue = characterSet('1');
		System.out.println(oneValue);
		String twoValue = characterSet('2');
		System.out.println(twoValue);
		
		// prints special character
		String dollahValue = characterSet('$');
		System.out.println(dollahValue);
		String starValue = characterSet('*');
		System.out.println(starValue);
		String plusValue = characterSet('+');
		System.out.println(plusValue);
		String fSlashValue = characterSet('/');
		System.out.println(fSlashValue);
		
		// prints blank character
		String blankValue = characterSet(' ');
		System.out.println(blankValue);
	} // end main method
} // end class Lab2Integer
```
