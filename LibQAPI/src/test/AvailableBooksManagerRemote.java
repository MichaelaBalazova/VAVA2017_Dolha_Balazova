package test;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Available_booksModel;

@Remote
public interface AvailableBooksManagerRemote {

	public List<Available_booksModel> getAllAvailableBooks(int limit, int offset);
	public boolean borrowBook(Date date_from, Date date_to, int available_id, int member_id, int employee_id);
	
}
	