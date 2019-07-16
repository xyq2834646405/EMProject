package com.xyq.servlet.back;

import com.xyq.service.back.IDeptServiceBack;
import com.xyq.service.back.IEmpServiceBack;
import com.xyq.service.back.ILevelServiceBack;
import com.xyq.service.back.impl.DeptServiceBackImpl;
import com.xyq.service.back.impl.EmpServiceBackImpl;
import com.xyq.service.back.impl.LevelServiceBackImpl;
import com.xyq.servlet.abs.EMServlet;
import com.xyq.util.factory.ServiceFactory;
import com.xyq.vo.Dept;
import com.xyq.vo.Emp;
import com.xyq.vo.Level;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet("/pages/back/emp/EmpServletBack/*")
public class EmpServletBack extends EMServlet {
    private Emp emp = new Emp();

    public String addPre(){
        if(auth("emp:add")){//判断当前用户是否具备有指定的权限
            IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);
            try {
                Map<String, Object> map = empServiceBack.addPre(getMid());
                request.setAttribute("allDepts",map.get("allDepts"));
                request.setAttribute("allLevels",map.get("allLevels"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "emp.add.page";
        }else {
            addError("auth","auth.failure.msg");
            return "error.page";
        }
    }

    public String add(){
        if(auth("emp:add")) {
            if(isUploadFile()){//现在有文件上传
                emp.setPhoto(createSingleFileName());//创建文件名称
            }else {
                emp.setPhoto("nophoto.png");
            }
            emp.setMid(getMid());//保存当前操作的用户编号
            IEmpServiceBack empServiceBack = ServiceFactory.getInstance(EmpServiceBackImpl.class);
            try {
                if (empServiceBack.add(emp)) {
                    setUrlAndMsg("emp.add.servlet", "vo.add.success.msg");
                    if(isUploadFile()){
                        saveUploadFile(emp.getPhoto());//保存图片
                        saveScale(emp.getPhoto());//保存缩略图
                    }
                } else {
                    setUrlAndMsg("emp.add.servlet", "vo.add.success.msg");
                }
            } catch (Exception e) {
                setUrlAndMsg("emp.add.servlet", "vo.add.failure.msg");
                e.printStackTrace();
            }
            return "forward.page";
        }else {
            addError("auth","auth.failure.msg");
            return "error.page";
        }
    }

    public void checkDept(){
        int deptno = getIntParameter("deptno");//接收发送来的部门编号
        IDeptServiceBack deptServiceBack = ServiceFactory.getInstance(DeptServiceBackImpl.class);
        try {
            Dept dept = deptServiceBack.get(deptno);
            print(dept.getCurrnum()<dept.getMaxnum());//如果是true表示可以使用
        } catch (Exception e) {
            print(false);
        }
    }

    public void checkSal(){//检测工资是否在指定范围之中
        int lid = getIntParameter("lid");//接收发送来的级别编号
        double sal = getDoubleParameter("sal");//接收发送来的工资数据
        ILevelServiceBack levelServiceBack = ServiceFactory.getInstance(LevelServiceBackImpl.class);
        try {
            Level level = levelServiceBack.get(lid);
            print(sal<=level.getHisal() && sal>=level.getLosal());
        } catch (Exception e) {
            print(false);
        }
    }

    public Emp getEmp() {
        return emp;
    }

    @Override
    public String getUploadDir() {
        return "/upload/emp/";
    }

    @Override
    public String getType() {
        return "雇员";
    }
}
