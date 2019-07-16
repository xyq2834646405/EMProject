package com.xyq.dao.impl;

import com.xyq.dao.IActionDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActionDaoImpl extends AbstractDAO implements IActionDao {
    public Set<String> findAllByMember(String mid) throws SQLException {
        Set<String> all = new HashSet<String>();
        String sql = "select flag from action where actid in(select actid from role_action where rid in(select rid from member_role where mid=?))";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,mid);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            all.add(rs.getString(1));
        }
        return all;
    }

    public boolean findExists(String mid, String flag) throws SQLException {
        String sql = "select flag from action where actid in(select actid from role_action where rid in(select rid from member_role where mid=?)) and flag=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,mid);
        pstmt.setString(2,flag);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return true;
        }
        return false;
    }

    public boolean doCreate(Action vo) throws SQLException {
        return false;
    }

    public boolean doUpdate(Action vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public Action findById(Integer id) throws SQLException {
        return null;
    }

    public List<Action> findAll() throws SQLException {
        return null;
    }

    public List<Action> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Action> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
