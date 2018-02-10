## Roman Numeral Conversion (ArrayLists and Conditional Statements)
  This program takes an integer and returns a String in the form of Roman Numerals. I take advantage of
  ArrayLists ability to "grow" in size, so theortically I could expand this program to take any integer
  value. Because each digit-position(ones, tens, hundreds, thousands) has its own Roman Numeral conversion,
  I have conditional statements for each. If I expand this program to take ANY integer, then I need to
  take into account the various roman numeral conversion at higher digit-positions. 
```
/**
 * 
 * @author Sam Barrido/sbarrido
 * Last Edited: Feb. 2, 2018
 * Lab 4
 *
 */
import java.util.ArrayList;

public class RomanConversion 
{
	// method used to convert given number, n,
	// into roman number equivalent 
	public static String romanNum(int n)
	{
		// variables
		ArrayList<Integer> storeRoman = new ArrayList<Integer>();
		ArrayList<Integer> storeTemp = new ArrayList<Integer>();
		int input = n;
		int i;
		int j;
		
		// assigns user input to a string array
		// separates the input, value between 0 - 3999, into individual strings
		String[] splitarray = String.valueOf(input).split("");
		
		// converts the individual string type "numbers"
		// into Integer type values
		for(String s: splitarray) {
			storeRoman.add(Integer.parseInt(s));
		}
		
		// reverses the order of the original input
		// stores the reversed order into a temporary ArrayList
		int romanLength = storeRoman.size();
		for(i = 1; i <= romanLength; i++)
		{
			storeTemp.add(storeRoman.get(romanLength - i));
		}
		
		// loops through reversed order of user inputs
		// checks each element individually
		// i.e. [ 0, 0, 9, 3]
		// each element undergoes conditional checks to convert to roman number String
		String convertRoman = "";
		for(i = 0; i < storeTemp.size(); i++)
		{
			// makes each element accessible 
			int storedVal = storeTemp.get(i);
			
			// checks if element is a "ones" value
			// NOTE: reversed user input guarantees this is "ones" values
			if (i == 0)
			{
				// values less than 4 all share "I" in common
				if(storedVal < 4)
				{
					// loops as often as the storedVal
					// appends "I" to convertRoman String
					for(j = 0; j < storedVal ; j++)
					{
						convertRoman += "I";
					}
				}
				// value == to 4 is unique, "IV"
				// NOTE: reversed order because processing in reverse
				else if (storedVal == 4)
				{
					convertRoman += "VI";
				}
				// values > 4 and values < 9 all share "V"
				else if ((storedVal > 4) && (storedVal < 9))
				{
					// conditional for values > 5
					// all of these values have additional "I"
					if (storedVal !=  5)
					{
						// loops as often as the storedVal - 5
						// appends "I" to convertRoman
						for (j = 0; j < storedVal - 5; j++)
						{
							convertRoman += "I";
						}
					}
					// shared characteristic of all values in this range
					// NOTE: appended last because of reversed order
					convertRoman += "V";
				}
				// value == 9 is unique, "IX"
				//NOTE: reversed because of reversed order
				else if (storedVal == 9)
				{
					convertRoman += "XI";
				}
			}
			
			// checks if element is a "tens" value
			// NOTE: reversed user input guarantees this is "tens" values
			if (i == 1)
			{
				// values less than 4 all share "X" in common
				if(storedVal < 4)
				{
					// loops as often as the storedVal
					// appends "X" to convertRoman String
					for(j = 0; j < storedVal ; j++)
					{
						convertRoman += "X";
					}
				}
				// value == to 4 is unique, "LX"
				// NOTE: reversed order because processing in reverse
				else if (storedVal == 4)
				{
					convertRoman += "LX";
				}
				// values > 4 and values < 9 all share "L"
				else if ((storedVal > 4) && (storedVal < 9))
				{
					// conditional for values > 5
					// all of these values have additional "X"
					if (storedVal !=  5)
					{
						// loops as often as the storedVal - 5
						// appends "X" to convertRoman
						for (j = 0; j < storedVal - 5; j++)
						{
							convertRoman += "X";
						}
					}
					// shared characteristic of all values in this range
					// NOTE: appended last because of reversed order
					convertRoman += "L";
				}
				// value == 9 is unique, "XC"
				//NOTE: reversed because of reversed order
				else if (storedVal == 9)
				{
					convertRoman += "CX";
				}
			}
			// checks if element is a "hundreds" value
			// NOTE: reversed user input guarantees this is "hundreds" values
			if (i == 2)
			{
				// values less than 4 all share "X" in common
				if(storedVal < 4)
				{
					// loops as often as the storedVal
					// appends "C" to convertRoman String
					for(j = 0; j < storedVal ; j++)
					{
						convertRoman += "C";
					}
				}
				// value == to 4 is unique, "CD"
				// NOTE: reversed order because processing in reverse
				else if (storedVal == 4)
				{
					convertRoman += "DC";
				}
				// values > 4 and values < 9 all share "D"
				else if ((storedVal > 4) && (storedVal < 9))
				{
					// conditional for values > 5
					// all of these values have additional "X"
					if (storedVal !=  5)
					{
						// loops as often as the storedVal - 5
						// appends "C" to convertRoman
						for (j = 0; j < storedVal - 5; j++)
						{
							convertRoman += "C";
						}
					}
					// shared characteristic of all values in this range
					// NOTE: appended last because of reversed order
					convertRoman += "D";
				}
				// value == 9 is unique, "CM"
				//NOTE: reversed because of reversed order
				else if (storedVal == 9)
				{
					convertRoman += "MC";
				}
			}
			
			// checks if element is a "thousands" value
			// NOTE: reversed user input guarantees this is "thousands" values
			if (i == 3)
			{
				// values less than 4 all share "M" in common
				if(storedVal < 4)
				{
					// loops as often as the storedVal
					// appends "M" to convertRoman String
					for(j = 0; j < storedVal ; j++)
					{
						convertRoman += "M";
					}
				}
			}
		}
		
		// Reverses the reverse-translation-RomanString equivalent
		// returns the corrected String
		String corrected = "";
		for(i = 0; i < convertRoman.length(); i++)
		{
			corrected += convertRoman.charAt(convertRoman.length() - 1 - i);
		}
		return corrected;
	}
	
	// testing client
	public static void main(String[] args)
	{
		// Tests 9-digit for 'hundreds', 'tens', 'ones' and 3-thousands
		System.out.println(romanNum(3999));
		System.out.println("");
		
		// tests 6, 7, 8 digit in each position and 2-thousands
		System.out.println(romanNum(2678));
		System.out.println(romanNum(2867));
		System.out.println(romanNum(2786));
		System.out.println("");
		
		// tests 5-digit in eacch position and 1-thousands
		System.out.println(romanNum(1555));
		System.out.println("");
		
		// tests 4-digit in each position
		System.out.println(romanNum(444));
		System.out.println("");
		
		// tests 1, 2, 3 digit in each position
		System.out.println(romanNum(321));
		System.out.println(romanNum(132));
		System.out.println(romanNum(213));
		System.out.println("");
		
		// tests values of various sizes,
		System.out.println(romanNum(1));
		System.out.println(romanNum(15));
		System.out.println(romanNum(152));
		System.out.println(romanNum(1523));

	}
}
```
