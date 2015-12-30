package com.five9.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
import com.five9.model.Parameter;
@Service("lsmssql")
public class LsMSsqlConn implements Conn{
	private Parameter mssqlPara;
	private JdbcTemplate template;
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public Parameter getMssqlPara() {
		return mssqlPara;
	}

	@Autowired//parameter name must meet with xml id name
	public void setMssqlPara(Parameter mssqlPara) {
		this.mssqlPara = mssqlPara;
	}

	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from logisense mssql server");
		System.out.println(mssqlPara.getDriver());
		System.out.println(mssqlPara.getPassword());
		System.out.println(mssqlPara.getUsername());
		System.out.println(mssqlPara.getUrl());
		System.out.println(mssqlPara.getSql());
	}
		
}
