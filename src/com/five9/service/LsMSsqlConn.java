package com.five9.service;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

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
	private String path;
	private String deleteSql;
	private boolean deleteSwitch;
	private Map<String,String> csv_xlsx;
	
	public void update(){
		this.jdbctemplate.update(deleteSql);
	}
	public void delete(){
		this.jdbctemplate.update(deleteSql);
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
	private void batchInsertion(){
		this.jdbctemplate.batchUpdate(batchInsertionSql, new BatchPreparedStatementSetter(){
			Object[] keys = csv_xlsx.keySet().toArray();
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException{
				String key = (String)keys[i];
				ps.setString(1, key);
				ps.setString(2, csv_xlsx.get(key));
			}
			@Override
			public int getBatchSize(){
				return csv_xlsx.size();
			}
		});
	}
	void setResultToMssql(String path){
		if(path == null || path.length() ==0) throw new IllegalArgumentException("Wrong format of path :" + path);
		Map<String,String> result = ReadCSV_XLSX.savaToMap(path);
		System.out.println(result);
	}
	
	/* <p>Function to moniter connection progress </p> 
	 * @param none
	 *
	 * */
	
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from logisense mssql server");
//		setResultToMssql(path);
//		update();
		query();
	}
	
	/*setters and getters*/
	
	public String getPath() {
		return path;
	}
	@Autowired
	public void setPath(String path) {
		this.path = path;
	}
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
	public String getDeleteSql() {
		return deleteSql;
	}
	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
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
	public boolean isDeleteSwitch() {
		return deleteSwitch;
	}
	public void setDeleteSwitch(boolean deleteSwitch) {
		this.deleteSwitch = deleteSwitch;
	}
	
	 
		
}
