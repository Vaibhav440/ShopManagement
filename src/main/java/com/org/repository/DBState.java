package com.org.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class DBState {

	 private static final Logger logger = Logger.getLogger(DBState.class);
	DBConfig config=DBConfig.getInstance();
	protected Connection conn=config.getConn();
	protected PreparedStatement stmt=config.getStatement();
	protected ResultSet rs=config.getResultSet();
	protected CallableStatement cstmt=config.getCallStatement();
	public DBState() {
		logger.info("Initializing DBState...");
        logger.debug("Connection object: " + conn);
        logger.debug("PreparedStatement object: " + stmt);
        logger.debug("ResultSet object: " + rs);
        logger.debug("CallableStatement object: " + cstmt);
	}
}
