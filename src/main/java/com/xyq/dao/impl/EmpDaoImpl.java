package com.xyq.dao.impl;

import com.xyq.dao.IEmpDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Emp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<Emp> findAllByFlag(Integer flag, String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        List<Emp> all = new ArrayList<Emp>();
        String sql = "select empno,deptno,mid,lid,ename,job,sal,comm,hiredate,photo,flag from emp where "+column+" like ? and flag=? limit ?,?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1,"%"+keyWord+"%");
         pstmt.setInt(2,flag);
         pstmt.setInt(3,(currentPage-1)*lineSize);
         pstmt.setInt(4,lineSize);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Emp vo = new Emp();
            vo.setEmpno(rs.getInt(1));
            vo.setDeptno(rs.getInt(2));
            vo.setMid(rs.getString(3));
            vo.setLid(rs.getInt(4));
            vo.setEname(rs.getString(5));
            vo.setJob(rs.getString(6));
            vo.setSal(rs.getDouble(7));
            vo.setComm(rs.getDouble(7));
            vo.setHiredate(rs.getDate(9));
            vo.setPhoto(rs.getString(10));
            vo.setFlag(rs.getInt(11));
            all.add(vo);
        }
        return all;
    }

    public List<Emp> findAllByFlag(Integer flag, Integer currentPage, Integer lineSize) throws SQLException {
        List<Emp> all = new ArrayList<Emp>();
        String sql = "select empno,deptno,mid,lid,ename,job,sal,comm,hiredate,photo,flag from emp where flag=? limit ?,?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,flag);
        pstmt.setInt(2,(currentPage-1)*lineSize);
        pstmt.setInt(3,lineSize);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            Emp vo = new Emp();
            vo.setEmpno(rs.getInt(1));
            vo.setDeptno(rs.getInt(2));
            vo.setMid(rs.getString(3));
            vo.setLid(rs.getInt(4));
            vo.setEname(rs.getString(5));
            vo.setJob(rs.getString(6));
            vo.setSal(rs.getDouble(7));
            vo.setComm(rs.getDouble(7));
            vo.setHiredate(rs.getDate(9));
            vo.setPhoto(rs.getString(10));
            vo.setFlag(rs.getInt(11));
            all.add(vo);
        }
        return all;
    }

    public Integer getAllCountByFlag(Integer flag, String column, String keyWord) throws SQLException {
        String sql = "select count(*) from emp where "+column+" like ? and flag=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,"%"+keyWord+"%");
        pstmt.setInt(2,flag);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }

    public Integer getAllCountByFlag(Integer flag) throws SQLException {
        String sql = "select count(*) from emp where flag=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,flag);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
}
