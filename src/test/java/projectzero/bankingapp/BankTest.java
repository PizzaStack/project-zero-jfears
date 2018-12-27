package projectzero.bankingapp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;



public class BankTest 
   
{
   
	@Test
    public void CustomerInitiated() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	assertEquals(customer.name, name);
    	assertEquals(customer.username, username);
    	assertEquals(customer.password, password);
    }
	@Test
    public void CostumerUsernameTest()
    {
		String name = "Justice";
		String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	assertEquals(customer.username, username);
    }
    
    @Test
    public void CostumerPasswordTest() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	assertEquals(customer.password, password);
    }
    
    @Test
    public void CustomerApplyForAccount() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	customer.applyForAccount();
    }
    
    @Test
    public void CustomerApplyJointlyForAccount() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	String name2 = "Tamara";
    	String username2 = "tfran";
    	String password2 = "heart";
    	Customer customer2 = new Customer(name2, username2, password2);
    	customer.applyJointAccount(customer2);
    }
    
    @Test
    public void CustomerWithdraw() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	Account account = new Account(20.00);
    	double amount = 5.60;
    	double check = (20.00 - 5.60);
    	customer.withdraw(account, amount);
    	assertEquals(account.balance, check, 0);
    }
    
    @Test
    public void CustomerDeposit() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	Account account = new Account(20.00);
    	double amount = 5.60;
    	double check = (20.00 + 5.60);
    	customer.deposit(account, amount);
    	assertEquals(account.balance, check, 0);
    }
    
    @Test
    public void CustomerTransfer() {
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	Account account = new Account(20.00);
    	Account account2 = new Account(20.00);
    	double amount = 5.60;
    	double checkpos = (20.00 + 5.60);
    	double checkneg = (20.00 - 5.60);
    	customer.transfer(account, account2, amount);
    	assertEquals(account.balance, checkneg, 0);
    	assertEquals(account2.balance, checkpos, 0);
    }
    
	@Test
    public void EmployeeInitiated() {
    	String name = "Taylor";
    	int employeeId = 1; 
    	Employee employee = new Employee(name, employeeId);
    	assertEquals(employee.name, name);
    	assertEquals(employee.employeeId, employeeId, 0);
    }
    
    @Test
    public void EmpoyeeViewAccountInfo() {
    	String name = "Taylor";
    	int employeeId = 1;
    	Employee employee = new Employee(name, employeeId);
    	Account account = new Account();
    	String[] check = {"Checking", String.valueOf(account.accountNumber), "0"};
     	Arrays.equals(employee.viewAccountInfo(account), check);
    }
    
    @Test
    public void EmployeeViewAccounBalance() {
    	String name = "Taylor";
    	int employeeId = 1;
    	Employee employee = new Employee(name, employeeId);
		Account account = new Account(20.00);
		double check = 20.00;
		assertEquals(employee.viewAccountBalance(account), check, 0);
    }
    
    @Test
    public void EmployeeViewCustomerInfo() {
    	String name = "Taylor";
    	int employeeId = 1;
    	Employee employee = new Employee(name, employeeId);
    	String name2 = "Justice";
    	String username = "jfears";
    	String password = "smile";
       	Customer customer = new Customer(name2, username, password);
       	String[] check = {name2};
       	Arrays.equals(employee.viewCustomerInfo(customer), check);
    }
    
    @Test
    public void employeeDenyAccountApp() {
    	String name = "Taylor";
    	int employeeId = 1;
    	Employee employee = new Employee(name, employeeId);
    	String name2 = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name2, username, password);
    	Application app = customer.applyForAccount();
    	employee.reviewAccountApp(app, Status.DENIED);
    	assertEquals(app.status, Status.DENIED);
    }
    
    @Test
    public void employeeApproveAccountApp() {
    	String name = "Taylor";
    	int employeeId = 1;
    	Employee employee = new Employee(name, employeeId);
    	String name2 = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name2, username, password);
    	Application app = customer.applyForAccount();
    	employee.reviewAccountApp(app, Status.APPROVED);
    	assertEquals(app.status, Status.APPROVED);
    }
    
    @SuppressWarnings("unused")
	@Test
    public void adminInstantiated() {
    	Admin admin = new Admin();
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    }
    
    @Test
    public void adminViewAccountInfo() {
    	Admin admin = new Admin();
    	Account account = new Account();
    	String[] check = {"Checking", String.valueOf(account.accountNumber), "0"};
     	Arrays.equals(admin.viewAccountInfo(account), check);
    }
    
    @Test
    public void adminViewAccountBalance() {
    	Admin admin = new Admin();
    	Account account = new Account(20.00);
    	double check = 20.00;
     	assertEquals(admin.viewAccountBalance(account), check, 0);
    }
    
    @Test
    public void adminViewCustomerInfo() {
    	Admin admin = new Admin();
    	Customer customer = new Customer();
    	admin.viewCustomerInfo(customer);
    }
    
    @Test
    public void adminApproveAccountApp() {
    	Admin admin = new Admin();
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	Application app = customer.applyForAccount();
    	admin.reviewAccountApp(app, Status.APPROVED);
    	assertEquals(app.status, Status.APPROVED);
    	
    }
    
    @Test
    public void adminDenyAccountApp() {
    	Admin admin = new Admin();
    	String name = "Justice";
    	String username = "jfears";
    	String password = "smile";
    	Customer customer = new Customer(name, username, password);
    	Application app = customer.applyForAccount();
    	admin.reviewAccountApp(app, Status.DENIED);
    	assertEquals(app.status, Status.DENIED);
    	
    }
    
    @Test
    public void adminWithdraw() {
    	Admin admin = new Admin();
    	Account account = new Account(20.00);
    	double amount = 5.60;
    	double check = (20.00 - 5.60);
    	admin.withdraw(account, amount);
    	assertEquals(account.balance, check, 0);
    }
    
    @Test
    public void AdminTransfer() {
    	Admin admin = new Admin();
    	Account account = new Account(20.00);
    	Account account2 = new Account(20.00);
    	double amount = 5.60;
    	double checkpos = (20.00 + 5.60);
    	double checkneg = (20.00 - 5.60);
    	admin.transfer(account, account2, amount);
    	assertEquals(account.balance, checkneg, 0);
    	assertEquals(account2.balance, checkpos, 0);
    }
    
    @Test
    public void AdminDeposit() {
    	Admin admin = new Admin();
    	Account account = new Account(20.00);
    	double amount = 5.60;
    	double check = (20.00 + 5.60);
    	admin.deposit(account, amount);
    	assertEquals(account.balance, check, 0);
    }
    
    @Test
    public void AdminCancelAccount() {
    	Admin admin = new Admin();
    	Account account = new Account();
    	admin.cancelAccount(account);
    } 
}
