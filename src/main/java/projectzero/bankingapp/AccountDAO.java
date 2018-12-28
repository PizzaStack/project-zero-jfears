package projectzero.bankingapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccountDAO {
	private DAOParent dao = new DAOParent();
	
	public Boolean checkAccNum(int accNum, Customer customer) {
		String sql = "select * from account where accnum=" + accNum;
		Boolean rs = false;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				int checkCusId = resultSet.getInt("cusid");
				if(checkCusId == customer.cusId)
					rs = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public Account createAccObj(int accNum) {
		String sql = "select * from account where accnum=" + accNum;
		Account account = new Account();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				String name = rs.getString("accname");
				Double balance = rs.getDouble("accbal");
				account.name = name;
				account.accountNumber = accNum;
				account.balance = balance;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();}
		return account;
	}

	public void updateAccbal(int accountNumber, double balance) {
		String sql = "update account set accbal=" + balance + "where accnum=" +accountNumber;
		try {
			Statement statement = dao.connection.createStatement();
			statement.execute(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();}
	}

	public Boolean checkAccNum(int accNum) {
		String sql = "select * from account where accnum=" + accNum;
		Boolean rs = false;
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				rs = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void cancel(int accCancel) {
		String sql = "delete * from account where accnum=" + accCancel;
		try {
			Statement statement = dao.connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getAccNames() {
		String sql = "select accname from account";
		ArrayList<String> accNames = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) 
				accNames.add(resultSet.getString("accname"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return accNames;
	}

	public ArrayList<Integer> getAccNums() {
		String sql = "select accnum from account";
		ArrayList<Integer> accNums = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) 
				accNums.add(resultSet.getInt("accnum"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return accNums;
	}

	public ArrayList<Double> getAccBals() {
		String sql = "select accbal from account";
		ArrayList<Double> accBals = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) 
				accBals.add(resultSet.getDouble("accbal"));
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return accBals;
	}

}
