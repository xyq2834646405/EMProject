package com.xyq.dao;

import com.xyq.util.dao.IDAO;
import com.xyq.vo.Action;

import java.sql.SQLException;
import java.util.Set;

public interface IActionDao extends IDAO<Integer, Action> {
    /**
     * 可以根据用户的编号取得所有的权限数据
     * @param mid 用户的编号
     * @return
     * @throws Exception
     */
    public Set<String> findAllByMember(String mid) throws SQLException;
}
