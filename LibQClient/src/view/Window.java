package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;

public class Window extends JFrame implements ActionListener {
	
	private JPanel panel = new JPanel();
	public BooksTab books_tab;
	public MembersTab members_tab;
	public Available_booksTab available_books_tab;
	public ReservationsTab reservations_tab;
	private File configFile = new File("resources/config.xml");	 
	private InputStream inputStream;
	private Properties props = new Properties();
	private static Logger LOG = Logger.getLogger(Window.class.getName());
	
	public Window() {
		try {
			inputStream = new FileInputStream(configFile);
			props.loadFromXML(inputStream);
			LOG.info("Successfuly opened xml config file");
		} catch (IOException e) {
			LOG.severe("Error: "+e);
		}
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(props.getProperty("begin_x"));
		setBounds(Integer.parseInt(props.getProperty("begin_x")), Integer.parseInt(props.getProperty("begin_y")),
				Integer.parseInt(props.getProperty("width")), Integer.parseInt(props.getProperty("height")));
		setTitle("Library Management System");
		setResizable(false);
		setLocationRelativeTo(null);
		panel.setBackground(new Color(204,229,255));
		setContentPane(panel);
		panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(35, 28, 2509, 1360);
		tabbedPane.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(tabbedPane);
		
		//panel Books
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Book Catalogue", null, panel_1, null);
		panel_1.setLayout(null);		
		books_tab = new BooksTab(panel_1);
		
		//panel Members
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Members", null, panel_2, null);
		panel_2.setLayout(null);					
		members_tab = new MembersTab(panel_2);
		
		//panel Borrow a book (Tabulka Available_books a nasledne pridavanie do Borrowed books a zmena stavu v Available_books)
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Borrow a book", null, panel_3, null);
		panel_3.setLayout(null);				
		available_books_tab = new Available_booksTab(panel_3);
		
		//panel Reservations
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Reservations", null, panel_4, null);
		panel_4.setLayout(null);			
		reservations_tab = new ReservationsTab(panel_4);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}