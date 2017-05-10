package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class BooksTab {
	
	private static Logger LOG = Logger.getLogger(BooksTab.class.getName());
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	public int offset = 0;
	private ImageIcon imgSK = new ImageIcon("img/sk-flag.png");
	private ImageIcon imgEN = new ImageIcon("img/en-flag.png");
	private JButton langSK = new JButton(imgSK);
	private JButton langEN =  new JButton(imgEN);
	private JButton print_QR =  new JButton(resourceBundle.getString("BooksTab.btn.Print_QR"));
	private JButton all_books =  new JButton(resourceBundle.getString("BooksTab.btn.Show_all_books")); 
	private JButton detail_books = new JButton(resourceBundle.getString("BooksTab.btn.Detail_of_selected_book"));
	private JLabel books_per_page = new JLabel (resourceBundle.getString("BooksTab.lbl.Books_per_page"));
	private JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separator2 = new JSeparator(SwingConstants.HORIZONTAL);
	private JSeparator separator3 = new JSeparator(SwingConstants.HORIZONTAL);
	private JLabel l_filter_by_author =  new JLabel(resourceBundle.getString("BooksTab.lbl.L_filter_by_author"));
	private JLabel l_filter_by_genre = new JLabel(resourceBundle.getString("BooksTab.lbl.L_filter_by_genre"));
	private JLabel l_filter_by_publication_date = new JLabel(resourceBundle.getString("BooksTab.lbl.L_filter_by_publication_date"));
	private JButton filter_by_author = new JButton(resourceBundle.getString("BooksTab.btn.Filter_by_author"));
	private JButton filter_by_genre = new JButton(resourceBundle.getString("BooksTab.btn.Filter_by_genre"));
	private JButton filter_by_publication_date = new JButton(resourceBundle.getString("BooksTab.btn.Filter_by_publication_date"));
	private JCheckBox check_filter_by_author = new JCheckBox();
	private JCheckBox check_filter_by_genre = new JCheckBox();
	private JCheckBox check_publication_date =  new JCheckBox();
	private JTextField text_filter_by_author = new JTextField();
	private JComboBox text_filter_by_genre = new JComboBox();
	private JDatePickerImpl datePicker;
	private JPanel panel;
	private JButton prev = new JButton(resourceBundle.getString("BooksTab.btn.PREV"));
	private JButton next = new JButton(resourceBundle.getString("BooksTab.btn.NEXT"));
	private String[] columns_books = {"ID", 
			resourceBundle.getString("BooksTab.clmn.Title"), 
			resourceBundle.getString("BooksTab.clmn.Publisher"), 
			resourceBundle.getString("BooksTab.clmn.Pages"), 
			resourceBundle.getString("BooksTab.clmn.Pieces"), 
			resourceBundle.getString("BooksTab.clmn.EAN_Code"), 
			resourceBundle.getString("BooksTab.clmn.Book_genre")}; 
	private JTextField limit_txt = new JTextField();;
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
		table.setFont(new Font("Segoe", Font.BOLD, 20)); 
		table.setRowHeight(40);
		scroll.setBounds(280, 19, 2198, 1233);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);	
		table.getTableHeader().setFont(new Font("Sans Serif", Font.PLAIN, 20));
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
		prev.setBounds(280, 1270, 200, 35);		
		next.setBounds(2277, 1270, 200, 35);
		print_QR.setBounds(26, 250, 209, 41);
		separator1.setBounds(10, 320, 260, 41);
		check_filter_by_author.setBounds(26, 330, 30, 30);
		l_filter_by_author.setBounds(60, 330, 200, 30);
		text_filter_by_author.setBounds(26, 365, 180, 30);
		filter_by_author.setBounds(26, 405, 120, 30);
		separator2.setBounds(10, 460, 260, 41);
		check_filter_by_genre.setBounds(26, 470, 30, 30);
		l_filter_by_genre .setBounds(60, 470, 200, 30);
		text_filter_by_genre.setBounds(26, 505, 180, 30);
		filter_by_genre.setBounds(26, 545, 120, 30);
		separator3.setBounds(10, 590, 260, 41);
		check_publication_date.setBounds(26, 600, 30, 30);
		l_filter_by_publication_date.setBounds(60, 600, 200, 30);
		filter_by_publication_date.setBounds(26, 675, 120, 30);
		langSK.setBounds(26, 800, 60, 35);
		langEN.setBounds(106, 800, 60, 35);
		
		//nastavovanie pisma pre komponenty
		all_books.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		detail_books.setFont(new Font("Sans Serif", Font.PLAIN, 16)); 
		print_QR.setFont(new Font("Sans Serif", Font.PLAIN, 16)); 
		books_per_page.setFont(new Font("Sans Serif", Font.PLAIN, 18)); 
		prev.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		next.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		langSK.setActionCommand("setLangSK");
		langEN.setActionCommand("setLangEN");
		
		//nastavenie funkcionality jazykovych zmien
		langSK.addActionListener(e -> setLanguage(e));
		langEN.addActionListener(e -> setLanguage(e));
		
		//vizibilita pre strankovacie buttony
		prev.setEnabled(false);
		next.setEnabled(false);
		detail_books.setEnabled(false);
		print_QR.setEnabled(false);
		filter_by_author.setEnabled(false);
		filter_by_genre.setEnabled(false);
		filter_by_publication_date.setEnabled(false);
		text_filter_by_author.setEditable(false);
		text_filter_by_genre.setEditable(false);

		//pridavanie komponentov na panel
		panel.add(next);
		panel.add(prev);
		panel.add(all_books);
		panel.add(detail_books);
		panel.add(print_QR);
		panel.add(langSK);
		panel.add(langEN);
		panel.add(books_per_page);
		panel.add(separator1);
		panel.add(check_filter_by_author);
		panel.add(l_filter_by_author);
		panel.add(text_filter_by_author);
		panel.add(filter_by_author);
		panel.add(separator2);
		panel.add(check_filter_by_genre);
		panel.add(l_filter_by_genre);
		panel.add(text_filter_by_genre);
		panel.add(filter_by_genre);
		panel.add(separator3);
		panel.add(check_publication_date);
		panel.add(l_filter_by_publication_date);
		panel.add(filter_by_publication_date);
		
		limit_txt.setText("30"); 
		limit_txt.setBounds(26, 116, 120, 39);
		limit_txt.setColumns(10);
		limit_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		panel.add(limit_txt);
		
		UtilDateModel model1 = new UtilDateModel();
		Properties p1 = new Properties();
		p1.put("text.today", "Today");
		p1.put("text.month", "Month");
		p1.put("text.year", "Year");
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p1);
		datePicker = new JDatePickerImpl(datePanel1, new DateComponentFormatter());
		
		datePicker.setBounds(26, 635, 120, 30);
		panel.add(datePicker);
		
				
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent e) { 
		            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		            detail_books.setEnabled(!lsm.isSelectionEmpty());
		            print_QR.setEnabled(!lsm.isSelectionEmpty());
		        }
		});
		
		check_filter_by_author.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(check_filter_by_author.isSelected()){
					check_publication_date.setSelected(false);
					check_filter_by_genre.setSelected(false);
					filter_by_author.setEnabled(true);
					text_filter_by_author.setEditable(true);	
					all_books.setEnabled(false);
					filter_by_genre.setEnabled(false);
					filter_by_publication_date.setEnabled(false);
				}
				else {
					filter_by_author.setEnabled(false);
					text_filter_by_author.setEditable(false);				
					all_books.setEnabled(true);
					filter_by_genre.setEnabled(false);
					filter_by_publication_date.setEnabled(false);
				}
			}
		});
		check_filter_by_genre.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(check_filter_by_genre.isSelected()){
					check_filter_by_author.setSelected(false);
					check_publication_date.setSelected(false);
					filter_by_author.setEnabled(false);
					all_books.setEnabled(false);
					filter_by_genre.setEnabled(true);
					text_filter_by_genre.setEditable(true);
					filter_by_publication_date.setEnabled(false);
				}
				else {
					filter_by_author.setEnabled(false);
					all_books.setEnabled(true);
					text_filter_by_genre.setEditable(false);
					filter_by_genre.setEnabled(false);
					filter_by_publication_date.setEnabled(false);
				}
			}
		});
		check_publication_date.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(check_publication_date.isSelected()){
					check_filter_by_genre.setSelected(false);
					check_filter_by_author.setSelected(false);
					filter_by_author.setEnabled(false);
					all_books.setEnabled(false);
					filter_by_genre.setEnabled(false);
					filter_by_publication_date.setEnabled(true);
				}
				else {
					filter_by_author.setEnabled(false);
					all_books.setEnabled(true);
					filter_by_genre.setEnabled(false);
					filter_by_publication_date.setEnabled(false);
				}
			}
		});
		
	}
	
	public void addActions(ActionListener showAllBooks, ActionListener prevBooks, ActionListener nextBooks,
			ActionListener findAuthor, ActionListener findGenre, ActionListener findPublicationDate,
			ActionListener detailBook, ActionListener qrcode){
		all_books.addActionListener(showAllBooks);
		prev.addActionListener(prevBooks);
		next.addActionListener(nextBooks);
		filter_by_author.addActionListener(findAuthor);
		filter_by_genre.addActionListener(findGenre);
		filter_by_publication_date.addActionListener(findPublicationDate);
		detail_books.addActionListener(detailBook);
		print_QR.addActionListener(qrcode);
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
	
	public String getFindAuthorText(){
		return this.text_filter_by_author.getText();
	}
	
	public void addComboBoxItem(String item){
		this.text_filter_by_genre.addItem(makeObj(item));
	}
	
	private Object makeObj(final String item)  {
	     return new Object() { public String toString() { return item; } };
	}
	
	public String getSelectedGenre(){
		return this.text_filter_by_genre.getSelectedItem().toString();
	}
	
	public Date getPublicationDate(){
		return (Date) this.datePicker.getModel().getValue();
	}
	
	public int getSelectedBook(){
		int selected_row = (int) this.table.getValueAt(table.getSelectedRow(), 0);
		return selected_row;
	}
	
	public boolean isAuthorFilterSelected(){
		if(check_filter_by_author.isSelected()){
			return true;
		}
		else return false;
	}
	
	public boolean isGenreFilterSelected(){
		if(check_filter_by_genre.isSelected()){
			return true;
		}
		else return false;
	}
	
	public boolean isDateFilterSelected(){
		if(check_publication_date.isSelected()){
			return true;
		}
		else return false;
	}
	
	public File getSaveFilePath(){
		Boolean good_suffix = false;
		File file = null;
		JFileChooser file_chooser = new JFileChooser();
		file_chooser.setSelectedFile(new File("*.png"));
		FileFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getWriterFileSuffixes());
		file_chooser.setFileFilter(filter);
		if (file_chooser.showSaveDialog(this.panel) == JFileChooser.APPROVE_OPTION) {
		  file = file_chooser.getSelectedFile();
		}
		for (String suff : ImageIO.getWriterFileSuffixes()){
			if (file != null && file.getName().endsWith("."+suff)){
				good_suffix = true;
				break;
			}
		}
		while (!good_suffix){
			JOptionPane.showMessageDialog(null,"Please enter a name of file to save in form"
					+ " NAME.FILETYPE FILETYPES: [png,jpg,jpeg,bmp,gif,wbmp]","ERROR",JOptionPane.ERROR_MESSAGE);
			if (file_chooser.showSaveDialog(this.panel) == JFileChooser.APPROVE_OPTION){
				file = file_chooser.getSelectedFile();
			}
			for (String suff : ImageIO.getWriterFileSuffixes()){
				if (file != null && file.getName().endsWith("."+suff)){
					good_suffix = true;
					break;
				}
			}
		}
		return file;
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
		all_books.setText(resourceBundle.getString("BooksTab.btn.Show_all_books"));
		detail_books.setText(resourceBundle.getString("BooksTab.btn.Detail_of_selected_book"));
		print_QR.setText(resourceBundle.getString("BooksTab.btn.Print_QR"));
		books_per_page.setText(resourceBundle.getString("BooksTab.lbl.Books_per_page")); 
		prev.setText(resourceBundle.getString("BooksTab.btn.PREV"));
		next.setText(resourceBundle.getString("BooksTab.btn.NEXT"));
		l_filter_by_author.setText(resourceBundle.getString("BooksTab.lbl.L_filter_by_author"));
		l_filter_by_genre.setText(resourceBundle.getString("BooksTab.lbl.L_filter_by_genre"));
		l_filter_by_publication_date.setText(resourceBundle.getString("BooksTab.lbl.L_filter_by_publication_date"));
		filter_by_author.setText(resourceBundle.getString("BooksTab.btn.Filter_by_author"));
		filter_by_genre.setText(resourceBundle.getString("BooksTab.btn.Filter_by_genre"));
		filter_by_publication_date.setText(resourceBundle.getString("BooksTab.btn.Filter_by_publication_date"));
		table.getColumnModel().getColumn(1).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Title"));
		table.getColumnModel().getColumn(2).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Publisher"));
		table.getColumnModel().getColumn(3).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Pages"));
		table.getColumnModel().getColumn(4).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Pieces"));
		table.getColumnModel().getColumn(5).setHeaderValue(resourceBundle.getString("BooksTab.clmn.EAN_Code"));
		table.getColumnModel().getColumn(6).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Book_genre"));
		table.getTableHeader().repaint();
	}
}
