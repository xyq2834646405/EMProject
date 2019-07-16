package com.xyq.dao.impl;

import com.xyq.dao.ILevelDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Level;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LevelDaoImpl extends AbstractDAO implements ILevelDao {
    public boolean doCreate(Level vo) throws SQLException {
        return false;
    }

    public boolean doUpdate(Level vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public Level findById(Integer id) throws SQLException {
        Level vo = null;
        String sql = "select lid,losal,hisal,title,flag from level where lid=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            vo = new Level();
            vo.setLid(rs.getInt(1));
            vo.setLosal(rs.getDouble(2));
            vo.setHisal(rs.getDouble(3));
            vo.setTitle(rs.getString(4));
            vo.setFlag(rs.getString(5));
        }
        return vo;
    }

    public List<Level> findAll() throws SQLException {
        List<Level> all = new ArrayList<Level>();
        String sql = "select lid,losal,hisal,title,flag from level";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Level vo = new Level();
            vo.setLid(rs.getInt(1));
            vo.setLosal(rs.getDouble(2));
            vo.setHisal(rs.getDouble(3));
            vo.setTitle(rs.getString(4));
            vo.setFlag(rs.getString(5));
            all.add(vo);
        }
        return all;
    }

    public List<Level> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Level> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
