package persistanceManagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import entity.BooksModel;
import entity.Borrowed_booksModel;
import entity.EmployeesModel;
import entity.MembersModel;
import entity.StateModel;
import test.MembersManagerRemote;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
public class MembersManagerBean implements MembersManagerRemote {
	
	private static Logger LOG = Logger.getLogger(MembersManagerBean.class.getName());
    
    public List<MembersModel> getAllMembers(int limit, int offset){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<MembersModel> list = new ArrayList<MembersModel>();
		try {
			ctx = new InitialContext();
			db = (DataSource) ctx.lookup("java:/PostgresDS");
		} catch (NamingException e) {
			LOG.severe("Error: "+e);
		}
		Statement stm1 = null;
		Statement stm2 = null;
		int min_id = 9999999, max_id = 0;
		
		try{
			conn = db.getConnection();
			if (conn == null) {
				LOG.severe("Failed to make connection in MembersManagerBean.getAllMembers!");
			}
			
			stm1 = conn.createStatement();
	        ResultSet rs1 = stm1.executeQuery("SELECT id, first_name, last_name, date_birth, email, telephone, address, member_from "
	        		+ "FROM members ORDER BY id OFFSET " + offset + " LIMIT " + limit + ";");
	        	               
	        while( rs1.next() ){
	        	list.add(new MembersModel(rs1.getInt("id"),
		           rs1.getString("first_name"),
		           rs1.getString("last_name"),
		           rs1.getDate("date_birth"),
		           rs1.getString("email"),
		           rs1.getString("telephone"),
		           rs1.getString("address"),
		           rs1.getDate("member_from")
	        			));
	        	
	        	if (rs1.getInt("id") < min_id) min_id = rs1.getInt("id");
	        	if (rs1.getInt("id") > max_id) max_id = rs1.getInt("id");
	        }

	        rs1.close();
	        
	        stm2 = conn.createStatement();
	        ResultSet rs2 = stm2.executeQuery("SELECT member_id, COUNT(available_id) AS cnt FROM borrowed_books " +
	        		"GROUP BY member_id HAVING member_id >= " + min_id + " AND member_id <= " + max_id + 
	        		" ORDER BY member_id;");
	        	        
	        while ( rs2.next() ) {	  
	           for (MembersModel m : list) {
	        	   if (m.getId() == rs2.getInt("member_id")){
	        		   m.setNum_of_borrowed(rs2.getInt("cnt"));
	        	   }
	           }
	        }
	        
	        rs2.close();
		}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}
		finally {
			try { if (stm1 != null) stm1.close(); } catch (Exception e) {};
			try { if (stm2 != null) stm2.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		
		return list;	
    }
    
    public List<Borrowed_booksModel> getMemberListOfBorrowedBooks(int member_id){	

    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<Borrowed_booksModel> list = new ArrayList<Borrowed_booksModel>();
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
				LOG.severe("Failed to make connection in MembersManagerBean.getMemberListOfBorrowedBooks!");
			}
				
			stm = conn.createStatement();
			ResultSet rs = null;
					
			rs = stm.executeQuery("SELECT borrowed_books.id AS id, books.title AS title, books.publisher AS publisher, "
					+ "borrowed_books.borrowed_from AS from, borrowed_books.borrowed_to AS to, "
					+ "state.type_of_state AS state, employees.first_name AS first_name, "
					+ "employees.last_name AS last_name, employees.add_info AS depart FROM borrowed_books "
					+ "JOIN available_books ON available_books.id = borrowed_books.available_id " 
					+ "JOIN books ON books.id = available_books.book_id "
					+ "JOIN state ON state.id = available_books.state_id "
					+ "JOIN employees ON employees.id = borrowed_books.employee_id "
					+ "WHERE borrowed_books.member_id = " + member_id + " ORDER BY borrowed_books.borrowed_from DESC;");
					
			while ( rs.next() ) {
					list.add(new Borrowed_booksModel(rs.getInt("id"),
						           new BooksModel(rs.getString("title"), rs.getString("publisher")),
						           rs.getDate("from"),
						           rs.getDate("to"), 
						           new StateModel(rs.getString("state")), 
						           new EmployeesModel(rs.getString("first_name"), rs.getString("last_name"), rs.getString("depart"))
						           ));
			}
			rs.close();
			}
		catch (SQLException e1) {
			LOG.severe("Error: "+e1);
		}	
		finally {
			try { if (stm != null) stm.close(); } catch (Exception e) {};
		    try { if (conn!= null) conn.close(); } catch (Exception e) {};
		}
				
		return list;
	}
    
    public List<MembersModel> filterByNumBorrowed(int limit, int offset, int num){
		
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<MembersModel> list = new ArrayList<MembersModel>();
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
				LOG.severe("Failed to make connection in MembersManagerBean.filterByNumBorrowed!");
			}
			
			stm = conn.createStatement();
			ResultSet rs = null;
			
			if(num > 0){
			
				rs = stm.executeQuery("SELECT id, first_name, last_name, date_birth, email, telephone, address, member_from "
	        		+ "FROM members WHERE id IN (SELECT member_id AS cnt FROM borrowed_books GROUP BY member_id "
	        		+ "HAVING COUNT(available_id) = " + num + ") ORDER BY id OFFSET " + offset + " LIMIT " + limit + ";");
			}
			else if (num == 0){
				
				rs = stm.executeQuery("SELECT id, first_name, last_name, date_birth, email, telephone, address, member_from "
		        	+ "FROM members WHERE id NOT IN (SELECT member_id AS cnt FROM borrowed_books GROUP BY member_id "
		        	+ "HAVING COUNT(available_id) > " + num + ") ORDER BY id OFFSET " + offset + " LIMIT " + limit + ";");				
			}
			
			while ( rs.next() ) {
				list.add(new MembersModel(rs.getInt("id"),
				           rs.getString("first_name"),
				           rs.getString("last_name"),
				           rs.getDate("date_birth"),
				           rs.getString("email"),
				           rs.getString("telephone"),
				           rs.getString("address"),
				           rs.getDate("member_from"),
				           num
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
    
    public List<MembersModel> findMember(String find){
		
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<MembersModel> list = new ArrayList<MembersModel>();
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
				LOG.severe("Failed to make connection in MembersManagerBean.findMember!");
			}
			
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT id, first_name, last_name, date_birth FROM members "
					+ "WHERE first_name LIKE '%" + find + "%' OR last_name LIKE '%" + find + "%' ORDER BY id;" );
					
			while ( rs.next() ) {
					list.add(new MembersModel(rs.getInt("id"),
						           rs.getString("first_name"),
						           rs.getString("last_name"),
						           rs.getDate("date_birth")
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
    
    public boolean changeMember(int member_id){
    	return false;
    }

}
