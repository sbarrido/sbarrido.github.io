package libraryapp.gui.utilities;
import libraryapp.gui.*;
import libraryapp.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListSelector implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String bookString = SearchResultsMenu.foundList.getSelectedValue();
		String[] splitISBN = bookString.split("\\: ");
		if(splitISBN.length > 0)
		{
			ArrayList<Book> results = MainFrame.myLibrary.getMap("isbn").get(Book.stringISBN(Long.valueOf(splitISBN[1])));
			for(Person user : MainFrame.myLibrary.getUsers())
			{
				if(Observer.currentUser.getPerson().getID().equals(user.getID()))
				{
					if(user instanceof Student)
					{
						if(results != null)
						{
							Lending_ID newLend = new Lending_ID(results.get(0));
							((Student) user).addLendingIDList(newLend);
							System.out.println("i added a book to user");
						}
						else
						{
							System.out.println("fail");
						}
					}
				}
			}
		}
		else
		{
			System.out.println("System error");
		}
	}
	
}
