package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.undo.CompoundEdit;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class MembersTab {
	
	private static Logger LOG = Logger.getLogger(MembersTab.class.getName());
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	private ImageIcon imgSK = new ImageIcon("img/sk-flag.png");
	private ImageIcon imgEN = new ImageIcon("img/en-flag.png");
	private JButton langSK = new JButton(imgSK);
	private JButton langEN = new JButton(imgEN);
	private int offset = 0;
	private JCheckBox checkbox = new JCheckBox("Enable filter");
	private JButton all_members = new JButton("Show all members");
	private JButton filter_records = new JButton("Filter Records");
	private JLabel members_per_page = new JLabel("Members Per Page");
	private JPanel panel;
	private JButton prev = new JButton("PREV");
	private JButton next = new JButton("NEXT");
	private JButton change_person = new JButton("Update selected person");
	private String[] columns_members = {"ID", "First Name", "Second Name", "Birthday", "Email", "Telephone", "Address","Member from", "Borrowed Books" };
	private JTextField limit_txt = new JTextField();;
	private JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
	private JLabel filter1 = new JLabel("Filter for borrowed books");
	private JLabel filter2 = new JLabel("and date of membership");
	private JLabel filter3 = new JLabel("Select number of borrowed books");
	private JLabel change_label = new JLabel("Change personal info of person");
	private JTextField find = new JTextField();
	private JLabel find_l = new JLabel("Find a person in table");
	private JButton person_borrowed = new JButton("Show list of borrowed books");
	private JTable table = new JTable();
	private JScrollPane scroll = new JScrollPane(table);
	private String[] options = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };		
	private JComboBox comboBox = new JComboBox(options);
	private DefaultTableModel model= new DefaultTableModel(){
		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	};

	public MembersTab(JPanel members_panel) {
		this.panel = members_panel;
		
		//nastavenie tabulky s Members 
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
		
		//combobox pre filtrovanie podla poctu pozicanych knih
		comboBox.setBounds(26, 310, 209, 39);
		comboBox.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		panel.add(comboBox);
		
		//checkbox pre filtrovanie zaznamov
		checkbox.setBounds(50, 240, 150, 30);
		checkbox.setFont(new Font("Sans Serif", Font.PLAIN, 20));;
		
		//textfield pre zadavanie offsetu
		limit_txt.setText("30");
		limit_txt.setBounds(26, 116, 120, 39);
		limit_txt.setColumns(10);
		limit_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		
		//nastavovanie pozicii komponentov
		all_members.setBounds(26, 28, 209, 41);
		members_per_page.setBounds(26, 86, 197, 33);
		filter_records.setBounds(26, 375, 210, 41);
		prev.setBounds(280, 1270, 200, 35);		
		next.setBounds(2277, 1270, 200, 35);
		separator1.setBounds(10, 183, 260, 20);
		separator2.setBounds(10, 435, 260, 20);
		separator3.setBounds(10, 525, 260, 20);
		change_person.setBounds(26, 470, 210, 35);
		//filter1.setBounds(26, 187, 220, 22);
		filter1.setBounds(26, 187, 220, 22);
	    filter2.setBounds(26, 202, 200, 22);
	  	filter3.setBounds(26, 285, 220, 22);
	  	person_borrowed.setBounds(26, 545, 210, 35);
	  	change_label.setBounds(26, 438, 230, 22);
	  	find_l.setBounds(26, 625, 220, 20);
	  	find.setBounds(26, 645, 200, 35);
		langSK.setBounds(26, 300, 60, 35);
		langEN.setBounds(106, 300, 60, 35);
		
		//nastavovanie pisma komponentov
		filter_records.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		all_members.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		change_person.setFont(new Font("Sans Serif", Font.PLAIN, 17));
		person_borrowed.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		find.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		change_label.setFont(new Font("Sans Serif", Font.BOLD, 15));
		filter1.setFont(new Font("Sans Serif", Font.BOLD, 16));
		filter2.setFont(new Font("Sans Serif", Font.BOLD, 16));
		filter3.setFont(new Font("Sans Serif", Font.PLAIN, 14));
		members_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		find_l.setFont(new Font("Sans Serif", Font.PLAIN, 18));
		langSK.setActionCommand("setLangSK");
		langEN.setActionCommand("setLangEN");
		
		//nastavenie funkcionality jazykovych zmien	
		langSK.addActionListener(e -> setLanguage(e));
		langEN.addActionListener(e -> setLanguage(e));
		
		//vizibilita komponentov
		prev.setEnabled(false);
		next.setEnabled(false);		
		change_person.setEnabled(false);
		person_borrowed.setEnabled(false);
		find.setEditable(false);
		filter_records.setEnabled(false);
		comboBox.setEnabled(false);
		
		//pridavanie komponentov na panel
		panel.add(members_per_page);
		panel.add(next);
		panel.add(prev);
		panel.add(separator1);
		panel.add(separator2);
		panel.add(separator3);
		panel.add(filter1);
		panel.add(filter2);
		panel.add(filter3);
		panel.add(change_person);
		panel.add(change_label);
		panel.add(person_borrowed);
		panel.add(filter_records);
		panel.add(all_members);
		panel.add(limit_txt);		
		panel.add(find);
		panel.add(find_l);
		panel.add(checkbox);
		panel.add(langSK);
		panel.add(langEN);
			
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
		            //change_person.setEnabled(!lsm.isSelectionEmpty());
		            person_borrowed.setEnabled(!lsm.isSelectionEmpty());
		        }
		});
		
		checkbox.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(checkbox.isSelected()){
					filter_records.setEnabled(true);
					comboBox.setEnabled(true);
					all_members.setEnabled(false);
				}
				else {
					filter_records.setEnabled(false);
					comboBox.setEnabled(false);
					all_members.setEnabled(true);
				}
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
		all_members.setText(resourceBundle.getString("MembersTab.btn.All_members"));
		filter_records.setText(resourceBundle.getString("MembersTab.btn.Filter_records"));
		members_per_page.setText(resourceBundle.getString("MembersTab.lbl.Members_Per_Page"));
		change_person.setText(resourceBundle.getString("MembersTab.btn.Change_person"));
		prev.setText(resourceBundle.getString("MembersTab.btn.PREV"));
		next.setText(resourceBundle.getString("MembersTab.btn.NEXT"));
		table.getColumnModel().getColumn(1).setHeaderValue(resourceBundle.getString("MembersTab.clmn.First_name"));
		table.getColumnModel().getColumn(2).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Second_name"));
		table.getColumnModel().getColumn(3).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Birthday"));
		table.getColumnModel().getColumn(4).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Email"));
		table.getColumnModel().getColumn(5).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Telephone"));
		table.getColumnModel().getColumn(6).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Address"));
		table.getColumnModel().getColumn(7).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Member_from"));
		table.getColumnModel().getColumn(8).setHeaderValue(resourceBundle.getString("MembersTab.clmn.Borrowed_books"));
		table.getTableHeader().repaint();
		filter1.setText(resourceBundle.getString("MembersTab.lbl.Filter1"));
		filter2.setText(resourceBundle.getString("MembersTab.lbl.Filter2"));
		filter3.setText(resourceBundle.getString("MembersTab.lbl.Filter3"));
		change_label.setText(resourceBundle.getString("MembersTab.lbl.Change_label"));
		find_l.setText(resourceBundle.getString("MembersTab.lbl.Find_l"));
		person_borrowed.setText(resourceBundle.getString("MembersTab.btn.Person_borrowed"));
	}
	
	public void addActions(ActionListener showAllMembers, ActionListener prevMembers, ActionListener nextMembers, 
			ActionListener memberListOfBorrowedBooks){
		all_members.addActionListener(showAllMembers);
		filter_records.addActionListener(showAllMembers);
		prev.addActionListener(prevMembers);
		next.addActionListener(nextMembers);
		person_borrowed.addActionListener(memberListOfBorrowedBooks);
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
			limit = Integer.parseInt(limit_txt.getText());
			if (limit < 1){
				JOptionPane.showMessageDialog(null,"The number is invalid","Error",JOptionPane.ERROR_MESSAGE);
				return -1;
			}
		}
		catch(NumberFormatException e){
			LOG.severe("Error: "+e);			
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
	
	public int getSelectedRowMember_id(){
		int selected_row = (int) this.table.getValueAt(table.getSelectedRow(), 0);
		return selected_row;
	}
	
	public String getSelectedRowFirstname(){
		return this.table.getValueAt(table.getSelectedRow(), 1).toString();
	}
	
	public String getSelectedRowSecondname(){
		return this.table.getValueAt(table.getSelectedRow(), 2).toString();
	}
	
	public boolean isCheckBoxSelected(){
		if (checkbox.isSelected()){
			return true;
		}
		else return false;
	}
	
	public int getSelectedNum(){
		return Integer.parseInt((String)comboBox.getSelectedItem());
	}
}
