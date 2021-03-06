package com.xyq.service.back.impl;

import com.xyq.dao.IDeptDao;
import com.xyq.dao.IElogDao;
import com.xyq.dao.IEmpDao;
import com.xyq.dao.ILevelDao;
import com.xyq.dao.impl.DeptDaoImpl;
import com.xyq.dao.impl.ElogDaoImpl;
import com.xyq.dao.impl.EmpDaoImpl;
import com.xyq.dao.impl.LevelDaoImpl;
import com.xyq.service.abs.AbstractService;
import com.xyq.service.back.IEmpServiceBack;
import com.xyq.util.DateUtil;
import com.xyq.util.factory.DAOFactory;
import com.xyq.vo.Dept;
import com.xyq.vo.Elog;
import com.xyq.vo.Emp;
import com.xyq.vo.Level;

import java.util.*;

public class EmpServiceBackImpl extends AbstractService implements IEmpServiceBack {
    public Map<String, Object> listByFlag(String mid, int flag, String column, String keyWord, int currentPage, int lineSize) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if(auth(mid,"emp:list")){
            //查询所有的部门数据
            IDeptDao deptDao = DAOFactory.getInstance(DeptDaoImpl.class);
            List<Dept> allDepts = deptDao.findAll();
            Map<Long,String> deptMap = new HashMap<Long, String>();
            Iterator<Dept> iterDept = allDepts.iterator();
            while(iterDept.hasNext()){
                Dept vo = iterDept.next();
                deptMap.put(vo.getDeptno().longValue(),vo.getDname());
            }
            map.put("allDepts",deptMap);
            //查询出所有的雇员等级信息
            ILevelDao levelDao = DAOFactory.getInstance(LevelDaoImpl.class);
            List<Level> allLevels = levelDao.findAll();
            Iterator<Level> iterLevel = allLevels.iterator();
            Map<Long,String> levelMap = new HashMap<Long, String>();
            while (iterLevel.hasNext()){
                Level lev = iterLevel.next();
                levelMap.put(lev.getLid().longValue(),lev.getTitle()+"-"+lev.getFlag());
            }
            map.put("allLevels",levelMap);
            IEmpDao empDao = DAOFactory.getInstance(EmpDaoImpl.class);
            if(column==null||"".equals(column)||keyWord==null||"".equals(keyWord)){
                map.put("allEmps",empDao.findAllByFlag(flag,currentPage,lineSize));
                map.put("empCount",empDao.getAllCountByFlag(flag));
            }else {
                map.put("allEmps",empDao.findAllByFlag(flag,column,keyWord,currentPage,lineSize));
                map.put("empCount",empDao.getAllCountByFlag(flag,column,keyWord));
            }
        }
        return map;
    }

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

    public boolean add(Emp vo, Elog log) throws Exception {
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
                    if(empDao.doCreate(vo)){//雇员保存成功,那么随后还需要进行部门人数的增加
                        int empno = empDao.getLastId();//取得当前操作的最后一次ID内容
                        if(deptDao.doUpdateCurrnum(vo.getDeptno(),1)){//部门人数修改成功
                            log.setEmpno(empno);//保存对应雇员的编号
                            log.setDeptno(vo.getDeptno());//保存雇员对应的部门编号
                            log.setMid(vo.getMid());//保存操作的管理员账号
                            log.setLid(vo.getLid());//保存级别信息
                            log.setJob(vo.getJob());
                            log.setSal(vo.getSal());
                            log.setComm(vo.getComm());
                            log.setSflag(0);
                            log.setFlag(1);
                            log.setNote("["+DateUtil.getFormatDate(vo.getHiredate())+"]"+log.getNote());
                            IElogDao elogDao = DAOFactory.getInstance(ElogDaoImpl.class);
                            return elogDao.doCreate(log);
                        }
                    }
                }
            }
        }
        return false;
    }
}
