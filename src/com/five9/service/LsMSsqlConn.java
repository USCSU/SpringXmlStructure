package com.five9.service;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;
/*
 * <p> connection with Microsoft SQL Server (logisense report)</p>
 * DataSource will be wired from xml configuration file. 
 * JdbcTemplate is used to do all the operations on database;
 * */
public class LsMSsqlConn  implements Conn{
	private DataSource mssqlPara;
	private JdbcTemplate jdbctemplate;
	private String updateSql;
	private String querySql;
	private String batchInsertionSql;
	
	public String getBatchInsertionSql() {
		return batchInsertionSql;
	}
	@Autowired 
	public void setBatchInsertionSql(String batchInsertionSql) {
		this.batchInsertionSql = batchInsertionSql;
	}
	public String getQuerySql() {
		return querySql;
	}
	@Autowired
	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}
	public DataSource getMssqlPara() {
		return mssqlPara;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	@Autowired
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}
	/* <p>DataSource injection function. </p>
	 * JdbcTemplate will receive Microsoft SQL Server data source here.
	 * 
	 */
	@Autowired//parameter name must meet with xml id name
	public void setMssqlPara(DataSource mssqlPara) {
		this.mssqlPara = mssqlPara;
		this.jdbctemplate= new JdbcTemplate(mssqlPara);
	}
	public void update(){
		this.jdbctemplate.update(updateSql);
	}
	public void query(){
		 List<Map<String, Object>> ret = this.jdbctemplate.queryForList(querySql);
		 for(Map<String,Object> map:ret){
			 System.out.println("---------");
			 Iterator<String> iterator = map.keySet().iterator();
			 while(iterator.hasNext()){
				 String key = iterator.next();
				 System.out.println(key + "->" + map.get(key));
			 }
		 }
	}
	
	/* <p>Function to moniter connection progress </p> 
	 * @param none
	 *
	 * */
	
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from logisense mssql server");
//		update();
		query();
	}
		
}
