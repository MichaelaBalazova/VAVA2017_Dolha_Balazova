package entity;

import java.io.Serializable;
import java.util.Date;

public class Borrowed_booksModel implements Serializable{
	
	private int id;
	private Date borrowed_from;
	private Date borrowed_to;
	private Available_booksModel available_id;
	private MembersModel member_id;
	private EmployeesModel employee_id;
	private BooksModel book;
	private StateModel state;

	public Borrowed_booksModel(int id, Date borrowed_from, Date borrowed_to, Available_booksModel available_id, MembersModel member_id, EmployeesModel employee_id) {
		setId(id);
		setBorrowed_from(borrowed_from);
		setBorrowed_to(borrowed_to);
		setAvailable_id(available_id);
		setMember_id(member_id);
		setEmployee_id(employee_id);
	}
	
	public Borrowed_booksModel(int id, BooksModel books, java.sql.Date from, java.sql.Date to, StateModel state, EmployeesModel employee_id) {
		setId(id);
		setBook(books);
		setBorrowed_from(from);
		setBorrowed_to(to);
		setState(state);
		setEmployee_id(employee_id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBorrowed_from() {
		return borrowed_from;
	}

	public void setBorrowed_from(Date borrowed_from) {
		this.borrowed_from = borrowed_from;
	}

	public Date getBorrowed_to() {
		return borrowed_to;
	}

	public void setBorrowed_to(Date borrowed_to) {
		this.borrowed_to = borrowed_to;
	}

	public Available_booksModel getAvailable_id() {
		return available_id;
	}

	public void setAvailable_id(Available_booksModel available_id) {
		this.available_id = available_id;
	}

	public MembersModel getMember_id() {
		return member_id;
	}

	public void setMember_id(MembersModel member_id) {
		this.member_id = member_id;
	}

	public EmployeesModel getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(EmployeesModel employee_id) {
		this.employee_id = employee_id;
	}
	
	public BooksModel getBook() {
		return book;
	}

	public void setBook(BooksModel book) {
		this.book = book;
	}
	
	public StateModel getState() {
		return state;
	}

	public void setState(StateModel state) {
		this.state = state;
	}

}
