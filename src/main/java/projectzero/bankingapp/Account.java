package projectzero.bankingapp;

import java.util.Random;
public class Account {

	protected String name;
	protected int accountNumber;
	protected double balance;
	
	public Account() {
		this.name = "Checking";
		this.balance = 0;
		this.accountNumber();
	}
	public Account(String name) {
		this.name = name;
		this.balance = 0;
		this.accountNumber();
	}
	
	public Account(String name, double amount) {
		this.name = name;
		this.balance = amount;
		this.accountNumber();
	}
	
	public Account(double amount) {
		this.name = "Checking";
		this.balance = amount;
		this.accountNumber();
	}
	private void accountNumber() {
		Random rand = new Random();
		this.accountNumber = rand.nextInt(2000000000)+1;
	}
	public int getAccountNumber() {
		return this.accountNumber;
		
	}
	public String[] info() {
		String[] info = {this.name, String.valueOf(this.accountNumber), String.valueOf(this.balance)};
		return info;
	}
	public Boolean withdraw(double amount) {
		Boolean rs = false;
		if(this.balance > amount) {
			this.balance = (this.balance - amount);
			rs = true;
		}
		return rs;
	}
	public void deposit(double amount) {
		this.balance = this.balance + amount;
	}
	public void cancel() {
		//TODO
	}

}
