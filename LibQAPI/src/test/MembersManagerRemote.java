package test;

import java.util.List;

import javax.ejb.Remote;

import entity.Borrowed_booksModel;
import entity.MembersModel;

@Remote
public interface MembersManagerRemote {

	public List<MembersModel> getAllMembers(int limit, int offset);
    public List<Borrowed_booksModel> getMemberListOfBorrowedBooks(int member_id);
	
}
	