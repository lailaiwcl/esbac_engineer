package org.lailaiwcl.esbac.event;


public class User_Role implements BaseEvent{
	
	private int userid;
	
	private int roleid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "User_Role [roleid=" + roleid + ", userid=" + userid + "]";
	}
	
	

}
