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

import entity.EmployeesModel;
import test.EmployeeManagerRemote;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
public class EmployeeManagerBean implements EmployeeManagerRemote {

    public String tstPlus(String str1, String str2){
    	return (str1 + " " + str2 + "!!!");
    }
    
    public List<EmployeesModel> getAllEmployees(){
    	
    	Context ctx;
    	DataSource db = null;
    	Connection conn = null;
    	List<EmployeesModel> list = new ArrayList<EmployeesModel>();
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
	        ResultSet rs = stm.executeQuery("SELECT id, first_name, last_name, date_birth, add_info FROM employees ORDER BY id;");
	        
	        while ( rs.next() ) {
	        	list.add(new EmployeesModel(rs.getInt("id"), 
	        			rs.getString("first_name"),
		          		rs.getString("last_name"),
		          		rs.getDate("date_birth"),
		          		rs.getString("add_info")
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