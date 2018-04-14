package libraryapp.gui;
import libraryapp.gui.utilities.*;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;

public class Login extends JPanel
{
	private static final long serialVersionUID = 1L;

	public static JTextField idLogin = new JTextField(20);
	public static JTextField passLogin = new JTextField(20);
	
	public Login()
	{
		super();
		
		JLabel title = new JLabel("Login Authentication");
		JPanel userContainer = userField();
		JPanel passContainer = passField();
		JPanel buttContainer = loginButtons();
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		userContainer.setAlignmentX(CENTER_ALIGNMENT);
		passContainer.setAlignmentX(CENTER_ALIGNMENT);
		buttContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel compContainer = new JPanel();
		compContainer.setLayout(new BoxLayout(compContainer, BoxLayout.Y_AXIS));
		compContainer.add(title);
		compContainer.add(userContainer);
		compContainer.add(passContainer);
		compContainer.add(buttContainer);
		
		this.setLayout(new BorderLayout());
		this.add(compContainer, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private JPanel userField()
	{
		JPanel userContainer = new JPanel();
		JLabel userLabel = new JLabel("Username");
		idLogin.setName("idLogin");
		idLogin.setBounds(5, 5, 150, 20);
		
		userContainer.add(userLabel);
		userContainer.add(idLogin);
		
		return userContainer;
	}
	private JPanel passField()
	{
		JPanel passContainer = new JPanel();
		JLabel passLabel = new JLabel("Password");
		passLogin.setName("passLogin");
		passLogin.setBounds(5, 5, 150, 20);
		
		passContainer.add(passLabel);
		passContainer.add(passLogin);
		
		return passContainer;
	}
	private JPanel loginButtons()
	{
		JPanel formatContainer = new JPanel();
		formatContainer.setLayout(new BoxLayout(formatContainer, BoxLayout.Y_AXIS));
		
		JPanel buttContainer = new JPanel();
		JButton buttLogin = new JButton("Login");
		JButton buttBack = new JButton("Back");
		
		buttLogin.setActionCommand("books");
		buttLogin.addActionListener(new LoginListener());
		buttLogin.addActionListener(new SMenuListener());
		
		buttBack.setActionCommand("Home");
		buttBack.addActionListener(new CardListener());
		
		buttContainer.add(buttLogin);
		buttContainer.add(buttBack);
		buttContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		formatContainer.add(buttContainer);
		
		return formatContainer;
	}
}
