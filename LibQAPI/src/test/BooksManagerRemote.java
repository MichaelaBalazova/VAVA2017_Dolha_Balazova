package test;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.BooksModel;
import entity.GenresModel;

@Remote
public interface BooksManagerRemote {

	public List<BooksModel> getAllBooks(int limit, int offset);
	public List<BooksModel> findAuthor(String name, int limit, int offset);
	public List<GenresModel> fillComboBox();
	public List<BooksModel> findGenre(String genre, int limit, int offset);
	public List<BooksModel> findPublicationDate(Date date, int limit, int offset);
	public BooksModel getDetail(int book_id);
	
}
	