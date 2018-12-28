package projectzero.bankingapp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ApplicationDAO {
	private DAOParent dao = new DAOParent();
	
	public void updateTable(Application application) {
		String sql = "insert into applications values("+ application.cusId + ",\'" + application.status 
					+ "\'," + "\'f\')"; 
		try {
			Statement statement = dao.connection.createStatement();
			statement.execute(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();}
	}

	public ArrayList<Integer> getAppCusIds() {
		String sql = "select cusid from applications";
		ArrayList<Integer> cusIds = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				cusIds.add(rs.getInt("cusid"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();}
	return cusIds;
	}
	
	public ArrayList<String> getAppStatuses(){
		String sql = "select appstatus from applications";
		ArrayList<String> statuses = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				statuses.add(rs.getString("appstatus"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();}
	return statuses;
		
	}
	
	public ArrayList<Boolean> getAppJoint(){
		String sql = "select jointapp from applications";
		ArrayList<Boolean> joints = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				joints.add(rs.getBoolean("jointapp"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();}
	return joints;
	}
	
	public ArrayList<Integer> getAppIds() {
		String sql = "select appid from applications";
		ArrayList<Integer> appIds = new ArrayList<>();
		try {
			Statement statement = dao.connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				appIds.add(rs.getInt("appid"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();}
	return appIds;
	}

	public void approveApp(int accNum) {
		String sql = "update applications set appstatus='APPROVED' where appid=" + accNum;
		try {
			Statement statement = dao.connection.createStatement();
			statement.execute(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();}
		
	}

	public void denyApp(int accNum) {
		String sql = "update applications set appstatus='DENIED' where appid=" + accNum;
		try {
			Statement statement = dao.connection.createStatement();
			statement.execute(sql);
		}
		catch(SQLException e) {
			e.printStackTrace();}
		
	}

}
