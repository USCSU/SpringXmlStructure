package com.five9.service;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
/* <p>Provide connection to mysql database and all the needed operations</p>
 * DataSource will be wired from xml configuration file. 
 * JdbcTemplate is used to do all the operations on database;
 * 
 */
public class VccMysqlConn implements Conn{
	private DataSource mysqlPara;
	private JdbcTemplate jdbctemplate;
	private String updateSql;
	private String querySql;
	private String batchInsertionSql;
	
	public String getBatchInsertionSql() {
		return batchInsertionSql;
	}
//	@Autowired
	public void setBatchInsertionSql(String batchInsertionSql) {
		this.batchInsertionSql = batchInsertionSql;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}
	public String getQuerySql() {
		return querySql;
	}
//	@Value(value = "select * from student")
//	@Autowired
	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}
	public DataSource getMysqlPara() {
		return mysqlPara;
	}
	/* <p>DataSource injection function.</p> 
	 * JdbcTemplate will receive mysql data source here.
	 * 
	 */
	@Autowired(required = false)//parameter name must meet with xml id name
	public void setMysqlPara(DataSource mysqlPara) {
		this.mysqlPara = mysqlPara;
		this.jdbctemplate = new JdbcTemplate(mysqlPara);
	}
	public void update(){
		this.jdbctemplate.update(updateSql);
	}
	public void query(){
		 List<Map<String, Object>> ret = this.jdbctemplate.queryForList(querySql);
		 for(Map<String,Object> map:ret){
			 Iterator<String> iterator = map.keySet().iterator();
			 while(iterator.hasNext()){
				 String key = iterator.next();
				 System.out.println(key + "->" + map.get(key));
			 }
		 }
	}
	
	/* <p>Function to moniter connection progress</p>
	 */
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from vcc mysql server");
//		update();
		query();
	}

}
