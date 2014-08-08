package com.amphenol.agis.pojo;

import com.amphenol.agis.model.UserModel;

public class User 
{
	private long id;
	private String name;
	private String username;
	private String password;
	private String roleids;
	private String rolenames;
	private Long organizationid;
	private String img;
	private Boolean locked;
	private String resourcenames;
	private String station;
	private UserModel usermodel;
	
	public User(){}
	
	public User(UserModel u)
	{
		this.usermodel=u;
		this.id=u.getLong("id");
		this.name=u.getStr("name");
		this.username=u.getStr("username");
		this.password=u.getStr("password");
		this.roleids=u.getStr("role_ids");
		this.rolenames=u.getRoleNames();
		this.resourcenames=u.getResourceNames();
		this.organizationid=u.getLong("organization_id");
		this.img=u.getStr("img");
		this.locked=u.getBoolean("locked");
		this.station=u.getStr("station");
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleids() {
		return roleids;
	}
	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}
	public String getRolenames() {
		return rolenames;
	}
	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}
	public Long getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(Long organizationid) {
		this.organizationid = organizationid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}		
	public String getResourcenames() {
		return resourcenames;
	}
	public void setResourcenames(String resourcenames) {
		this.resourcenames = resourcenames;
	}
	public UserModel getUsermodel() {
		return usermodel;
	}
	public void setUsermodel(UserModel usermodel) {
		this.usermodel = usermodel;
	}
	
	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((img == null) ? 0 : img.hashCode());
		result = prime * result + ((locked == null) ? 0 : locked.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((organizationid == null) ? 0 : organizationid.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roleids == null) ? 0 : roleids.hashCode());
		result = prime * result
				+ ((rolenames == null) ? 0 : rolenames.hashCode());
		result = prime * result
				+ ((usermodel == null) ? 0 : usermodel.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (img == null) {
			if (other.img != null)
				return false;
		} else if (!img.equals(other.img))
			return false;
		if (locked == null) {
			if (other.locked != null)
				return false;
		} else if (!locked.equals(other.locked))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (organizationid == null) {
			if (other.organizationid != null)
				return false;
		} else if (!organizationid.equals(other.organizationid))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roleids == null) {
			if (other.roleids != null)
				return false;
		} else if (!roleids.equals(other.roleids))
			return false;
		if (rolenames == null) {
			if (other.rolenames != null)
				return false;
		} else if (!rolenames.equals(other.rolenames))
			return false;
		if (usermodel == null) {
			if (other.usermodel != null)
				return false;
		} else if (!usermodel.equals(other.usermodel))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username
				+ ", password=" + password + ", roleids=" + roleids
				+ ", rolenames=" + rolenames + ", organizationid="
				+ organizationid + ", img=" + img + ", locked=" + locked
				+ ", resourcenames=" + resourcenames + ", usermodel="
				+ usermodel + "]";
	}
	
}
