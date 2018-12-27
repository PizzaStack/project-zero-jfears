package projectzero.bankingapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank 
{
    public static void main( String[] args )
    {
        Scanner scn = new Scanner(System.in);
    	System.out.println( "\tWelcome to the Bank of Justice!" );
        System.out.print("\t\t   Menu\n\tLog-in enter\t\t\t\t\t1\n\tRegister as a new customer enter\t\t2\n");
        int menuChoice = scn.nextInt();
        String username;
        String password;
        CustomerDAO checkCus = new CustomerDAO();
        AccountDAO checkAcc = new AccountDAO();
        WorkerDAO checkWork = new WorkerDAO();
        int attempt = 0;
        switch(menuChoice){ 
        	case 1:
        		System.out.println("\tCustomer log-in enter\t\t\t\t1\n\tEmployee login-in enter\t\t\t\t2");
        		int loginChoice = scn.nextInt();
        		System.out.print("\tPlease enter your username: ");
        		username = scn.next();
        		switch(loginChoice) {
        		case 1:
        			if(checkCus.checkUsername(username)) {
            			System.out.print("\tPlease enter your password: ");
            			password = scn.next();
               			while(!checkCus.checkPassword(password, username) && attempt < 3){
               				System.out.println("\tIncorrect password");
                			System.out.print("\tPlease re-enter your password: ");
                			password = scn.next();
                  			attempt++;
               			if(attempt > 2) 
               				System.out.println("\tMaximum attempts reached, please try again later.");
               				break;
               			}
        			
        			Customer customer = checkCus.makeNewCustomer(username, password);
        			System.out.println("\n\tWelcome back " + customer.name);
        			System.out.println("\n\tApply for new account \t\t\t\t1\n\tView an account balance(s)\t\t\t2\n\tMake a withdrawl\t\t\t\t3"
        					+"\n\tMake a transfer\t\t\t\t\t4\n\tMake a deposit\t\t\t\t\t5");
        			int accountAction = scn.nextInt();
        			switch(accountAction) {
        			case 1:
        				System.out.println("\tWould you like to apply jointly with someone else? (y/n)");
        				String jointChoice = scn.next();
        				if(jointChoice.equalsIgnoreCase("y")) {
        					System.out.println("\tPlease enter the customer id of the person you would like to apply with: ");
        					int idWith = scn.nextInt();
        					Customer customer2 = checkCus.applyJoint(idWith);
        					Application application = customer.applyJointAccount(customer2);
        				}
        				else
        					customer.applyForAccount();
        				System.out.println("\tYour application has been subbmitted.");
        				break;
        			case 2:
        				System.out.println("\tAccount balances:\n");
        				ArrayList<Double> accBalance = checkCus.getBalance(customer);
        				ArrayList<String> names = checkCus.getAccName(customer);
        				for(int i=0;i<accBalance.size();i++) {
        					System.out.println("\tAccount Name: " + names.get(i)+ "\t\tAccount Balance: $" + accBalance.get(i));
        				}
        				break;
        			case 3:
        				System.out.println("\tEnter the account number you would like to withdraw from: ");
        				int accNum = scn.nextInt();
        				Account account = new Account();
        				if(checkAcc.checkAccNum(accNum,customer)) 
        					account = checkAcc.createAccObj(accNum);
        				else {
        					System.out.println("\tAccount not found");
        				    break;
        				}
           				System.out.println("\tEnter the amount you would like to withdraw: ");
           				Double amount = scn.nextDouble();
           				if(account.withdraw(amount)) { 
           					System.out.println("\tTransaction completed");
           					checkAcc.updateAccbal(account.accountNumber,account.balance);
           					break;
           				}
           				else {
           					System.out.println("\tPotential overdraft detected, transation not complete.");
           					break;
           				}
        			case 4:
        				System.out.println("\tEnter the account number you would like to transfer FROM: ");
        				int accFrom = scn.nextInt();
        				Account accountFrom = new Account();
        				Account accountTo = new Account();
        				if(checkAcc.checkAccNum(accFrom,customer)) 
        					accountFrom = checkAcc.createAccObj(accFrom);
        				else {
        					System.out.println("\tAccount not found");
        				    break;
        				}
        				System.out.println("\tEnter the account number you would like to transfer TO: ");
        				int accTo = scn.nextInt();
        				if(checkAcc.checkAccNum(accTo,customer)) 
        					accountTo = checkAcc.createAccObj(accTo);
        				else {
        					System.out.println("\tAccount not found");
        				    break;
        				}
        				System.out.println("\tEnter the amount you would like to transfer: ");
        				Double amountTran = scn.nextDouble();
        				accountFrom.withdraw(amountTran);
        				accountTo.deposit(amountTran);
        				checkAcc.updateAccbal(accountTo.accountNumber, accountTo.balance);
        				checkAcc.updateAccbal(accountFrom.accountNumber, accountFrom.balance);
        				break;
        			case 5:
        				System.out.println("\tEnter the account number you would like to withdraw from: ");
        				int accNum2 = scn.nextInt();
        				Account account2 = new Account();
        				if(checkAcc.checkAccNum(accNum2,customer)) 
        					account = checkAcc.createAccObj(accNum2);
        				else {
        					System.out.println("\tAccount not found");
        				    break;
        				}
           				System.out.println("\tEnter the amount you would like to deposit: ");
           				Double amount2 = scn.nextDouble();
           				if(amount2 > 0.00) { 
           					account2.deposit(amount2);
           					System.out.println("\tTransaction completed");
           					checkAcc.updateAccbal(account.accountNumber,account.balance);
           					break;
           				}
        			break;
        			}
        			}
        			break;
        		case 2:
        			if(checkWork.checkUsername(username)) {
            			System.out.print("\tPlease enter your password: ");
            			password = scn.next();
        			while(!checkWork.checkPassword(password, username) && attempt < 3){
           				System.out.println("\tIncorrect password");
            			System.out.print("\tPlease re-enter your password: ");
            			password = scn.next();
              			attempt++;
           			if(attempt > 2) 
           				System.out.println("\tMaximum attempts reached, please try again later.");
        			}
        		  }
        			break;
        		}
           		
        		break; 
        	case 2:
        		System.out.print("\tPlease enter your name: ");
        		String cusname = scn.next();
        		System.out.print("\tPlease enter your desired username: ");
        		String cusUsername = scn.next();
        		while(checkCus.checkUsername(cusUsername)) {
        			System.out.print("\tThat username is taken please choose a different user name: ");
        			cusUsername=scn.next();
        		};
        		System.out.print("\tPlease enter your desired password: ");
        		String cusPass = scn.next();
        		Customer registeredCustomer = new Customer(cusname, cusUsername, cusPass);
        		if(checkCus.addToDB(registeredCustomer));
        		System.out.println("\tCongradulations " + cusname +" is now registered!");
        		break;
        }
        scn.close();
   		checkCus.closeConnection();
    }
}

