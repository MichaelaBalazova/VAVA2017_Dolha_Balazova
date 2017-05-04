package entity;

public class Authors_booksModel {
	
	private int id;
	private AuthorsModel author_id;
	private BooksModel book_id;
	
	public Authors_booksModel(int id, AuthorsModel author_id, BooksModel book_id) {
		setId(id);
		setAuthor_id(author_id);	
		setBook_id(book_id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public AuthorsModel getAuthor_id() {
		return author_id;
	}
	
	public void setAuthor_id(AuthorsModel author_id) {
		this.author_id = author_id;
	}
	
	public BooksModel getBook_id() {
		return book_id;
	}
	
	public void setBook_id(BooksModel book_id) {
		this.book_id = book_id;
	}	
}
