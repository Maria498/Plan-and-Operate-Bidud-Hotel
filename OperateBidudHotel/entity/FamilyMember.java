package entity;

public class FamilyMember {

	private int id;
	private int qCitizenID;
	private String relation;
	
	
	public FamilyMember(int id, int qCitizenID, String relation) {
		super();
		this.id = id;
		this.qCitizenID = qCitizenID;
		this.relation = relation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getqCitizenID() {
		return qCitizenID;
	}
	public void setqCitizenID(int qCitizenID) {
		this.qCitizenID = qCitizenID;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		FamilyMember other = (FamilyMember) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "FamilyMember [id=" + id + ", qCitizenID=" + qCitizenID + ", relation=" + relation + "]";
	}
	
	
	
}
