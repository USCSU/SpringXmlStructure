package com.five9.app;
/*
 *  <p>Main function;</p> 
 *  Entrance to whole app
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.five9.consumer.DBConnection;;
public class RatePlanApp {
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");
		DBConnection conn  = (DBConnection) ctx.getBean("dbconn");
		conn.moniter();
	}
}
