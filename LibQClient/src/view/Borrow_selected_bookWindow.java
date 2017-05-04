package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class Borrow_selected_bookWindow extends JFrame{

	private JPanel panel = new JPanel();
	private JButton find_member = new JButton("Find a member");
	private JDatePickerImpl datePicker_from;
	private JDatePickerImpl datePicker_to;
	private JLabel label_from = new JLabel("Borrowed from");
	private JLabel label_to = new JLabel("Borrowed to");
	private JTextField member = new JTextField();
	private JTextField employee = new JTextField();
	private JTable table_members  = new JTable();
	private JTable table_employees  = new JTable();
	private String[] columns_members = {"ID", "First Name", "Second Name", "Birthday" };
	private String[] columns_employees = {"ID", "First Name", "Second Name", "Birthday", "Job description" };
	private TableRowSorter<TableModel> rowSorter_employees;
	private JScrollPane scroll_members = new JScrollPane(table_members);
	private JScrollPane scroll_employees = new JScrollPane(table_employees);
	private JButton commit = new JButton("Borrow a book!");
	private DefaultTableModel model_members = new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};
	private DefaultTableModel model_employees = new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};;
	
	public Borrow_selected_bookWindow(int available_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1750, 1350);
		setTitle("Borrow selected book");
		setResizable(false);
		this.setLocationRelativeTo(null);
		panel.setBackground(new Color(204,229,255));
		setContentPane(panel);
		panel.setLayout(null);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
		datePicker_from = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
		
		datePicker_from.setBounds(35, 50, 142, 30);
		panel.add(datePicker_from);
		label_from.setBounds(35, 20, 142, 30);
		panel.add(label_from);
		
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		datePicker_to = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
		
		datePicker_to.setBounds(235, 50, 142, 30);
		panel.add(datePicker_to);
		label_to.setBounds(235, 20, 142, 30);
		label_to.setFont(new Font("Sans Serif", Font.BOLD, 20));
		label_from.setFont(new Font("Sans Serif", Font.BOLD, 20));
		panel.add(label_to);
		
		model_members.setColumnIdentifiers(columns_members);
		model_employees.setColumnIdentifiers(columns_employees);
		
		table_members.setModel(model_members);
		table_members.setBackground(new Color(240,248,255));
		table_members.setFont(new Font("Sans Serif", Font.BOLD, 20));
		table_members.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20));
		table_members.setRowHeight(40);
		scroll_members.setBounds(35, 150, 760, 1100);
		scroll_members.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_members.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll_members);
		table_members.getColumnModel().getColumn(0).setPreferredWidth(1);
		
		table_employees.setModel(model_employees);
		table_employees.setBackground(new Color(240,248,255));
		table_employees.setFont(new Font("Sans Serif", Font.BOLD, 20));
		table_employees.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20));
		table_employees.setRowHeight(40);
		scroll_employees.setBounds(815, 150, 900, 1100);
		scroll_employees.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll_employees.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll_employees);
		table_employees.getColumnModel().getColumn(0).setPreferredWidth(1);
		
		member.setBounds(35, 100, 250, 30);
		employee.setBounds(815, 100, 250, 30);
		member.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		employee.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		commit.setFont(new Font("Sans Serif", Font.BOLD, 20));
		commit.setBounds(1500, 40, 200, 35);
		panel.add(member);
		panel.add(employee);
		panel.add(commit);
		find_member.setBounds(595, 100, 200, 30);
		find_member.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(find_member);
		
		find_member.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});				
		
		TableRowSorter<TableModel> rowSorter_employees = new TableRowSorter<>(table_employees.getModel());
		table_employees.setRowSorter(rowSorter_employees);
		add_filter(employee, rowSorter_employees);
		
		Calendar cal1 = Calendar.getInstance();
		datePicker_from.getModel().setDate(cal1.get(Calendar.YEAR), cal1.get(Calendar.MONTH), cal1.get(Calendar.DAY_OF_MONTH));
		datePicker_from.getModel().setSelected(true);
		
		Calendar cal2 = Calendar.getInstance();
		datePicker_to.getModel().setDate(cal2.get(Calendar.YEAR), cal2.get(Calendar.MONTH), cal2.get(Calendar.DAY_OF_MONTH) + 30);
		datePicker_to.getModel().setSelected(true);
		
		Date date_from = (Date) datePicker_from.getModel().getValue();
		Date date_to = (Date) datePicker_to.getModel().getValue();
		
		commit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});
		
	}
	
	private void add_filter(JTextField txtfld, TableRowSorter<TableModel> rowSorter) {
		txtfld.getDocument().addDocumentListener(new DocumentListener(){
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
	             if (txtfld.getText().trim().length() == 0) {
	                 rowSorter.setRowFilter(null);
	             } else {
	                 rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtfld.getText()));
	             }				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
                if (txtfld.getText().trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtfld.getText()));
                }
			}

        });
	}
}


