package libraryapp.gui;
import libraryapp.gui.utilities.*;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;

public class Register extends JPanel
{
	private static final long serialVersionUID = 1L;
	public static JTextField idRegister = new JTextField(20);
	public static JTextField passRegister = new JTextField(20);
	public static JTextField passConfirm = new JTextField(20);

	public Register()
	{
		super();
		
		JLabel title = new JLabel("Register Account");
		JPanel idContainer = userField();
		JPanel passContainer = passField();
		JPanel confirmContainer = confirmField();
		JPanel buttContainer = registerButtons();
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		idContainer.setAlignmentX(CENTER_ALIGNMENT);
		passContainer.setAlignmentX(CENTER_ALIGNMENT);
		confirmContainer.setAlignmentX(CENTER_ALIGNMENT);
		buttContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel compContainer = new JPanel();
		compContainer.setLayout(new BoxLayout(compContainer, BoxLayout.Y_AXIS));
		compContainer.add(title);
		compContainer.add(idContainer);
		compContainer.add(passContainer);
		compContainer.add(confirmContainer);
		compContainer.add(buttContainer);
		
		this.setLayout(new BorderLayout());
		this.add(compContainer, BorderLayout.CENTER);
		this.setVisible(true);
	}
	private JPanel userField()
	{
		JPanel userContainer = new JPanel();
		JLabel userLabel = new JLabel("Username");
		idRegister.setName("idRegister");
		idRegister.setBounds(5, 5, 150, 20);
		
		userContainer.add(userLabel);
		userContainer.add(idRegister);
		
		return userContainer;
	}
	private JPanel passField()
	{
		JPanel passContainer = new JPanel();
		JLabel passLabel = new JLabel("Password");
		passRegister.setName("passRegister");
		passRegister.setBounds(5, 5, 150, 20);
		
		passContainer.add(passLabel);
		passContainer.add(passRegister);
		
		return passContainer;
	}
	private JPanel confirmField()
	{
		JPanel confirmContainer = new JPanel();
		JLabel passLabel = new JLabel("Confirm Password");
		passConfirm.setName("passConfirm");
		passConfirm.setBounds(5, 5, 150, 20);
		
		confirmContainer.add(passLabel);
		confirmContainer.add(passConfirm);
		
		return confirmContainer;
	}
	private JPanel registerButtons()
	{
		JPanel formatContainer = new JPanel();
		formatContainer.setLayout(new BoxLayout(formatContainer, BoxLayout.Y_AXIS));
		JPanel buttContainer = new JPanel();
		JButton buttRegister = new JButton("Register");
		JButton buttBack = new JButton("Back");
		
		buttRegister.setActionCommand("Home");
		buttRegister.addActionListener(new RegisterListener());
		
		buttBack.setActionCommand("Home");
		buttBack.addActionListener(new CardListener());
		
		buttContainer.add(buttRegister);
		buttContainer.add(buttBack);
		buttContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		formatContainer.add(buttContainer);
		
		return formatContainer;
		
	}
}
