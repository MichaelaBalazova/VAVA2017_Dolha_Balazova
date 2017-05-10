package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class BooksModel implements Serializable{
	
	private int id;
	private String title;
	private String publisher;
	private Date publication_date;
	private int no_pages;
	private int no_pieces;
	private String ean_code;
	private GenresModel genre_id;
	private LocationModel location_id;
	private List<AuthorsModel> authors_list;
	private AuthorsModel author;
	
	public BooksModel(int id, String title, String publisher, Date publication_date, int no_pages, int no_pieces, String ean_code, GenresModel genre_id, LocationModel location_id) {
		setId(id);
		setTitle(title);
		setPublisher(publisher);
		setPublication_date(publication_date);
		setNo_pages(no_pages);
		setNo_pieces(no_pieces);
		setEan_code(ean_code);
		setGenre_id(genre_id);
		setLocation_id(location_id);
	}
	
	public BooksModel(int id, String title, String publisher, int no_pages, int no_pieces, String ean_code, GenresModel genre){
		setId(id);
		setTitle(title);
		setPublisher(publisher);
		setNo_pages(no_pages);
		setNo_pieces(no_pieces);
		setEan_code(ean_code);
		setGenre_id(genre);
	}
	
	public BooksModel(int id, String title, String publisher, int no_pages, int no_pieces, String ean_code, AuthorsModel author){
		setId(id);
		setTitle(title);
		setPublisher(publisher);
		setNo_pages(no_pages);
		setNo_pieces(no_pieces);
		setEan_code(ean_code);
		setAuthor(author);
	}
	
	public BooksModel(String title, String publisher) {
		setTitle(title);
		setPublisher(publisher);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Date getPublication_date() {
		return publication_date;
	}
	
	public void setPublication_date(Date publication_date) {
		this.publication_date = publication_date;
	}
	
	public int getNo_pages() {
		return no_pages;
	}
	
	public void setNo_pages(int no_pages) {
		this.no_pages = no_pages;
	}
	
	public int getNo_pieces() {
		return no_pieces;
	}
	
	public void setNo_pieces(int no_pieces) {
		this.no_pieces = no_pieces;
	}
	
	public String getEan_code() {
		return ean_code;
	}
	
	public void setEan_code(String ean_code) {
		this.ean_code = ean_code;
	}
	
	public GenresModel getGenre_id() {
		return genre_id;
	}
	
	public void setGenre_id(GenresModel genre_id) {
		this.genre_id = genre_id;
	}
	
	public LocationModel getLocation_id() {
		return location_id;
	}
	
	public void setLocation_id(LocationModel location_id) {
		this.location_id = location_id;
	}
	
	public List<AuthorsModel> getAuthors_list() {
		return authors_list;
	}
	
	public void setAuthors_list(List<AuthorsModel> authors_list) {
		this.authors_list = authors_list;
	}
	
	public AuthorsModel getAuthor() {
		return author;
	}

	public void setAuthor(AuthorsModel author) {
		this.author = author;
	}
}
