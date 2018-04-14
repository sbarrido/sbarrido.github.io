package libraryapp.gui;
import libraryapp.gui.utilities.*;
import libraryapp.model.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;

public class StudentMenu extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	public static CardLayout studentCard = new CardLayout();
	public static JPanel studentCont = new JPanel(studentCard);
	private static JLabel username = new JLabel();
	private static JLabel owed = new JLabel();
	private static JTextArea bookList = new JTextArea(20, 40);
	private static JTextField payment = new JTextField(5);
	private static JTextField authSearch = new JTextField(20);
	private static JTextField titlSearch = new JTextField(20);
	private static JTextField isbSearch = new JTextField(20);
	
	
	private Student user = new Student();
	
	public StudentMenu()
	{
		Observer.currentUser.add(this);
		
		this.setLayout(new BorderLayout());
		this.add(cardPanel(), BorderLayout.CENTER);
		this.add(userPanel(), BorderLayout.WEST);
		this.setVisible(true);
	}
	private JPanel userPanel()
	{
		JPanel compContainer = new JPanel();
		compContainer.setLayout(new BorderLayout());
		
		
		JPanel userMenu = new JPanel();
		userMenu.setLayout(new BoxLayout(userMenu, BoxLayout.Y_AXIS));
		
		JButton userBooks = new JButton("My Books");
		JButton searchBooks = new JButton("Search");
		JButton returnBooks = new JButton("Return");
		JButton userDues = new JButton("My Dues");
		JButton logout = new JButton("Logout");
		
		userBooks.setActionCommand("books");
		userBooks.addActionListener(new SMenuListener());
		
		searchBooks.setActionCommand("search");
		searchBooks.addActionListener(new SMenuListener());
		
		returnBooks.setActionCommand("return");
		returnBooks.addActionListener(new SMenuListener());
		
		userDues.setActionCommand("dues");
		userDues.addActionListener(new SMenuListener());
		
		logout.setActionCommand("Home");
		logout.addActionListener(new CardListener());
		
		userBooks.setAlignmentX(CENTER_ALIGNMENT);
		searchBooks.setAlignmentX(CENTER_ALIGNMENT);
		returnBooks.setAlignmentX(CENTER_ALIGNMENT);
		userDues.setAlignmentX(CENTER_ALIGNMENT);
		
		userMenu.add(userBooks);
		userMenu.add(searchBooks);
		userMenu.add(returnBooks);
		userMenu.add(userDues);
		
		username.setText(user.getID());
	
		compContainer.add(username, BorderLayout.PAGE_START);
		compContainer.add(userMenu, BorderLayout.CENTER);
		compContainer.add(logout, BorderLayout.PAGE_END);
		
		return compContainer;
	}
	private JPanel cardPanel()
	{
		JPanel books = new JPanel();	
		books.add(showBooks());
		
		JPanel search = showSearch();
		JPanel returns = showReturn();
		JPanel dues = showDues();
		
		studentCont.add(books, "books");
		studentCont.add(search, "search");
		studentCont.add(returns, "return");
		studentCont.add(dues, "dues");
		
		studentCard.show(studentCont, "books");
		studentCont.setVisible(true);
		return studentCont;
	}
	private JScrollPane showBooks()
	{
		bookList.setText(updateText());
		bookList.setEditable(false);
		JScrollPane scroll = new JScrollPane(bookList);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		return scroll;
	}
	private JPanel showSearch()
	{
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		JPanel comps = new JPanel();
		comps.setLayout(new BoxLayout(comps, BoxLayout.Y_AXIS));
		JPanel authorSearch = new JPanel();
		JPanel titleSearch = new JPanel();
		JPanel isbnSearch = new JPanel();
		
		JLabel title = new JLabel("Search Catalogue");
		JLabel authorS = new JLabel("Search by Author");
		JLabel titleS = new JLabel("Search by Title");
		JLabel isbnS = new JLabel("Search by ISBN");
		
		JButton search = new JButton("Search");
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		authorSearch.add(authorS);
		authorSearch.add(authSearch);
		authorSearch.setAlignmentX(CENTER_ALIGNMENT);
		 
		titleSearch.add(titleS);
		titleSearch.add(titlSearch);
		titleSearch.setAlignmentX(CENTER_ALIGNMENT);
		 
		isbnSearch.add(isbnS);
		isbnSearch.add(isbSearch);
		isbnSearch.setAlignmentX(CENTER_ALIGNMENT);
		 
		comps.add(title);
		comps.add(authorSearch);
		comps.add(titleSearch);
		comps.add(isbnSearch);
		comps.add(search);
		
		container.add(comps, BorderLayout.CENTER);
		return container;
	}
	private JPanel showReturn()
	{
		JPanel container = new JPanel();
		container.setLayout( new BorderLayout());
		JPanel buttons = new JPanel();
		JPanel comps = new JPanel();
		comps.setLayout(new BoxLayout(comps, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Return My Books");
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton confirm = new JButton("Confirm");
		JButton back = new JButton("Back");
		
		comps.add(title);
		
		buttons.add(confirm);
		buttons.add(back);
		
		container.add(comps, BorderLayout.PAGE_START);
		container.add(buttons, BorderLayout.PAGE_END);
		container.setVisible(true);
		return container;
	}
	private JPanel showDues()
	{
		JPanel container = new JPanel(new BorderLayout());
		JPanel comps = new JPanel();
		comps.setLayout(new BoxLayout(comps, BoxLayout.Y_AXIS));
		JPanel buttons = new JPanel();
		JPanel payCont = new JPanel();
		
		JLabel title = new JLabel("Overdue Payment Portal");
		owed.setText("Total Dues Owed: " + user.getPenalities());
		JLabel payPrompt = new JLabel("Amount: ");
		
		JButton confirm = new JButton("Confirm Payment");
		JButton back = new JButton("Back");
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		owed.setAlignmentX(CENTER_ALIGNMENT);
		payment.setAlignmentX(CENTER_ALIGNMENT);
		
		payCont.add(payPrompt);
		payCont.add(payment);
		
		comps.add(title);
		comps.add(owed);
		comps.add(payCont);
		
		buttons.add(confirm);
		buttons.add(back);
		
		container.add(comps, BorderLayout.CENTER);
		container.add(buttons, BorderLayout.PAGE_END);
		return container;
	}
	private String updateText()
	{
		String toPrint = "";
		String newLine = "\n\n";
		for(Lending_ID lendID : user.getLendIDS())
		{
			toPrint += lendID.toString() + newLine;
		}
		
		return toPrint;
	}
	@Override
	public void update() 
	{
		if(Observer.currentUser.getPerson() instanceof Student)
		{
			user = (Student) Observer.currentUser.getPerson();
		}
	
		StudentMenu.username.setText("Student: " + user.getID());
		bookList.setText(updateText());
		owed.setText("Total Dues Owed: " + user.getPenalities());
	}

}
