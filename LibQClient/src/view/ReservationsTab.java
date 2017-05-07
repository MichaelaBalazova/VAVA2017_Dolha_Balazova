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

public class ReservationsTab {

	private JButton all_reservations = new JButton("Show all reservations");
	private JButton delete_reservation = new JButton("Delete selected reservation");
	private JPanel panel;
	private JLabel reservations_per_page = new JLabel("Records Per Page");
	private JButton prev = new JButton("PREV");
	private JButton next = new JButton("NEXT");
	private String[] columns_reservations = {"ID", "Date from", "Date to", "Title", "Publisher", "Member ID", "Member name"};
	private JTextField offset_txt = new JTextField();
	private JTable table = new JTable();
	private JScrollPane scroll = new JScrollPane(table);
	private DefaultTableModel model= new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	
	public ReservationsTab(JPanel reservations_panel) {
		this.panel = reservations_panel;
		
		//tabulka pre Reservations
		table.setModel(model);
		table.setBackground(new Color(240,248,255));
		table.setFont(new Font("Segoe", Font.BOLD, 20));
		table.setRowHeight(40);
		scroll.setBounds(280, 19, 2198, 1233);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		model.setColumnIdentifiers(columns_reservations);
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20));
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(1);
		panel.add(scroll);
				
		//nastvenie pozicii pre komponenty
		reservations_per_page.setBounds(26, 86, 197, 33);
		all_reservations.setBounds(26, 28, 209, 41);
		prev.setBounds(280, 1270, 200, 35);		
		next.setBounds(2277, 1270, 200, 35);
		delete_reservation.setBounds(26, 183, 209, 41);
		
		//nastevenie pisma pre komponenty
		reservations_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		all_reservations.setFont(new Font("Sans Serif", Font.PLAIN, 18));	
		delete_reservation.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		
		//vizibilita buttonov
		prev.setEnabled(false);
		next.setEnabled(false);
		delete_reservation.setEnabled(false);
		
		//pridanie na panel
		panel.add(reservations_per_page);
		panel.add(all_reservations);
		panel.add(next);
		panel.add(prev);
		panel.add(delete_reservation);	
		
		//textfield pre offset
		offset_txt.setText("30");
		offset_txt.setBounds(26, 116, 120, 39);
		offset_txt.setColumns(10);
		offset_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(offset_txt);
		
		all_reservations.addActionListener(new ActionListener() { 
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
		            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		            delete_reservation.setEnabled(!lsm.isSelectionEmpty());
		        }
		});
		
		delete_reservation.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
		    } 
		});
	}
}
