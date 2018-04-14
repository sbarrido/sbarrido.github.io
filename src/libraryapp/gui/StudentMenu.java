package libraryapp.gui;
import libraryapp.gui.utilities.*;
import libraryapp.model.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;

public class StudentMenu extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	private Student user;
	
	public StudentMenu()
	{
		Observer.currentUser.add(this);
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	private JPanel userPanel()
	{
		JPanel compContainer = new JPanel();
		compContainer.setLayout(new BorderLayout());
		
		JLabel username = new JLabel(user.getID());
		JPanel userMenu = new JPanel();
		userMenu.setLayout(new BoxLayout(userMenu, BoxLayout.Y_AXIS));
		
		JButton userBooks = new JButton("My Books");
		JButton searchBooks = new JButton("Search");
		JButton returnBooks = new JButton("Return");
		JButton userDues = new JButton("My Dues");
		JButton logout = new JButton("Logout");
		
		userBooks.setAlignmentX(CENTER_ALIGNMENT);
		searchBooks.setAlignmentX(CENTER_ALIGNMENT);
		returnBooks.setAlignmentX(CENTER_ALIGNMENT);
		userDues.setAlignmentX(CENTER_ALIGNMENT);
		
		userMenu.add(userBooks);
		userMenu.add(searchBooks);
		userMenu.add(returnBooks);
		userMenu.add(userDues);
		
		compContainer.add(username, BorderLayout.PAGE_START);
		compContainer.add(userMenu, BorderLayout.CENTER);
		compContainer.add(logout, BorderLayout.PAGE_END);
		
		return compContainer;
	}
	@Override
	public void update() 
	{
		if(Observer.currentUser.getPerson() instanceof Student)
		{
			user = (Student) Observer.currentUser.getPerson();
		}
		this.add(userPanel(), BorderLayout.WEST);
	}

}
