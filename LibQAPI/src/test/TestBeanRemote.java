package test;

import javax.ejb.Remote;

@Remote
public interface TestBeanRemote {

	public String tstPlus(String str1, String str2);
	
}
	