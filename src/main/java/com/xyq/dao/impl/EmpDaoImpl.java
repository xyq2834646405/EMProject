package com.xyq.dao.impl;

import com.xyq.dao.IEmpDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Emp;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class EmpDaoImpl extends AbstractDAO implements IEmpDao {
    public boolean doCreate(Emp vo) throws SQLException {
        String sql = "insert into emp(deptno,mid,lid,ename,job,sal,comm,hiredate,photo,flag) values(?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,vo.getDeptno());
        pstmt.setString(2,vo.getMid());
        pstmt.setInt(3,vo.getLid());
        pstmt.setString(4,vo.getEname());
        pstmt.setString(5,vo.getJob());
        pstmt.setDouble(6,vo.getSal());
        pstmt.setDouble(7,vo.getComm());
        pstmt.setDate(8,new Date(vo.getHiredate().getTime()));
        pstmt.setString(9,vo.getPhoto());
        pstmt.setInt(10,vo.getFlag());
        return pstmt.executeUpdate()>0;
    }

    public boolean doUpdate(Emp vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public Emp findById(Integer id) throws SQLException {
        return null;
    }

    public List<Emp> findAll() throws SQLException {
        return null;
    }

    public List<Emp> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Emp> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
