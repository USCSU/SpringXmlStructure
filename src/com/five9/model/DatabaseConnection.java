package com.five9.model;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.five9.service.ReadCSV_XLSX;

public abstract class DatabaseConnection {
	
	protected JdbcTemplate jdbctemplate;
	protected String updateSql;
	protected String querySql;
	protected String batchInsertionSql;
	protected String path;
	protected String deleteSql;
	protected boolean deleteSwitch;
	protected Map<String,String> csv_xlsx;
	
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
	protected void importDataToDB(){
		if(path == null || path.length() ==0) throw new IllegalArgumentException("Wrong format of path :" + path);
		if(csv_xlsx==null || csv_xlsx.isEmpty()) throw new IllegalArgumentException("No data from csv file!");
		 System.out.println("Saving data to database....");
		 batchInsertion();
		 System.out.println("saving data done!");
	}
	 protected void readCSV_XLSX(){
		 System.out.println("Reading data from csv/xlsx file....");
		 //static function here 
		 csv_xlsx =  ReadCSV_XLSX.savaToMap(path);
		 System.out.println("Done reading file.");
	 }
	/* <p>Function to moniter connection progress </p> 
	 * @param none
	 *
	 * */
}
