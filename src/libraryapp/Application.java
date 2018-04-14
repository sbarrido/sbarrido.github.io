package libraryapp;
import libraryapp.gui.*;

import javax.swing.SwingUtilities;

public class Application
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() 
					{
						//TESTING//
						System.out.println(SwingUtilities.isEventDispatchThread());
						//TESTING//
						
						createAndShowGUI();
					}
				});
	}
	
	private static void createAndShowGUI()
	{
		MainFrame main = new MainFrame();
		main.initComponents();
		main.pack();
		main.setVisible(true);
	}
}
