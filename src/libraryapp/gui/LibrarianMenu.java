package libraryapp.gui;
import libraryapp.gui.utilities.*;
import libraryapp.model.*;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
public class LibrarianMenu extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	private Librarian user = new Librarian();
	private static JLabel username = new JLabel();
	public LibrarianMenu()
	{
		Observer.currentUser.add(this);
		
		this.setLayout(new BorderLayout());
		this.add(userPanel(), BorderLayout.WEST);
		this.setVisible(true);
	}
	private JPanel userPanel()
	{
		JPanel compContainer = new JPanel();
		compContainer.setLayout(new BorderLayout());
		
		JPanel userMenu = new JPanel();
		userMenu.setLayout(new BoxLayout(userMenu, BoxLayout.Y_AXIS));
		
		JButton addBook = new JButton("Add Book");
		JButton rmvBook = new JButton("Remove Book");
		JButton searchBooks = new JButton("Search");
		JButton logout = new JButton("Logout");
		
		addBook.setActionCommand("add");
		
		rmvBook.setActionCommand("remove");
		
		searchBooks.setActionCommand("search");
		
		logout.setActionCommand("Home");
		logout.addActionListener(new CardListener());
		
		addBook.setAlignmentX(CENTER_ALIGNMENT);
		rmvBook.setAlignmentX(CENTER_ALIGNMENT);
		searchBooks.setAlignmentX(CENTER_ALIGNMENT);
		
		userMenu.add(addBook);
		userMenu.add(rmvBook);
		userMenu.add(searchBooks);
		
		username.setText(user.getID());
		
		compContainer.add(username, BorderLayout.PAGE_START);
		compContainer.add(userMenu, BorderLayout.CENTER);
		compContainer.add(logout, BorderLayout.PAGE_END);
		
		return compContainer;
	}
	@Override
	public void update() 
	{
		if(Observer.currentUser.getPerson() instanceof Librarian)
		{
			user = (Librarian) Observer.currentUser.getPerson();
		}
		LibrarianMenu.username.setText("Librarian: " + user.getID());
	}

}