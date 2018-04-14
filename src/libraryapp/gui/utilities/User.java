package libraryapp.gui.utilities;
import libraryapp.model.*;

import java.util.ArrayList;
public class User 
{
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private Person currentUser;
	
	public User()
	{
		currentUser = new Student();
	}
	public void add(Observer obs)
	{
		observers.add(obs);
	}
	public Person getPerson()
	{
		return this.currentUser;
	}
	protected void setPerson(Person _user)
	{
		currentUser = _user;
		this.execute();
	}
	private void execute()
	{
		for(Observer observer : observers)
		{
			observer.update();
		}
	}
}
