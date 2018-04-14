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
		
		MainFrame.cardLO.show(MainFrame.mainContainer, card);
	}

}
