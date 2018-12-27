package projectzero.bankingapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application {
	protected String[] info;
	protected List<String> jointInfo;
	protected Status status = Status.PENDING;
	
	public Application(String[] info) {
		this.info = info;
	}
	
	public Application(String[] info2, String[] info3) {
		this.jointInfo = new ArrayList<String>(info2.length + info3.length);
	    Collections.addAll(jointInfo, info2);
	    Collections.addAll(jointInfo, info3);
	    jointInfo.toArray(new String[jointInfo.size()]);
	}

	public void makeDecision(Status decision) {
		this.status = decision;
	}
	
}
