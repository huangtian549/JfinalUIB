package com.platform.tools.code;

import java.util.List;

import com.jfinal.plugin.activerecord.Config;
import com.platform.config.ConfigCore;
import com.platform.constant.ConstantInit;
import com.platform.dto.DataBase;
import com.platform.tools.ToolDataBase;
import com.platform.tools.ToolString;
import com.platform.tools.code.handler.BaseHandler;
import com.platform.tools.code.handler.ColumnDto;
import com.platform.tools.code.handler.MySQLHandler;

public class GenerateCode {

	public static String[] tableArr = {"dg_purchase"};
	
	public static String tablePrefix = "dg_";
	
	public static String srcFolder = "src";
	
	public static String packageBase = "com.workshop.mvc";
	
	public static String basePath = "workshop";
	
	/**
	 * 表所在的数据源名称
	 */
	public static final String dataSource = ConstantInit.db_dataSource_main;
	
	public static void main(String[] args) throws InstantiationException {
		System.out.println("start..............");
		ConfigCore.getSingleton();
		
		DataBase dataBase = ToolDataBase.getDbMap(dataSource);
		
//		Config config = new Config(configName, dataSource);
		
		MySQLHandler handler = new MySQLHandler();
		
		handler.setDataBase(dataBase);
		
		handler.setSrcFolder(srcFolder);
		handler.setPackageBase(packageBase);
		handler.setBasePath(basePath);
		handler.setDataSource(dataSource);
		
		handler.init();
		
		for (int i = 0; i < tableArr.length; i++) {
			String tableName = tableArr[i];
			String classNameSmall  = tableName.replaceFirst(tablePrefix, "");
			String className = ToolString.toUpperCaseFirstOne(classNameSmall);
			
			List<ColumnDto> columnList = handler.getColunm(tableName);
			
//			handler.sql(classNameSmall, tableName);
			
			handler.model(className, classNameSmall, null, tableName, "ids", columnList);
			
//			handler.validator(className, classNameSmall);
			
//			handler.controller(className, classNameSmall, tableName);
			
//			handler.service(className, classNameSmall);
			
			System.out.println("generate table:" + tableName);
		}
		
		System.out.println("end............");
		System.exit(0);
	}

}
