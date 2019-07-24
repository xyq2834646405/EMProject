package com.xyq.dao;

import com.xyq.util.dao.IDAO;
import com.xyq.vo.Emp;

import java.sql.SQLException;
import java.util.List;

public interface IEmpDao extends IDAO<Integer, Emp> {
    public List<Emp> findAllByFlag(Integer flag,String column,String keyWord,Integer currentPage,Integer lineSize) throws SQLException;

    public List<Emp> findAllByFlag(Integer flag,Integer currentPage,Integer lineSize) throws SQLException;

    public Integer getAllCountByFlag(Integer flag,String column,String keyWord) throws SQLException;

    public Integer getAllCountByFlag(Integer flag) throws SQLException;
}
