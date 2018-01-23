## Math in Java
Standard exercise to practice the use of Mathematical operations in Java Language.
Exposure to built-in classes and object-oriented programs
```
/** @author Sam Barrido/sbarrido
 *  Lasted Edited: Jan. 18, 2018
 *  Lab 2
 */
public class Lab2Exercises 
{
	// computes volume of a sphere when given its diameter
    public static double volumeOfSphere(int diameter) 
    {
    	double radius = diameter / 2; // radius calculation
    	double rCubed = Math.pow(radius, 3); // radius cubed calculation
    	double volume = (4.0 / 3) * Math.PI * rCubed; // formula for Sphere Volume (4/3)*PI*R^3
    	
    	return volume;
    }
    
    // computes surface area of a cylinder with given diameter and height
    public static double surfaceAreaCylinder(double diameter, double height) 
    {
    	// Circle Area Calculations
    	double radius = diameter / 2; // radius calculation
    	double rSquared = Math.pow(radius, 2); // radius squared calculation
    	double cArea = Math.PI * rSquared; // formula for Circle Area PI*R^2
    	
    	// Rectangle area Calculations
    	double length = 2 * Math.PI * radius; // circumference = length; C = 2*PI*R
    	double width = height; // width = height; given
    	double rArea = length * width; // formula for rectangle area
    	
    	//Surface Area of Cylinder computation
    	double cylArea = (2 * cArea) + rArea; // 2 circles + rectangle make up cylinder
    	
    	return cylArea;
    	
    }
    
    // returns a String, in the following format "SquareArea: *, Perimeter: *, Diagonal: *" where * refers to square of 
    // the rectanglar area, perimeter, and the diagonal of the square respectively 
    // (use Pythagorean Theorem)
    public static String squareMeasurements(int len) 
    {
    	double sArea = Math.pow(len, 2); // formula for circle area
    	double perimeter = 4.0 * len; // formula for perimeter
    	double diagonal = Math.hypot(len, len); // built-in pythagorean theorem
     
    	// formats the string
    	String text = String.format("SquareArea: %f, Perimeter: %f, Diagonal: %f", sArea, perimeter, diagonal);
    	return text;
    
    }
    
    // reads a number between 1000 and 9999999 and prints it with commas (,) separating every three digits
    // for example: 12317910 will be printed as 12,317,910
    // hint use modulus (%) to save part of the number, then concatenate back to gether as a String
    public static String addCommas(int num) 
    {
    	int hundreds = (num % 1000); //stores last 3 digits
    	int thousands = ((num % 1000000) - hundreds) / 1000; //stores middle 3 digits
    	int millions = (num - thousands - hundreds) / 1000000; //stores first 3 digits
    	
    	// formats into string with commas
    	String commas = String.format("%d,%d,%d", millions, thousands, hundreds);
    	return commas;
    }
    
    // given angle in Degrees, convert to radians (hint look at Math class Java doc API)
    // and return a string of the Sin, Cos, and Tangent of the angle  
    public static String trigFunctions(double angleInDegrees) 
    {
    	double radians = Math.toRadians(angleInDegrees); // built-in conversion
    	double sin = Math.sin(radians); // built-in function
    	double cos = Math.cos(radians); // built-in function
    	double tan = Math.tan(radians); // built-in function
    	
    	// formats into string 
    	String trig = String.format("Sin: %f%nCos: %f%nTan: %f", sin, cos, tan);
    	return trig;
    }
    
 
    // test client 
    public static void main(String[] args) 
    {
    	double sphere = volumeOfSphere(6); // calculated volume of sphere with diameter 6
    	double vol = (4.0 / 3) * Math.PI * 27; // calculated volume of sphere with radius 3
    	String calcVol= String.format("Volume: %f", sphere); // formatting String for method volumeOfSphere
    	String expectVol = String.format("Expected: %f%n", vol); // formatting String for expected volume value
    	System.out.println(calcVol); // volumeOfSphere value printed
    	System.out.println(expectVol); // manual calculation of volume printed
    	
    	double cylinder = surfaceAreaCylinder(4,2); //calculated surface area of cylinder, diameter 4 and height 2
    	double sArea = (Math.PI * 8) + (Math.PI * 8); // calculated surface area of cylinder, diameter 4 and height 2
    	String calcArea = String.format("Surface Area: %f", cylinder); // formatting String for surfaceAreaCylinder calculation
    	String expectArea = String.format("Expected: %f%n", sArea); // formatting String for expected surface area
    	System.out.println(calcArea); // print formatted method surfaceAreaCylinder result
    	System.out.println(expectArea); // print formatted expected results
    	
    	String measure = squareMeasurements(2); // calculated square measurements of length 2
    	double hypot = Math.hypot(2, 2); // calculated expected diagonal
    	String expectVal = String.format("Expected SqauareArea: %f, Perimeter: %f, Diagonal: %f%n", 4.0, 8.0, hypot); // formatting string of expected results
    	System.out.println(measure); // print method squareMeasurements(2) results
    	System.out.println(expectVal); // print expected results
     
    	String comma = addCommas(1530323); // calculated results of addCommas(1530323)
    	String expectComma = String.format("Expected: %s%n", "1,530,323"); // format expected results
    	String calcComma = String.format("Calculated: %s", comma); // format method results
    	System.out.println(calcComma); // print method results
    	System.out.println(expectComma); // print expected results
    	
    	String trig = trigFunctions(45); // calculated results of trigFunctions(45)
    	double sin = Math.sin(Math.PI / 4); // expected results of sin(pi/4) equivalent to 45 degrees
    	double cos = Math.cos(Math.PI / 4); // expected results for pi / 4 equivalent to 45 degrees
    	double tan = Math.tan(Math.PI / 4); // expected results for pi / 4 equivalent to 45 degrees
    	String expectTrig = String.format("Expected Sin: %f%nExpected Cos: %f%nExpected Tan: %f", sin, cos, tan); // formatting expected results
    	System.out.println(trig); // print method results
    	System.out.println(expectTrig); // print expected results
    }
}


/*
 * Questions 1-3 are on explicit and implicit casting of some numerical types  
 *
 * 1.  What happens if you multipy a double with a char? 
 * 			A mismatch error will occur because of the different data types. 
 * 
 * 2.  What happens if a method has a parameter of type int, but you pass it a char?
 * 			An error will occur because the method requires a parameter of type int not
 * 			a char.
 * 
 * 3.  What happens if a method has a parameter of type char, but you pass it an int? 
 * 			An error will occur because the method requires a parameter of type char not
 * 			an int. 
 * 4.  What are the 9 primitive data types in the Java language
 * 			1. int
 * 			2. byte
 * 			3. short
 * 			4. long
 * 			5. double
 * 			6. double
 * 			7. float
 * 			8. char
 * 			9. boolean 
 * 
 * 5.  Consider the following code snippet.
 *     int i = 10;
 *     n = ++(i++);
 *     System.out.println(++(i++)) % 5;
 * 
 *     What are the values of i and n after the code is executed?
 *        i = 14 and n = 11
 *     What are the final value printed?
 *     	Final value printed = 3
 */
```
