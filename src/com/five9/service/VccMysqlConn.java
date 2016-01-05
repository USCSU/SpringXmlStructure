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
	private String path;
	private String deleteSql;
	private Map<String,String> csv_xlsx;
	private boolean deleteSwitch;
	
	
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
	public void delete(){
		this.jdbctemplate.update(deleteSql);
	}
	/*save the result to map*/
	 void setResultToMysql(){
		 readCSV_XLSX();
		 if(csv_xlsx==null || csv_xlsx.isEmpty()) throw new IllegalArgumentException("No data from csv file!");
		 System.out.println("Saving data to mysql database....");
		 batchInsertion();
		 System.out.println("saving data done!");
		 
	}
	 private void readCSV_XLSX(){
		 System.out.println("Reading data from csv file....");
		 //static function here 
		 csv_xlsx =  ReadCSV_XLSX.savaToMap(path);
		 System.out.println("Done reading file.");
	 }
	
	
	
	/* <p>Function to moniter connection progress</p>
	 */
	@Override
	public void moniter(){
		System.out.println();
		System.out.println("Echo from vcc mysql server.......");
//		update();
		if(deleteSwitch)
			this.delete();
		setResultToMysql();
		query();
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
	/* setters and getters */
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
	public String getPath() {
		return path;
	}
	@Autowired(required = false)
	public void setPath(String path) {
		this.path = path;
	}
	public String getDeleteSql() {
		return deleteSql;
	}
	public void setDeleteSql(String deleteSql) {
		this.deleteSql = deleteSql;
	}
	public boolean isDeleteSwitch() {
		return deleteSwitch;
	}
	public void setDeleteSwitch(boolean deleteSwitch) {
		this.deleteSwitch = deleteSwitch;
	}
}
