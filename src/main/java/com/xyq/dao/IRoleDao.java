package com.xyq.dao;

import com.xyq.util.dao.IDAO;
import com.xyq.vo.Role;

import java.sql.SQLException;
import java.util.Set;

public interface IRoleDao extends IDAO<Integer,Role> {
    /**
     * 可以根据用户的编号取得所有的角色信息
     * @param mid 用户的编号
     * @return
     * @throws Exception
     */
    public Set<String> findAllByMember(String mid) throws SQLException;
}
