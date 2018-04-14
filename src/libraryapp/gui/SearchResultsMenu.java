package libraryapp.gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class SearchResultsMenu extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JList<String> foundList;
	public static DefaultListModel<String> foundBooks = new DefaultListModel<String>();
	
	public SearchResultsMenu()
	{
		super();
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		
		JPanel titleCont = new JPanel();
		titleCont.setLayout(new BoxLayout(titleCont, BoxLayout.Y_AXIS));
		JPanel buttCont = new JPanel();
		buttCont.setLayout(new BoxLayout(buttCont, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Search Results");
		foundList = new JList<String>(foundBooks);
		JButton add = new JButton("Checkout");
		
		title.setAlignmentX(CENTER_ALIGNMENT);
		foundList.setAlignmentX(CENTER_ALIGNMENT);
		add.setAlignmentX(CENTER_ALIGNMENT);
		
		foundList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane listScroll = new JScrollPane(foundList);
		listScroll.setPreferredSize(new Dimension(250, 200));
		listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		titleCont.add(title);
		buttCont.add(add);
		
		container.add(titleCont, BorderLayout.PAGE_START);
		container.add(listScroll, BorderLayout.CENTER);
		container.add(buttCont, BorderLayout.PAGE_END);
		
		this.add(container);
		this.validate();
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		SwingUtilities.invokeLater(new Runnable()
				{
					@Override
					public void run() 
					{
						setText();
						StudentMenu.studentCard.show(StudentMenu.studentCont, "result");
					}
					
				});
	}
	private void setText()
	{
		foundList.setModel(foundBooks);
	}
}
