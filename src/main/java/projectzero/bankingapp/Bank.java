package projectzero.bankingapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
	public static void main( String[] args )
    {
        Scanner scn = new Scanner(System.in);
    	System.out.println( "\tWelcome to the Bank of Justice!" );
        System.out.print("\t\t   Menu\n\tRegister as a new customer enter\t\t1\n\tLog-in\t\t\t\t\t\t2\n");
        int menuChoice = scn.nextInt();
        int accountAction = 0;
        int empChoice = 0;
        String username;
        String password;
        CustomerDAO checkCus = new CustomerDAO();
        AccountDAO checkAcc = new AccountDAO();
        WorkerDAO checkWork = new WorkerDAO();
        ApplicationDAO checkApp = new ApplicationDAO();
        int attempt = 0;
        Boolean loggedinCus = false;
        Boolean loggedinEmp = false;
        Customer customer = new Customer();
        switch(menuChoice){ 
    	case 1:
    		System.out.print("\tPlease enter your name: ");
    		String cusname = scn.next();
    		System.out.print("\tPlease enter your desired username: ");
    		String cusUsername = scn.next();
    		while(!checkCus.checkUsername(cusUsername)) {
    			System.out.print("\tThat username is taken please choose a different user name: ");
    			cusUsername=scn.next();
    		};
    		System.out.print("\tPlease enter your desired password: ");
    		String cusPass = scn.next();
    		Customer registeredCustomer = new Customer(cusname, cusUsername, cusPass);
    		if(!checkCus.addToDB(registeredCustomer));
    		System.out.println("\tCongradulations " + cusname +" is now registered!");
    		checkCus.addToDB(registeredCustomer);
    		break;
    	
        case 2:
        		System.out.println("\tCustomer log-in enter\t\t\t\t1\n\tEmployee log-in enter\t\t\t\t2");
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
        			loggedinCus= true;
        			customer = checkCus.makeNewCustomer(username, password);
        			System.out.println("\n\tWelcome back " + customer.name);
        			System.out.println("\n\tApply for new account \t\t\t\t1\n\tView an account balance(s)\t\t\t2\n\tMake a withdrawl\t\t\t\t3"
        					+"\n\tMake a transfer\t\t\t\t\t4\n\tMake a deposit\t\t\t\t\t5");
        			accountAction = scn.nextInt();
        		}
        			
    				do {
						switch (accountAction) {
						case 1:
							System.out.println("\tWould you like to apply jointly with someone else? (y/n)");
							String jointChoice = scn.next();
							if (jointChoice.equalsIgnoreCase("y")) {
								System.out.println(
										"\tPlease enter the customer id of the person you would like to apply with: ");
								int idWith = scn.nextInt();
								Customer customer2 = checkCus.applyJoint(idWith);
								Application applicationJoint = customer.applyJointAccount(customer2);
								checkApp.updateTable(applicationJoint);
							} else {
								Application application = customer.applyForAccount();
								checkApp.updateTable(application);
								System.out.println("\tYour application has been subbmitted.");
								break;
							}
							break;
						case 2:
							System.out.println("\tAccount balances:\n");
							ArrayList<Double> accBalance = checkCus.getBalance(customer);
							ArrayList<String> names = checkCus.getAccName(customer);
							for (int i = 0; i < accBalance.size(); i++) {
								System.out.println("\tAccount Name: " + names.get(i) + "\t\tAccount Balance: $"
										+ accBalance.get(i));
							}
							break;
						case 3:
							System.out.println("\tEnter the account number you would like to withdraw from: ");
							int accNum = scn.nextInt();
							Account account = new Account();
							if (checkAcc.checkAccNum(accNum, customer))
								account = checkAcc.createAccObj(accNum);
							else {
								System.out.println("\tAccount not found");
								break;
							}
							System.out.println("\tEnter the amount you would like to withdraw: ");
							Double amount = scn.nextDouble();
							if (account.withdraw(amount)) {
								System.out.println("\tTransaction completed");
								checkAcc.updateAccbal(account.accountNumber, account.balance);
								break;
							} else {
								System.out.println("\tPotential overdraft detected, transation not complete.");
								break;
							}
						case 4:
							System.out.println("\tEnter the account number you would like to transfer FROM: ");
							int accFrom = scn.nextInt();
							Account accountFrom = new Account();
							Account accountTo = new Account();
							if (checkAcc.checkAccNum(accFrom, customer))
								accountFrom = checkAcc.createAccObj(accFrom);
							else {
								System.out.println("\tAccount not found");
								break;
							}
							System.out.println("\tEnter the account number you would like to transfer TO: ");
							int accTo = scn.nextInt();
							if (checkAcc.checkAccNum(accTo, customer))
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
							if (checkAcc.checkAccNum(accNum2, customer))
								account = checkAcc.createAccObj(accNum2);
							else {
								System.out.println("\tAccount not found");
								break;
							}
							System.out.println("\tEnter the amount you would like to deposit: ");
							Double amount2 = scn.nextDouble();
							if (amount2 > 0.00) {
								account2.deposit(amount2);
								System.out.println("\tTransaction completed");
								checkAcc.updateAccbal(account.accountNumber, account.balance);
								break;
							}
							
							break;
						}
						System.out.print("\tTo log-out type exit, otherwise enter another menu option number: ");
						String logOutChoice = scn.next();	
						if (logOutChoice.equalsIgnoreCase("exit")) {
								loggedinCus = false;
								scn.close();
						   		checkCus.closeConnection();
								break;
						}
							else
								accountAction = Integer.parseInt(logOutChoice);
					
					} while (loggedinCus);
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
           			if(attempt > 2) {
           				System.out.println("\tMaximum attempts reached, please try again later.");
           				break;
           			}
        			}
           			 
           				loggedinEmp = true;
           				System.out.print("\n\tView applications\t\t\t\t1\n\tView customer information\t\t\t2\n\tView account information\t\t\t3");
            			if(checkWork.checkAdmin(username)) {
            				System.out.print("\n\tWithdraw from an account\t\t\t4\n\t"
            						+ "Deposit into an account\t\t\t\t5\n\t"
            						+"Transfer between accounts\t\t\t6\n\tCancel an account\t\t\t\t7\n");
            				empChoice = scn.nextInt();
            			}
           			
        			
        			
        			
        			}
        			do { 
						switch (empChoice) {
						case 1:
							System.out.println(
									"\tCustomer ID\t\t\tApplication Status\t\t\tJoint App\t\t\tApplication Number");
							ArrayList<Integer> cusIds = checkApp.getAppCusIds();
							ArrayList<String> statuses = checkApp.getAppStatuses();
							ArrayList<Boolean> joints = checkApp.getAppJoint();
							ArrayList<Integer> appIds = checkApp.getAppIds();
							for (int i = 0; i < cusIds.size(); i++) {
								System.out.print("\t" + cusIds.get(i));
								System.out.print("\t\t\t\t" + statuses.get(i));
								if (joints.get(i))
									System.out.print("\t\t\t\t\tYes");
								else
									System.out.print("\t\t\t\t\tNo");
								System.out.print("\t\t\t\t" + appIds.get(i) + "\n");

							}
							System.out.print("\tWould you like to approve or deny an application? (y/n) ");
							String appChoice = scn.next();
							if (appChoice.equalsIgnoreCase("y")) {
								System.out.print("\tEnter the application number you would like to review: ");
								int accNum3 = scn.nextInt();
								System.out.print("\n\tApprove or Deny?");
								String decision = scn.next();
								if (decision.equalsIgnoreCase("Approve"))
									checkApp.approveApp(accNum3);
								else
									checkApp.denyApp(accNum3);
							}
							break;
						case 2:
							System.out.println("\tCustomer ID\t\t\tCustomer Name\t\t\tCustomer Username");
							ArrayList<Integer> allcusIds = checkCus.getCusIds();
							ArrayList<String> cusNames = checkCus.getCusNames();
							ArrayList<String> cusUsernames = checkCus.getCusUsernames();
							for (int i = 0; i < allcusIds.size(); i++)
								System.out.println("\t" + allcusIds.get(i) + "\t\t\t\t" + cusNames.get(i) + "\t\t\t\t"
										+ cusUsernames.get(i));
							break;
						case 3:
							System.out.println("\tCustomer ID\t\t\tApplication Name\t\t\tAccount Number\t\t\tBalance");
							ArrayList<Integer> allcusIds2 = checkCus.getCusIds();
							ArrayList<String> accNames = checkAcc.getAccNames();
							ArrayList<Integer> accNums = checkAcc.getAccNums();
							ArrayList<Double> accBals = checkAcc.getAccBals();
							for (int i = 0; i < allcusIds2.size(); i++)
								System.out.println("\t" + allcusIds2.get(i) + "\t\t\t\t" + accNames.get(i) + "\t\t\t\t"
										+ accNums.get(i) + "\t\t\t\t" + accBals.get(i));
							break;
						case 4:
							System.out.println("\tEnter the account number you would like to withdraw from: ");
							int accNum3 = scn.nextInt();
							Account account3 = new Account();
							if (checkAcc.checkAccNum(accNum3)) {
								account3 = checkAcc.createAccObj(accNum3);
								System.out.println("\tEnter the amount you would like to withdraw: ");
								double amount3 = scn.nextDouble();
								account3.withdraw(amount3);
							} else {
								System.out.println("\tAccount not found");
								break;
							}
							break;

						case 5:
							System.out.println("\tEnter the account number you would like to deposit into: ");
							int accNum4 = scn.nextInt();
							Account account4 = new Account();
							if (checkAcc.checkAccNum(accNum4)) {
								account4 = checkAcc.createAccObj(accNum4);
								System.out.println("\tEnter the amount you would like to deposit: ");
								double amount4 = scn.nextDouble();
								account4.deposit(amount4);
							} else {
								System.out.println("\tAccount not found");
								break;
							}
							break;
						case 6:
							System.out.println("\tEnter the account number you would like to transfer FROM: ");
							int accFrom2 = scn.nextInt();
							Account accountFrom2 = new Account();
							Account accountTo2 = new Account();
							if (checkAcc.checkAccNum(accFrom2))
								accountFrom2 = checkAcc.createAccObj(accFrom2);
							else {
								System.out.println("\tAccount not found");
								break;
							}
							System.out.println("\tEnter the account number you would like to transfer TO: ");
							int accTo2 = scn.nextInt();
							if (checkAcc.checkAccNum(accTo2))
								accountTo2 = checkAcc.createAccObj(accTo2);
							else {
								System.out.println("\tAccount not found");
								break;
							}
							System.out.println("\tEnter the amount you would like to transfer: ");
							Double amountTran2 = scn.nextDouble();
							accountFrom2.withdraw(amountTran2);
							accountTo2.deposit(amountTran2);
							checkAcc.updateAccbal(accountTo2.accountNumber, accountTo2.balance);
							checkAcc.updateAccbal(accountFrom2.accountNumber, accountFrom2.balance);
							break;
						case 7:
							System.out.println("\tEnter the account number you would like to cancel: ");
							int accCancel = scn.nextInt();
							if (checkAcc.checkAccNum(accCancel)) {
								checkAcc.cancel(accCancel);
								System.out.println("\tAccount canceled");
							} else {
								System.out.println("\tAccount not found");
								break;
							}
							break;
						}
							System.out.print("\tTo log-out type exit, otherwise enter another menu option number: ");
							String logOutChoice = scn.next();	
							if (logOutChoice.equalsIgnoreCase("exit")) {
									loggedinCus = false;
									scn.close();
							   		checkCus.closeConnection();
									break;
							}
								else
									empChoice = Integer.parseInt(logOutChoice);
						
					} while (loggedinEmp);
        			}
        		break;
        		}
        
        }
}