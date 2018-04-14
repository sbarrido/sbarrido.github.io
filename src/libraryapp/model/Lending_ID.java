// CSCI-C 212, Group 29
// Taylor Devine, Sam Barrido, Gavin Steever, Jamal Kasem
// Final Project Online Library, 04-06-2018

package libraryapp.model;
import java.util.Date;
import java.text.*;

/**
 * Lending ID class representing a lending ID created when a book is borrowed.
 * 
 * 04 April 2018
 * @author Taylor Devine, Sam Barrido, Gavin Steever, Jamal Kasem
 *
 */
public class Lending_ID {
	
	// INSTANCE FIELDS
	private static int ID_Counter = 0;	//I think this might make more sense so we never have duplicate lending IDs
	private int lendingID;
	private Book myBook;
	private Date rentDate;
	private Date returnDate;
	private Date todaysDate;
	public static SimpleDateFormat dateFormat = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a");
												//ex: Sun 2018.04.08 at 04:04:12 PM 
	final static long MINUTE = 1000L;
	final static long HOUR = 60 * 1000L;
	final static long DAY = 24 * 60 * 1000L;
	
	
	
	// CONSTRUCTOR
	/** Initializes the book class */
	public Lending_ID(Book book) {
		// TO DO
		this.myBook = book;
		// if not given, sets rent date to the date lendingID was created
		this.rentDate = new Date();
		// if not given, sets return date to 7 days after rent date
		this.returnDate = new Date(this.rentDate.getTime() + (7L * Lending_ID.DAY));	
		ID_Counter++;
		lendingID = ID_Counter;
		this.todaysDate = new Date();
	}
	public Lending_ID(Book book, Date d) {
		this.myBook = book;
		this.returnDate = d;
		this.rentDate = new Date();		// if the dates not given, just assign it to the date the lending ID was created?		
		ID_Counter++;
		lendingID = ID_Counter;
		this.todaysDate = new Date();
	}
	public Lending_ID(/*int l, */Book b, Date rentd, Date returnd) {
		//this.lendingID = l;
		this.myBook = b;
		this.rentDate = rentd;
		this.returnDate = returnd;
		ID_Counter++;
		lendingID = ID_Counter;
		this.todaysDate = new Date();
	}
	// SETTERS
	//protected void setID(int i) 		{ this.lendingID = i; }		/** Sets the lending ID */
	protected void setBook(Book b) 		{ this.myBook = b; }		/** Sets the book */
	protected void setRentDate(Date d) 	{ this.rentDate = d; }		/** Sets the date it was rented */
	protected void setReturnDate(Date d){ this.returnDate = d; }	/** Sets the date it's due to return by */
	//protected void setTodaysDate()	{ this.todaysDate = new Date(); }
	// GETTERS
	protected int getID() 			{ return lendingID; }/** Returns the lending ID */
	public Book getBook() 		{ return this.myBook; }			/** Returns the book */
	protected Date getRentDate()	{ return this.rentDate; }		/** Returns the date it was rented */
	protected Date getReturnDate() 	{ return this.returnDate; }		/** Returns the date it's due to return by */
	protected Date getTodaysDate() 	{ return this.todaysDate; }		/** Returns today's date */
	
	/** Returns rent date in a String with SimpleDateFormat */
	protected String getRentDateString() { return "" + dateFormat.format(this.rentDate); }
	/** Returns return date in a String with SimpleDateFormat */
	protected String getReturnDateString() { return "" + dateFormat.format(this.returnDate); }
	/** Returns todays date in a String with SimpleDateFormat */
	protected String getTodaysDateString() { return "" + dateFormat.format(this.todaysDate); }
	
	
	
	
	
	// METHODS
	
	/** Updates today's date */
	protected void updateTodaysDate() {
		this.todaysDate = new Date();
	}
	
	/** Returns if it is overdue or not based on when it was supposed to be returned */
	protected boolean isOverdue() {
		// this might be backwards, go back and check later
		/*int i = todaysDate.compareTo(returnDate);// returns 0, a positive num, or a negative num
		if (i == 0) { return false;}		// the dates are equal, not overdue
		else if (i > 0) { return true; }	// positive value if the invoking object is later than date
		else { return false; } */			// negative value if the invoking object is earlier than date
		
		// can also do
		if (todaysDate.getTime() > returnDate.getTime() ) { return true; }
		else { return false; }	// if the difference in time is less than or equal
	}
	
	
	/** Returns a string representation of lending ID */
	public String toString() {
		return "Lend ID: " + getID() + "\n" + myBook.toString() +"\n" +  "due: " + getReturnDate();
	}
}










