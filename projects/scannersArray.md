## Scanner and Array 
This is my attempt to work ahead of the class schedule. Currently **Incomplete** as I 
explore various methods to solve each problem. Potential improvements should include java API
documentation within the source code. 
```
/**
 * @author Sam Barrido/sbarrido
 * Last Edited: Jan. 19, 2018
 * Lab 3
 */
import java.util.Objects;
import java.util.Scanner;
public class Lab3Exercises 
{
	public static Boolean palindrome(String myPString)
	{
		char[] letters = myPString.toCharArray(); // changes myPString to a char array
		int letterCounter = letters.length; // stores length of char array
		char[] reversedChars = new char[letterCounter]; //creates an empty char array with same length as myPString
		int current = 0; // recursive counting beginning at position[0]
		
		// increments current position until less than original myPString
		// assigns the character of opposite position in myPString to
		// current position in reversedChars character array
		while(current < letterCounter)
		{
			reversedChars[current] =
					 myPString.charAt(letterCounter - 1 - current);
			current++;			
		}
		
		// creates a new String from the reversedChars character array
		String reversedPString = new String(reversedChars);
		
		// compares myPString and reversedPStrying
		// true if same, false if different
		if (Objects.equals(myPString, reversedPString))
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	public static String numbers()
	{
		Scanner input = new Scanner(System.in);
		String 
	}
	
	public static String grade()
	{
		//TODO
	}
	
	public static String intToBinary(int n)
	{
		//TODO
	}
	
	public static void main(String[] args)
	{
		boolean fail = palindrome("apple");
		boolean pass = palindrome("racecar");
		System.out.printf("apple palindrome is %b%n", fail);
		System.out.println("Expected: false");
		System.out.printf("racecar palindrome is %b%n", pass);
		System.out.println("Expected: true");
		System.out.println("======End palindrome test=======");
		
		Scanner inputScan = new Scanner(System.in);
		System.out.println("Enter a word: ");
		String input = inputScan.next();
		boolean value = palindrome(input);
		System.out.printf("This is %b%n", value);
		System.out.println("=======End Palindrome method=====");
		//================End Palindrome Method==============
		
	}
}
```
