package com.xyq.util.dao;

import com.xyq.util.dbc.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AbstractDAO {
	protected Connection conn ;
	protected PreparedStatement pstmt ;
	public AbstractDAO() {
		this.conn = DatabaseConnection.get() ;
	}

	public Integer getLastId() throws SQLException{
		String sql = "select LAST_INSERT_ID()";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}else {
			return null;
		}
	}
}
