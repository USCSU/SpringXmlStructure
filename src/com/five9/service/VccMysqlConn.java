package com.five9.service;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
@Service("vccmysql")
@Primary
public class VccMysqlConn implements Conn{
	private DataSource mysqlPara;
	private JdbcTemplate jdbctemplate;
	public DataSource getMysqlPara() {
		return mysqlPara;
	}
	@Autowired//parameter name must meet with xml id name
	public void setMysqlPara(DataSource mysqlPara) {
		this.mysqlPara = mysqlPara;
		this.jdbctemplate = new JdbcTemplate(mysqlPara);
	}
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from vcc mysql server");
		System.out.println(mysqlPara.toString());
	}

}
