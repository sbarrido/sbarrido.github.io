package libraryapp.gui.utilities;
import libraryapp.model.*;
import libraryapp.gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class CheckoutListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(!SearchResultsMenu.foundList.isSelectionEmpty())
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
								for(Book book : results)
								{
									if(book.getAvailable())
									{
										Lending_ID newLend = new Lending_ID(book);
										book.setAvailable(false);
										((Student) user).addLendingIDList(newLend);
										break;
									}
									else
									{
										System.out.println("none available");
									}
								}
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
}
