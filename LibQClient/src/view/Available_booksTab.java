package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private JButton all_available_books = new JButton("Show all available books");
	private JPanel panel;
	private JLabel available_books_per_page = new JLabel("Records Per Page");
	private JButton borrow_book = new JButton("Borrow selected book");
	private JButton prev = new JButton("PREV");
	private JButton next = new JButton("NEXT");
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
		
		//nastavovanie pisma pre komponenty
		all_available_books.setFont(new Font("Sans Serif", Font.PLAIN, 17));
		available_books_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		borrow_book.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		
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
	
}
