package projectzero.bankingapp;

import java.util.ArrayList;

public class Customer {

	protected String name;
	protected String username;
	protected String password;
	protected ArrayList<Account> accounts;

	public Customer() {}
	public Customer (String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
		
	}
	public Application applyForAccount() {
		Application application = new Application(this.info());
		return application;
	}
	public void applyJointAccount(Customer customer2) {
		
		
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
