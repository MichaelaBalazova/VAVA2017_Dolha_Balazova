package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class BooksTab {
	
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	public int offset = 0;
	private ImageIcon imgSK = new ImageIcon("img/sk-flag.png");
	private ImageIcon imgEN = new ImageIcon("img/en-flag.png");
	private JButton langSK = new JButton(imgSK);
	private JButton langEN = new JButton(imgEN);
	private JButton print_QR = new JButton(resourceBundle.getString("BooksTab.btn.Print_QR"));
	private JButton all_books = new JButton(resourceBundle.getString("BooksTab.btn.Show_all_books")); 
	private JButton detail_books = new JButton(resourceBundle.getString("BooksTab.btn.Detail_of_selected_book"));
	private JLabel books_per_page = new JLabel(resourceBundle.getString("BooksTab.lbl.Books_per_page"));
	private JPanel panel;
	private JButton prev = new JButton(resourceBundle.getString("BooksTab.btn.PREV"));
	private JButton next = new JButton(resourceBundle.getString("BooksTab.btn.NEXT"));
	private String[] columns_books = {"ID", "Title", "Publisher", "Pages", "Pieces", "EAN_code", "Book genre"}; 
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
		prev.setBounds(280, 1270, 120, 35);		
		next.setBounds(2357, 1270, 120, 35);
		langSK.setBounds(26, 300, 60, 35);
		langEN.setBounds(106, 300, 60, 35);
		print_QR.setBounds(26, 350, 209, 41);
		
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

		//pridavanie komponentov na panel
		panel.add(next);
		panel.add(prev);
		panel.add(all_books);
		panel.add(detail_books);
		panel.add(print_QR);
		panel.add(langSK);
		panel.add(langEN);
		panel.add(books_per_page);
		
		limit_txt.setText("30"); 
		limit_txt.setBounds(26, 116, 120, 39);
		limit_txt.setColumns(10);
		limit_txt.setFont(new Font("Sans Serif", Font.PLAIN, 20)); 
		panel.add(limit_txt);
				
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
		        public void valueChanged(ListSelectionEvent e) { 
		            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		            detail_books.setEnabled(!lsm.isSelectionEmpty());
		        }
		});
			
		detail_books.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
			    } 
		});
		
	}
	
	public void addActions(ActionListener showAllBooks, ActionListener prevBooks, ActionListener nextBooks){
		all_books.addActionListener(showAllBooks);
		prev.addActionListener(prevBooks);
		next.addActionListener(nextBooks);
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
			System.out.println("Error : " + e.getMessage());	
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
		table.getColumnModel().getColumn(1).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Title"));
		table.getColumnModel().getColumn(2).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Publisher"));
		table.getColumnModel().getColumn(3).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Pages"));
		table.getColumnModel().getColumn(4).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Pieces"));
		table.getColumnModel().getColumn(5).setHeaderValue(resourceBundle.getString("BooksTab.clmn.EAN_Code"));
		table.getColumnModel().getColumn(6).setHeaderValue(resourceBundle.getString("BooksTab.clmn.Book_genre"));
		table.getTableHeader().repaint();
	}
}
