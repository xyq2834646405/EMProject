package com.xyq.service.back;

import com.xyq.vo.Dept;

public interface IDeptServiceBack {
    /**
     * 根据部门的编号查询出部门的基本信息
     * @param id
     * @return
     * @throws Exception
     */
    public Dept get(int id) throws Exception;
}
