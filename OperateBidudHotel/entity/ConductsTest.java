package entity;

public class ConductsTest {
	
	private int nurseID;
	private int testID;
	
	public ConductsTest(int nurseID, int testID) {
		super();
		this.nurseID = nurseID;
		this.testID = testID;
	}
	public int getNurseID() {
		return nurseID;
	}
	public void setNurseID(int nurseID) {
		this.nurseID = nurseID;
	}
	public int getTestID() {
		return testID;
	}
	public void setTestID(int testID) {
		this.testID = testID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nurseID;
		result = prime * result + testID;
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
		ConductsTest other = (ConductsTest) obj;
		if (nurseID != other.nurseID)
			return false;
		if (testID != other.testID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ConductsTest [nurseID=" + nurseID + ", testID=" + testID + "]";
	}

	
	
}