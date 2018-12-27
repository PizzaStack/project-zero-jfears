package projectzero.bankingapp;

import java.util.ArrayList;
import java.util.Random;

public class Customer {

	protected String name;
	protected int cusId;
	protected String username;
	protected String password;
	protected ArrayList<Account> accounts;

	public Customer() {}
	public Customer (String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	public void setId() {
		Random rand = new Random();
		this.cusId = rand.nextInt(2000000000)+1;
	}
	public void setUsername(String username) {
		this.username = username;
		
	}
	public Application applyForAccount() {
		Application application = new Application(this.info());
		return application;
	}
	public Application applyJointAccount(Customer customer2) {
		Application application = new Application(this.info(), customer2.info());
		return application;
		
	}
	public void withdraw(Account account, double amount) {
		account.withdraw(amount);
		
	}
	public void deposit(Account account, double amount) {
		account.deposit(amount);
		
	}
	public void transfer(Account account, Account account2, double amount) {
		if (account.balance > amount) {
			account.withdraw(amount);
			account2.deposit(amount);
		}
		
	}
	public String[] info() {
		String[] info = {this.name};
		return info;
	}

}
