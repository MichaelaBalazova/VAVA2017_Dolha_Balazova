package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class MembersTab {
	
	private JButton all_members = new JButton("Show all members");
	private JButton select_num_borrowed = new JButton("Filter Records");
	private JPanel panel;
	private JButton prev = new JButton("PREV");
	private JButton next = new JButton("NEXT");
	private JButton change_person = new JButton("Update selected person");
	private String[] columns_members = {"ID", "First Name", "Second Name", "Birthday", "Email", "Telephone", "Address","Member from", "Borrowed Books" };
	private JTextField offset_txt;
	private JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
	private JLabel filter1 = new JLabel("Filter for borrowed books");
	private JLabel filter2 = new JLabel("and date of membership");
	private JLabel filter3 = new JLabel("Select number of borrowed books");
	private JLabel filter4 = new JLabel("Select date 'Member from'");
	private JLabel change_label = new JLabel("Change personal info of person");
	private JTextField find = new JTextField();
	private JLabel find_l = new JLabel("Find a person in table");
	private JButton person_borrowed = new JButton("Show list of borrowed books");
	private JTable table = new JTable();
	private JScrollPane scroll = new JScrollPane(table);
	private DefaultTableModel model= new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public MembersTab(JPanel members_panel) {
		this.panel = members_panel;
		
		table.setModel(model);
		table.setBackground(new Color(240,248,255));
		table.setFont(new Font("Segoe", Font.BOLD, 20));
		table.setRowHeight(40);
		scroll.setBounds(280, 19, 2198, 1233);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20));
		model.setColumnIdentifiers(columns_members);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setPreferredWidth(1);
		panel.add(scroll);
		
		String[] options = { "---", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };		
		JComboBox comboBox = new JComboBox(options);
		comboBox.setBounds(26, 260, 209, 39);
		comboBox.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(comboBox);
		
		all_members.setBounds(26, 28, 209, 41);
		all_members.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(all_members);
		
		select_num_borrowed.setBounds(26, 375, 210, 41);
		select_num_borrowed.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(select_num_borrowed);
		
		offset_txt = new JTextField();
		offset_txt.setText("30");
		offset_txt.setBounds(26, 116, 120, 39);
		offset_txt.setColumns(10);
		offset_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(offset_txt);
		
		JLabel lblPer2 = new JLabel("Members Per Page");
		lblPer2.setBounds(26, 86, 197, 33);
		lblPer2.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(lblPer2);
		prev.setBounds(280, 1270, 120, 35);		
		next.setBounds(2357, 1270, 120, 35);	
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		prev.setEnabled(false);
		next.setEnabled(false);
		separator1.setBounds(10, 183, 260, 20);
		separator2.setBounds(10, 450, 260, 20);
		separator3.setBounds(10, 545, 260, 20);
		change_label.setBounds(26, 453, 230, 22);
		change_label.setFont(new Font("Sans Serif", Font.BOLD, 15));
		change_person.setBounds(26, 485, 210, 35);
		filter1.setBounds(26, 187, 220, 22);
		filter2.setBounds(26, 202, 200, 22);
		filter3.setBounds(26, 240, 220, 22);
		filter4.setBounds(26, 310, 200, 22);
		filter1.setFont(new Font("Sans Serif", Font.BOLD, 16));
		filter2.setFont(new Font("Sans Serif", Font.BOLD, 16));
		filter3.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		filter4.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		person_borrowed.setBounds(26, 560, 210, 35);
		
		change_person.setEnabled(false);
		person_borrowed.setEnabled(false);
		find.setEditable(false);
		change_person.setFont(new Font("Sans Serif", Font.PLAIN, 17));
		person_borrowed.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		find.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(next);
		panel.add(prev);
		panel.add(separator1);
		panel.add(separator2);
		panel.add(separator3);
		panel.add(filter1);
		panel.add(filter2);
		panel.add(filter3);
		panel.add(filter4);
		panel.add(change_person);
		panel.add(change_label);
		panel.add(person_borrowed);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		
		datePicker.setBounds(26, 330, 120, 45);
		panel.add(datePicker);
		
		find_l.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		find_l.setBounds(26, 640, 220, 20);
		find.setBounds(26, 660, 200, 35);
		panel.add(find);
		panel.add(find_l);
		
		all_members.addActionListener(new ActionListener() { 
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
		
		select_num_borrowed.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
		    	} 
		});
		
		change_person.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 					
				}
		});
		
		person_borrowed.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				}
		});
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent e) { 
		            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		            change_person.setEnabled(!lsm.isSelectionEmpty());
		            person_borrowed.setEnabled(!lsm.isSelectionEmpty());
		        }
		});
		
	}
	
}
