package projectzero.bankingapp;

import java.util.Random;

public class Application {
	protected int cusId;
	protected int jointId = 0;
	protected int appId;
	protected Status status = Status.PENDING;
	
	public Application(int cusId) {
		this.cusId = cusId;
		setId();
	}
	
	public Application(int cusId, int cusId2) {
		this.cusId = cusId;
		this.jointId = cusId2;
		setId();
		
	}

	public void makeDecision(Status decision) {
		this.status = decision;
	}
	
	public void setId() {
		Random rand = new Random();
		this.appId = rand.nextInt(100)+1;
	}
	
}
