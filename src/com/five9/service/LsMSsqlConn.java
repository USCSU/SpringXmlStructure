package com.five9.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
import com.five9.model.Parameter;
/*
 * <p> connection with Microsoft SQL Server (logisense report)</p>
 * 
 * */
@Service("lsmssql")
public class LsMSsqlConn  implements Conn{
	private Parameter mssqlPara;

	public Parameter getMssqlPara() {
		return mssqlPara;
	}

	@Autowired//parameter name must meet with xml id name
	public void setMssqlPara(Parameter mssqlPara) {
		this.mssqlPara = mssqlPara;
	}

	/* <p>This function is mainly for monitering purpose</p> 
	 * @param none
	 *
	 * */
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
