package persistanceManagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.GenresModel;
import entity.LocationModel;
import entity.AuthorsModel;
import entity.BooksModel;
import test.BooksManagerRemote;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
public class BooksManagerBean implements BooksManagerRemote {
    
	private static Logger LOG = Logger.getLogger(BooksManagerBean.class.getName());
	
    public List<BooksModel> getAllBooks(int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<BooksModel> list = new ArrayList<BooksModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in BooksManagerBean.getAllBooks!");
			}
			
			stm = conn.createStatement();
	        ResultSet rs = stm.executeQuery("SELECT books.id AS id, title, publisher, no_pages, no_pieces, "
	        		+ "EAN_code, genres.name AS gname, genres.add_text AS gtext "
	        		+ "FROM books "
	        		+ "JOIN genres ON genres.id = books.genre_id  "
	        		+ "ORDER BY books.id OFFSET " + offset + " LIMIT " + limit + ";");
	        
	        while ( rs.next() ) {
	        	list.add(new BooksModel(rs.getInt("id"), 
	        			rs.getString("title"),
		          		rs.getString("publisher"),
		          		rs.getInt("no_pages"),
		          		rs.getInt("no_pieces"),
		          		rs.getString("EAN_code"),
		          		new GenresModel(rs.getString("gname"), rs.getString("gtext"))
		          		));
	        }
	        rs.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;	
    }
    
    public List<BooksModel> findAuthor(String name, int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<BooksModel> list = new ArrayList<BooksModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in BooksManagerBean.getAllBooks!");
			}
			
			stm = conn.createStatement();
	        ResultSet rs = stm.executeQuery("SELECT books.id AS b_id, books.title AS b_title, "
	        	+ "books.publisher AS b_publisher, authors.first_name AS a_first, authors.last_name AS a_last, "
	        	+ "books.publication_date AS b_date, books.no_pages AS b_pgs, books.no_pieces AS b_pcs, "
	        	+ "books.ean_code AS b_ean FROM books "
	        	+ "JOIN authors_books ON authors_books.book_id = books.id "
	        	+ "JOIN authors ON authors.id = authors_books.author_id "
	        	+ "WHERE (authors.first_name LIKE '%" + name + "%' "
	        	+ "OR authors.last_name LIKE '%" + name + "%' )"
	        	+ "ORDER BY books.id "
	        	+ "OFFSET " + offset + " LIMIT " + limit + ";");
	        
	        while ( rs.next() ) {
	        	list.add(new BooksModel(rs.getInt("b_id"), 
	        			rs.getString("b_title"),
		          		rs.getString("b_publisher"),
		          		rs.getInt("b_pgs"),
		          		rs.getInt("b_pcs"),
		          		rs.getString("b_ean"),
		          		new AuthorsModel(rs.getString("a_first"), rs.getString("a_last"))
		          		));
	        }
	        rs.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;	
    }
    
    public List<GenresModel> fillComboBox(){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<GenresModel> list = new ArrayList<GenresModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in BooksManagerBean.getAllBooks!");
			}
			
			stm = conn.createStatement();
	        ResultSet rs = stm.executeQuery("SELECT genres.name AS gname FROM genres ORDER BY genres.name;");
	        
	        while ( rs.next() ) {
	        	list.add(new GenresModel(rs.getString("gname")
		          		));
	        }
	        rs.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;	
    }
    
    public List<BooksModel> findGenre(String genre, int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<BooksModel> list = new ArrayList<BooksModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in BooksManagerBean.getAllBooks!");
			}
			
			stm = conn.createStatement();
	        ResultSet rs = stm.executeQuery("SELECT books.id AS b_id, books.title AS b_title, "
	        		+ "books.publisher AS b_publisher, books.publication_date AS b_date, books.no_pages AS b_pgs, "
	        		+ "books.no_pieces AS b_pcs, books.ean_code AS b_ean, genres.name AS g_name, "
	        		+ "genres.add_text AS g_text "
	        		+ "FROM books "
	        		+ "JOIN genres ON genres.id = books.genre_id "
	        		+ "WHERE genres.name LIKE '%" + genre + "%' ORDER BY books.id "
	        		+ "OFFSET " + offset + " LIMIT " + limit + ";");
	        
	        while ( rs.next() ) {
	        	list.add(new BooksModel(rs.getInt("b_id"), 
	        			rs.getString("b_title"),
		          		rs.getString("b_publisher"),
		          		rs.getInt("b_pgs"),
		          		rs.getInt("b_pcs"),
		          		rs.getString("b_ean"),
		          		new GenresModel(rs.getString("g_name"), rs.getString("g_text"))
		          		));
	        }
	        rs.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;	
    }
    
    public List<BooksModel> findPublicationDate(Date date, int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<BooksModel> list = new ArrayList<BooksModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in BooksManagerBean.getAllBooks!");
			}
			
			stm = conn.createStatement();
	        ResultSet rs = stm.executeQuery("SELECT books.id AS b_id, books.title AS b_title, "
	        		+ "books.publisher AS b_publisher, books.publication_date AS b_date, "
	        		+ "books.no_pages AS b_pgs, books.no_pieces AS b_pcs, books.ean_code AS b_ean "
	        		+ "FROM books WHERE books.publication_date >= '" + date + "' ORDER BY books.id "
	        		+ "OFFSET " + offset + " LIMIT " + limit + ";");
	        
	        while ( rs.next() ) {
	        	list.add(new BooksModel(rs.getInt("b_id"), 
	        			rs.getString("b_title"),
		          		rs.getString("b_publisher"),
		          		rs.getInt("b_pgs"),
		          		rs.getInt("b_pcs"),
		          		rs.getString("b_ean"),
		          		rs.getDate("b_date")
		          		));
	        }
	        rs.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;		
	}
    
    public BooksModel getDetail(int book_id){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
  
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		BooksModel book = null;
		List<AuthorsModel> list_authors = new ArrayList<AuthorsModel>();
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in BooksManagerBean.getAllBooks!");
			}
			
			stm = conn.createStatement();
	        ResultSet rs = stm.executeQuery("SELECT books.id AS b_id, books.title AS b_title, genres.name AS g_name, " + 
	        	"books.publisher AS b_publisher, authors.first_name AS a_first, authors.last_name AS a_last, " +
	        	"books.publication_date AS b_date, books.no_pages AS b_pgs, books.no_pieces AS b_pcs, books.ean_code AS b_ean, " +
	        	"genres.add_text AS g_text, location.floor AS l_floor, location.room AS l_room, location.row AS l_row, " +
	        	"location.shelf AS l_shelf, authors.add_text AS a_text " +
	        	"FROM books " +
				"JOIN genres ON genres.id = books.genre_id " + 
				"JOIN location ON location.id = books.location_id " + 
				"JOIN authors_books ON authors_books.book_id = books.id " + 
				"JOIN authors ON authors.id = authors_books.author_id " + 
				"WHERE books.id = " + book_id +
				" ORDER BY books.id;");
	        
	        if ( rs.next() ){
	        		book = new BooksModel(rs.getInt("b_id"),
	        					rs.getString("b_title"), 
	        					rs.getString("b_publisher"), 
	        					rs.getDate("b_date"),
	        					rs.getInt("b_pgs"), 
	        					rs.getInt("b_pcs"), 
	        					rs.getString("b_ean"), 
	        					
	        					new GenresModel(rs.getString("g_name"), rs.getString("g_text")),
	        					
	        					new LocationModel(    					
	        					rs.getInt("l_floor"),
	        		 	        rs.getInt("l_room"),
	        		 	        rs.getInt("l_row"), 
	        		 	        rs.getInt("l_shelf"))
	        				        		 	        	        					
	        		);
	        		list_authors.add(new AuthorsModel(rs.getString("a_first"), rs.getString("a_last"), rs.getString("a_text")));
	        		while ( rs.next() ){
	        			list_authors.add(new AuthorsModel(rs.getString("a_first"), rs.getString("a_last"), rs.getString("a_text")));	        			
	        		}	
	        		book.setAuthors_list(list_authors);
	        }
	        rs.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return book;		
	}
    
}
