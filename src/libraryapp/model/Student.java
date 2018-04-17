package libraryapp.model;

import java.util.ArrayList;

public class Student extends Person
{
	private int Penalties;
	ArrayList<Lending_ID> Lending_IDS = new ArrayList<>();
	
	public Student()
	{
		this.setID("0");
		this.setPass("test");
	}
	public Student(String _username , String _pass)
	{
		this.setID(_username);
		this.setPass(_pass);
	}
	//GETTERS
	public int getPenalities() {
		return this.Penalties;
	}
	public ArrayList<Lending_ID> getLendIDS()
	{
		return this.Lending_IDS;
	}
	//SETTERS
	public void setPenalties(int amount) {
		this.Penalties = amount;
	}
	
	//METHODS
	protected void addPenalty() {
		
	}
	protected void rmvPenalty() {
		
	}
	public void addLendingIDList(Lending_ID id) {
		if(Penalties >= 0)
		{
			Lending_IDS.add(id);
			System.out.println("i added a book to user");
		}
		else
		{
			System.out.println("too many penalties");
		}
	}
	public void rmvLendingIDList(Lending_ID id) {
		Lending_IDS.remove(id);
	}
	protected void setLendingIDs(ArrayList<Lending_ID> list) {
		Lending_IDS = list;
	}
	protected String formatLendIDs()
	{
		String formatted = "";
		for(Lending_ID lendID : Lending_IDS)
		{
			formatted += lendID.getID() + "{" + Book.stringISBN(lendID.getBook().getISBN())
			+ "/" + lendID.getRentDate().getTime() + "/" + lendID.getReturnDate().getTime() + "}";
			
			formatted += "*";
		}
		return formatted;
	}
}

