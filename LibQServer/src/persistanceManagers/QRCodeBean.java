package persistanceManagers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import test.QRCodeBeanRemote;

/**
 * Session Bean implementation class QRCodeBean
 */
@Stateless
public class QRCodeBean implements QRCodeBeanRemote {

    public int createQRCode(File saveFile, String stringToEncode){
		int size = 250;
		String fileType = null;
		for (String suff : ImageIO.getWriterFileSuffixes()){
			if (saveFile.getName().endsWith("."+suff)){
				fileType = suff;
				break;
			}
		}
		try {			
			Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
 
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix byteMatrix = qrCodeWriter.encode(stringToEncode, BarcodeFormat.QR_CODE, size,
					size, hintMap);
			int width = byteMatrix.getWidth();
			BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
			image.createGraphics();
 
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, width);
			graphics.setColor(Color.BLACK);
 
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < width; j++) {
					if (byteMatrix.get(i, j)) {
						graphics.fillRect(i, j, 1, 1);
					}
				}
			}
			ImageIO.write(image, fileType, saveFile);
		} catch (WriterException e) {
			e.printStackTrace();
			//log unsuccessful creation of QR
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			//log unsuccessful creation of QR
			return -1;
		}
		//log successful creation of QR
		return 0;
    }

}
