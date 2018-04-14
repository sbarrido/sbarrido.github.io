package libraryapp.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Library 
{
	private ArrayList<Person> userList;
	private ConcurrentHashMap<String , ArrayList<Book>> bookMap;
	private String userPath;
	private String bookPath;
	
	public Library()
	{
		bookMap = new ConcurrentHashMap<String, ArrayList<Book>>();
		userList = new ArrayList<Person>();
	}
	//Modifiers
	public void addBook(Book book)
	{
		try
		{
			File file = new File(bookPath);
			Scanner fileScan = new Scanner(file);
			
			String bookValues = "";
			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				String[] elements = line.split(",");
				if(elements.length != 0)
				{
					bookValues += line + "\n";
				}
				else
				{
					break;
				}
			}fileScan.close();
			
			bookValues += book.getAuthor() + "," + book.getTitle() + "," + Book.stringISBN(book.getISBN()) +"\n";
			PrintWriter writer = new PrintWriter(file);
			writer.write(bookValues);
			writer.close();
		}catch(FileNotFoundException e){}
		finally {readBookList(bookPath);}
	}
	public void rmvBook(Book book)
	{
		;
	}
	public void addPerson(Person person)
	{
		try
		{
			File file = new File(userPath);
			Scanner fileScan = new Scanner(file);
			
			String personInfo = "";
			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				String[] elements = line.split(",");
				if(elements.length != 0)
				{
					personInfo += line + "\n";
				}
				else
				{
					break;
				}
			}fileScan.close();
			
			if(person instanceof Student)
			{
				Student student = (Student) person;
				
				personInfo += student.getID() + "," + student.getPass() + "," 
				+ student.formatLendIDs() + "," + student.getPenalities();
				personInfo += "\n";
				
			}
			if(person instanceof Librarian)
			{
				Librarian librarian = (Librarian) person;
				personInfo += librarian.getID() + "," + librarian.getPass();
			}
			
			PrintWriter writer = new PrintWriter(file);
			writer.write(personInfo);
			writer.close();
		}catch(FileNotFoundException e){}
		finally {readUserList(userPath);}
	}
	public void rmvPerson(Person person)
	{
		;
	}
	public void readUserList(String filePath)
	{
		try 
		{
			File file = new File(filePath);
			Scanner fileScan = new Scanner(file);
			
			userPath = filePath;
			boolean firstRow = true;
			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				String[] elements = line.split(",");
				
				if(!firstRow)
				{
					if(elements.length != 0)
					{
						boolean isLibrarian = elements[0].contains("*");
						if(isLibrarian)
						{
							Librarian librarian = new Librarian(elements[0], elements[1]);
							userList.add(librarian);
						}
						else
						{
							Student student = new Student(elements[0], elements[1]);
							userList.add(student);
						}
					}
					else
					{
						break;
					}
				}
				else
				{
					firstRow = false;
				}
			} fileScan.close();
		}catch(FileNotFoundException e) {}
	}
	public void readBookList(String filePath)
	{
		try 
		{
			File file = new File(filePath);
			Scanner fileScan = new Scanner(file);
			
			bookPath = filePath;
			boolean firstRow = true;
			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				String[] elements = line.split(",");
				
				if(!firstRow)
				{
					if(elements.length != 0)
					{
						bookMap.put(elements[0], dupeValue(elements[0], filePath));
						bookMap.put(elements[1], dupeValue(elements[1], filePath));
						bookMap.put(elements[2], dupeValue(elements[2], filePath));
					}
					else
					{
						break;
					}
				}
				else
				{
					firstRow = false;
				}
			} fileScan.close();
		} catch(FileNotFoundException e) {}
	}
	
	//Getters
	protected ConcurrentHashMap<String, ArrayList<Book>> getBookMap()
	{
		return this.bookMap;
	}
	
	//Methods
	protected boolean isUser(String _username, String _password)
	{
		boolean toReturn = false;
		for(Person user : userList)
		{
			boolean correctLogin = user.getID().equals(_username) && user.getPass().equals(_password);
			if(correctLogin)
			{
				toReturn = true;
				break;
			}
		}
		return toReturn;
	}
	private ArrayList<Book> dupeValue(String value, String filePath)
	{
		ArrayList<Book> dupedValues = new ArrayList<Book>();
		try
		{
			File file = new File(filePath);
			Scanner fileScan = new Scanner(file);
			boolean firstRow = true;
			while(fileScan.hasNextLine())
			{
				String line = fileScan.nextLine();
				String[] elements = line.split(",");
				
				if(!firstRow)
				{
					for(String elem : elements)
					{
						if(value.equals(elem))
						{
							Book newBook = new Book(elements[0], elements[1], Book.longISBN(elements[2]));
							dupedValues.add(newBook);
						}
					}
				}
				else
				{
					firstRow = false;
				}
			} fileScan.close();
		}catch(FileNotFoundException e) {}
		return dupedValues;
	}
	public ArrayList<Person> getUsers()
	{
		return this.userList;
	}
}
