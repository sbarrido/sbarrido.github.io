package libraryapp.gui.utilities;
import libraryapp.gui.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		MainFrame.myLibrary.updatePenalties();
		MainFrame.myLibrary.updateUserList();
		MainFrame.myLibrary.updateBookList();
		
		SearchResultsMenu.foundBooks.clear();
	}

}
