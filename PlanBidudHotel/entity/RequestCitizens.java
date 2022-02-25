package entity;

import Utils.RelationType;

public class RequestCitizens {
	
	private long relativesID;
	private int requestID;
	private RelationType relation;
	
	public RequestCitizens(long relativesID, int requestID, String relation) {
		super();
		this.relativesID = relativesID;
		this.requestID = requestID;
		if(relation.equals("Partner"))
			this.relation = RelationType.PARTNER;
		else if(relation.equals("Child"))
			this.relation = RelationType.CHILD;
	}

	public long getRelativesID() {
		return relativesID;
	}

	public void setRelativesID(long relativesID) {
		this.relativesID = relativesID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public RelationType getRelation() {
		return relation;
	}

	public void setRelation(RelationType relation) {
		this.relation = relation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (relativesID ^ (relativesID >>> 32));
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
		RequestCitizens other = (RequestCitizens) obj;
		if (relativesID != other.relativesID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RequestCitizens [relativesID=" + relativesID + ", requestID=" + requestID + ", relation=" + relation
				+ "]";
	}
	
	

}
