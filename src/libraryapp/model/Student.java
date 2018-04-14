package libraryapp.model;

import java.util.ArrayList;

public class Student extends Person
{
	private int Penalties;
	ArrayList<LendingID> Lending_IDS = new ArrayList<>();
	
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
	protected int getPenalities() {
		return this.Penalties;
	}
	
	//SETTERS
	protected void setPenalties(int amount) {
		this.Penalties = amount;
	}
	
	//METHODS
	protected void addPenalty() {
		
	}
	protected void rmvPenalty() {
		
	}
	protected void addLendingIDList(LendingID id) {
		Lending_IDS.add(id);
	}
	protected void rmvLendingIDList(LendingID id) {
		Lending_IDS.remove(id);
	}
	protected ArrayList<LendingID> getLendingIDs(){
		return this.Lending_IDS;
	}
	protected String formatLendIDs()
	{
		String formatted = "";
		for(LendingID lendID : Lending_IDS)
		{
			formatted += lendID.getID() + "{" + Book.stringISBN(lendID.getBook().getISBN())
			+ "/" + lendID.getRentDate() + "/" + lendID.getReturnDate() + "}";
			
			formatted += "*";
		}
		return formatted;
	}
}

