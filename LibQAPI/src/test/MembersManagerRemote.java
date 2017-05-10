package test;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Borrowed_booksModel;
import entity.MembersModel;

@Remote
public interface MembersManagerRemote {

	public List<MembersModel> getAllMembers(int limit, int offset);
    public List<Borrowed_booksModel> getMemberListOfBorrowedBooks(int member_id);
	public List<MembersModel> filterByNumBorrowed(int members_limit, int members_offset, int num);
    public List<MembersModel> findMember(String find);	
    public boolean changeMember(int member_id, String first, String last, Date birthday, String email,
    		String telephone, String address, Date member_from);
}
	