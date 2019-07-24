package com.xyq.dao.impl;

import com.xyq.dao.IDeptDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Dept;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeptDaoImpl extends AbstractDAO implements IDeptDao {
    public List<Dept> findAllByEmpty() throws SQLException {
        List<Dept> all = new ArrayList<Dept>();
        String sql = "select deptno,dname,maxnum,currnum from dept where currnum<maxnum";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Dept vo = new Dept();
            vo.setDeptno(rs.getInt(1));
            vo.setDname(rs.getString(2));
            vo.setMaxnum(rs.getInt(3));
            vo.setCurrnum(rs.getInt(4));
            all.add(vo);
        }
        return all;
    }

    public boolean doUpdateCurrnum(Integer id, Integer num) throws SQLException {
        String sql = "update dept set currnum=currnum+"+num+" where deptno=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        return pstmt.executeUpdate()>0;
    }

    public boolean doCreate(Dept vo) throws SQLException {
        return false;
    }

    public boolean doUpdate(Dept vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public Dept findById(Integer id) throws SQLException {
        Dept vo = null;
        String sql = "select deptno,dname,maxnum,currnum from dept where deptno=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()){
            vo = new Dept();
            vo.setDeptno(rs.getInt(1));
            vo.setDname(rs.getString(2));
            vo.setMaxnum(rs.getInt(3));
            vo.setCurrnum(rs.getInt(4));
        }
        return vo;
    }

    public List<Dept> findAll() throws SQLException {
        List<Dept> all = new ArrayList<Dept>();
        String sql = "select deptno,dname,maxnum,currnum from dept";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Dept vo = new Dept();
            vo.setDeptno(rs.getInt(1));
            vo.setDname(rs.getString(2));
            vo.setMaxnum(rs.getInt(3));
            vo.setCurrnum(rs.getInt(4));
            all.add(vo);
        }
        return all;
    }

    public List<Dept> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Dept> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
