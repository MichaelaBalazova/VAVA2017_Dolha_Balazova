package test;

import java.io.File;

import javax.ejb.Remote;

@Remote
public interface QRCodeBeanRemote {

	 public int createQRCode(File saveFile, String stringToEncode);
	
}
