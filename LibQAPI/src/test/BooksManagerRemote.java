package test;

import java.util.List;

import javax.ejb.Remote;

import entity.BooksModel;

@Remote
public interface BooksManagerRemote {

	public List<BooksModel> getAllBooks(int limit, int offset);
	
}
	