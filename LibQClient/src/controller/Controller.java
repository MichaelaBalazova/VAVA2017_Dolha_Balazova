package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entity.BooksModel;
import test.BooksManagerRemote;
import view.Window;

public class Controller {
	
	private Window win;
	private Context context;
	private int books_limit;
	private int books_offset;

	public Controller(Window window) {
		this.win = window;
		try {
			context = createRemoteEjbContext("localhost", "8080");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		ShowAllBooks showAllBooks = new ShowAllBooks(context, win, books_limit, books_offset);		
		PrevBooks prevBooks = new PrevBooks(context, win, books_limit, books_offset);
		NextBooks nextBooks = new NextBooks(context, win, books_limit, books_offset);
		win.books_tab.addActions(showAllBooks, prevBooks, nextBooks);
	}
	
	private class ShowAllBooks implements ActionListener{
		
		private Context context;
		private Window win;
		private int books_limit;
		private int books_offset;
		
		public ShowAllBooks(Context context, Window win, int books_limit, int books_offset){
			this.context = context;
			this.win = win;
			this.books_limit = books_limit;
			this.books_offset = books_offset;
		}
		
		public void actionPerformed(ActionEvent e){
			BooksManagerRemote remote = null;
			try {
				remote = (BooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//BooksManagerBean!test.BooksManagerRemote");
			} catch (NamingException e1) {
				e1.printStackTrace();
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
		
		public PrevBooks(Context context, Window win, int books_limit, int books_offset){
			this.context = context;
			this.win = win;
			this.books_limit = books_limit;
			this.books_offset = books_offset;
		}
		
		public void actionPerformed(ActionEvent e){
			BooksManagerRemote remote = null;
			try {
				remote = (BooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//BooksManagerBean!test.BooksManagerRemote");
			} catch (NamingException e1) {
				e1.printStackTrace();
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
		
		public NextBooks(Context context, Window win, int books_limit, int books_offset){
			this.context = context;
			this.win = win;
			this.books_limit = books_limit;
			this.books_offset = books_offset;
		}
		
		public void actionPerformed(ActionEvent e){
			BooksManagerRemote remote = null;
			try {
				remote = (BooksManagerRemote)context.lookup("ejb:LibQEAR/LibQServer//BooksManagerBean!test.BooksManagerRemote");
			} catch (NamingException e1) {
				e1.printStackTrace();
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
