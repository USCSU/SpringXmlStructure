package com.five9.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteCSV_XLSX {
	public static void writetoFile(String path, Map<String, List<String>> data){
		
	}
	public static Map<String,String> savaToMap(String path){
		if(path == null || path.isEmpty()) throw new IllegalArgumentException("Parameter is null or empty!");
		String[] resolver = path.split("\\.");
		String suffix = resolver[resolver.length-1];
		if(suffix.equals("csv"))
			return getResultCSV(path);
		else if(suffix.equals("xlsx"))
			return getResultXLSX(path);
		else throw new IllegalArgumentException("Parameter is not csv or xlsx");
	}
	private static Map<String,String> getResultCSV(String path){
		Map<String,String> ret = new HashMap<String,String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while((line = br.readLine())!=null){
				String[] cols = line.split(",");
				ret.put(cols[0], cols[1]);
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		} 
		return ret;
	}
	private static Map<String,String> getResultXLSX(String path){
		//Blank workbook
		Map<String,String> ret = new HashMap<String,String>();
		try {
			XSSFWorkbook workbook;
			workbook = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(path));
		
			XSSFSheet sheet = workbook.getSheetAt(0);
        
			for(Row row:sheet){
				DataFormatter formatter = new DataFormatter();
				ret.put(formatter.formatCellValue(row.getCell(0)),formatter.formatCellValue(row.getCell(1)));
			}
        
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	 
}
