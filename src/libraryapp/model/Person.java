package libraryapp.model;

public abstract class Person 
{
	private String id;
	private String password;
	
	public String getID()
	{
		return this.id;
	}
	public String getPass()
	{
		return this.password;
	}
	protected void setID(String _id)
	{
		this.id = _id;
	}
	protected void setPass(String _pass)
	{
		this.password = _pass;
	}
}
