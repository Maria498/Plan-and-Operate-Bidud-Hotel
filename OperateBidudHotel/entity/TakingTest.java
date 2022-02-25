package entity;

public class TakingTest {

	private int testNum;
	private int nurseID;
	private int guestID;
	private String testResult;
	
	public TakingTest(int testNum, int nurseID, int guestID, String testResult) {
		super();
		this.testNum = testNum;
		this.nurseID = nurseID;
		this.guestID = guestID;
		this.testResult = testResult;
	}

	

	public int getGuestID() {
		return guestID;
	}

	public void setGuestID(int guestID) {
		this.guestID = guestID;
	}

	public int getNurseID() {
		return nurseID;
	}

	public void setNurseID(int nurseID) {
		this.nurseID = nurseID;
	}

	public int getTestNum() {
		return testNum;
	}

	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + guestID;
		result = prime * result + nurseID;
		result = prime * result + testNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TakingTest other = (TakingTest) obj;
		if (guestID != other.guestID)
			return false;
		if (nurseID != other.nurseID)
			return false;
		if (testNum != other.testNum)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "TakingTest [testNum=" + testNum + ", guestID=" + guestID + ", nurseID=" + nurseID + ", testResult="
				+ testResult + "]";
	}


	
	
}
