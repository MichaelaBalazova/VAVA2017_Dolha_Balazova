package persistanceManagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.GenresModel;
import entity.BooksModel;
import test.BooksManagerRemote;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
public class BooksManagerBean implements BooksManagerRemote {
    
    public List<BooksModel> getAllBooks(int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<BooksModel> list = new ArrayList<BooksModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) System.out.println("Failed to make connection!");
			
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
				System.out.println("Error: " + e1);
		}
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;	
    }

}
