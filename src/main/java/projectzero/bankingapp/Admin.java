package projectzero.bankingapp;

public class Admin extends Worker{



	public void transfer(Account account, Account account2, double amount) {
		if (account.balance > amount) {
			account.withdraw(amount);
			account2.deposit(amount);
		}
		
	}

	public void withdraw(Account account, double amount) {
		account.withdraw(amount);
		
	}

	public void deposit(Account account, double amount) {
		account.deposit(amount);
		
	}

	public void cancelAccount(Account account) {
		account = null;
	}

}
