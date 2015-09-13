package org.lailaiwcl.esbac.event;


public class Role_Resource implements BaseEvent{

	private int roleid;

	private String resourceid;

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	@Override
	public String toString() {
		return "Role_Resource [resourceid=" + resourceid + ", roleid=" + roleid
				+ "]";
	}

}
