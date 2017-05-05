package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

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

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", new Locale("sk","SK")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton all_available_books = new JButton(resourceBundle.getString("Available_booksTab.btn.Show_all_available_books")); //$NON-NLS-1$
	private JPanel panel;
	private JLabel available_books_per_page = new JLabel(resourceBundle.getString("Available_booksTab.lbl.Records_per_page")); //$NON-NLS-1$
	private JButton borrow_book = new JButton(resourceBundle.getString("Available_booksTab.btn.Borrow_selected_book")); //$NON-NLS-1$
	private JButton prev = new JButton(resourceBundle.getString("Available_booksTab.btn.PREW")); //$NON-NLS-1$
	private JButton next = new JButton(resourceBundle.getString("Available_booksTab.btn.NEXT")); //$NON-NLS-1$
	private String[] columns_available_books = {"ID", "State", "Identifier", "Title", "Publisher"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
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
		table.setFont(new Font("Segoe", Font.BOLD, 20)); //$NON-NLS-1$
		table.setRowHeight(40);
		scroll.setBounds(280, 19, 2198, 1233);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		model.setColumnIdentifiers(columns_available_books);
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
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
		
		//nastavovanie pisma pre komponenty
		all_available_books.setFont(new Font("Sans Serif", Font.PLAIN, 17)); //$NON-NLS-1$
		available_books_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18)); //$NON-NLS-1$
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
		borrow_book.setFont(new Font("Sans Serif", Font.PLAIN, 18)); //$NON-NLS-1$
		
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
		
		//textfield pre zadanie offsetu
		offset_txt.setText("30"); //$NON-NLS-1$
		offset_txt.setBounds(26, 116, 120, 39);
		offset_txt.setColumns(10);
		offset_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20)); //$NON-NLS-1$
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
	
}
