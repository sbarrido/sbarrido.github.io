package libraryapp.model;

import java.util.Date;

public class LendingID 
{
	private int lendingID;
	private Book myBook;
	private Date rentDate;
	private Date returnDate;
	
	public LendingID(Book book)
	{
		
	}
	protected int getID()
	{
		return this.lendingID;
	}
	protected Book getBook()
	{
		return this.myBook;
	}
	protected Date getRentDate()
	{
		return this.rentDate;
	}
	protected Date getReturnDate()
	{
		return this.returnDate;
	}
	protected boolean isOverdue()
	{
		//TODO
		return false;
	}
}
