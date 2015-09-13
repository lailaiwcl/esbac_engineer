package org.lailaiwcl.esbac.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.lailaiwcl.esbac.common.DBHelper;
import org.lailaiwcl.esbac.event.Resource;
import org.lailaiwcl.esbac.event.Role;
import org.lailaiwcl.esbac.event.Role_Resource;
import org.lailaiwcl.esbac.event.User;
import org.lailaiwcl.esbac.event.User_Role;
import org.lailaiwcl.esbac.spi.Initialization;

/**
 * 初始化Named Window对象。
 * 
 * @author wucl(lailaiwcl@gmail.com)
 * 
 */
public class DefaultInitialization implements Initialization {

	@Override
	public void init(EventStreamProcessEngneer engneer) {
		String sql = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String eventName = "";
		// 加载用户数据
		try {
			sql = "select * from users";
			conn = DBHelper.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			eventName = "User";
			engneer.getAdmin().createEPL(getCreateEPL(eventName));
			engneer.getAdmin().createEPL(getInsertEPL(eventName));
			while (rs.next()) {
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setLoginname(rs.getString("loginname"));
				user.setPassword(rs.getString("password"));
				engneer.getRuntime().sendEvent(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs);
			DBHelper.close(st);
			DBHelper.close(conn);
		}
		// 加载角色
		try {
			sql = "select * from role";
			conn = DBHelper.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			eventName = "Role";
			engneer.getAdmin().createEPL(getCreateEPL(eventName));
			engneer.getAdmin().createEPL(getInsertEPL(eventName));
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				engneer.getRuntime().sendEvent(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs);
			DBHelper.close(st);
			DBHelper.close(conn);
		}

		// 加载资源
		try {
			sql = "select * from resource";
			conn = DBHelper.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			eventName = "Resource";
			engneer.getAdmin().createEPL(getCreateEPL(eventName));
			engneer.getAdmin().createEPL(getInsertEPL(eventName));
			while (rs.next()) {
				Resource resource = new Resource();
				resource.setId(rs.getString("id"));
				resource.setName(rs.getString("name"));
				resource.setUrl(rs.getString("url"));
				engneer.getRuntime().sendEvent(resource);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs);
			DBHelper.close(st);
			DBHelper.close(conn);
		}
		// 加载用户角色
		try {
			sql = "select * from user_role";
			conn = DBHelper.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			eventName = "User_Role";
			engneer.getAdmin().createEPL(getCreateEPL(eventName));
			engneer.getAdmin().createEPL(getInsertEPL(eventName));
			while (rs.next()) {
				User_Role ur = new User_Role();
				ur.setUserid(rs.getInt("userid"));
				ur.setRoleid(rs.getInt("roleid"));
				engneer.getRuntime().sendEvent(ur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs);
			DBHelper.close(st);
			DBHelper.close(conn);
		}

		// 加载角色资源
		try {
			sql = "select * from role_resource";
			conn = DBHelper.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			eventName = "Role_Resource";
			engneer.getAdmin().createEPL(getCreateEPL(eventName));
			engneer.getAdmin().createEPL(getInsertEPL(eventName));
			while (rs.next()) {
				Role_Resource rr = new Role_Resource();
				rr.setRoleid(rs.getInt("roleid"));
				rr.setResourceid(rs.getString("resourceid"));
				engneer.getRuntime().sendEvent(rr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs);
			DBHelper.close(st);
			DBHelper.close(conn);
		}
	}

	private String getCreateEPL(String eventName) {
		return "create window " + eventName + "Window.win:length(1000) as "
				+ eventName;
	}

	private String getInsertEPL(String eventName) {
		return "insert into " + eventName + "Window select * from " + eventName;
	}

}
