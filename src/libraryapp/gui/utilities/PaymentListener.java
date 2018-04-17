package libraryapp.gui.utilities;
import libraryapp.gui.*;
import libraryapp.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		int inValue = Integer.valueOf(StudentMenu.payment.getText());
		int owed = ((Student) Observer.currentUser.getPerson()).getPenalities();
		
		if(owed < 0)
		{
			for(Person user : MainFrame.myLibrary.getUsers())
			{
				if(Observer.currentUser.getPerson().getID().equals(user.getID()))
				{
					((Student) user).setPenalties(owed + inValue);
					StudentMenu.owed.setText("Total Dues Owed: " + ((Student) user).getPenalities());
				}
			}
		}
		else
		{
			System.out.println("All dues Paied");
		}
	}

}
