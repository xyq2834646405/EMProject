package com.xyq.dao.impl;

import com.xyq.dao.IMemberDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class MemberDaoImpl extends AbstractDAO implements IMemberDao {
    public boolean doCreate(Member vo) throws SQLException {
        return false;
    }

    public boolean doUpdate(Member vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<String> ids) throws SQLException {
        return false;
    }

    public Member findById(String id) throws SQLException {
        Member vo = null;
        String sql = "select mid,password,name,sflag from member where mid=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setSflag(rs.getInt(4)    );
        }
        return vo;
    }

    public List<Member> findAll() throws SQLException {
        return null;
    }

    public List<Member> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Member> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
