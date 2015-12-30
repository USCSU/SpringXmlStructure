package com.five9.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Parameter {
	private String driver;
	private String url;
	private String sql;
	private String username;
	private String password;
	public String getDriver() {
		return driver;
	}
	@Autowired
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	@Autowired
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSql() {
		return sql;
	}
	@Autowired
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getUsername() {
		return username;
	}
	@Autowired
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	@Autowired
	public void setPassword(String password) {
		this.password = password;
	}

}
