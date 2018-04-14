package libraryapp.gui;
import libraryapp.gui.utilities.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;

public class AppHome extends JPanel
{
	private static final long serialVersionUID = 1L;
	public AppHome()
	{
		super();
		
		JPanel titleContainer = homeTitle();
		JPanel buttonContainer = homeButtons();
		
		BorderLayout borderLO = new BorderLayout();
		this.setLayout(borderLO);
		this.add(titleContainer, BorderLayout.PAGE_START);
		this.add(buttonContainer, BorderLayout.CENTER);

		this.setVisible(true);
	}
	private JPanel homeButtons()
	{
		JPanel buttonContainer = new JPanel();
		JButton login = new JButton("Login");
		JButton register = new JButton("Register");
		
		login.setActionCommand("Login");
		register.setActionCommand("Register");
		
		login.addActionListener(new CardListener());
		register.addActionListener(new CardListener());
		buttonContainer.add(login);
		buttonContainer.add(register);
		
		return buttonContainer;
	}
	private JPanel homeTitle()
	{
		JLabel title = new JLabel("Login or Register Account");
		title.setAlignmentX(CENTER_ALIGNMENT);
		JPanel titleContainer = new JPanel();
		titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
		titleContainer.add(title);
		
		return titleContainer;
	}
}
