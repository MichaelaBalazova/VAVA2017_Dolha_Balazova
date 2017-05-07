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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Available_booksTab {

	private ImageIcon imgSK = new ImageIcon("img/sk-flag.png");
	private ImageIcon imgEN = new ImageIcon("img/en-flag.png");
	private JButton langSK = new JButton(imgSK);
	private JButton langEN = new JButton(imgEN);
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	private JButton all_available_books = new JButton(resourceBundle.getString("Available_booksTab.btn.Show_all_available_books")); 
	private JPanel panel;
	private JLabel available_books_per_page = new JLabel(resourceBundle.getString("Available_booksTab.lbl.Records_per_page")); 
	private JButton borrow_book = new JButton(resourceBundle.getString("Available_booksTab.btn.Borrow_selected_book")); 
	private JButton prev = new JButton(resourceBundle.getString("Available_booksTab.btn.PREW")); 
	private JButton next = new JButton(resourceBundle.getString("Available_booksTab.btn.NEXT")); 
	private String[] columns_available_books = {"ID", "State", "Identifier", "Title", "Publisher"};
	private JTextField offset_txt = new JTextField();
	private JTable table = new JTable();
	private JScrollPane scroll = new JScrollPane(table);
	private DefaultTableModel model= new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public Available_booksTab(JPanel available_books_panel) {
		this.panel = available_books_panel;
		
		//tabulka pre Available Books
		table.setModel(model);
		table.setBackground(new Color(240,248,255));
		table.setFont(new Font("Segoe", Font.BOLD, 20));
		table.setRowHeight(40);
		scroll.setBounds(280, 19, 2198, 1233);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		model.setColumnIdentifiers(columns_available_books);
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(1);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		panel.add(scroll);
				
		//nastavovanie pozicii pre komponenty
		all_available_books.setBounds(26, 28, 209, 41);
		available_books_per_page.setBounds(26, 86, 197, 33);
		prev.setBounds(280, 1270, 120, 35);		
		next.setBounds(2357, 1270, 120, 35);
		borrow_book.setBounds(26, 183, 209, 41);
		langSK.setBounds(26, 300, 60, 35);
		langEN.setBounds(106, 300, 60, 35);
		
		//nastavovanie pisma pre komponenty
		all_available_books.setFont(new Font("Sans Serif", Font.PLAIN, 17));
		available_books_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18)); 
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		borrow_book.setFont(new Font("Sans Serif", Font.PLAIN, 18)); 
		langSK.setActionCommand("setLangSK");
		langEN.setActionCommand("setLangEN");
		
		//nastavenie funkcionality jazykovych zmien
		langSK.addActionListener(e -> setLanguage(e));
		langEN.addActionListener(e -> setLanguage(e));
		
		//viditelnost pre buttony
		prev.setEnabled(false);
		next.setEnabled(false);
		borrow_book.setEnabled(false);
		
		//pridavanie na panel
		panel.add(all_available_books);
		panel.add(available_books_per_page);
		panel.add(next);
		panel.add(prev);
		panel.add(borrow_book);
		panel.add(langSK);
		panel.add(langEN);
		
		//textfield pre zadanie offsetu
		offset_txt.setText("30"); 
		offset_txt.setBounds(26, 116, 120, 39);
		offset_txt.setColumns(10);
		offset_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		panel.add(offset_txt);
		
		all_available_books.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});
		
		next.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});
		
		prev.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent e) { 
		            
		        }
		});
		
		borrow_book.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
					
			}
		});
	}
	
	private void setLanguage(ActionEvent a){
		String actionCommand = a.getActionCommand();
		if (actionCommand.equals(langSK.getActionCommand())){
			Locale.setDefault(new Locale("sk","SK"));
		}
		if (actionCommand.equals(langEN.getActionCommand())){
			Locale.setDefault(new Locale("en","EN"));
		}
		resourceBundle = ResourceBundle.getBundle("messages");
		all_available_books.setText(resourceBundle.getString("Available_booksTab.btn.Show_all_available_books")); 
		available_books_per_page.setText(resourceBundle.getString("Available_booksTab.lbl.Records_per_page")); 
		borrow_book.setText(resourceBundle.getString("Available_booksTab.btn.Borrow_selected_book")); 
		prev.setText(resourceBundle.getString("Available_booksTab.btn.PREW")); 
		next.setText(resourceBundle.getString("Available_booksTab.btn.NEXT")); 
		table.getColumnModel().getColumn(1).setHeaderValue(resourceBundle.getString("Available_booksTab.clmn.State"));
		table.getColumnModel().getColumn(2).setHeaderValue(resourceBundle.getString("Available_booksTab.clmn.Identifier"));
		table.getColumnModel().getColumn(3).setHeaderValue(resourceBundle.getString("Available_booksTab.clmn.Title"));
		table.getColumnModel().getColumn(4).setHeaderValue(resourceBundle.getString("Available_booksTab.clmn.Publisher"));
		table.getTableHeader().repaint();
	}
	
}
