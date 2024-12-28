package com.org.repository;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

import org.apache.log4j.Logger;
public class DBConfig
{
	private static final Logger logger = Logger.getLogger(DBConfig.class);

		protected static Connection conn;
		protected static  PreparedStatement stmt;
		protected static ResultSet rs;
		protected static CallableStatement cstmt;
		protected static DBConfig db;
		private DBConfig()
		{
			try
			{
				logger.info("Initializing database configuration...");
				Class.forName("com.mysql.cj.jdbc.Driver");
				 logger.info("MySQL JDBC driver loaded successfully.");
				File f=new File("");
				String path=f.getAbsolutePath();
				logger.info("Project base path resolved: " + path);
				FileInputStream inputStream=new FileInputStream(path+"\\src\\main\\resources\\dbconfig.properties");
				Properties p=new Properties();
				p.load(inputStream);
				String driverClassName=p.getProperty("driver");
				String username=p.getProperty("username");
				String password=p.getProperty("password");
				String url=p.getProperty("url");
				conn=DriverManager.getConnection(url,username,password);
				 logger.info("Database connection established successfully to URL: " + url);
				
				
			} catch (Exception e) {
				logger.error("Error occurred during database configuration initialization.", e);
			}
		}
		public static DBConfig getInstance()
		{
			if(db==null)
			{
				 logger.info("Creating new DBConfig instance.");
				db=new DBConfig();
			}
			return db;
		}
		public static Connection getConn()
		{
			 logger.debug("Returning database connection.");
		   return conn;	
		}
		public static PreparedStatement getStatement()
		{
			logger.debug("Returning prepared statement.");
			return stmt;
		}
		public static ResultSet getResultSet()
		{
			logger.debug("Returning result set.");
			return rs;
		}
		public static CallableStatement getCallStatement()
		{
			 logger.debug("Returning callable statement.");
			return cstmt;
		}
		
}
