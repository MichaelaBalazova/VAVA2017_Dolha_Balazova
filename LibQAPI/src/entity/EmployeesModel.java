package entity;

import java.io.Serializable;
import java.util.Date;

public class EmployeesModel implements Serializable{
	
	private int id;
	private String first_name;
	private String last_name;
	private Date date_birth;
	private Date employee_from;
	private String add_info;
	
	public EmployeesModel(int id, String first_name, String last_name, Date date_birth, Date employee_from, String add_info) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
		setDate_birth(date_birth);
		setEmployee_from(employee_from);
		setAdd_info(add_info);
	}
	
	public EmployeesModel(int id, String first_name, String last_name, Date date_birth, String add_info) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
		setDate_birth(date_birth);
		setAdd_info(add_info);
	}
	
	public EmployeesModel(String first, String last) {
		setFirst_name(first);
		setLast_name(last);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public Date getDate_birth() {
		return date_birth;
	}


	public void setDate_birth(Date date_birth) {
		this.date_birth = date_birth;
	}
	
	public Date getEmployee_from() {
		return employee_from;
	}

	public void setEmployee_from(Date employee_from) {
		this.employee_from = employee_from;
	}

	public String getAdd_info() {
		return add_info;
	}

	public void setAdd_info(String add_info) {
		this.add_info = add_info;
	}
}
