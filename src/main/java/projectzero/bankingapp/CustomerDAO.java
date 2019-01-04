package projectzero.bankingapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerDAO {
	private DAOParent dao = new DAOParent();
	
	public Boolean checkUsername(String username) {
		String sql = "select * from customer where cususername=\'" + username + "\'";
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
		String sql = "select cuspassword from customer where  cususername=\'" + username + "\'";
		Boolean matches = false;
		String test;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				test = rs.getString("cuspassword");
				if(test.equals(password))
					matches = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return matches;
	}
	
	public Boolean addToDB(Customer customer) {
		String sql = "insert into customer values("+ customer.cusId+", \'"+customer.name+"\', \'"+ customer.username+"\', \'"+customer.password+"\')";
		Boolean added = false;
		try {
			Statement statement = dao.connection.createStatement();
			int rowsAffected = statement.executeUpdate(sql);
			if(rowsAffected > 0)
				added=true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return added;
	}
	
	public void closeConnection() {
		dao.closeConnection();
	}

	public Customer makeNewCustomer(String username, String password) {
		String sql = "select * from customer where cususername=\'" + username + "\'";
		Customer customer = new Customer();
		customer.password = password;
		customer.username = username;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				customer.name = rs.getString("cusname");
				customer.cusId = rs.getInt("cusid");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	

	public ArrayList<Double> getBalance(Customer customer) {
		String sql = "select accbal from account where cusid=" + customer.cusId;
		ArrayList<Double> balance= new ArrayList<Double>();
		int count = 0;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				balance.add(count, rs.getDouble("accbal"));
				count++;
			}
		}
		catch(SQLException e){
			e.printStackTrace();}
		return balance;
	}
	public ArrayList<String> getAccName(Customer customer){
		String sql = "select accname from account where cusid=" + customer.cusId;
		ArrayList<String> names = new ArrayList<String>();
		int count = 0;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				names.add(count, rs.getString("accname"));
				count++;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();}
		return names;
	}

	public ArrayList<Integer> getCusIds(){
		String sql = "select cusid from customer";
		ArrayList<Integer> cusIds = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
				cusIds.add(rs.getInt("cusid"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cusIds;
	}
	
	public ArrayList<String> getCusNames(){
		String sql = "select cusname from customer";
		ArrayList<String> cusNames = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
				cusNames.add(rs.getString("cusname"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cusNames;
	}
		
	public ArrayList<String> getCusUsernames(){
		String sql = "select cususername from customer";
		ArrayList<String> cusUsernames = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next())
				cusUsernames.add(rs.getString("cususername"));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cusUsernames;
	}
	
	public Customer applyJoint(int idWith) {
		String sql = "select * from customer where cusid=" + idWith;
		Customer customer2 = new Customer();
		customer2.cusId = idWith;
		
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				customer2.name = rs.getString("cusname");
				customer2.password = rs.getString("cususername");
				customer2.username = rs.getString("cuspassword");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customer2;
	}
}
