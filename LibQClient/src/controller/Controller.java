package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.Available_booksModel;
import entity.BooksModel;
import entity.Borrowed_booksModel;
import entity.EmployeesModel;
import entity.MembersModel;
import test.BooksManagerRemote;
import test.EmployeeManagerRemote;
import test.AvailableBooksManagerRemote;
import test.MembersManagerRemote;
import view.Available_booksTab;
import view.BorrowBookWindow;
import view.MemberListOfBorrowedBooksWindow;
import view.Window;

public class Controller {
	
	private static Logger LOG = Logger.getLogger(Controller.class.getName());
	private Window win;
	private Context context;

	public Controller(Window window) {
		this.win = window;
		try {
			context = createRemoteEjbContext("localhost", "8080");
			LOG.info("Successful creation of context in Controller class.");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		
		ShowAllBooks showAllBooks = new ShowAllBooks(context, win);		
		PrevBooks prevBooks = new PrevBooks(context, win);
		NextBooks nextBooks = new NextBooks(context, win);
		win.books_tab.addActions(showAllBooks, prevBooks, nextBooks);
		
		ShowAllMembers showAllMembers = new ShowAllMembers(context, win);		
		PrevMembers prevMembers = new PrevMembers(context, win);
		NextMembers nextMembers = new NextMembers(context, win);
		MemberListOfBorrowedBooks memberListOfBorrowedBooks = new MemberListOfBorrowedBooks(context, win);
		win.members_tab.addActions(showAllMembers, prevMembers, nextMembers, memberListOfBorrowedBooks);
		
		ShowAllAvailableBooks showAllAvailableBooks = new ShowAllAvailableBooks(context, win);		
		PrevAvailableBooks prevAvailableBooks = new PrevAvailableBooks(context, win);
		NextAvailableBooks nextAvailableBooks = new NextAvailableBooks(context, win);
		BorrowBook1 borrowBook1 = new BorrowBook1(context, win);
		win.available_books_tab.addActions(showAllAvailableBooks, prevAvailableBooks, nextAvailableBooks, borrowBook1);
	}
	
	private class ShowAllBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int books_limit;
		private int books_offset;
		
		public ShowAllBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
		
			BooksManagerRemote remote = null;
			try {
				remote = (BooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//BooksManagerBean!test.BooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e);
			}
			books_limit = win.books_tab.getLimit();
			books_offset = 0;		
			win.books_tab.setOffset(books_offset);
			win.books_tab.setNextEnabled(true);
			win.books_tab.setPrevEnabled(false);
			
			List<BooksModel> books_list = remote.getAllBooks(books_limit, books_offset);
			win.books_tab.clearTable();
			for(BooksModel book : books_list){
				Object[] row = new Object[7];
				row[0] = book.getId();
				row[1] = book.getTitle();
				row[2] = book.getPublisher();
				row[3] = book.getNo_pages();
				row[4] = book.getNo_pieces();
				row[5] = book.getEan_code();
				row[6] = getGenreText(book);
				win.books_tab.addTableRow(row);
			}
		}
		
		public String getGenreText(BooksModel book){
			String genre;
			if (book.getGenre_id().getAdd_text() != null){
				genre =  new StringBuilder().append(book.getGenre_id().getName()).append(" (").append(book.getGenre_id().getAdd_text()).append(")").toString();
			}
			else {
				genre =  new StringBuilder().append(book.getGenre_id().getName()).toString();				
			}
			return genre;
		}
	}
	
	private class PrevBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int books_limit;
		private int books_offset;
		
		public PrevBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			BooksManagerRemote remote = null;
			try {
				remote = (BooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//BooksManagerBean!test.BooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			
			books_limit = win.books_tab.getLimit();
			books_offset = win.books_tab.getOffset();
			books_offset -= books_limit;	
			win.books_tab.setOffset(books_offset);
			if (books_offset < books_limit){
				win.books_tab.setPrevEnabled(false);
			}
			
			List<BooksModel> books_list = remote.getAllBooks(books_limit, books_offset);
			win.books_tab.clearTable();
			for(BooksModel book : books_list){
				Object[] row = new Object[7];
				row[0] = book.getId();
				row[1] = book.getTitle();
				row[2] = book.getPublisher();
				row[3] = book.getNo_pages();
				row[4] = book.getNo_pieces();
				row[5] = book.getEan_code();
				row[6] = getGenreText(book);
				win.books_tab.addTableRow(row);
			}			
		}
	
		public String getGenreText(BooksModel book){
			String genre;
			if (book.getGenre_id().getAdd_text() != null){
				genre =  new StringBuilder().append(book.getGenre_id().getName()).append(" (").append(book.getGenre_id().getAdd_text()).append(")").toString();
			}
			else {
				genre =  new StringBuilder().append(book.getGenre_id().getName()).toString();				
			}
			return genre;
		}
	}
	
	private class NextBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int books_limit;
		private int books_offset;
		
		public NextBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			BooksManagerRemote remote = null;
			try {
				remote = (BooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//BooksManagerBean!test.BooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			books_limit = win.books_tab.getLimit();
			books_offset = win.books_tab.getOffset();
			books_offset += books_limit;
			win.books_tab.setOffset(books_offset);
			win.books_tab.setPrevEnabled(true);
			
			List<BooksModel> books_list = remote.getAllBooks(books_limit, books_offset);
			win.books_tab.clearTable();
			for(BooksModel book : books_list){
				Object[] row = new Object[7];
				row[0] = book.getId();
				row[1] = book.getTitle();
				row[2] = book.getPublisher();
				row[3] = book.getNo_pages();
				row[4] = book.getNo_pieces();
				row[5] = book.getEan_code();
				row[6] = getGenreText(book);
				win.books_tab.addTableRow(row);
			}			
		}
	
		public String getGenreText(BooksModel book){
			String genre;
			if (book.getGenre_id().getAdd_text() != null){
				genre =  new StringBuilder().append(book.getGenre_id().getName()).append(" (").append(book.getGenre_id().getAdd_text()).append(")").toString();
			}
			else {
				genre =  new StringBuilder().append(book.getGenre_id().getName()).toString();				
			}
			return genre;
		}
	}
	
	private class ShowAllMembers implements ActionListener{
		
		private Context context;
		private Window win;
		private int members_limit;
		private int members_offset;
		
		public ShowAllMembers(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			
			if (!win.members_tab.isCheckBoxSelected()){
				MembersManagerRemote remote = null;
				try {
					remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
				} catch (NamingException e1) {
					LOG.severe("Error: "+e1);
				}
				members_limit = win.members_tab.getLimit();
				members_offset = 0;		
				win.members_tab.setOffset(members_offset);
				win.members_tab.setNextEnabled(true);
				win.members_tab.setPrevEnabled(false);
				
				List<MembersModel> Members_list = remote.getAllMembers(members_limit, members_offset);
				win.members_tab.clearTable();
				for(MembersModel member : Members_list){
					Object[] row = new Object[9];
					row[0] = member.getId();
					row[1] = member.getFirst_name();
					row[2] = member.getLast_name();
					row[3] = member.getDate_birth();
					row[4] = member.getEmail();
					row[5] = member.getTelephone();
					row[6] = member.getAddress();
					row[7] = member.getMember_from();
					row[8] = member.getNum_of_borrowed();
					win.members_tab.addTableRow(row);
				}
			}
			else {
				filterRecords();
			}
		}
		
		public void filterRecords(){
			MembersManagerRemote remote = null;
			try {
				remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			members_limit = win.members_tab.getLimit();
			members_offset = 0;		
			win.members_tab.setOffset(members_offset);
			win.members_tab.setNextEnabled(true);
			win.members_tab.setPrevEnabled(false);
			int num = win.members_tab.getSelectedNum();
			
			List<MembersModel> Members_list = remote.filterByNumBorrowed(members_limit, members_offset, num);
			win.members_tab.clearTable();
			for(MembersModel member : Members_list){
				Object[] row = new Object[9];
				row[0] = member.getId();
				row[1] = member.getFirst_name();
				row[2] = member.getLast_name();
				row[3] = member.getDate_birth();
				row[4] = member.getEmail();
				row[5] = member.getTelephone();
				row[6] = member.getAddress();
				row[7] = member.getMember_from();
				row[8] = member.getNum_of_borrowed();
				win.members_tab.addTableRow(row);
			}			
		}
	}
	
	private class PrevMembers implements ActionListener{
		
		private Context context;
		private Window win;
		private int members_limit;
		private int members_offset;
		
		public PrevMembers(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			
			if (!win.members_tab.isCheckBoxSelected()){
				MembersManagerRemote remote = null;
				try {
					remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
				} catch (NamingException e1) {
					LOG.severe("Error: "+e1);
				}
				
				members_limit = win.members_tab.getLimit();
				members_offset = win.members_tab.getOffset();
				members_offset -= members_limit;	
				win.members_tab.setOffset(members_offset);
				if (members_offset < members_limit){
					win.members_tab.setPrevEnabled(false);
				}
				
				List<MembersModel> Members_list = remote.getAllMembers(members_limit, members_offset);
				win.members_tab.clearTable();
				for(MembersModel member : Members_list){
					Object[] row = new Object[9];
					row[0] = member.getId();
					row[1] = member.getFirst_name();
					row[2] = member.getLast_name();
					row[3] = member.getDate_birth();
					row[4] = member.getEmail();
					row[5] = member.getTelephone();
					row[6] = member.getAddress();
					row[7] = member.getMember_from();
					row[8] = member.getNum_of_borrowed();
					win.members_tab.addTableRow(row);
				}	
			}
			else{
				filterRecords();
			}
		}
		
		public void filterRecords(){
			MembersManagerRemote remote = null;
			try {
				remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			members_limit = win.members_tab.getLimit();
			members_offset = win.members_tab.getOffset();
			members_offset -= members_limit;	
			win.members_tab.setOffset(members_offset);
			if (members_offset < members_limit){
				win.members_tab.setPrevEnabled(false);
			}
			int num = win.members_tab.getSelectedNum();
			
			List<MembersModel> Members_list = remote.filterByNumBorrowed(members_limit, members_offset, num);
			win.members_tab.clearTable();
			for(MembersModel member : Members_list){
				Object[] row = new Object[9];
				row[0] = member.getId();
				row[1] = member.getFirst_name();
				row[2] = member.getLast_name();
				row[3] = member.getDate_birth();
				row[4] = member.getEmail();
				row[5] = member.getTelephone();
				row[6] = member.getAddress();
				row[7] = member.getMember_from();
				row[8] = member.getNum_of_borrowed();
				win.members_tab.addTableRow(row);
			}			
		}
	}
	
	private class NextMembers implements ActionListener{
		
		private Context context;
		private Window win;
		private int members_limit;
		private int members_offset;
		
		public NextMembers(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			
			if (!win.members_tab.isCheckBoxSelected()){
				MembersManagerRemote remote = null;
				try {
					remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
				} catch (NamingException e1) {
					LOG.severe("Error: "+e1);
				}
				members_limit = win.members_tab.getLimit();
				members_offset = win.members_tab.getOffset();
				members_offset += members_limit;
				win.members_tab.setOffset(members_offset);
				win.members_tab.setPrevEnabled(true);
				
				List<MembersModel> Members_list = remote.getAllMembers(members_limit, members_offset);
				win.members_tab.clearTable();
				for(MembersModel member : Members_list){
					Object[] row = new Object[9];
					row[0] = member.getId();
					row[1] = member.getFirst_name();
					row[2] = member.getLast_name();
					row[3] = member.getDate_birth();
					row[4] = member.getEmail();
					row[5] = member.getTelephone();
					row[6] = member.getAddress();
					row[7] = member.getMember_from();
					row[8] = member.getNum_of_borrowed();
					win.members_tab.addTableRow(row);
				}	
			}
			else {
				filterRecords();
			}
		}
		
		public void filterRecords(){
			MembersManagerRemote remote = null;
			try {
				remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			members_limit = win.members_tab.getLimit();
			members_offset = win.members_tab.getOffset();
			members_offset += members_limit;
			win.members_tab.setOffset(members_offset);
			win.members_tab.setPrevEnabled(true);
			int num = win.members_tab.getSelectedNum();
			
			List<MembersModel> Members_list = remote.filterByNumBorrowed(members_limit, members_offset, num);
			win.members_tab.clearTable();
			for(MembersModel member : Members_list){
				Object[] row = new Object[9];
				row[0] = member.getId();
				row[1] = member.getFirst_name();
				row[2] = member.getLast_name();
				row[3] = member.getDate_birth();
				row[4] = member.getEmail();
				row[5] = member.getTelephone();
				row[6] = member.getAddress();
				row[7] = member.getMember_from();
				row[8] = member.getNum_of_borrowed();
				win.members_tab.addTableRow(row);
			}			
		}
	}
	
	private class MemberListOfBorrowedBooks implements ActionListener{
		
		private Context context;
		private Window win;
		
		public MemberListOfBorrowedBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			MembersManagerRemote remote = null;
			try {
				remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			
			int member_id = win.members_tab.getSelectedRowMember_id();
			String firstname = win.members_tab.getSelectedRowFirstname();
			String secondname = win.members_tab.getSelectedRowSecondname();
			MemberListOfBorrowedBooksWindow bb_win = new MemberListOfBorrowedBooksWindow(member_id, firstname, secondname);
			bb_win.setWinVisile(true);
			
			List<Borrowed_booksModel> borrowed_books_list = remote.getMemberListOfBorrowedBooks(member_id);
			for(Borrowed_booksModel b_book : borrowed_books_list){
				Object[] row = new Object[8];	
				row[0] = b_book.getId();
				row[1] = b_book.getBook().getTitle();
				row[2] = b_book.getBook().getPublisher();
				row[3] = b_book.getBorrowed_from();
				row[4] = b_book.getBorrowed_to();
				row[5] = b_book.getState().getType_of_state();
				row[6] = getFullName(b_book.getEmployee_id());
				row[7] = b_book.getEmployee_id().getAdd_info();
				bb_win.addTableRow(row);
			}			
		}
		
		public String getFullName(EmployeesModel employee){
			String fullname =  new StringBuilder().append(employee.getFirst_name()).append(" ").append(employee.getLast_name()).toString();
			return fullname;
		}
	}
	
	private class ShowAllAvailableBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int av_books_limit;
		private int av_books_offset;
		
		public ShowAllAvailableBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
		
			AvailableBooksManagerRemote remote = null;
			try {
				remote = (AvailableBooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//AvailableBooksManagerBean!test.AvailableBooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			av_books_limit = win.available_books_tab.getLimit();
			av_books_offset = 0;		
			win.available_books_tab.setOffset(av_books_offset);
			win.available_books_tab.setNextEnabled(true);
			win.available_books_tab.setPrevEnabled(false);
			
			List<Available_booksModel> av_books_list = remote.getAllAvailableBooks(av_books_limit, av_books_offset);
			win.available_books_tab.clearTable();
			for(Available_booksModel av_book : av_books_list){
				Object[] row = new Object[5];
				row[0] = av_book.getId();
				row[1] = av_book.getState_id().getType_of_state();
				row[2] = av_book.getIdentifier();
				row[3] = av_book.getBook_id().getTitle();
				row[4] = av_book.getBook_id().getPublisher();
				win.available_books_tab.addTableRow(row);
			}
		}
	}
	
	private class PrevAvailableBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int av_books_limit;
		private int av_books_offset;
		
		public PrevAvailableBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			AvailableBooksManagerRemote remote = null;
			try {
				remote = (AvailableBooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//AvailableBooksManagerBean!test.AvailableBooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			
			av_books_limit = win.available_books_tab.getLimit();
			av_books_offset = win.available_books_tab.getOffset();
			av_books_offset -= av_books_limit;	
			win.available_books_tab.setOffset(av_books_offset);
			if (av_books_offset < av_books_limit){
				win.available_books_tab.setPrevEnabled(false);
			}
			
			List<Available_booksModel> av_books_list = remote.getAllAvailableBooks(av_books_limit, av_books_offset);
			win.available_books_tab.clearTable();
			for(Available_booksModel av_book : av_books_list){
				Object[] row = new Object[5];
				row[0] = av_book.getId();
				row[1] = av_book.getState_id().getType_of_state();
				row[2] = av_book.getIdentifier();
				row[3] = av_book.getBook_id().getTitle();
				row[4] = av_book.getBook_id().getPublisher();
				win.available_books_tab.addTableRow(row);
			}		
		}
	}
	
	private class NextAvailableBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int av_books_limit;
		private int av_books_offset;
		
		public NextAvailableBooks(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			AvailableBooksManagerRemote remote = null;
			try {
				remote = (AvailableBooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//AvailableBooksManagerBean!test.AvailableBooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			av_books_limit = win.available_books_tab.getLimit();
			av_books_offset = win.available_books_tab.getOffset();
			av_books_offset += av_books_limit;
			win.available_books_tab.setOffset(av_books_offset);
			win.available_books_tab.setPrevEnabled(true);
			
			List<Available_booksModel> av_books_list = remote.getAllAvailableBooks(av_books_limit, av_books_offset);
			win.available_books_tab.clearTable();
			for(Available_booksModel av_book : av_books_list){
				Object[] row = new Object[5];
				row[0] = av_book.getId();
				row[1] = av_book.getState_id().getType_of_state();
				row[2] = av_book.getIdentifier();
				row[3] = av_book.getBook_id().getTitle();
				row[4] = av_book.getBook_id().getPublisher();
				win.available_books_tab.addTableRow(row);
			}		
		}
	}
	
	private class BorrowBook1 implements ActionListener{
		
		private Context context;
		private Window win;
		
		public BorrowBook1(Context context, Window win){
			this.context = context;
			this.win = win;
		}
		
		public void actionPerformed(ActionEvent e){
			EmployeeManagerRemote remote = null;
			try {
				remote = (EmployeeManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//EmployeeManagerBean!test.EmployeeManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			Locale locale = win.available_books_tab.getLocale();
			int available_id = win.available_books_tab.getSelectedRowAvailableBook();
			BorrowBookWindow borrowBook = new BorrowBookWindow(available_id, locale);
			borrowBook.setWinVisible(true);
			FindMember findMember = new FindMember(context, win, borrowBook);
			BorrowBookCommit borrowBookCommit = new BorrowBookCommit(context, win, borrowBook, available_id);
			borrowBook.addActions(borrowBookCommit, findMember);
			
			List<EmployeesModel> employees_list = remote.getAllEmployees();
			for(EmployeesModel emp : employees_list){
				Object[] row = new Object[5];
				row[0] = emp.getId();
				row[1] = emp.getFirst_name();
				row[2] = emp.getLast_name();
				row[3] = emp.getDate_birth();
				row[4] = emp.getAdd_info();
				borrowBook.addEmployeeTableRow(row);
			}
		}
	}
	
	private class FindMember implements ActionListener{
		
		private Context context;
		private Window win;
		private BorrowBookWindow borrowBook;
		
		public FindMember(Context context, Window win, BorrowBookWindow borrowBook){
			this.context = context;
			this.win = win;
			this.borrowBook = borrowBook;
		}
		
		public void actionPerformed(ActionEvent e){
			MembersManagerRemote remote = null;
			try {
				remote = (MembersManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//MembersManagerBean!test.MembersManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			String find = borrowBook.getFindMemberText();
			
			List<MembersModel> members_list = remote.findMember(find);
			for(MembersModel member : members_list){
				Object[] row = new Object[4];
				row[0] = member.getId();
				row[1] = member.getFirst_name();
				row[2] = member.getLast_name();
				row[3] = member.getDate_birth();
				borrowBook.addMemberTableRow(row);
			}
		}
	}
	
	private class BorrowBookCommit implements ActionListener{
		
		private Context context;
		private Window win;
		private BorrowBookWindow borrowBook;
		private int available_id;
		
		public BorrowBookCommit(Context context, Window win, BorrowBookWindow borrowBook, int available_id){
			this.context = context;
			this.win = win;
			this.borrowBook = borrowBook;
			this.available_id = available_id;
		}
		
		public void actionPerformed(ActionEvent e){
			AvailableBooksManagerRemote remote = null;
			try {
				remote = (AvailableBooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//AvailableBooksManagerBean!test.AvailableBooksManagerRemote");
			} catch (NamingException e1) {
				LOG.severe("Error: "+e1);
			}
			int member_id = borrowBook.getSelectedRowMember();
			int employee_id = borrowBook.getSelectedRowEmloyee();
			Date dateFrom = borrowBook.getDateFrom();
			Date dateTo = borrowBook.getDateTo();	
			
			if(member_id == -1 || employee_id == -1 || dateFrom == null || dateTo == null){
				borrowBook.showActionResult(false);
			}
			else{			
				boolean result = remote.borrowBook(dateFrom, dateTo, available_id, member_id, employee_id);
				borrowBook.showActionResult(result);
				if (result){
					win.available_books_tab.getRefreshButton().doClick();
					borrowBook.setWinVisible(false);
				}
			}
		}
	}
	
	
	private static Context createRemoteEjbContext(String host, String port) throws NamingException {
		Hashtable<Object, Object> props = new Hashtable<Object, Object>();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		
		props.put("jboss.naming.client.ejb.context", false);
		props.put("org.jboss.ejb.client.scoped.context", true);
 
		props.put("endpoint.name", "client-endpoint");
		props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
		props.put("remote.connections", "default");
		props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
 
        props.put(Context.PROVIDER_URL, "http-remoting://" + host + ":" + port);
        props.put("remote.connection.default.host", host);
        props.put("remote.connection.default.port", port);
 
        return new InitialContext(props);
    }

}
