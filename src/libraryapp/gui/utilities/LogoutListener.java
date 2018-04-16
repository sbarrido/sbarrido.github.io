package libraryapp.gui.utilities;
import libraryapp.gui.*;
import libraryapp.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		for(Person user : MainFrame.myLibrary.getUsers())
		{
			MainFrame.myLibrary.rmvPerson(user);
			MainFrame.myLibrary.addPerson(user);
		}
	}

}
