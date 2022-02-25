package entity;

import java.sql.Date;

public class CovidTest {
	
	private int testNum;
	private Date testDate;
	private String testTime;
	
	public CovidTest(int testNum, Date testDate, String testTime) {
		super();
		this.testNum = testNum;
		this.testDate = testDate;
		this.testTime = testTime;
	}
	public int getTestNum() {
		return testNum;
	}
	public void setTestNum(int testNum) {
		this.testNum = testNum;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		CovidTest other = (CovidTest) obj;
		if (testNum != other.testNum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CovidTest [testNum=" + testNum + ", testDate=" + testDate + ", testTime=" + testTime + "]";
	}
	
	

}
