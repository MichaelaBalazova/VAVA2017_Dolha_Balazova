package test;

import java.util.List;

import javax.ejb.Remote;

import entity.BooksModel;
import entity.GenresModel;

@Remote
public interface BooksManagerRemote {

	public List<BooksModel> getAllBooks(int limit, int offset);
	public List<BooksModel> findAuthor(String name);
	public List<GenresModel> fillComboBox();
	
}
	