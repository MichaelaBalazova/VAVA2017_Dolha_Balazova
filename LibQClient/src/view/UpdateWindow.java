package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;


public class UpdateWindow extends JFrame {

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	private JPanel panel = new JPanel();
	private JButton update = new JButton(resourceBundle.getString("UpdateWindow.btn.Update"));
	public JTextField id = new JTextField();
	public JTextField first_name = new JTextField();
	public JTextField last_name = new JTextField();
	public JTextField email = new JTextField();
	public JTextField telephone = new JTextField();
	public JTextField address = new JTextField();
	private JLabel id_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Id_l"));
	private JLabel first_name_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.First_name_l"));
	private JLabel last_name_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Last_name_l"));
	private JLabel birthday_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Birthday_l"));
	private JLabel email_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Email_l"));
	private JLabel telephone_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Telephone_l"));
	private JLabel address_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Address_l"));
	private JLabel member_from_l = new JLabel(resourceBundle.getString("UpdateWindow.lbl.Member_from_l"));
	private JDatePickerImpl datePicker_birthday;
	private JDatePickerImpl datePicker_member_from;

	public UpdateWindow() {		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 700, 750);
		setTitle("Update a member");
		setResizable(false);
		this.setLocationRelativeTo(null);
		panel.setBackground(new Color(204,229,255));
		setContentPane(panel);
		panel.setLayout(null);
				
		id_l.setBounds(50, 20, 130, 20);
		id_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		id.setBounds(50, 50, 80, 30);
		id.setEditable(false);
		id.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(id);
		panel.add(id_l);
		
		first_name_l.setBounds(50, 100, 200, 20);
		first_name_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		first_name.setBounds(50, 130, 142, 30);
		first_name.setEditable(true);
		first_name.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(first_name);
		panel.add(first_name_l);
		
		last_name_l.setBounds(50, 180, 200, 20);
		last_name_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		last_name.setBounds(50, 210, 142, 30);
		last_name.setEditable(true);
		last_name.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(last_name);
		panel.add(last_name_l);
		
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
		datePicker_birthday = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
		
		birthday_l.setBounds(50, 260, 200, 20);
		birthday_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		panel.add(birthday_l);
		datePicker_birthday.setBounds(50, 290, 142, 30);
		panel.add(datePicker_birthday);
		
		email_l.setBounds(50, 340, 200, 20);
		email_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		email.setBounds(50, 370, 350, 30);
		email.setEditable(true);
		email.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(email);
		panel.add(email_l);
		
		telephone_l.setBounds(50, 420, 200, 20);
		telephone_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		telephone.setBounds(50, 450, 170, 30);
		telephone.setEditable(true);
		telephone.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(telephone);
		panel.add(telephone_l);
		
		address.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		address.setEditable(true);
		address.setBounds(50, 530, 350, 30);		
		panel.add(address);		
		address_l.setBounds(50, 500, 200, 20);
		address_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		panel.add(address_l);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
		datePicker_member_from = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
		
		member_from_l.setBounds(50, 580, 250, 20);
		member_from_l.setFont(new Font("Sans Serif", Font.BOLD, 19));
		panel.add(member_from_l);
		datePicker_member_from.setBounds(50, 610, 142, 30);
		panel.add(datePicker_member_from);
		
		update.setBounds(426, 29, 242, 40);
		update.setFont(new Font("Sans Serif", Font.BOLD, 19));
		panel.add(update);

	}
		
	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JTextField getFirst_name() {
		return first_name;
	}

	public void setFirst_name(JTextField first_name) {
		this.first_name = first_name;
	}

	public JTextField getLast_name() {
		return last_name;
	}

	public void setLast_name(JTextField last_name) {
		this.last_name = last_name;
	}

	public JTextField getEmail() {
		return email;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JTextField getTelephone() {
		return telephone;
	}

	public void setTelephone(JTextField telephone) {
		this.telephone = telephone;
	}
	
	public JDatePickerImpl getDatePicker_birthday() {
		return datePicker_birthday;
	}

	public void setDatePicker_birthday(JDatePickerImpl datePicker_birthday) {
		this.datePicker_birthday = datePicker_birthday;
	}

	public JDatePickerImpl getDatePicker_member_from() {
		return datePicker_member_from;
	}

	public void setDatePicker_member_from(JDatePickerImpl datePicker_member_from) {
		this.datePicker_member_from = datePicker_member_from;
	}
	
	public JTextField getAddress() {
		return address;
	}

	public void setAddress(JTextField address) {
		this.address = address;
	}
}
