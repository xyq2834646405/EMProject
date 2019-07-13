package com.xyq.util.dao;

import com.xyq.util.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;


public class AbstractDAO {
	protected Connection conn ;
	protected PreparedStatement pstmt ;
	public AbstractDAO() {
		this.conn = DatabaseConnection.get() ;
	}
	
}
