package com.xyq.service.back.impl;

import com.xyq.dao.IDeptDao;
import com.xyq.dao.IEmpDao;
import com.xyq.dao.ILevelDao;
import com.xyq.dao.impl.DeptDaoImpl;
import com.xyq.dao.impl.EmpDaoImpl;
import com.xyq.dao.impl.LevelDaoImpl;
import com.xyq.service.abs.AbstractService;
import com.xyq.service.back.IEmpServiceBack;
import com.xyq.util.factory.DAOFactory;
import com.xyq.vo.Dept;
import com.xyq.vo.Emp;
import com.xyq.vo.Level;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {
    public Map<String, Object> addPre(String mid) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if(auth(mid,"emp:add")){
            IDeptDao deptDao = DAOFactory.getInstance(DeptDaoImpl.class);
            ILevelDao levelDao = DAOFactory.getInstance(LevelDaoImpl.class);
            map.put("allDepts",deptDao.findAllByEmpty());
            map.put("allLevels",levelDao.findAll());
        }
        return map;
    }

    public boolean add(Emp vo) throws Exception {
        if(auth(vo.getMid(),"emp:add")) {
            IDeptDao deptDao = DAOFactory.getInstance(DeptDaoImpl.class);
            Dept dept = deptDao.findById(vo.getDeptno());//根据雇员所在的部门查询部门数据
            if(dept.getCurrnum()<dept.getMaxnum()){
                ILevelDao levelDao = DAOFactory.getInstance(LevelDaoImpl.class);
                Level level = levelDao.findById(vo.getLid());//取得指定的级别信息
                if(vo.getSal()>=level.getLosal() && vo.getSal()<=level.getHisal()){//判断增加的雇员工资是否在指定的级别范围内
                    IEmpDao empDao = DAOFactory.getInstance(EmpDaoImpl.class);
                    vo.setFlag(1);//表示当前员工在职
                    vo.setHiredate(new Date());//雇佣日期为今天的日期
                    if(empDao.doCreate(vo)){
                        return deptDao.doUpdateCurrnum(vo.getDeptno(),1);
                    }
                }
            }
        }
        return false;
    }
}
