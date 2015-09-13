package org.lailaiwcl.esbac.event;

import java.sql.Timestamp;


public class Request implements BaseEvent{
	
	private long key;
	
	private int userid;
	
	private String url;
	
	private Timestamp starttime;

	public Request() {
		super();
		this.key = System.currentTimeMillis();
		this.starttime = new Timestamp(this.key);
	}

	public Request(int userid, String url) {
		this();
		this.userid = userid;
		this.url = url;
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getStarttime() {
		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	@Override
	public String toString() {
		return "Request [key=" + key + ", starttime=" + starttime + ", url="
				+ url + ", userid=" + userid + "]";
	}

}
