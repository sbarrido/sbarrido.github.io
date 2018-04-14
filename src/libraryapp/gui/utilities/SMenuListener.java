package libraryapp.gui.utilities;

import javax.swing.JButton;

import libraryapp.gui.StudentMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SMenuListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JButton button = (JButton) event.getSource();
		String card = button.getActionCommand();
		
		StudentMenu.studentCard.show(StudentMenu.studentCont, card);
	}

}
