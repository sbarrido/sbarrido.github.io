## Matrix (Multi-Dimensional Arrays)
Using 2D-Arrays, I can create a Matrix of any size. This project utilizes 
object-oriented programming, constructing Matrix-type objects and creating various
methods to use on such objects. Many examples of processing through 2D-Arrays and
creating methods used in Linear Algebra. 

```
/**
 * 
 * @author Samuel Barrido/sbarrido
 * Last Edited: Feb. 7, 2018
 * Lab 5
 *
 */

public class Matrix 
{
	private double[][] matrix;
	private final int NUMROW;
	private final int NUMCOL;
	
	// constructor for Matrix
	public Matrix(double[][] m)
	{ 
		// variable assignment from given double 2D array
		this.NUMROW = m.length;
		this.NUMCOL = m[0].length;
		this.matrix = new double[this.NUMROW][this.NUMCOL];
		
		// initializes the private variable, matrix
		// by looping through the given double 2D array
		int i;
		int j;
		for(i = 0; i < this.NUMROW; i++)
		{
			for(j = 0; j < this.NUMCOL; j++)
			{
				this.matrix[i][j] =
						m[i][j];
			}
		}
	}
	
	// Method used to convert a Matrix Object to String
	public String toString()
	{ 
		// variables 
		int i;
		int j;
		String tempStore = "";
		String newLine = String.format("%n");
		
		// loops through the Object.matrix 2D array
		// Stores values into a String with proper punctuation
		for(i = 0; i < this.NUMROW; i ++)
		{
			// start of matrix needs a bracket
			if (i == 0)
			{
				tempStore += "[";
			}
			
			// loops through individual row 
			// stores values with proper punctuation
			// depending on conditions
			for(j = 0; j < this.NUMCOL; j++)
			{
				if((i == (this.NUMROW - 1)) && (j == (this.NUMCOL - 1)))
				{
					tempStore += matrix[i][j] + "]";
				}
				else if(j == (this.NUMCOL - 1))
				{
					tempStore += matrix[i][j];
				}
				else
				{
					tempStore += matrix[i][j] + ", ";
				}
			}
			// each row starts on a newLine
			tempStore += newLine;
		}
		return tempStore;
	}
	
	// transpose "flip" the axis of the given Matrix
	public Matrix transposeMatrix() 
	{
		// variables to "flip" matrix
		int oldRow = this.matrix.length;
		int oldCol = this.matrix[0].length;
		int newRow = this.matrix[0].length;
		int newCol = this.matrix.length;
		
		double[][] tempArrays = new double[newRow][newCol];
		
		// loops through original matrix
		// stores those values in the opposite position
		// within a temporary array
		int i;
		int j;
		for(i = 0; i < oldRow; i++)
		{
			for(j = 0; j < oldCol; j++)
			{
				
				tempArrays[j][i] = this.matrix[i][j];
				
			}
		}
		
		// constructs a new Matrix from the transposed Array
		Matrix transMatrix = new Matrix(tempArrays);
		return transMatrix;
	}
	
	// assigns 0.0 to all values above the main diagonal of matrix
	public Matrix getUpperDiagonal() 
	{
		// variables
		// diagonal functions assume square
		int width = this.NUMROW;
		int i;
		int j;
		double[][] tempArrays = new double[width][width];
		
		// loops through Object.matrix
		// stores values in a temporary array based on conditions
		for(i = 0; i < width; i++)
		{
			for(j = 0; j < width; j++)
			{
				if (j <= i)
				{
					tempArrays[i][j] =
							this.matrix[i][j];
				}
				else
				{
					tempArrays[i][j] =
							0.0;
				}
			}
		}
		
		// constructs new Matrix object from the stored values
		Matrix upDiagonal = new Matrix(tempArrays);
		return upDiagonal;
	}
	
	// assigns 0.0 to all values below the main diagonal of matrix
	public Matrix getLowerDiagonal() 
	{
		// variables
		// diagonal functions assume square
		int width = this.NUMROW;
		int i;
		int j;
		double[][] tempArrays = new double[width][width];
		
		// loops through Object.matrix
		// stores values in a temporary array based on conditions
		for(i = 0; i < width; i++)
		{
			for(j = 0; j < width; j++)
			{
				if (i <= j)
				{
					tempArrays[i][j] =
							this.matrix[i][j];
				}
				else
				{
					tempArrays[i][j] =
							0.0;
				}
			}
		}
		
		// constructs new Matrix object from the stored values
		Matrix downDiagonal = new Matrix(tempArrays);
		return downDiagonal;
	}
	
	// assigns 0.0 for all variables except for main diagonal
	// takes advantage of previous methods 
	public Matrix getDiagonal() 
	{
		// constructs new Matrix with Object's matrix variable
		Matrix diagMatrix = new Matrix(this.matrix);
		
		return diagMatrix.getLowerDiagonal().getUpperDiagonal();
	}
	
	// assigns 0.0 for all variables except ANTI-diagonal
	public Matrix getAntiDiagonal() 
	{ 
		// variables
		// diagonal functions assume square
		int width = this.NUMROW;
		int i;
		int j;
		double[][] tempArrays = new double[width][width];
		
		// loops through Object.matrix
		// stores values in a temporary array based on conditions
		for(i = 0; i < width; i++)
		{
			for(j = 0; j < width; j++)
			{
				if (i == (this.NUMCOL - j -1))
				{
					tempArrays[i][j] =
							this.matrix[i][j];
				}
				else
				{
					tempArrays[i][j] =
							0.0;
				}
			}
		}
		
		// constructs new Matrix object from the stored values
		Matrix antiDiagonal = new Matrix(tempArrays);
		return antiDiagonal;
	}
	
	// Method returns true if Square
	public boolean isSquareMatrix()
	{
		if(this.NUMROW == this.NUMCOL)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Method returns True if an Identity Matrix
	public boolean isIdentityMatrix()
	{
		// variables
		Matrix testMatrix = new Matrix(this.matrix);
		
		// basic condition to qualify for Identity Matrix
		if(testMatrix.isSquareMatrix())
		{
			// diagonal functions assume square
			int width = this.NUMROW;
			int i;
			int j;
			double[] tempArrays = new double[width];
			
			// loops through Object's matrix 
			// stores ONLY values when i == j
			// keeps values in a temporary Array
			for(i = 0, j = 0; i < width; i++, j++)
			{
				tempArrays[i] =
						this.matrix[i][j];
			}
			
			// loops through the temporary array
			// returns true if every value is 1
			for(i = 0; i < tempArrays.length; i++)
			{
				if(tempArrays[i] != 1)
				{
					return false;
				}
			}
			return true;
		}
		
		// not a square, not a problem
		else
		{
			return false;
		}
	}
	
	// Method returns true if every value is equal
	public boolean isEqual(Matrix m) 
	{
		// variables
		int newRow = m.NUMROW;
		int newCol = m.NUMCOL;
		
		// basic condition to even consider being equal
		if((this.NUMROW == newRow) && (this.NUMROW == newCol))
		{
			int i;
			int j;
			
			// loops through each Row
			for(i = 0; i < newRow; i++)
			{
				// loops through each position
				// checks if each value is equivalent
				for(j = 0; j < newCol; j++)
				{
					if (m.matrix[i][j] != this.matrix[i][j])
					{
						return false;
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// testing client
	public static void main(String[] args) 
	{
		// variables to test
		double[][] testArray = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
		double[][] eqArray = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
		double[][] idArray = {{1.0, 0.0, 0.0}, {0.0, 1.0, 0.0}, {0.0, 0.0, 1.0}};
		Matrix idMatrix = new Matrix(idArray);
		Matrix testMatrix = new Matrix(testArray);
		Matrix eqMatrix = new Matrix(eqArray);


		//===============toString() Test=================
		System.out.println("========toString() Test=================");
		System.out.println(testMatrix.toString());
		String expectedStr = String.format("[1.0, 2.0, 3.0%n4.0, 5.0, 6.0%n7.0, 8.0, 9.0]%n");
		System.out.println("Expected:");
		System.out.println(expectedStr);
		//===============End toString() Test===============
		
		//=====================transposeMatrix() Test==============
		System.out.println("========transposeMatrix() Test=================");
		System.out.println(testMatrix.transposeMatrix());
		String expectedTrans = String.format("[1.0, 4.0, 7.0%n2.0, 5.0, 8.0%n3.0, 6.0, 9.0]%n");
		System.out.println("Expected:");
		System.out.println(expectedTrans);
		//====================End transposeMatrix() Test======================
		
		//==================getUpperDiagonal() Test===================
		System.out.println("==============getUpperDiagonal() Test=========");
		System.out.println(testMatrix.getUpperDiagonal());
		String expectedUpDiag = String.format("[1.0, 0.0, 0.0%n4.0, 5.0, 0.0%n7.0, 8.0, 9.0]%n");
		System.out.println("Expected:");
		System.out.println(expectedUpDiag);
		//==================End getUppderDiagonal() Test================
		
		//===================getLowerDiagonal() Test================= 
		System.out.println("==============getLowerDiagonal() Test=========");
		System.out.println(testMatrix.getLowerDiagonal());
		String expectedLowDiag = String.format("[1.0, 2.0, 3.0%n0.0, 5.0, 6.0%n0.0, 0.0, 9.0]%n");
		System.out.println("Expected:");
		System.out.println(expectedLowDiag);
		//====================End getLowerDiagonal() Test===============
		
		//==================getDiagonal() Test=================
		System.out.println("==============getDiagonal() Test=========");
		System.out.println(testMatrix.getDiagonal());
		String expectedDiag = String.format("[1.0, 0.0, 0.0%n0.0, 5.0, 0.0%n0.0, 0.0, 9.0]%n");
		System.out.println("Expected:");
		System.out.println(expectedDiag);
		//==================End getDiagonal() Test=================
		
		//==================getAntiDiagonal() Test=================
		System.out.println("==============getAntiDiagonal() Test=========");
		System.out.println(testMatrix.getAntiDiagonal());
		String expectedAntiDiag = String.format("[0.0, 0.0, 3.0%n0.0, 5.0, 0.0%n7.0, 0.0, 0.0]%n");
		System.out.println("Expected:");
		System.out.println(expectedAntiDiag);
		
		//==================End getAntiDiagonal() Test=================
		
		//==================isSquareMatrix() Test=================
		System.out.println("==============isSquareMatrix() Test=========");
		System.out.println(testMatrix.isSquareMatrix());
		System.out.println("Expected: true");
		//==================End isSquareMatrix() Test=================
		
		//==================isIdentityMatrix() Test=================
		System.out.println("==============isIdentityMatrix() Test=========");
		System.out.println(idMatrix.isIdentityMatrix());
		System.out.println("Expected: true");
		System.out.println(testMatrix.isIdentityMatrix());
		System.out.println("Expected: false");
		//==================End isIdentityMatrix() Test=================
		
		//==================isEqual() Test=================
		System.out.println("==============isEqual() Test=========");
		System.out.println(testMatrix.isEqual(idMatrix));
		System.out.println("Expected: false");
		System.out.println(testMatrix.isEqual(eqMatrix));
		System.out.println("Expected: true");
		//==================End isEqual() Test=================
	}
}
```
