package com.xyq.dao.impl;

import com.xyq.dao.IRoleDao;
import com.xyq.util.dao.AbstractDAO;
import com.xyq.vo.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleDaoImpl extends AbstractDAO implements IRoleDao {
    public Set<String> findAllByMember(String mid) throws SQLException {
        Set<String> all = new HashSet<String>();
        String sql = "select flag from role where rid in(select rid from member_role where mid=?)";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,mid);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            all.add(rs.getString(1));
        }
        return all ;
    }

    public boolean doCreate(Role vo) throws SQLException {
        return false;
    }

    public boolean doUpdate(Role vo) throws SQLException {
        return false;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public Role findById(Integer id) throws SQLException {
        return null;
    }

    public List<Role> findAll() throws SQLException {
        return null;
    }

    public List<Role> findAllSplit(Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public List<Role> findAllSplit(String column, String keyWord, Integer currentPage, Integer lineSize) throws SQLException {
        return null;
    }

    public Integer getAllCount() throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
