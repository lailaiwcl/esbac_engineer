package org.lailaiwcl.esbac.event;


public class User implements BaseEvent{
	private int uid;
	
	private String loginname;
	
	private String password;
	
	public User() {
		super();
	}
	
	public User(int uid, String loginname, String password) {
		super();
		this.uid = uid;
		this.loginname = loginname;
		this.password = password;
	}



	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [loginname=" + loginname + ", password=" + password
				+ ", uid=" + uid + "]";
	}
	
	

}
