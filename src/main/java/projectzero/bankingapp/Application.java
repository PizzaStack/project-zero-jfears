package projectzero.bankingapp;

public class Application {
	protected int cusId;
	protected int jointInfo[] = new int[2];
	protected Status status = Status.PENDING;
	
	public Application(int cusId) {
		this.cusId = cusId;
	}
	
	public Application(int cusId, int cusId2) {
		this.jointInfo[0] = cusId;
		this.jointInfo[1] = cusId2;
		
	}

	public void makeDecision(Status decision) {
		this.status = decision;
	}
	
}
