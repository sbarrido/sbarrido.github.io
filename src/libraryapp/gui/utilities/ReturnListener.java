package libraryapp.gui.utilities;
import libraryapp.gui.*;
import libraryapp.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class ReturnListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(!StudentMenu.returnList.isSelectionEmpty())
		{
			List<String> lendingID = StudentMenu.returnList.getSelectedValuesList();
			
			for(Person user : MainFrame.myLibrary.getUsers())
			{
				if(Observer.currentUser.getPerson().getID().equals(user.getID()))
				{
					if(user instanceof Student)
					{
						Iterator<Lending_ID> lendIterator = ((Student) user).getLendIDS().iterator();
						while(lendIterator.hasNext())
						{
							Lending_ID lend = lendIterator.next();
							String[] splitLendingID = lendingID.get(0).split("\\ ");
							for(String s : splitLendingID)
							{
								System.out.println(s);
							}
							if(lend.getID() ==  Integer.valueOf(splitLendingID[2]))
							{
								lendIterator.remove();
								ArrayList<Book> books = MainFrame.myLibrary.getMap("isbn").get(Book.stringISBN(lend.getBook().getISBN()));
								if(books!= null)
								{
									for(Book book : books)
									{
										if(!book.getAvailable())
										{
											book.setAvailable(true);
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
