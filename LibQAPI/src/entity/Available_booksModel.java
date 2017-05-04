package entity;

public class Available_booksModel {
	
	private int id;
	private String identifier;
	private BooksModel book_id;
	private StateModel state_id;
	
	public Available_booksModel(int id, StateModel state_id, String identifier, BooksModel book_id) {
		setId(id);
		setState_id(state_id);
		setIdentifier(identifier);
		setBook_id(book_id);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public BooksModel getBook_id() {
		return book_id;
	}

	public void setBook_id(BooksModel book_id) {
		this.book_id = book_id;
	}

	public StateModel getState_id() {
		return state_id;
	}

	public void setState_id(StateModel state_id) {
		this.state_id = state_id;
	}

}
