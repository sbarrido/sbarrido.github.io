package libraryapp.gui.utilities;
import libraryapp.gui.*;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JButton button = (JButton) event.getSource();
		String card = button.getActionCommand();
		
		Login.idLogin.setText("");
		Login.passLogin.setText("");
		Register.idRegister.setText("");
		Register.passRegister.setText("");
		Register.passConfirm.setText("");
		StudentMenu.authSearch.setText("");
		StudentMenu.isbSearch.setText("");
		StudentMenu.titlSearch.setText("");
		StudentMenu.payment.setText("");
		StudentMenu.myBooks.clear();
		SearchResultsMenu.foundBooks.clear();
		
		
		MainFrame.cardLO.show(MainFrame.mainContainer, card);
	}

}
