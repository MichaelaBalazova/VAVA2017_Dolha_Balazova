package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class List_of_borrowed_booksWindow extends JFrame{
	
	private JPanel panel = new JPanel();
	private JTable table  = new JTable();
	private JScrollPane scroll = new JScrollPane(table);
	private JEditorPane txt = new JEditorPane("text/html", "");
	private String[] columns = {"ID", "Book title", "Publisher", "Borrowed_from", "Borrowed_to", "State", "Borrowed By" };
	private DefaultTableModel model = new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public List_of_borrowed_booksWindow(int member_id, String first_name, String last_name) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1900, 650);
		setTitle("List of borrowed books of a member");
		setResizable(false);
		this.setLocationRelativeTo(null);
		panel.setBackground(new Color(204,229,255));
		setContentPane(panel);
		panel.setLayout(null);
		
		//tabulka pre Borrowed Books
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setBackground(new Color(240,248,255));
		table.setFont(new Font("Sans Serif", Font.BOLD, 20));
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20));
		table.setRowHeight(40);
		scroll.setBounds(35, 70, 1830, 500);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(5).setPreferredWidth(1);
		
		txt.setBounds(35, 7, 800, 50);
		txt.setEditable(false);
		txt.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
		txt.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		txt.setBackground(new Color(204,229,255));
		panel.add(txt);
		
		txt.setText("<b>Member ID:</b> " + member_id + "<br>" + first_name + " " + last_name ); 
		
	}
}
