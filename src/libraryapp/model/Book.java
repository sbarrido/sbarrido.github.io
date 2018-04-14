package libraryapp.model;

public class Book 
{
	private String AUTHOR;
	private String TITLE;
	private long ISBN;
	private Boolean AVAILABLE;
	private static long fourteenNums = 10000000000000L;		//the L is here bc it said it needs to have an "L" on the end to make it a literal              
	
	
	
	// CONSTRUCTOR
	public Book(String author, String title, long isbn) {
		// Initializes the book class
		this.AUTHOR = author;
		this.TITLE = title;
		this.ISBN = isbn;
	}
	public Book(String author, String title) {
		// Initializes the book class
		this.AUTHOR = author;
		this.TITLE = title;
		this.ISBN = Book.fourteenNums;
		Book.fourteenNums++;
	}
	
	
	
	// SETTERS
	public void setAuthor(String author) { this.AUTHOR = author; }			// Sets the author
	public void setTitle(String title) 	{ this.TITLE = title; }				// Sets the title
	public void setISBN(long isbn) 		{ this.ISBN = isbn; }			// Sets the ISBN number
	public void setAvailable(Boolean bool) { this.AVAILABLE = bool; }		// Sets the availability

	// GETTERS
	public String getAuthor() 		{ return AUTHOR; }				// Returns the author
	public String getTitle()		{ return TITLE; }				// Returns the title
	public long getISBN()			{ return ISBN; }				// Returns the ISBN number
	public Boolean getAvailable()	{ return AVAILABLE; }			// Returns the availability

	
	
	
	
	// METHODS
	public void rentBook() {
		// Rents a book
		// TO-DO
	}
	protected static long longISBN(String isbn)
	{
		String[] nums = isbn.split("-");
		String target = "";
		for(String s : nums)
		{
			target +=s;
		}
		
		return Long.parseLong(target);
	}
	public static String stringISBN(Long isbn)
	{
		String value = String.valueOf(isbn);
		String formatted = "";
		formatted += value.substring(0, 3) + "-" + value.substring(3, 6) + "-" + value.substring(6);
		
		return formatted;
	}
	public String toString() {
		// Returns a string representation of the book class
		return "\"" + TITLE + "\" by " + AUTHOR + "\n" + "ISBN: " + ISBN;
	}	
}

