package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class BooksTab {
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages"); //$NON-NLS-1$ //$NON-NLS-2$
	public int offset = 0;
	private ImageIcon imgSK = new ImageIcon("img/sk-flag.png");
	private ImageIcon imgEN = new ImageIcon("img/en-flag.png");
	private JButton langSK = new JButton(imgSK);
	private JButton langEN = new JButton(imgEN);
	private JButton all_books = new JButton(resourceBundle.getString("BooksTab.btn.Show_all_books")); //$NON-NLS-1$
	private JButton detail_books = new JButton(resourceBundle.getString("BooksTab.btn.Detail_of_selected_book")); //$NON-NLS-1$
	private JLabel books_per_page = new JLabel(resourceBundle.getString("BooksTab.lbl.Books_per_page")); //$NON-NLS-1$
	private JPanel panel;
	private JButton prev = new JButton(resourceBundle.getString("BooksTab.btn.PREV")); //$NON-NLS-1$
	private JButton next = new JButton(resourceBundle.getString("BooksTab.btn.NEXT")); //$NON-NLS-1$
	private String[] columns_books = {"ID", "Title", "Publisher", "Pages", "Pieces", "EAN_code", "Book genre" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	private JTextField offset_txt;
	private JTable table = new JTable();
	private JScrollPane scroll = new JScrollPane(table);
	private DefaultTableModel model= new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public BooksTab(JPanel books_panel) {
		this.panel = books_panel;
						
		//JTable pre Books
		table.setModel(model);
		table.setBackground(new Color(240,248,255));
		table.setFont(new Font("Segoe", Font.BOLD, 20)); //$NON-NLS-1$
		table.setRowHeight(40);
		scroll.setBounds(280, 19, 2198, 1233);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		model.setColumnIdentifiers(columns_books);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(240);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(3).setPreferredWidth(1);
		table.getColumnModel().getColumn(4).setPreferredWidth(1);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(190);
		panel.add(scroll);
		
		//nastavovanie pozicii pre Buttons 
		all_books.setBounds(26, 28, 209, 41);
		detail_books.setBounds(26, 183, 209, 41);
		books_per_page.setBounds(26, 86, 197, 33);
		prev.setBounds(280, 1270, 120, 35);		
		next.setBounds(2357, 1270, 120, 35);
		langSK.setBounds(26, 300, 60, 35);
		langEN.setBounds(106, 300, 60, 35);
		
		//nastavovanie pisma pre komponenty
		all_books.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		detail_books.setFont(new Font("Sans Serif", Font.PLAIN, 16)); //$NON-NLS-1$
		books_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18)); //$NON-NLS-1$
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		
		//nastavenie funkcionality jazykovych zmien
		langSK.addActionListener(e -> {Locale.setDefault(new Locale("sk", "SK")); 
			resourceBundle = resourceBundle.getBundle("messages");});
		langEN.addActionListener(e -> {Locale.setDefault(new Locale("en", "EN")); 
			resourceBundle = resourceBundle.getBundle("messages");});
		
		//vizibilita pre strankovacie buttony
		prev.setEnabled(false);
		next.setEnabled(false);
		detail_books.setEnabled(false);
		
		//pridavanie komponentov na panel
		panel.add(next);
		panel.add(prev);
		panel.add(all_books);
		panel.add(detail_books);
		panel.add(langSK);
		panel.add(langEN);
		panel.add(books_per_page);
		
		offset_txt = new JTextField();
		offset_txt.setText("30"); //$NON-NLS-1$
		offset_txt.setBounds(26, 116, 120, 39);
		offset_txt.setColumns(10);
		offset_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		panel.add(offset_txt);
				
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent e) { 
		            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		            detail_books.setEnabled(!lsm.isSelectionEmpty());
		        }
		});
			
		detail_books.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});
		
	}
	
	public void addActions(ActionListener showAllBooks, ActionListener prevBooks, ActionListener nextBooks){
		all_books.addActionListener(showAllBooks);
		prev.addActionListener(prevBooks);
		next.addActionListener(nextBooks);
	}
	
	public void addTableRow(Object[] row){
		this.model.addRow(row);
	}
	
	public void clearTable(){
		this.model.setRowCount(0);
	}
	
	public int getLimit(){
		int limit = 0;
		try{
			limit = Integer.parseInt(offset_txt.getText());
			if (limit < 1){
				JOptionPane.showMessageDialog(null,"The number is invalid","Error",JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$ //$NON-NLS-2$
				return -1;
			}
		}
		catch(NumberFormatException e){
			System.out.println("Error : " + e.getMessage());			 //$NON-NLS-1$
		}
		return limit;
	}
	
	public void setNextEnabled(boolean enabled){
		next.setEnabled(enabled);
	}
	
	public void setPrevEnabled(boolean enabled){
		prev.setEnabled(enabled);
	}
	
	public void setOffset(int offset){
		this.offset = offset;
	}
	
	public int getOffset(){
		return this.offset;
	}
}
