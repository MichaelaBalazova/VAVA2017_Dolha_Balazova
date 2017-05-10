package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Okno, ktore zobrazuje vytvoreny QR kod o detaile knihy
 * @author Michaela, Domca
 *
 */
public class QRCodeWindow extends JFrame {

	private static Logger LOG = Logger.getLogger(QRCodeWindow.class.getName());
	
	private JPanel panel = new JPanel();
	private ImageIcon qrcode = null;
	private JLabel qrcode_label = null;
	
	public QRCodeWindow(File file) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 450, 500);
		setResizable(false);
		this.setLocationRelativeTo(null);
		panel.setBackground(new Color(204,229,255));
		setContentPane(panel);
		panel.setLayout(null);	
		qrcode_label = new JLabel();
		qrcode_label.setBounds(20, 20, 400, 400);
		try {
			qrcode_label.setIcon(new ImageIcon(ImageIO.read(file)));
		} catch (IOException e) {
			LOG.severe("Error: "+e);
		}
		panel.add(qrcode_label);	
	}
	
	public void setWinVisible(boolean value){
		this.setVisible(value);
	}
	
	public void showErrorMessage(){
		JLabel label = new JLabel("QR Code was not created!");
		label.setFont(new Font("Sans Serif", Font.BOLD, 20));
		JOptionPane.showMessageDialog(null,label,"ERROR",JOptionPane.ERROR_MESSAGE);
	}
}

