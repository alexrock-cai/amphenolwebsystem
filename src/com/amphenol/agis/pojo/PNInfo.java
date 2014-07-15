package com.amphenol.agis.pojo;

public class PNInfo 
{
	private String customerName;
	private String pn;
	private String customerPN;
	private String rev;
	private String team;
	private String wo;
	private String sn;
	private String customerSN;
	private String timeStamp;
	private String time;
	private String date;
	private String allProgStatus;
	private String leftProgStatus;
	private String rightProgStatus;
	private String verStatus;
	public PNInfo() {
	// TODO Auto-generated constructor stub
	}
	public PNInfo(String customerName, String pn, String customerPN,
			String rev, String team, String wo, String sn, String customerSN,
			String timeStamp) {
		super();
		this.customerName = customerName;
		this.pn = pn;
		this.customerPN = customerPN;
		this.rev = rev;
		this.team = team;
		this.wo = wo;
		this.sn = sn;
		this.customerSN = customerSN;
		this.timeStamp = timeStamp;
	}
	public PNInfo(String customerName, String pn, String wo, String customerSN) {
		super();
		this.customerName = customerName;
		this.pn = pn;
		this.wo = wo;
		this.customerSN = customerSN;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPn() {
		return pn;
	}
	public void setPn(String pn) {
		this.pn = pn;
	}
	public String getCustomerPN() {
		return customerPN;
	}
	public void setCustomerPN(String customerPN) {
		this.customerPN = customerPN;
	}
	public String getRev() {
		return rev;
	}
	public void setRev(String rev) {
		this.rev = rev;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getWo() {
		return wo;
	}
	public void setWo(String wo) {
		this.wo = wo;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCustomerSN() {
		return customerSN;
	}
	public void setCustomerSN(String customerSN) {
		this.customerSN = customerSN;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAllProgStatus() {
		return allProgStatus;
	}
	public void setAllProgStatus(String allProgStatus) {
		this.allProgStatus = allProgStatus;
	}
	public String getLeftProgStatus() {
		return leftProgStatus;
	}
	public void setLeftProgStatus(String leftProgStatus) {
		this.leftProgStatus = leftProgStatus;
	}
	public String getRightProgStatus() {
		return rightProgStatus;
	}
	public void setRightProgStatus(String rightProgStatus) {
		this.rightProgStatus = rightProgStatus;
	}
	public String getVerStatus() {
		return verStatus;
	}
	public void setVerStatus(String verStatus) {
		this.verStatus = verStatus;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allProgStatus == null) ? 0 : allProgStatus.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((customerPN == null) ? 0 : customerPN.hashCode());
		result = prime * result
				+ ((customerSN == null) ? 0 : customerSN.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((leftProgStatus == null) ? 0 : leftProgStatus.hashCode());
		result = prime * result + ((pn == null) ? 0 : pn.hashCode());
		result = prime * result + ((rev == null) ? 0 : rev.hashCode());
		result = prime * result
				+ ((rightProgStatus == null) ? 0 : rightProgStatus.hashCode());
		result = prime * result + ((sn == null) ? 0 : sn.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
		result = prime * result
				+ ((verStatus == null) ? 0 : verStatus.hashCode());
		result = prime * result + ((wo == null) ? 0 : wo.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PNInfo other = (PNInfo) obj;
		if (allProgStatus == null) {
			if (other.allProgStatus != null)
				return false;
		} else if (!allProgStatus.equals(other.allProgStatus))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerPN == null) {
			if (other.customerPN != null)
				return false;
		} else if (!customerPN.equals(other.customerPN))
			return false;
		if (customerSN == null) {
			if (other.customerSN != null)
				return false;
		} else if (!customerSN.equals(other.customerSN))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (leftProgStatus == null) {
			if (other.leftProgStatus != null)
				return false;
		} else if (!leftProgStatus.equals(other.leftProgStatus))
			return false;
		if (pn == null) {
			if (other.pn != null)
				return false;
		} else if (!pn.equals(other.pn))
			return false;
		if (rev == null) {
			if (other.rev != null)
				return false;
		} else if (!rev.equals(other.rev))
			return false;
		if (rightProgStatus == null) {
			if (other.rightProgStatus != null)
				return false;
		} else if (!rightProgStatus.equals(other.rightProgStatus))
			return false;
		if (sn == null) {
			if (other.sn != null)
				return false;
		} else if (!sn.equals(other.sn))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		if (verStatus == null) {
			if (other.verStatus != null)
				return false;
		} else if (!verStatus.equals(other.verStatus))
			return false;
		if (wo == null) {
			if (other.wo != null)
				return false;
		} else if (!wo.equals(other.wo))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PNInfo [customerName=" + customerName + ", pn=" + pn
				+ ", customerPN=" + customerPN + ", rev=" + rev + ", team="
				+ team + ", wo=" + wo + ", sn=" + sn + ", customerSN="
				+ customerSN + ", timeStamp=" + timeStamp + ", time=" + time
				+ ", date=" + date + ", allProgStatus=" + allProgStatus
				+ ", leftProgStatus=" + leftProgStatus + ", rightProgStatus="
				+ rightProgStatus + ", verStatus=" + verStatus + "]";
	}

	
}
