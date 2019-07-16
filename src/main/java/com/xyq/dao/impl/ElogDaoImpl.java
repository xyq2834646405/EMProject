package com.xyq.dao.impl;

import com.xyq.dao.IElogDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Elog;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ElogDaoImpl extends AbstractDAO implements IElogDao {
    public boolean doCreate(Elog vo) throws SQLException {
        String sql = "insert into elog(empno,deptno,mid,lid,job,sal,comm,sflag,flag,note) values(?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,vo.getEmpno());
        pstmt.setInt(2,vo.getDeptno());
        pstmt.setString(3,vo.getMid());
        pstmt.setInt(4,vo.getLid());
        pstmt.setString(5,vo.getJob());
        pstmt.setDouble(6,vo.getSal());
        pstmt.setDouble(7,vo.getComm());
        pstmt.setInt(8,vo.getSflag());
        pstmt.setInt(9,vo.getFlag());
        pstmt.setString(10,vo.getNote());
        return pstmt.executeUpdate()>0;
    }

    public boolean doUpdate(Elog vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public Elog findById(Integer id) throws SQLException {
        return null;
    }

    public List<Elog> findAll() throws SQLException {
        return null;
    }

    public List<Elog> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Elog> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
