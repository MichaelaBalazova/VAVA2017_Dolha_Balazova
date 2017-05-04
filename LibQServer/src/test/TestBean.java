package test;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
public class TestBean implements TestBeanRemote {

    public String tstPlus(String str1, String str2){
    	return (str1 + " " + str2 + "!!!");
    }

}
