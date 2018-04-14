package libraryapp.gui.utilities;

import javax.swing.SwingUtilities;

import libraryapp.model.Lending_ID;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RButtonListener implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		SwingUtilities.invokeLater(new Runnable()
				{

					@Override
					public void run() 
					{
						
					}
			
				});
	}

}
