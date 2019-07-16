package com.xyq.service.back.impl;

import com.xyq.dao.IDeptDao;
import com.xyq.dao.impl.DeptDaoImpl;
import com.xyq.service.abs.AbstractService;
import com.xyq.service.back.IDeptServiceBack;
import com.xyq.util.factory.DAOFactory;
import com.xyq.vo.Dept;

public class DeptServiceBackImpl extends AbstractService implements IDeptServiceBack {
    public Dept get(int id) throws Exception {
        IDeptDao deptDao = DAOFactory.getInstance(DeptDaoImpl.class);
        return deptDao.findById(id);
    }
}
