package com.five9.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.five9.model.Conn;

/*<p>Dao Impl class. which is used to do actual job after connection with database</p>
 * 
 */
@Service("dbconn")
public class DBConnection {
	@Autowired
	private Conn mysql;
	@Autowired
	@Qualifier("lsmssql")
	private Conn mssql;
	public void moniter(){
		mysql.moniter();
		mssql.moniter();
	}

	public Conn getMysql() {
		return mysql;
	}
	public void setMysql(Conn mysql) {
		this.mysql = mysql;
	}
	public Conn getMssql() {
		return mssql;
	}
	public void setMssql(Conn mssql) {
		this.mssql = mssql;
	}
	
	
}
