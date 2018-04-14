package libraryapp.model;

public class Librarian extends Person
{
	public Librarian(String _username, String _pass)
	{
		this.setID(_username);
		this.setPass(_pass);
	}
}
