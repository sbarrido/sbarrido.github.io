package libraryapp.gui.utilities;
import libraryapp.model.*;
import libraryapp.gui.*;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		String idInput = Register.idRegister.getText();
		String passInput = Register.passRegister.getText();
		String confInput = Register.passConfirm.getText();
		if(!isExist(idInput))
		{
			if(passInput.equals(confInput))
			{
				SwingUtilities.invokeLater(new Runnable()
				{

					@Override
					public void run() 
					{
						registerUser(idInput, passInput);
					}
			
				});
				MainFrame.cardLO.show(MainFrame.mainContainer, "Home");
			}
			else
			{
				System.out.println("Please type the same password");
			}
		}
		else
		{
			Register.idRegister.setText("");
			System.out.println("User already Exists");
		}
	}
	private boolean isExist(String inputID)
	{
		boolean toReturn = true;
		search:
			for(Person user : MainFrame.myLibrary.getUsers())
			{
				boolean userExists = inputID.equals(user.getID());
				if(userExists)
				{
					toReturn = true;
					break search;
				}
				else
				{
					toReturn = false;
				}
			}
		
		return toReturn;
	}
	private void registerUser(String id, String pass)
	{
		if(id.contains("\\*"))
		{
			Librarian lib = new Librarian(id, pass);
			MainFrame.myLibrary.addPerson(lib);
		}
		else
		{
			Student stud = new Student(id, pass);
			MainFrame.myLibrary.addPerson(stud);
		}
	}
}
