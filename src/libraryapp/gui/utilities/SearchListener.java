package libraryapp.gui.utilities;
import libraryapp.model.*;
import libraryapp.gui.*;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class SearchListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JButton button = (JButton) event.getSource();
		String searchType = button.getActionCommand();
		
		switch(searchType)
		{
		case "author":
			String authName = StudentMenu.authSearch.getText();
			searchLibrary(authName, searchType);
			break;
		case "title":
			String titlName = StudentMenu.titlSearch.getText();
			searchLibrary(titlName, searchType);
			break;
		case "isbn":
			String isbnName = StudentMenu.isbSearch.getText();
			searchLibrary(isbnName, searchType);
			break;
		default:
			break;
		}
	}
	private void searchLibrary(String target, String mapType)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run() 
			{
				ArrayList<Book> targetBooks = MainFrame.myLibrary.getMap(mapType).get(target);
				if(MainFrame.myLibrary.getMap(mapType).get(target) != null)
				{
					for(Book book : targetBooks)
					{
						SearchResultsMenu.foundBooks.addElement(book.toString());
					}
				}
			}
	
		});
	}
}
