package com.five9.app;
/*
 *  <p>Main function;</p> 
 *  Entrance to whole app
 */
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.five9.consumer.DBConnection;;
public class RatePlanApp {
	public static void main(String[] args){
		try(ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml")){
		DBConnection mysqlConn  = (DBConnection) ctx.getBean("mysql");
		mysqlConn.moniter();
		DBConnection mssqlConn  = (DBConnection) ctx.getBean("mysql");
		mssqlConn.moniter();
		}
	}
}
