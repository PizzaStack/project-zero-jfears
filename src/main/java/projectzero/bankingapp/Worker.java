package projectzero.bankingapp;

public abstract class Worker{
	protected String name;
	protected int employeeId;
	
	public Worker() {} 
	public double viewAccountBalance(Account account) {
		return account.balance;
	};
	public String[] viewCustomerInfo(Customer customer) {
		return customer.info();
	};
	public String[] viewAccountInfo(Account account) {
		return account.info();
	};
	public void reviewAccountApp(Application application, Status decision) {
		application.makeDecision(decision);
	};
}