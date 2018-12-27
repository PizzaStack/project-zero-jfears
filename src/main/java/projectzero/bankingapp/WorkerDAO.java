package projectzero.bankingapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkerDAO {
	private DAOParent dao = new DAOParent();

	public boolean checkUsername(String username) {
		String sql = "select * from employee where empusername=\'" + username + "\'";
		Boolean rs = false;
		try {
			Statement statement = dao.connection.createStatement();
			rs = statement.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public Boolean checkPassword(String password, String username) {
		String sql = "select emppassword from employee where  empusername=\'" + username + "\'";
		Boolean matches = false;
		String test;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				test = rs.getString("emppassword");
			if(test.equals(password))
				matches = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return matches;
		
	}

}
