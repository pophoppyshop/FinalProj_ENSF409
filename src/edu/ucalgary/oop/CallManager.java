package ucalgary.edu.oop;

import java.util.List;
import java.util.ArrayList;

public class CallManager{
	private List <CrisisCall> callList;
	
	public CallManager() {
		callList = new ArrayList<>
	}
	
	public void addCall(CrisisCall call) {
		callList.add(call);
	}
	
	public void modifyCallDetails(CrisisCall call) {
		// still need to implement
	}
	
	public void updateStatus(CrisisCall call, String status) {
		call.setStatus(status);
	}
	
	public CrisisCall[] getCallList() {
		CrisisCall [] callListArr = new CrisisCall[callList.size()];
		
		for (int i = 0; i < callList.size(); i++) {
			callListArr[i] = callList.get(i); 
		}
		return callListArr;
	}
}
