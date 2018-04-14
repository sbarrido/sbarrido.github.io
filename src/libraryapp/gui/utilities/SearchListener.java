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
		StudentMenu.bookResults.clear();
		
		switch(searchType)
		{
		case "author":
			String authName = StudentMenu.authSearch.getText();
			searchLibrary(authName);
			break;
		case "title":
			String titlName = StudentMenu.titlSearch.getText();
			searchLibrary(titlName);
			break;
		case "isbn":
			String isbnName = StudentMenu.isbSearch.getText();
			searchLibrary(isbnName);
			break;
		default:
			break;
		}
	}
	private void searchLibrary(String target)
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run() 
			{
				ArrayList<Book> targetBooks = MainFrame.myLibrary.getBookMap().get(target);
				for(Book book : targetBooks)
				{
					SearchResultsMenu.foundBooks.addElement(book.toString());
				}
			}
	
		});
	}
}
