package com.five9.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
import com.five9.model.Parameter;
@Service("vccmysql")
@Primary
public class VccMysqlConn implements Conn{
	private Parameter mysqlPara;
	public Parameter getMysqlPara() {
		return mysqlPara;
	}
	@Autowired//parameter name must meet with xml id name
	public void setMysqlPara(Parameter mysqlPara) {
		this.mysqlPara = mysqlPara;
	}
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from vcc mysql server");
		System.out.println(mysqlPara.getDriver());
		System.out.println(mysqlPara.getPassword());
		System.out.println(mysqlPara.getUsername());
		System.out.println(mysqlPara.getUrl());
		System.out.println(mysqlPara.getSql());
	}

}
