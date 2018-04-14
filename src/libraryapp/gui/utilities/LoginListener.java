package libraryapp.gui.utilities;
import libraryapp.gui.*;
import libraryapp.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		search:
			for(Person user : MainFrame.myLibrary.getUsers())
			{
				String idInput = Login.idLogin.getText();
				String userID = user.getID();
				String passInput = Login.passLogin.getText();
				String userPass = user.getPass();
				
				boolean correctInfo = (idInput.equals(userID)) && (passInput.equals(userPass));
				if(correctInfo)
				{
					Observer.currentUser.setPerson(user);
					break search;
				}
				else
				{
					System.out.println("Incorrect Username or Pass");
				}
			}
	}
}
