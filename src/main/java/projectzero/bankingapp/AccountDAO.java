package projectzero.bankingapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	private DAOParent dao = new DAOParent();
	
	public Boolean checkAccNum(int accNum, Customer customer) {
		String sql = "select * from account where accnum=\'" + accNum + "\'";
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
		String sql = "select * from account where accnum=\'" + accNum + "\'";
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

}
