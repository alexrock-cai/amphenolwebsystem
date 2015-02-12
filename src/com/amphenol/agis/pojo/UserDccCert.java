package com.amphenol.agis.pojo;

import com.amphenol.agis.model.DCCListModel;
import com.amphenol.agis.model.UserDccCertModel;
import com.amphenol.agis.model.UserModel;

public class UserDccCert {
	
	private long id;
	private User user;
	private DCC dcc;
	private String certTime;
	private int validity;
	private String validDate;
	private User certUser;
	private boolean available;
	
	public UserDccCert(){}
	
	public UserDccCert(UserDccCertModel certModel){
		this.id = certModel.getLong("id");
		this.user = new User(UserModel.dao.findById(certModel.getLong("userId")));
		this.dcc = new DCC(DCCListModel.dao.findById(certModel.getLong("dccId")));
		this.certTime = certModel.getStr("certTime");
		this.validity = certModel.getInt("validity");
		this.validDate = certModel.getStr("validDate");
		this.certUser = new User(UserModel.dao.findById(certModel.getLong("certUser")));
		this.available = certModel.getBoolean("available");
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the dcc
	 */
	public DCC getDcc() {
		return dcc;
	}
	/**
	 * @param dcc the dcc to set
	 */
	public void setDcc(DCC dcc) {
		this.dcc = dcc;
	}
	/**
	 * @return the certTime
	 */
	public String getCertTime() {
		return certTime;
	}
	/**
	 * @param certTime the certTime to set
	 */
	public void setCertTime(String certTime) {
		this.certTime = certTime;
	}
	/**
	 * @return the validity
	 */
	public int getValidity() {
		return validity;
	}
	/**
	 * @param validity the validity to set
	 */
	public void setValidity(int validity) {
		this.validity = validity;
	}
	/**
	 * @return the certUser
	 */
	public User getCertUser() {
		return certUser;
	}
	/**
	 * @param certUser the certUser to set
	 */
	public void setCertUser(User certUser) {
		this.certUser = certUser;
	}
	/**
	 * @return the available
	 */
	public boolean isAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/**
	 * @return the validDate
	 */
	public String getValidDate() {
		return validDate;
	}

	/**
	 * @param validDate the validDate to set
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result
				+ ((certTime == null) ? 0 : certTime.hashCode());
		result = prime * result
				+ ((certUser == null) ? 0 : certUser.hashCode());
		result = prime * result + ((dcc == null) ? 0 : dcc.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result
				+ ((validDate == null) ? 0 : validDate.hashCode());
		result = prime * result + validity;
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
		UserDccCert other = (UserDccCert) obj;
		if (available != other.available)
			return false;
		if (certTime == null) {
			if (other.certTime != null)
				return false;
		} else if (!certTime.equals(other.certTime))
			return false;
		if (certUser == null) {
			if (other.certUser != null)
				return false;
		} else if (!certUser.equals(other.certUser))
			return false;
		if (dcc == null) {
			if (other.dcc != null)
				return false;
		} else if (!dcc.equals(other.dcc))
			return false;
		if (id != other.id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (validDate == null) {
			if (other.validDate != null)
				return false;
		} else if (!validDate.equals(other.validDate))
			return false;
		if (validity != other.validity)
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDccCert [id=" + id + ", user=" + user.getName() + ", dcc=" + dcc.getFilename()
				+ ", certTime=" + certTime + ", validity=" + validity
				+ ", validDate=" + validDate + ", certUser=" + certUser.getName()
				+ ", available=" + available + "]";
	}
	

}
