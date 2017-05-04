package entity;

import java.util.Date;

public class ReservationsModel {

	private int id;
	private Date reservation_from;
	private Date reservation_to;
	private Available_booksModel available_id;
	private MembersModel member_id;
	private BooksModel book;

	public ReservationsModel(int id, Date reservation_from, Date reservation_to, Available_booksModel available_id, MembersModel member_id) {
		setId(id);
		setReservation_from(reservation_from);
		setReservation_to(reservation_to);
		setAvailable_id(available_id);
		setMember_id(member_id);
	}

	public ReservationsModel(int id, Date reservation_from, Date reservation_to, BooksModel book, MembersModel member_id) {
		setId(id);
		setReservation_from(reservation_from);
		setReservation_to(reservation_to);
		setBook(book);
		setMember_id(member_id);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReservation_from() {
		return reservation_from;
	}

	public void setReservation_from(Date reservation_from) {
		this.reservation_from = reservation_from;
	}

	public Date getReservation_to() {
		return reservation_to;
	}

	public void setReservation_to(Date reservation_to) {
		this.reservation_to = reservation_to;
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
	
	public BooksModel getBook() {
		return book;
	}

	public void setBook(BooksModel book) {
		this.book = book;
	}
}
