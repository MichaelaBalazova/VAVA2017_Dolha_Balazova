package test;

import java.util.List;

import javax.ejb.Remote;

import entity.EmployeesModel;

@Remote
public interface EmployeeManagerRemote {

	public String tstPlus(String str1, String str2);
	public List<EmployeesModel> getAllEmployees();
	
}
	