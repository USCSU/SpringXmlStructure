package com.five9.service;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
/* <p>Provide connection to mysql database and all the needed operations</p>
 * DataSource will be wired from xml configuration file. 
 * JdbcTemplate is used to do all the operations on database;
 * 
 */

@Service("vccmysql")
@Primary
public class VccMysqlConn implements Conn{
	private DataSource mysqlPara;
	private JdbcTemplate jdbctemplate;
	public DataSource getMysqlPara() {
		return mysqlPara;
	}
	/* <p>DataSource injection function.</p> 
	 * JdbcTemplate will receive mysql data source here.
	 * 
	 */
	@Autowired//parameter name must meet with xml id name
	public void setMysqlPara(DataSource mysqlPara) {
		this.mysqlPara = mysqlPara;
		this.jdbctemplate = new JdbcTemplate(mysqlPara);
	}
	/* <p>Function to moniter connection progress</p>
	 */
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from vcc mysql server");
		System.out.println(mysqlPara.toString());
	}

}
