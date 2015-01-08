package com.amphenol.agis.pojo;

public class TestData {
	private int id;
	private String sender;
	private String received;
	
	public TestData(int id,String sender,String received){
		this.id=id;
		this.sender=sender;
		this.received=received;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceived() {
		return received;
	}
	public void setReceived(String received) {
		this.received = received;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestData [id=" + id + ", sender=" + sender + ", received="
				+ received + "]";
	}
	
	
}
