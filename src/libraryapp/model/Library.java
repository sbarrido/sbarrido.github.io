package libraryapp.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;

public class Library 
{
	private ArrayList<Person> userList;
	private ConcurrentHashMap<String, ArrayList<Book>> bookMap;
	private ConcurrentHashMap<String , ArrayList<Book>> authorMap;
	private ConcurrentHashMap<String , ArrayList<Book>> titleMap;
	private ConcurrentHashMap<String , ArrayList<Book>> isbnMap;
	private String userPath;
	private String bookPath;
	
	public Library()
	{
		bookMap = new ConcurrentHashMap<String, ArrayList<Book>>();
		authorMap = new ConcurrentHashMap<String, ArrayList<Book>>();
		titleMap = new ConcurrentHashMap<String, ArrayList<Book>>();
		isbnMap = new ConcurrentHashMap<String, ArrayList<Book>>();
		userList = new ArrayList<Person>();
		readBookList("book.csv");
		readUserList("user.csv");
		
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
		//TODO;
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
					if(!elements[0].equals(person.getID()))
					{
						personInfo += line + "\n";
					}
				}
				else
				{
					break;
				}
			}fileScan.close();
			
			PrintWriter writer = new PrintWriter(file);
			writer.write(personInfo);
			writer.close();
		}catch(FileNotFoundException e) {}
		finally {readUserList(userPath);}
	}
	private void readUserList(String filePath)
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
							student.setLendingIDs(readLendingIDs(elements[2]));
							student.setPenalties(Integer.valueOf(elements[3]));
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
	private void readBookList(String filePath)
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
						authorMap.put(elements[0], dupeValue(elements[0], filePath));
						titleMap.put(elements[1], dupeValue(elements[1], filePath));
						isbnMap.put(elements[2], dupeValue(elements[2], filePath));
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
	public ConcurrentHashMap<String, ArrayList<Book>> getMap(String type)
	{
		switch(type)
		{
		case "author":
			return this.authorMap;

		case "title":
			return this.titleMap;
		case "isbn":
			return this.isbnMap;
		default:
			return this.bookMap;
		}
	}
	public ArrayList<Person> getUsers()
	{
		return this.userList;
	}
	
	//Methods
	protected boolean isUser(String _username, String _password)
	{
		boolean toReturn = false;
		for(Person user : userList)
		{
			boolean correctLogin = (user.getID().equals(_username) && user.getPass().equals(_password));
			if(correctLogin)
			{
				toReturn = true;
				break;
			}
		}
		return toReturn;
	}
	private ArrayList<Lending_ID> readLendingIDs(String interpret)
	{
		ArrayList<Lending_ID> toReturn = new ArrayList<Lending_ID>();
		
		String[] stringLends = interpret.split("\\*");
		for(String stringLendID : stringLends)
		{
			String[] lendIDInfo = stringLendID.split("[\\{\\}]");
			for(String info : lendIDInfo)
			{
				String[] isbn_and_date = info.split("\\/");
				if(isbn_and_date.length > 1)
				{
					
					if(isbnMap.containsKey(isbn_and_date[0]))
					{
						Book book = isbnMap.get(isbn_and_date[0]).get(0);
						Date rent = new Date(Long.valueOf(isbn_and_date[1]));
						Date returns = new Date(Long.valueOf(isbn_and_date[2]));
						
						Lending_ID lendID = new Lending_ID(book, rent, returns);
						toReturn.add(lendID);
					}
				}
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
}
