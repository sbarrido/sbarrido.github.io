package libraryapp.gui;
import libraryapp.model.*;
import libraryapp.gui.utilities.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;

public class MainFrame extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;
	public static CardLayout cardLO = new CardLayout();
	public static JPanel mainContainer = new JPanel(cardLO);
	public static Library myLibrary = new Library();
	
	public MainFrame()
	{
		super();
		
		Observer.currentUser.add(this);
		
		this.setTitle("Library Application");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void initComponents(String userPath, String bookPath)
	{
		myLibrary.readUserList(userPath);
		myLibrary.readBookList(bookPath);
		
		AppHome home = new AppHome();
		Login login = new Login();
		Register register = new Register();
		StudentMenu studMenu = new StudentMenu();
		
		mainContainer.add(home, "Home");
		mainContainer.add(login, "Login");
		mainContainer.add(register, "Register");
		mainContainer.add(studMenu, "Student");
		
		cardLO.show(mainContainer, "Home");
		this.getContentPane().add(mainContainer);
	}
	@Override
	public void update() 
	{
		String card = "Home";
		if(Observer.currentUser.getPerson() instanceof Student)
		{
			card = "Student";
		}
		if(Observer.currentUser.getPerson() instanceof Librarian)
		{
			card = "Librarian";
		}
		cardLO.show(mainContainer, card);
	}
}
