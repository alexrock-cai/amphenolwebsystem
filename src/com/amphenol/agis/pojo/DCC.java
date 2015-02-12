package com.amphenol.agis.pojo;

import com.amphenol.agis.model.DCCListModel;

public class DCC {
	
	private long id;
	private String customer;
	private String station;
	private String pn;
	private String type;
	private String rev;
	private String filepath;
	private String filename;
	private String lastmodify;
	private String operate;
	
	public DCC(){}
	
	public DCC(DCCListModel dcc){
		this.id = dcc.getLong("id");
		this.customer = dcc.getStr("customer");
		this.station = dcc.getStr("station");
		this.pn = dcc.getStr("pn");
		this.type = dcc.getStr("type");
		this.rev = dcc.getStr("rev");
		this.filepath = dcc.getStr("filepath");
		this.filename = dcc.getStr("filename");
		this.lastmodify = dcc.getStr("lastmodify");
		this.operate = dcc.getStr("operate");
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
	 * @return the customer
	 */
	public String getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}
	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}
	/**
	 * @return the pn
	 */
	public String getPn() {
		return pn;
	}
	/**
	 * @param pn the pn to set
	 */
	public void setPn(String pn) {
		this.pn = pn;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the rev
	 */
	public String getRev() {
		return rev;
	}
	/**
	 * @param rev the rev to set
	 */
	public void setRev(String rev) {
		this.rev = rev;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the lastmodify
	 */
	public String getLastmodify() {
		return lastmodify;
	}
	/**
	 * @param lastmodify the lastmodify to set
	 */
	public void setLastmodify(String lastmodify) {
		this.lastmodify = lastmodify;
	}
	/**
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}
	/**
	 * @param operate the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((filename == null) ? 0 : filename.hashCode());
		result = prime * result
				+ ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((lastmodify == null) ? 0 : lastmodify.hashCode());
		result = prime * result + ((operate == null) ? 0 : operate.hashCode());
		result = prime * result + ((pn == null) ? 0 : pn.hashCode());
		result = prime * result + ((rev == null) ? 0 : rev.hashCode());
		result = prime * result + ((station == null) ? 0 : station.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		DCC other = (DCC) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		if (filepath == null) {
			if (other.filepath != null)
				return false;
		} else if (!filepath.equals(other.filepath))
			return false;
		if (id != other.id)
			return false;
		if (lastmodify == null) {
			if (other.lastmodify != null)
				return false;
		} else if (!lastmodify.equals(other.lastmodify))
			return false;
		if (operate == null) {
			if (other.operate != null)
				return false;
		} else if (!operate.equals(other.operate))
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
		if (station == null) {
			if (other.station != null)
				return false;
		} else if (!station.equals(other.station))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DCC [id=" + id + ", customer=" + customer + ", station="
				+ station + ", pn=" + pn + ", type=" + type + ", rev=" + rev
				+ ", filepath=" + filepath + ", filename=" + filename
				+ ", lastmodify=" + lastmodify + ", operate=" + operate + "]";
	}
	
	
	
}
