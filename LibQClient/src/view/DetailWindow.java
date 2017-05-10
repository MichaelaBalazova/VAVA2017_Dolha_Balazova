package view;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Okno zobrazujuce detail o zvolenej knihe
 * @author Michaela, Domca
 *
 */
public class DetailWindow extends JFrame {

	private JPanel panel = new JPanel();
	public JTextArea txt = new JTextArea();
	
	public DetailWindow(String str) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 900, 750);
		setTitle("Detail of selected book");
		setResizable(false);
		this.setLocationRelativeTo(null);
		panel.setBackground(new Color(204,229,255));
		setContentPane(panel);
		panel.setLayout(null);
		txt.setBounds(50, 50, 900, 500);
		txt.setEditable(false);
		txt.setFont(new Font("Sans Serif", Font.BOLD, 21));
		txt.setBackground(new Color(204,229,255));
		panel.add(txt);
		
		txt.append(str);
	}
	
	public void setWinVisible(boolean value){
		this.setVisible(value);
	}
}
