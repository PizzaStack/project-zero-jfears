package projectzero.bankingapp;

public class Application {
	protected String[] info;
	protected Status status = Status.PENDING;
	
	public Application(String[] info) {
		this.info = info;
	}
	
	public void makeDecision(Status decision) {
		this.status = decision;
	}
	
}
