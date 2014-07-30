package com.amphenol.agis.pojo;

import com.amphenol.agis.model.RoleModel;

public class Role 
{
	private long id;
	private String role;
	private String description;
	private String resourceids;
	private String resourcenames;
	private Boolean available;
	private RoleModel rolemodel;
	
	public Role(){}
	
	public Role(RoleModel r)
	{
		this.rolemodel=r;
		this.id=r.getLong("id");
		this.role=r.getStr("role");
		this.description=r.getStr("description");
		this.resourceids=r.getStr("resource_ids");
		this.resourcenames=r.getResourceNames();
		this.available =r.getBoolean("available");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceids() {
		return resourceids;
	}

	public void setResourceids(String resourceids) {
		this.resourceids = resourceids;
	}

	public String getResourcenames() {
		return resourcenames;
	}

	public void setResourcenames(String resourcenames) {
		this.resourcenames = resourcenames;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public RoleModel getRolemodel() {
		return rolemodel;
	}

	public void setRolemodel(RoleModel rolemodel) {
		this.rolemodel = rolemodel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((available == null) ? 0 : available.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((resourceids == null) ? 0 : resourceids.hashCode());
		result = prime * result
				+ ((resourcenames == null) ? 0 : resourcenames.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result
				+ ((rolemodel == null) ? 0 : rolemodel.hashCode());
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
		Role other = (Role) obj;
		if (available == null) {
			if (other.available != null)
				return false;
		} else if (!available.equals(other.available))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resourceids == null) {
			if (other.resourceids != null)
				return false;
		} else if (!resourceids.equals(other.resourceids))
			return false;
		if (resourcenames == null) {
			if (other.resourcenames != null)
				return false;
		} else if (!resourcenames.equals(other.resourcenames))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (rolemodel == null) {
			if (other.rolemodel != null)
				return false;
		} else if (!rolemodel.equals(other.rolemodel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", description="
				+ description + ", resourceids=" + resourceids
				+ ", resourcenames=" + resourcenames + ", available="
				+ available + ", rolemodel=" + rolemodel + "]";
	}
	
	
}
