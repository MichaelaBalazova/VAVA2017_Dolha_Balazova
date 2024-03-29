package persistanceManagers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import entity.BooksModel;
import entity.StateModel;
import entity.Available_booksModel;
import test.AvailableBooksManagerRemote;

/**
 * Session Beana, ktora pracuje s DB tabulkou Available Books
 * @author Michaela, Domca
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AvailableBooksManagerBean implements AvailableBooksManagerRemote {
    
	private static Logger LOG = Logger.getLogger(AvailableBooksManagerBean.class.getName());
	@Resource javax.transaction.UserTransaction tx;
	
	/**
	 * metoda ktora vykonava select vsetkych dostupnych knih (state == Available) a strankuje zaznamy
	 */
    public List<Available_booksModel> getAllAvailableBooks(int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<Available_booksModel> list = new ArrayList<Available_booksModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm = null;
		
		try{
			conn = db.getConnection();
			if (conn == null) System.out.println("Failed to make connection!");
			
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT available_books.id AS id, state.type_of_state AS state, "
	        		+ "available_books.identifier AS identifier, books.title AS title, books.publisher AS publisher "
	        		+ "FROM available_books JOIN books ON books.id = available_books.book_id "
	        		+ "JOIN state ON state.id = available_books.state_id WHERE state.type_of_state = 'Available' ORDER BY available_books.id "
	        		+ "OFFSET " + offset + " LIMIT " + limit + ";");
	        
	        while ( rs.next() ) {
	        	list.add(new Available_booksModel(rs.getInt("id"), 
	        			new StateModel(rs.getString("state")), 
	        			rs.getString("identifier"), 
	        			new BooksModel(rs.getString("title"), rs.getString("publisher"))
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
    
    /**
	 * metoda ktora vykonava insert do tabulky Borrowed Books a updatuje stav knihy z Available na Borrowed 
	 * v tabulke Available Books
	 * pouzivane transakcie
	 */
	public boolean borrowBook(Date date_from, Date date_to, int available_id, int member_id, int employee_id){
		
		Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	boolean res = false;
    	
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stmt_id = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		
		try {
			tx.begin();
		} catch (NotSupportedException e2) { 
			LOG.severe("Error: "+e2);
		} catch (SystemException e2) { 
			LOG.severe("Error: "+e2);
		}
		
		try{
			conn = db.getConnection();
			if (conn == null) System.out.println("Failed to make connection!");

			int max_id = 0;
			stmt_id = conn.createStatement();
			
	        ResultSet rs = stmt_id.executeQuery("SELECT MAX(id) as max FROM borrowed_books");			
		    while(rs.next()){
	        	max_id = rs.getInt("max") + 1;
		    }
	        rs.close();		
						
			String createStatementString = "INSERT INTO borrowed_books(id, borrowed_from, borrowed_to, available_id, member_id, "
					+ "employee_id) VALUES(?,?,?,?,?,?)";
			stmt1 = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt1.setInt(1, max_id);
			stmt1.setDate(2, new java.sql.Date(date_from.getTime()));
			stmt1.setDate(3, new java.sql.Date(date_to.getTime()));
			stmt1.setInt(4, available_id);
			stmt1.setInt(5, member_id);
			stmt1.setInt(6, employee_id);			
			stmt1.executeUpdate();
			
			stmt2 = (PreparedStatement) conn.prepareStatement("UPDATE available_books SET state_id = 3 WHERE id = " + available_id + ";");			
			stmt2.executeUpdate();

			try {
				tx.commit();
			} catch (SecurityException e) { 
				LOG.severe("Error: "+e);
			} catch (IllegalStateException e) { 
				LOG.severe("Error: "+e);
			} catch (RollbackException e) { 
				LOG.severe("Error: "+e);
			} catch (HeuristicMixedException e) { 
				LOG.severe("Error: "+e);
			} catch (HeuristicRollbackException e) { 
				LOG.severe("Error: "+e);
			} catch (SystemException e) { 
				LOG.severe("Error: "+e);
			}
			
			res = true;

		} catch (SQLException e) {
			try {
				tx.setRollbackOnly();
			} catch (IllegalStateException e1) {
				LOG.severe("Error: "+e1);
			} catch (SystemException e1) { 
				LOG.severe("Error: "+e1);
			}
			res = false;
		} finally {
			try { if (stmt_id != null) stmt_id.close(); } catch (Exception e) {};
			try { if (stmt1 != null) stmt1.close(); } catch (Exception e) {};
			try { if (stmt2 != null) stmt2.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return res;
	}

}
