package entity;

import java.util.Date;

public class MembersModel {
	
	private int id;
	private String first_name;
	private String last_name;
	private Date date_birth;
	private String address;
	private String email;
	private String telephone;
	private Date member_from;
	private Date member_to;
	private int num_of_borrowed;

	public MembersModel(int id, String first_name, String last_name, Date date_birth, String email, String telephone, String address, Date member_from) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
		setDate_birth(date_birth);
		setEmail(email);
		setTelephone(telephone);
		setAddress(address);
		setMember_from(member_from);
	}
	
	public MembersModel(int id, String first_name, String last_name, Date date_birth, String email, String telephone, String address, Date member_from, int num_of_borrowed) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
		setDate_birth(date_birth);
		setEmail(email);
		setTelephone(telephone);
		setAddress(address);
		setMember_from(member_from);
		setNum_of_borrowed(num_of_borrowed);
	}
	
	public MembersModel(int id, String first_name, String last_name, java.sql.Date date_birth) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
		setDate_birth(date_birth);
	}

	public MembersModel(int id, String first_name, String last_name) {
		setId(id);
		setFirst_name(first_name);
		setLast_name(last_name);
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getMember_from() {
		return member_from;
	}

	public void setMember_from(Date member_from) {
		this.member_from = member_from;
	}

	public Date getMember_to() {
		return member_to;
	}

	public void setMember_to(Date member_to) {
		this.member_to = member_to;
	}
	
	public int getNum_of_borrowed() {
		return num_of_borrowed;
	}

	public void setNum_of_borrowed(int num_of_borrowed) {
		this.num_of_borrowed = num_of_borrowed;
	}

}
