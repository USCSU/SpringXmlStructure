package com.five9.service;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
/*
 * <p> connection with Microsoft SQL Server (logisense report)</p>
 * 
 * */
@Service("lsmssql")
public class LsMSsqlConn  implements Conn{
	private DataSource mssqlPara;
	private JdbcTemplate jdbctemplate;
	public DataSource getMssqlPara() {
		return mssqlPara;
	}

	@Autowired//parameter name must meet with xml id name
	public void setMssqlPara(DataSource mssqlPara) {
		this.mssqlPara = mssqlPara;
		this.jdbctemplate= new JdbcTemplate(mssqlPara);
	}

	/* <p>This function is mainly for monitering purpose</p> 
	 * @param none
	 *
	 * */
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from logisense mssql server");
//		System.out.println(this.jdbctemplate.getDriver());
		System.out.println(mssqlPara.toString() );
	}
		
}
